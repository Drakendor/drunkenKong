package org.academiadecodigo.bootcamp11.drunkenkong;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 09/10/17.
 */
public class Plataform implements Collidable {

    private Rectangle rectangle;
    public static final int PLATAFORM_THICK = 10;

    public Plataform(int x, int y, int width, int height){
        this.rectangle = new Rectangle(x,y,width,height);
        draw();
    }

    @Override
    public void draw(){
        rectangle.setColor(Color.PINK);
        rectangle.fill();
    }

    @Override
    public void hide(){
        rectangle.delete();
    }

    @Override
    public boolean comparePosition(Collidable object){

        return true;
    }

    public int getX(){
        return rectangle.getX();
    }

    public int getWidth(){
        return rectangle.getWidth();
    }

    public int getY(){
        return rectangle.getY();
    }
}
