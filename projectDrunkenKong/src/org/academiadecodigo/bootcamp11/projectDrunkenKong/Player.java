package org.academiadecodigo.bootcamp11.drunkenkong;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player implements KeyboardHandler, Movable {

    private Picture picture;  //picture
    private Field field;
    private int indexPreviousPlataform;
    private Direction direction;
    private boolean alive = true;
    private boolean walk = false;
    private boolean jump = false;
    private boolean upstairs = false;
    private Sound jumpSound = new Sound("/resources/jump.wav");


    public Player(Field field) {
        this.field = field;
        this.picture = new Picture(750, 555,"resources/mario-nintendo-mario-kart transparent 3.png");
        draw();

        createKeyboardEvents();
    }

    @Override
    public void draw() {
        picture.grow(6,5);
        picture.draw();
    }

    @Override
    public void hide() {
        picture.delete();
    }

    @Override
    public void move() {

        if (!alive) {
            return;
        }
        int index = currentPlataform();
        if (index == -1) {
            return;
        }

        if (direction == null) {
            picture.translate(0, 0);
            return;
        }

        if (isWalk()) {
            switch (direction) {
                case UP:
                    moveUp();
                    break;
                case DOWN:
                    moveDown();
                    break;
                case LEFT:
                    picture.load("resources/mario-nintendo-mario-kart transparent 3.png");
                    moveLeft();
                    break;
                case RIGHT:
                    picture.load("resources/mario-nintendo-mario-kart transparent 3 Right.png");
                    moveRight();
                    break;
                default:
                    picture.translate(0, 0);
                    break;
            }
        }
    }

    private void setDirection(Direction direction) {
        this.direction = direction;
    }

    private void moveUp() {
        int indexCurrent = currentPlataform();

        if (indexPreviousPlataform == 0) {
            indexPreviousPlataform = indexCurrent;
        }
        //Checks if the index of the plataform has changed, if it did, stop to climb the stairs
        if (indexCurrent != indexPreviousPlataform) {
            indexPreviousPlataform = indexCurrent;
            setUpstairs(false);
            return;
        }
        //When get the higher plataform, it stop climbing and, that way, we don't have a NullPointer Exception
        if(indexCurrent == 0){
            return;
        }
        //Checks the right position to climb, only when we have a stair
        if (field.getStairs()[indexCurrent - 1].getMinX() <= picture.getX() &&
                field.getStairs()[indexCurrent - 1].getMaxX() >= picture.getX()) {
            picture.translate(0, -5);
            indexPreviousPlataform = indexCurrent;
            picture.load("resources/Donkey_Kong_-_Back_Art.png");
        }

    }

    private void moveDown() {
        int indexCurrent = currentPlataform();

        if (indexPreviousPlataform == 0) {
            indexPreviousPlataform = indexCurrent;
        }

        if (colisionPlataformY(field.getPlataforms()[currentPlataform()])) {
            return;
        }

        if (indexCurrent != indexPreviousPlataform) {
            indexPreviousPlataform = indexCurrent;
            return;
        }
        if(isUpstairs()){
            picture.translate(0, 5);
            indexPreviousPlataform = indexCurrent;
        }

    }

    private void moveLeft() {
        int y = 0;
        if (colisionFieldLeft()) {
            return;
        }
        if (!colisionPlataformY(field.getPlataforms()[currentPlataform()])) {
            return;
        }
        if (currentPlataform() == 0 && colisionPlataformXLeft(field.getPlataforms()[currentPlataform()])) {
            setWalk(false);
            return;
        }
        if (currentPlataform() % 2 == 0 && colisionPlataformXLeft(field.getPlataforms()[currentPlataform()])) {
            setWalk(false);
            return;
        }
        if (isJumping()) {
            //Check if the result of the jump goes beyond the field
            y = -(Field.PLATAFORM_GAP - picture.getHeight() - Plataform.PLATAFORM_THICK);
            if (picture.getX() + picture.getWidth() - 60 < field.getPadding()) {
                picture.translate(-(picture.getX() - field.getPadding()), y);
                return;
            }
            picture.translate(-(WaterBottles.BOTTLE_WIDTH + 60), y);
            return;
        }
        picture.translate(-5, y);
    }

    private void moveRight() {
        int y = 0;
        if (colisionFieldRight()) {
            return;
        }
        if (!colisionPlataformY(field.getPlataforms()[currentPlataform()])) {
            return;
        }
        if (currentPlataform() == 0 && colisionPlataformXRight(field.getPlataforms()[currentPlataform()])) {
            return;
        }
        if (currentPlataform() % 2 != 0 && colisionPlataformXRight(field.getPlataforms()[currentPlataform()])) {
            return;
        }
        if (isJumping()) {
            y = -(Field.PLATAFORM_GAP - picture.getHeight() - Plataform.PLATAFORM_THICK);
            if (picture.getX() + picture.getWidth() + 60 > field.getWidth()) {
                picture.translate(field.getWidth() - picture.getWidth() - picture.getX(), y);
                return;
            }
            picture.translate(WaterBottles.BOTTLE_WIDTH + 60, y);
            return;
        }
        picture.translate(5, y);
    }

    private void jump() {

        if (!isAlive()) {
            return;
        }

        if (isWalk() && !isJumping()) {
            return;
        }
        if (isUpstairs() && !isJumping()) {
            setJump(false);
            picture.translate(0, 0);
            return;
        }
        if (!isWalk() && !isJumping() ) {
            if (!colisionPlataformY(field.getPlataforms()[currentPlataform()])) {
                return;
            }
            picture.translate(0, -(Field.PLATAFORM_GAP - picture.getHeight() - Plataform.PLATAFORM_THICK));

        }
        if (isJumping()) {
            if (colisionPlataformY(field.getPlataforms()[currentPlataform()])) {
                return;
            }
            picture.translate(0,(Field.PLATAFORM_GAP - picture.getHeight() - Plataform.PLATAFORM_THICK));
            setJump(false);
        }

    }

    @Override
    public boolean comparePosition(Collidable object) {
        if (object instanceof WaterBottles) {
            int x = ((WaterBottles) object).getX();
            int y = ((WaterBottles) object).getY();
            int width = ((WaterBottles) object).getWidth();
            int height = ((WaterBottles) object).getHeight();
            return checkColision(x, y, width, height);
        }
        return false;
    }

    public boolean scorePoints(WaterBottles waterBottles){
        //checks if the player and the bottle are on the same plataform
        if (currentPlataform() * Field.PLATAFORM_GAP + Field.FIRST_PLATAFORMGAP != waterBottles.getY() + waterBottles.getHeight()){
            return false;
        }
        // Checks if the position of the player is above the water bottle
        if (picture.getY() < waterBottles.getY() &&
                picture.getX() + 10 < waterBottles.getX() + waterBottles.getWidth() &&
                picture.getX() + picture.getWidth() + 10 > waterBottles.getX()){
            return true;
        }
        return false;
    }

    public boolean backPlataform(){
        int indexPlataform = currentPlataform();
        return colisionPlataformY(field.getPlataforms()[indexPlataform]);
    }

    public boolean makeTheTop() throws InterruptedException {
        if(picture.getY() == field.getPlataforms()[0].getY()){
            picture.load("resources/Celebration.png");
            Thread.sleep(5000);
            return true;
        }
        return false;
    }

    private boolean checkColision(int x, int y, int width, int height) {
        if (picture.getX() + picture.getWidth() >= x && picture.getX() <= x + width) {
            if (picture.getY() + picture.getHeight() >= y && picture.getY() <= y + height) {
                return true;
            }
        }
        return false;
    }

    //returns the index of the current plataform
    private int currentPlataform() {
        for (int i = 0; i < field.getPlataforms().length; i++) {
            if (picture.getY() + this.getHeight() <= field.getPlataforms()[i].getY()) {
                return i;
            }
        }
        return -1;
    }

    //checks if the player is in contact with the plataform that is passed as an argument
    private boolean colisionPlataformY(Plataform plataform) {
        if (picture.getY() + getHeight() >= plataform.getY()) {
            return true;
        }
        return false;
    }

    //check if the player is at the right edge of the plataform
    private boolean colisionPlataformXRight(Plataform plataform) {
        if (picture.getX() + picture.getWidth() >= plataform.getX() + plataform.getWidth()) {
            return true;
        }
        return false;
    }

    //check if the player is at the left edge of the plataform
    private boolean colisionPlataformXLeft(Plataform plataform) {
        if (picture.getX() <= plataform.getX()) {
            return true;
        }
        return false;
    }

    //check if the player is at the right edge of the field
    private boolean colisionFieldRight() {
        if (picture.getX() + getWidth() > field.getWidth()) {
            return true;
        }
        return false;
    }

    //check if the player is at the left edge of the field
    private boolean colisionFieldLeft() {
        if (picture.getX() <= field.getPadding()) {
            return true;
        }
        return false;
    }

    public void uploadPicture(){
        picture.load("resources/MarioDead.png");
    }

    @Override
    public void keyPressed(KeyboardEvent e) {

        int code = e.getKey();

        if (code == KeyboardEvent.KEY_A) {
            setDirection(Direction.LEFT);
            setWalk(true);
        }
        if (code == KeyboardEvent.KEY_D) {
            setDirection(Direction.RIGHT);
            setWalk(true);
        }
        if (code == KeyboardEvent.KEY_W) {
            setDirection(Direction.UP);
            setWalk(true);
            setUpstairs(true);
        }
        if (code == KeyboardEvent.KEY_S) {
            setDirection(Direction.DOWN);
            setWalk(true);
            setUpstairs(true);
        }
        if (code == KeyboardEvent.KEY_SPACE) {
            //setDirection(Direction.JUMP);
            int indexCurrent = currentPlataform();
            if (indexCurrent != 0) {
                int yInf = field.getPlataforms()[indexCurrent].getY();
                int ySup = field.getPlataforms()[indexCurrent - 1].getY();
                if (isJumping() || picture.getHeight() + picture.getY() < yInf &&
                        picture.getHeight() + picture.getY() > ySup) {
                    return;
                }
            }
            jump();
            setJump(true);
            jumpSound.play(true);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {
        int code = e.getKey();

        if (code == KeyboardEvent.KEY_A) {
            setWalk(false);
        }
        if (code == KeyboardEvent.KEY_D) {
            setWalk(false);
        }
        if (code == KeyboardEvent.KEY_W) {
            setWalk(false);
            setUpstairs(false);
        }
        if (code == KeyboardEvent.KEY_S) {
            setWalk(false);
            setUpstairs(false);
        }
        if (code == KeyboardEvent.KEY_SPACE) {
            int indexCurrent = currentPlataform();
            if (indexCurrent != 0) {
                if (!isJumping() && isUpstairs()) {
                    return;
                }
            }
            jump();
            setJump(false);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void dead() {
        this.alive = false;
    }

    private boolean isJumping() {
        return this.jump;
    }

    private void setJump(boolean jump) {
        this.jump = jump;
    }

    private void setWalk(boolean walk) {
        this.walk = walk;
    }

    public boolean isWalk() {
        return this.walk;
    }

    public boolean isUpstairs() {
        return this.upstairs;
    }

    public void setUpstairs(boolean upstairs) {
        this.upstairs = upstairs;
    }

    public int getHeight() {
        return picture.getHeight();
    }

    public int getWidth() {
        return picture.getWidth();
    }

    public Picture getPicture() {
        return picture;
    }

    private void createKeyboardEvents() {

        Keyboard key = new Keyboard(this);
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_D);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        key.addEventListener(event);

        KeyboardEvent event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_A);
        event1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        key.addEventListener(event1);

        KeyboardEvent event2 = new KeyboardEvent();
        event2.setKey(KeyboardEvent.KEY_SPACE);
        event2.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        key.addEventListener(event2);

        KeyboardEvent event3 = new KeyboardEvent();
        event3.setKey(KeyboardEvent.KEY_SPACE);
        event3.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        key.addEventListener(event3);

        KeyboardEvent event4 = new KeyboardEvent();
        event4.setKey(KeyboardEvent.KEY_W);
        event4.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        key.addEventListener(event4);

        KeyboardEvent event5 = new KeyboardEvent();
        event5.setKey(KeyboardEvent.KEY_A);
        event5.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        key.addEventListener(event5);

        KeyboardEvent event6 = new KeyboardEvent();
        event6.setKey(KeyboardEvent.KEY_D);
        event6.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        key.addEventListener(event6);

        KeyboardEvent event7 = new KeyboardEvent();
        event7.setKey(KeyboardEvent.KEY_W);
        event7.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        key.addEventListener(event7);

        KeyboardEvent event8 = new KeyboardEvent();
        event8.setKey(KeyboardEvent.KEY_S);
        event8.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        key.addEventListener(event8);

        KeyboardEvent event9 = new KeyboardEvent();
        event9.setKey(KeyboardEvent.KEY_S);
        event9.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        key.addEventListener(event9);
    }
}



