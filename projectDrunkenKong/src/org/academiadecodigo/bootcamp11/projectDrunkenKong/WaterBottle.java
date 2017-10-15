package org.academiadecodigo.bootcamp11.projectDrunkenKong;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Shape;

/**
 * Created by codecadet on 10/10/17.
 */
public class WaterBottle implements Movable {

    private Rectangle rectangle;
    private int width = 60;
    private int height = 30;
    private int x;
    private int y;


    public WaterBottle() {
        this.rectangle = new Rectangle(60, 200, width, height);
        draw();
    }

    public WaterBottle(int x, int y) {
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(x, y, width, height);
        draw();
    }

    @Override
    public void draw() {
        rectangle.setColor(Color.BLUE);
        rectangle.fill();
    }

    @Override
    public void hide() {
        rectangle.delete();
    }

    @Override
    public void move() {
        int x = 5;
        int y = 0;
        this.rectangle.translate(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean comparePosition(Collidable collidable) {

        if (collidable instanceof Field) {
            Field field = (Field) collidable;

            if (rectangle.getX() + getWidth() == field.getWidth()) {
                return true;
            }
        }
        return false;
    }
}
