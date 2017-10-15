package org.academiadecodigo.bootcamp11.projectDrunkenKong;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


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
    private boolean jump = false;
    private int x;
    private int y;
    private int height = 20;
    private int width = 60;


    public Player(String name) {
        this.name = name;
        this.rectangle = new Rectangle(904, 728, height, width);
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
        while (alive) {
            x = 0;
            y = 0;

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
    }

    @Override
    public boolean comparePosition(Collidable collidable) {


        if (collidable instanceof WaterBottle) {
            WaterBottle waterBottle = (WaterBottle) collidable;

            if (rectangle.getX() == waterBottle.getX() + waterBottle.getWidth()) { //compare position player vs waterBottle
                return true;
            }
        }
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
                System.out.println(rectangle.getY());
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
                setJump(true);
                break;

            default:

        }
        move();
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

    public boolean isJump() {
        return jump;
    }



    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public Rectangle getRectangle() {
        return rectangle;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public enum Orientation {

        LEFT,
        RIGHT,
        UP,
        DOWN,
        JUMP

    }
}
