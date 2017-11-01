package org.academiadecodigo.bootcamp11.drunkenkong.field;

import org.academiadecodigo.bootcamp11.drunkenkong.game.Collidable;
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
    private Text text, text2, text3;
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
        beer = new Picture(plataforms[0].getX() + plataforms[0].getWidth()/3, 17, "resources/Beer.png");
        beer.draw();
    }

    public void createScore(int score, int rounds, int tries){
        text = new Text(600, 30, "SCORE " + score );
        text.grow(40,15);
        text.setColor(Color.WHITE);
        text.draw();
        text2 = new Text(600, 55,"ROUND " + (rounds + 1));
        text2.grow(40,15);
        text2.setColor(Color.WHITE);
        text2.draw();
        text3 = new Text(600, 80,"LIFES " + tries);
        text3.grow(40,15);
        text3.setColor(Color.WHITE);
        text3.draw();
    }

    public void points(int points, int rounds, int lifes) {
        text.setText("SCORE " + points);
        text2.setText("ROUND " + (rounds + 1));
        text3.setText("LIFES " + lifes);
    }


    private void createPlataforms() {
        for (int i = 0; i < plataforms.length; i++) {
            if (i == 0) {
                plataforms[i] = new Plataform(rectangle.getWidth() / 3, FIRST_PLATAFORMGAP, 100, Plataform.PLATAFORM_THICK);
                continue;
            }
            if (i % 2 != 0 && i != 5) {
                plataforms[i] = new Plataform(padding, PLATAFORM_GAP * i + FIRST_PLATAFORMGAP, rectangle.getWidth() - offset, Plataform.PLATAFORM_THICK);
                continue;
            }
            if (i % 2 == 0) {
                plataforms[i] = new Plataform(offset, PLATAFORM_GAP * i + FIRST_PLATAFORMGAP, rectangle.getWidth() - offset + padding, Plataform.PLATAFORM_THICK);
                continue;
            }
            plataforms[i] = new Plataform(padding, rectangle.getHeight(), rectangle.getWidth(), Plataform.PLATAFORM_THICK);
        }

    }

    private void createStairs() {
        for (int i = 0; i < stairs.length; i++) {
            if (i == 0) {
                stairs[i] = new Stair((plataforms[0].getX() + 15), FIRST_PLATAFORMGAP);
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
