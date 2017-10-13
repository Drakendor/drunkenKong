package org.academiadecodigo.bootcamp11.projectDrunkenKong;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 09/10/17.
 */
public class Field implements Collidable {

    private Rectangle rectangle;
    private int padding = 10;
    private int offset = 150;


    public Field(int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x, y, width, height);
        draw();

        Plataform plataform = new Plataform(rectangle.getWidth() / 3, 100, 100, 10);
        Plataform plataform1 = new Plataform(padding, 230, rectangle.getWidth() - offset, 10);
        Plataform plataform2 = new Plataform(offset, 360, rectangle.getWidth() - offset + padding, 10);
        Plataform plataform3 = new Plataform(padding, 490, rectangle.getWidth() - offset, 10);
        Plataform plataform4 = new Plataform(offset, 620, rectangle.getWidth() - offset + padding, 10);

        Plataform plataform5 = new Plataform(padding, rectangle.getHeight(), rectangle.getWidth(), 10);
    }

    public void draw() {
        rectangle.setColor(Color.BLACK);
        rectangle.fill();
    }

    public void hide() {
        rectangle.delete();
    }

    public boolean comparePosition(Collidable collidable) {

        if (collidable instanceof Player) {
            Player player = (Player) collidable;
        }

        return false;
    }


    public int getPadding() {
        return padding;
    }

    public int getWidth() {
        return rectangle.getWidth();
    }

    public int getEight() {
        return rectangle.getHeight();
    }

}
