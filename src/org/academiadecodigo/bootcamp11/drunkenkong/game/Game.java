package org.academiadecodigo.bootcamp11.drunkenkong.game;

import org.academiadecodigo.bootcamp11.drunkenkong.field.Field;
import org.academiadecodigo.bootcamp11.drunkenkong.gameobjects.CPU;
import org.academiadecodigo.bootcamp11.drunkenkong.gameobjects.Item;
import org.academiadecodigo.bootcamp11.drunkenkong.gameobjects.WaterBottles;
import org.academiadecodigo.bootcamp11.drunkenkong.player.Player;
import org.academiadecodigo.bootcamp11.drunkenkong.sound.Sound;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private WaterBottles[] waterBottles;
    private Item[] itens;
    private Field field;
    private CPU cpu;
    private int score;
    private int rounds = 0;
    private int tries = 3;
    private Player player;
    private int delay = 25;
    public static final int DISTANCE = 120;
    private Sound soundGameover = new Sound("/resources/voiceGO.wav");  // dead sound
    private Sound sound = new Sound("/resources/music.wav");  // Background music
    private Sound winSound = new Sound("/resources/winSound.wav"); // victory sound
    private Sound beerSound = new Sound("/resources/beerOpen.wav");  // Beer Open sound


    public void init() {

        this.field = new Field(10, 10, 800, 600);
        createFirstWaterBottles(11);

        createItens(6);
        field.createScore(score, rounds, tries);

        sound.play(true);  // Background music

        this.player = new Player(field);

        this.cpu = new CPU(field);

    }

    private void createItens(int numberItens){
        itens = new Item[numberItens];
        for (int i = 0; i < numberItens; i++){
            int x = (int) ((Math.random() * (field.getWidth() - field.getOffset() * 2 - WaterBottles.BOTTLE_WIDTH)) + field.getOffset());
            int y = (int) (Math.ceil(Math.random() * 5));

            if (checkClosePositionsItens(i, x, y)) {
                i = i - 1;
                continue;
            }
            itens[i] = new Item(x, y);
        }
    }

    private void createFirstWaterBottles(int numWaterBottles) {
        waterBottles = new WaterBottles[numWaterBottles];
        int i;
        for (i = 0; i < numWaterBottles; i++) {
            int x = (int) ((Math.random() * (field.getWidth() - field.getOffset() * 2 - WaterBottles.BOTTLE_WIDTH)) + field.getOffset());
            int y = (int) (Math.ceil(Math.random() * 4));

            if (checkClosePositionsBottles(i, x, y)) {
                i = i - 1;
                continue;
            }
            if (i == 10) {
                waterBottles[i] = new WaterBottles(field);
                return;
            }
            waterBottles[i] = new WaterBottles(x, y, field);
        }
    }

    private boolean checkClosePositionsItens(int i, int x, int y){
        for (int j = 0; j < i; j++) {
            if (x >= itens[j].getX() - DISTANCE && x <= itens[j].getX() + DISTANCE
                    && ((y * Field.PLATAFORM_GAP) + Field.FIRST_PLATAFORMGAP - Item.ITEM_WIDTH) == itens[j].getY()) {
                return true;
            }
        }
        return false;
    }

    private boolean checkClosePositionsBottles(int i, int x, int y) {
        for (int j = 0; j < i; j++) {
            if (x >= waterBottles[j].getX() - DISTANCE && x <= waterBottles[j].getX() + DISTANCE
                    && ((y * Field.PLATAFORM_GAP) + Field.FIRST_PLATAFORMGAP - WaterBottles.BOTTLE_WIDTH) == waterBottles[j].getY()) {
                return true;
            }
        }
        return false;
    }


    public void start() throws InterruptedException {

        while (true) {
            if (gameOver() ) {
                return;
            }
            Thread.sleep(delay);
            moveAll();
            if (winRound()) {
                delay = delay - 5;
                score = score + 1000;
                if(rounds == 3){
                    score = 0;
                    rounds = 0;
                    tries = 3;
                    delay = 25;
                    init();
                    start();
                    return;
                }
                init();
                continue;
            }
            if (!player.isAlive() && 3 > tries && tries > 0) {
                player = new Player(field);
                createFirstWaterBottles(11);
                for (Item i : itens) {
                    i.draw();
                }
                sound.play(true);  // Background music
            }
        }
    }


    private void moveAll() throws InterruptedException {
        player.move();
        for (Item i : itens) {
            if(i.comparePosition(player)){
                score = score + 100;
                field.points(score, rounds, tries);
                i.hide();
                break;
            }
        }
        for (WaterBottles waterBottles : waterBottles) {
            waterBottles.move();
            if (waterBottles.comparePosition(field)) {
                uploadPictureCpu();
                waterBottles.hide();
            }
            if (player.comparePosition(waterBottles)) {
                player.dead();
                tries = tries - 1;
                score = 0;
                field.points(score, rounds, tries);
                loseRound();
                return;
            }
        }

    }

    private boolean gameOver() {
        return (tries == 0);
    }

    private boolean winRound() throws InterruptedException {
        if (player.makeTheTop()) {
            sound.stop();  // stop background music
            winSound.play(true);
            rounds = rounds + 1;
            drawVictory();
            Thread.sleep(2500);
            winSound.stop();
            return true;
        }
        return false;
    }

    private void loseRound() throws InterruptedException {
        if (tries == 0) {
            Rectangle rectangle = new Rectangle(10, 10, 800, 600);
            rectangle.setColor(Color.DARK_GRAY);
            rectangle.fill();
            Picture picture = new Picture(field.getWidth() / 3, field.getHeight() * 2 / 6, "resources/DKBarrelroll_Small.png");
            picture.draw();
            Text text = new Text(field.getWidth() / 3, field.getHeight() / 5, "LOSER!!!!!");
            text.grow(200, 100);
            text.translate(100, 0);
            text.setColor(Color.ORANGE);
            text.draw();
            picture.translate(0, 25);
            sound.stop();  // stop background music
            soundGameover.play(true);  // play dead player sound
            return;
        }
        Rectangle rectangle = new Rectangle(10, 10, 800, 600);
        rectangle.setColor(Color.WHITE);
        rectangle.fill();
        Picture picture = new Picture(field.getWidth() / 3, field.getHeight() * 2 / 6, "resources/TryAgainDK_Big.png");
        picture.draw();
        Text text = new Text(field.getWidth() / 3, field.getHeight() / 5, "YOU CAN DO IT, TRY AGAIN!");
        text.grow(250, 100);
        text.translate(50, 0);
        text.setColor(Color.ORANGE);
        text.draw();
        picture.translate(-80, 0);
        sound.stop();  // stop background music
        soundGameover.play(true);  // play dead player sound
        Thread.sleep(2500);
        deleteEverything(text, picture, rectangle);
    }

    private void deleteEverything(Text text, Picture picture, Rectangle rectangle) {
        text.delete();
        rectangle.delete();
        picture.delete();
        player.hide();
        for (WaterBottles w : waterBottles) {
            w.delete();
        }
    }

    private void drawVictory() {
        if (rounds == 3) {
            Rectangle rectangle = new Rectangle(10, 10, 800, 600);
            rectangle.setColor(Color.DARK_GRAY);
            rectangle.fill();
            Picture picture = new Picture(field.getWidth() / 2, field.getHeight() * 2 / 6, "resources/DK_barrel.png");
            picture.grow(-20,-20);
            picture.translate(-170, -40);
            picture.draw();
            Text text = new Text(field.getWidth() / 3, field.getHeight() / 5, "GREAT SUCCESS!!!");
            text.grow(170, 80);
            text.translate(90,-50);
            text.setColor(Color.ORANGE);
            text.draw();
            return;
        }
        Rectangle rectangle = new Rectangle(10, 10, 800, 600);
        rectangle.setColor(Color.DARK_GRAY);
        rectangle.fill();
        Picture picture = new Picture(field.getWidth() / 2, field.getHeight() * 2 / 6, "resources/donkey-kong-beer.png");
        picture.grow(-30,-70);
        picture.draw();
        Text text = new Text(field.getWidth() / 3, field.getHeight() / 5, "WAY TO GO, KEEP GOING!");
        text.grow(250, 70);
        text.translate(70,-50);
        text.setColor(Color.ORANGE);
        text.draw();
        picture.translate(-200, -100);

    }

    private void uploadPictureCpu() {
        if (player.isAlive()) {
            cpu.uploadPicture();
            return;
        }
        cpu.uploadFirstPicture();
    }

}
