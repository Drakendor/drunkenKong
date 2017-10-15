package org.academiadecodigo.bootcamp11.drunkenkong;

public class Game {

    private WaterBottles[] waterBottles;
    private Field field;
    private CPU cpu;
    private Player player;
    private int delay = 20;
    private Sound soundGameover = new Sound("/resources/voiceGO.wav");
    private Sound sound = new Sound("/resources/music.wav");
    private Sound winSound = new Sound("/resources/winMusic.wav");
    private Sound beerSound = new Sound("/resources/beerOpen.wav");

    public void init() {

        this.field = new Field(10, 10, 800, 600);
        createFirstWaterBottles(11);

        this.player = new Player(field);

        this.cpu = new CPU(field);

    }

    private void createFirstWaterBottles(int numWaterBottles) {
        waterBottles = new WaterBottles[numWaterBottles];
        int i;
        for (i = 0; i < numWaterBottles; i++) {
            int x = (int) ((Math.random() * (field.getWidth() - field.getOffset() * 2 - WaterBottles.BOTTLE_WIDTH)) + field.getOffset());
            int y = (int) (Math.ceil(Math.random() * 5));

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

    private boolean checkClosePositionsBottles(int i, int x, int y) {
        for (int j = 0; j < i; j++) {
            if (x >= waterBottles[j].getX() - 100 && x <= waterBottles[j].getX() + 100
                    && ((y * Field.PLATAFORM_GAP) + Field.FIRST_PLATAFORMGAP - WaterBottles.BOTTLE_WIDTH) == waterBottles[j].getY()) {
                return true;
            }
        }
        return false;
    }


    public void start() throws InterruptedException {

        sound.play(true);

        while (true) {

            if (gameOver()) {
                sound.stop();
                soundGameover.play(true);
                return;
            }
            Thread.sleep(delay);
            moveAll();
            uploadPictureCpu();
            if(winRound()){
                delay = delay - 5;
                sound.stop();
                winSound.play(true);
                beerSound.play(true);
                init();
            }
        }
    }


    public void moveAll() {
        player.move();
        for (WaterBottles waterBottles : waterBottles) {
            waterBottles.move();
            if (waterBottles.comparePosition(field)) {
                waterBottles.hide();
            }
            if (player.comparePosition(waterBottles)) {
                player.dead();
                player.uploadPicture();
            }
            if (player.scorePoints(waterBottles) && player.backPlataform()) {
                field.points(100);
            }
        }

    }

    private boolean winRound() throws InterruptedException {
        if(player.makeTheTop()){
            return true;
        }
        return false;
    }

    private boolean gameOver() {
        return !player.isAlive();
    }

    private void uploadPictureCpu() {
        if (player.isAlive()) {
            cpu.uploadPicture();
            return;
        }
        cpu.uploadFirstPicture();
    }

}
