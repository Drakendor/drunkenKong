package org.academiadecodigo.bootcamp11.drunkenkong.field;

import org.academiadecodigo.bootcamp11.drunkenkong.game.Drawable;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 13/10/17.
 */
public class Stair implements Drawable {

    private int x;
    private int y;
    public static final int WIDTH = 70;
    public static final int THICK = 10;
    private Rectangle[] rectangles;

    public Stair(int x, int y) {
        this.x = x;
        this.y = y;
        rectangles = new Rectangle[6];
        buildStair();
    }

    public void buildStair() {
        for (int i = 0; i < rectangles.length; i++) {
            if (i == 0) {
                rectangles[i] = new Rectangle(x, y, THICK, Field.PLATAFORM_GAP);
            }
            if (i == rectangles.length - 1) {
                rectangles[i] = new Rectangle(x + WIDTH - THICK, y, THICK, Field.PLATAFORM_GAP);
            }
            if (i < rectangles.length - 1 && i > 0) {
                rectangles[i] = new Rectangle(x,  y + Field.PLATAFORM_GAP * i / (rectangles.length - 1), WIDTH, THICK);
            }
        }
        draw();
    }

    public int getMaxX(){
        return rectangles[5].getX();
    }

    public int getMinX(){
        return rectangles[0].getX();
    }

    @Override
    public void draw() {
        for (Rectangle r : rectangles) {
            r.setColor(Color.YELLOW);
            r.fill();
        }
    }

    @Override
    public void hide() {
        for (Rectangle r : rectangles) {
            r.delete();
        }
    }
}
