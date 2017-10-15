package org.academiadecodigo.bootcamp11.drunkenkong;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CPU implements Drawable{

    private Picture picture;
    private Field field;
    private int count = 100;

    public CPU(Field field){
        this.picture = new Picture(20,200-66,"resources/BowserStart.png");
        draw();
        this.field = field;
    }

    @Override
    public void draw() {
        picture.grow(10,10);
        picture.draw();
    }

    public void uploadPicture(){
        count--;
        if(count <= 0) {
            count = 100;
            picture.load("resources/BowserStart.png");
        }
        if(count < 10){
            picture.load("resources/BowserCpu.png");
            count--;
            return;
        }
        if(count < 20){
            picture.load("resources/Bowser2.png");
            count--;
            return;
        }
        if(count < 30){
            picture.load("resources/Bowser1.png");
            count--;
        }
    }

    public void uploadFirstPicture(){
        picture.load("resources/BowserStart.png");
    }

    @Override
    public void hide() {
        picture.delete();
    }

}
