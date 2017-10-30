package org.academiadecodigo.bootcamp11.drunkenkong.gameobjects;

import org.academiadecodigo.bootcamp11.drunkenkong.player.Player;
import org.academiadecodigo.bootcamp11.drunkenkong.field.Field;
import org.academiadecodigo.bootcamp11.drunkenkong.game.Collidable;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Item implements Collidable {

    private int points;
    private Picture tremoços;
    public static final int ITEM_WIDTH = 30;

    public Item(int x, int y){
        points = 100;
        tremoços = new Picture(x, ((y * Field.PLATAFORM_GAP) + Field.FIRST_PLATAFORMGAP - ITEM_WIDTH),"resources/TREMOCO-30x30.png");
        draw();
    }

    public int getX(){
        return tremoços.getX();
    }

    public int getY(){
        return tremoços.getY();
    }

    @Override
    public void draw() {
        if(tremoços.getX() < 0){
            tremoços.translate(1000,0);
            tremoços.draw();
            return;
        }
        tremoços.draw();
    }

    @Override
    public void hide() {
        tremoços.delete();
        tremoços.translate(-1000,0);
    }

    @Override
    public boolean comparePosition(Collidable object) {
        if(object instanceof Player){
            int x = ((Player) object).getX();
            int y = ((Player) object).getY();
            int width = ((Player) object).getWidth();
            int height = ((Player) object).getHeight();
            return checkColision(x, y, width, height);
        }
        return false;
    }

    private boolean checkColision(int x, int y, int width, int height) {
        if (tremoços.getX() + tremoços.getWidth() >= x && tremoços.getX() <= x + width) {
            if (tremoços.getY() + tremoços.getHeight() == y + height) {
                return true;
            }
        }
        return false;
    }

}
