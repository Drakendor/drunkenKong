package org.academiadecodigo.bootcamp11.drunkenkong;

import org.academiadecodigo.bootcamp11.drunkenkong.game.Game;
import org.academiadecodigo.bootcamp11.drunkenkong.game.Menu;

/**
 * Created by codecadet on 09/10/17.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Game game = new Game();
        Menu menu = new Menu(10, 10, game);
        boolean finish = false;
        while (!finish) {
            if (menu.isStart()) {
                game.init();
                game.start();
                finish = true;
            }

            Thread.sleep(1000);
        }
    }

}

