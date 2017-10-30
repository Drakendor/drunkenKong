package org.academiadecodigo.bootcamp11.drunkenkong.gameobjects;

import org.academiadecodigo.bootcamp11.drunkenkong.field.Field;
import org.academiadecodigo.bootcamp11.drunkenkong.game.Drawable;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CPU implements Drawable {

    private Picture picture;
    private Field field;
    private int count = 35;

    public CPU(Field field) {
        this.picture = new Picture(20, 200 - 66, "resources/BowserStart.png");
        draw();
        this.field = field;
    }

    @Override
    public void draw() {
        picture.grow(10, 10);
        picture.draw();
    }

    public void uploadPicture() {
        while (count >= 0) {
            picture.load("resources/BowserCpu.png");
            count--;
        }
        count = 35;
        picture.load("resources/BowserStart.png");
    }

    public void uploadFirstPicture() {
        picture.load("resources/BowserStart.png");
    }


    @Override
    public void hide() {
        picture.delete();
    }

}
