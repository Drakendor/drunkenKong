package org.academiadecodigo.bootcamp11.projectDrunkenKong;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 10/10/17.
 */
public class WaterBottle implements Movable {

    private Rectangle rectangle;
    private int width = 60;
    private int height = 30;

    public WaterBottle() {
        this.rectangle = new Rectangle(60, 200, width, height);
        draw();
    }

    public WaterBottle(int x, int y){

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

    }

    @Override
    public boolean comparePosition() {
        return false;
    }
}
