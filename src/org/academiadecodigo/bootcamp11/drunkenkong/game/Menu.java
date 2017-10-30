package org.academiadecodigo.bootcamp11.drunkenkong.game;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements KeyboardHandler {

    private Picture picture;
    private boolean start;
    private Text text;


    public Menu(int x, int y, Game game) throws InterruptedException {
        this.picture = new Picture(x, y, "resources/mario-donkey-kongMenu.png");
        picture.draw();
        createKeyboardEvents();

        this.text = new Text(400, 200,"DRUNKEN KONG");
        text.grow(250,100);
        text.setColor(Color.RED);
        text.draw();

        Thread.sleep(2000);
        text.setText("PRESS UP TO START");
        text.grow(-50,-50);
        text.translate(-25,250);
    }

    private void createKeyboardEvents() {
        Keyboard key = new Keyboard(this);
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(KeyboardEvent.KEY_UP);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        key.addEventListener(event);

        KeyboardEvent event1 = new KeyboardEvent();
        event1.setKey(KeyboardEvent.KEY_UP);
        event1.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        key.addEventListener(event1);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        int code = keyboardEvent.getKey();
        if (code == KeyboardEvent.KEY_UP) {
            setStart(true);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        int code = keyboardEvent.getKey();
        if (code == KeyboardEvent.KEY_UP) {
            setStart(true);
        }
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }


}
