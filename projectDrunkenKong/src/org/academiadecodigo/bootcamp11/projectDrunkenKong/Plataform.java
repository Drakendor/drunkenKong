package org.academiadecodigo.bootcamp11.projectDrunkenKong;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Created by codecadet on 09/10/17.
 */
public class Plataform implements Collidable {

    private Rectangle rectangle;

    public Plataform(int x, int y, int width, int height){
        this.rectangle = new Rectangle(x,y,width,height);
        draw();
    }

    public void draw(){
        rectangle.setColor(Color.PINK);
        rectangle.fill();
    }

    public void hide(){
        rectangle.delete();
    }

    public boolean comparePosition(Collidable collidable) {

            return false;
       /* if (collidable instanceof Player){
           Player player = (Player) collidable;

        if (rectangle.)
       }
    }*/
    }
}
