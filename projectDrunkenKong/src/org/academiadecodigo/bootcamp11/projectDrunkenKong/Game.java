package org.academiadecodigo.bootcamp11.projectDrunkenKong;

import org.academiadecodigo.bootcamp11.projectDrunkenKong.WaterBottle;
import org.academiadecodigo.bootcamp11.projectDrunkenKong.Player;

/**
 * Created by codecadet on 09/10/17.
 */
public class Game {

    private WaterBottle[] randomWaterBottle;
    private Player player;
    private Field field;
    private Cpu cpu;

    public void init() {


        field = new Field(10, 10, 1024, 768);

        cpu = new Cpu();

        player = new Player("luis lindo");

        randomWaterBottle = new WaterBottle[10];

        for (int i = 0; i < randomWaterBottle.length; i++) {

            int x = (int) (Math.random() * (1024 - 300 - 20) + 150);   // 20 = - padding   300= offset
            int y = (int) (Math.ceil(Math.random() * 4));

            randomWaterBottle[i] = new WaterBottle(x, (y * 130) + 100 - 30);


        }

    }

    public void start() {

        while (true) {

            if (player.isAlive()) {
                moveBottle();
               movePlayer();

            }

        }

    }

    private void moveBottle() {

        for (int i = 0; i < randomWaterBottle.length; i++) {
            randomWaterBottle[i].move();
        }
    }

    private void movePlayer(){
        if(player.comparePosition(field) == true){
            player.setAlive(false);
        }
        player.move();
    }


}
