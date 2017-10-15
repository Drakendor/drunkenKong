package org.academiadecodigo.bootcamp11.drunkenkong;


import org.academiadecodigo.simplegraphics.pictures.Picture;

public class WaterBottles implements Movable {

    private Picture picture;
    private Field field;
    private int count;
    public static final int OFFSET_TOP_PLATAFORM = 100;
    public static final int BOTTLE_WIDTH = 30;
    public static final int BOTTLE_HEIGHT = 30;

    // take in consideration the height of the rectangle on the Y argument.
    public WaterBottles(Field field) {
        this.picture = new Picture(70, (Field.FIRST_PLATAFORMGAP + Field.PLATAFORM_GAP - BOTTLE_HEIGHT), "resources/WatterBottle30x30.png");
        draw();
        this.field = field;
    }

    // take in consideration the height of the rectangle on the Y argument.
    public WaterBottles(int x, int y, Field field) {
        this.picture = new Picture(x, ((y * Field.PLATAFORM_GAP) + OFFSET_TOP_PLATAFORM - BOTTLE_WIDTH), "resources/WatterBottle30x30.png");
        draw();
        this.field = field;
    }

    @Override
    public void draw() {
        picture.draw();
    }

    @Override
    public void hide() {
        picture.translate(-(field.getWidth() - field.getPadding() - picture.getWidth()),-(field.getHeight()-field.getPlataforms()[1].getY()));
    }

    @Override
    public void move() {
        Plataform[] plataform = field.getPlataforms();
        for (int i = 1; i < plataform.length; i++) {
            if (this.getHeight() + this.getY() == plataform[i].getY() && i % 2 != 0) {
                moveRight();
                return;
            }
            if (this.getHeight() + this.getY() == plataform[i].getY() && i % 2 == 0) {
                moveLeft();
                return;
            }
        }
        //Gravity effect, slowly drop the bottle
        if (count < 10) {
            picture.translate(2, 10);
            count++;
            if (count == 10) {
                count = 0;
            }
        }
        if (count > 10){
            picture.translate(-2,10);
            count++;
            if (count == 20){
                count = 0;
            }
        }
    }

    public void moveRight() {
        Plataform plataform = field.getPlataforms()[field.getPlataforms().length - 1];
        if (picture.getX() > field.getWidth() - field.getOffset() + field.getPadding() && picture.getY() + picture.getHeight() < plataform.getY()) {
            picture.translate(2, 10);
            count++;
        }
        picture.translate(1, 0);

    }

    public void moveLeft() {
        Plataform[] plataform = field.getPlataforms();
        for (int i = 0; i < plataform.length; i++) {
            if (i % 2 == 0 && picture.getX() < 70 && picture.getY() + picture.getHeight() == plataform[i].getY()) {
                picture.translate(-2, 10);
                count = count + 11;
            }
        }
        picture.translate(-1, 0);

    }

    public int getX() {
        return picture.getX();
    }

    public int getY() {
        return picture.getY();
    }

    public int getWidth() {
        return picture.getWidth();
    }

    public int getHeight() {
        return picture.getHeight();
    }


    // Check colisions between the water bottles and the field
    @Override
    public boolean comparePosition(Collidable object) {
        if (object instanceof Field) {
            if (picture.getY() + picture.getHeight() == ((Field) object).getHeight() &&
                    picture.getX() + picture.getWidth() == ((Field) object).getWidth()) {
                return true;
            }
        }
        return false;
    }

}
