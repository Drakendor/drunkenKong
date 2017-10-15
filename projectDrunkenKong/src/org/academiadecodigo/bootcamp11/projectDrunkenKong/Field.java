package org.academiadecodigo.bootcamp11.drunkenkong;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Created by codecadet on 09/10/17.
 */
public class Field implements Collidable {

    private Rectangle rectangle;
    private Picture beer;
    private Text text;
    private int score = 0;
    private int padding = 10;
    private int offset = 100; // Part of the field that won't have a plataform, so that the bottles can fall.
    public static final int PLATAFORM_GAP = 100;
    public static final int FIRST_PLATAFORMGAP = 100;
    private Plataform[] plataforms;
    private Stair[] stairs;


    public Field(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
        draw();
        plataforms = new Plataform[6];
        createPlataforms();
        stairs = new Stair[5];
        createStairs();
        createScore();
        beer = new Picture(plataforms[0].getX() + plataforms[0].getWidth()/3, 17, "resources/Beer.png");
        beer.draw();
    }

    private void createScore(){
        text = new Text(600, 40, "SCORE " + score);
        text.grow(50,20);
        text.setColor(Color.WHITE);
        text.draw();
    }

    private void createPlataforms() {
        for (int i = 0; i < plataforms.length; i++) {
            if (i == 0) {
                plataforms[i] = new Plataform(rectangle.getWidth() / 3, FIRST_PLATAFORMGAP, 100, 10);
                continue;
            }
            if (i % 2 != 0 && i != 5) {
                plataforms[i] = new Plataform(padding, PLATAFORM_GAP * i + FIRST_PLATAFORMGAP, rectangle.getWidth() - offset, 10);
                continue;
            }
            if (i % 2 == 0) {
                plataforms[i] = new Plataform(offset, PLATAFORM_GAP * i + FIRST_PLATAFORMGAP, rectangle.getWidth() - offset + padding, 10);
                continue;
            }
            plataforms[i] = new Plataform(padding, rectangle.getHeight(), rectangle.getWidth(), 10);
        }

    }

    private void createStairs() {
        for (int i = 0; i < stairs.length; i++) {
            if (i == 0) {
                stairs[i] = new Stair((rectangle.getWidth() / 3) + 25, FIRST_PLATAFORMGAP);
                continue;
            }
            int x = (int) ((Math.random() * (rectangle.getWidth() - (offset * 2) - Stair.WIDTH)) + offset );
            int y = i;
            stairs[i] = new Stair(x, y * PLATAFORM_GAP + FIRST_PLATAFORMGAP);
        }
    }


    @Override
    public void draw() {
        rectangle.setColor(Color.BLACK);
        rectangle.fill();
    }

    @Override
    public void hide() {
        rectangle.delete();
    }

    public int getWidth() {
        return rectangle.getWidth();
    }

    public int getHeight() {
        return rectangle.getHeight();
    }

    public Plataform[] getPlataforms() {
        return plataforms;
    }

    public Stair[] getStairs() {
        return stairs;
    }

    public int getPadding() {
        return padding;
    }

    public int getOffset() {
        return offset;
    }

    public void points(int points) {
        this.score = score + points;
        text.setText("Score " + score);
    }

    @Override
    public boolean comparePosition(Collidable object) {
      /*  if (object instanceof WaterBottles) {
            int x = ((WaterBottles) object).getX();
            int y = ((WaterBottles) object).getY();
            if (x + ((WaterBottles) object).getWidth() == rectangle.getWidth() &&
                    y + ((WaterBottles) object).getHeight() == rectangle.getHeight()) {
                return true;
            }
        }
        return false;*/
      return false;
    }


}
