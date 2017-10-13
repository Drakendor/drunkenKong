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
        this.rectangle = new Rectangle(750, 560, 20, 40);
        draw();
        Keyboard keyboard = new Keyboard(this);
        addHandlers(keyboard);

       /*Picture pic = new Picture(730, 590, "http://www.ministryofpinball.com/media/catalog/category/Super_Paper_Mario_MoP_1.png");
       pic.grow(-80, -80);
       pic.draw();*/

    }


    private void addHandlers(Keyboard keyboard) {


       // while (true) {
            int[] keys = new int[]{
                    KeyboardEvent.KEY_W,
                    KeyboardEvent.KEY_S,
                    KeyboardEvent.KEY_A,
                    KeyboardEvent.KEY_D

            };

            for (int key : keys) {
                KeyboardEvent event = new KeyboardEvent();
                event.setKey(key);
                event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
                keyboard.addEventListener(event);

            }
            /*try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }


        @Override
        public void draw () {
            rectangle.setColor(Color.BLUE);
            rectangle.fill();

        }

        @Override
        public void hide () {

        }

        @Override
        public void move () {

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
                default:
            }
            this.rectangle.translate(x, y);

        }

        @Override
        public boolean comparePosition () {
            return false;
        }

        @Override
        public void keyPressed (KeyboardEvent keyboardEvent){


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
            }

           move();

            /* try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

*/
        }

        @Override
        public void keyReleased (KeyboardEvent keyboardEvent){
            return;
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
        DOWN

    }
}
