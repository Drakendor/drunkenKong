package org.academiadecodigo.bootcamp11.projectDrunkenKong;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 09/10/17.
 */
public class Player implements KeyboardHandler, Movable {

    private Rectangle rectangle;  //picture

    private boolean alive = true;

    private String name;

    public Player(String name) {
        this.name = name;
        this.rectangle = new Rectangle(904,728,20,40);
        draw();
        /*Picture pic = new Picture(730, 590, "http://www.ministryofpinball.com/media/catalog/category/Super_Paper_Mario_MoP_1.png");
        pic.grow(-80, -80);
        pic.draw();*/
    }



    @Override
    public void draw() {
        rectangle.setColor(Color.BLUE);
        rectangle.fill();

    }

    @Override
    public void hide() {

    }

    @Override
    public void move() {



    }

    @Override
    public boolean comparePosition() {
        return false;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
