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
    private static final int PIXELS = 20;
    private boolean alive = true;
    Orientation currentOrie;
    private Keyboard keyboard;
    private String name;

    public Player(String name) {
        this.name = name;
        this.rectangle = new Rectangle(904, 728, 20, 40);
        draw();
        this.keyboard = new Keyboard(this);
        addHandlers(keyboard);
      /*Picture pic = new Picture(730, 590, “http://www.ministryofpinball.com/media/catalog/category/Super_Paper_Mario_MoP_1.png“);
      pic.grow(-80, -80);
      pic.draw();*/
    }

    private void addHandlers(Keyboard keyboard) {

        int[] keys = new int[]{
                KeyboardEvent.KEY_W,
                KeyboardEvent.KEY_S,
                KeyboardEvent.KEY_A,
                KeyboardEvent.KEY_D,
                KeyboardEvent.KEY_SPACE
        };

        for (int key : keys) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKey(key);
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(event);
        }
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
        int x = 0;
        int y = 0;

        switch (currentOrie) {
            case UP:
                y = -PIXELS;
                break;
            case DOWN:
                y = PIXELS;
                break;
            case LEFT:
                x = -PIXELS;
                break;
            case RIGHT:
                x = PIXELS;
                break;
            case JUMP:
                y = -100;
            default:
        }
        this.rectangle.translate(x, y);
    }

    @Override
    public boolean comparePosition() {
        return false;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_W:
                System.out.println("UP");
                currentOrie = Orientation.UP;

                break;
            case KeyboardEvent.KEY_S:
                System.out.println("DOWN");
                currentOrie = Orientation.DOWN;
                break;

            case KeyboardEvent.KEY_A:
                System.out.println("LEFT");
                currentOrie = Orientation.LEFT;
                break;

            case KeyboardEvent.KEY_D:
                System.out.println("RIGHT");
                currentOrie = Orientation.RIGHT;
                break;
            case KeyboardEvent.KEY_SPACE:
                System.out.println("JUMP");
                currentOrie = Orientation.JUMP;
                break;

            default:

        }
        move();
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
       /* int key = keyboardEvent.getKey();

        if (key == KeyboardEvent.KEY_LEFT) {
            x = 0;
        }
        if (key == KeyboardEvent.KEY_SPACE) {

        }

        if (key == KeyboardEvent.KEY_RIGHT) {
            x = 0;
        }

        if (key == KeyboardEvent.KEY_UP) {
            y = 0;
        }

        if (key == KeyboardEvent.KEY_DOWN) {
            y = 0;
        }*/
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    public enum Orientation {

        LEFT,
        RIGHT,
        UP,
        DOWN,
        JUMP

    }
}
