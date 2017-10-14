package org.academiadecodigo.bootcamp11.projectDrunkenKong;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 10/10/17.
 */
public class Cpu implements Drawable {

    private WaterBottle waterBotle;
    private Rectangle rectangle;

    public Cpu() {

        this.rectangle = new Rectangle(10, 180, 50, 50);
        draw();
    }

    public WaterBottle createWaterBottle() {

        return new WaterBottle();
    }

    @Override
    public void draw() {
        rectangle.setColor(Color.RED);
        rectangle.fill();
    }

    @Override
    public void hide() {
        rectangle.delete();
    }
}
