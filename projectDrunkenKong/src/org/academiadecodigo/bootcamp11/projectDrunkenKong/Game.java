package org.academiadecodigo.bootcamp11.projectDrunkenKong;

/**
 * Created by codecadet on 09/10/17.
 */
public class Game {

    private WaterBottle[] randomWaterBottle;

    public void init() {

        Field field;

        field = new Field(10, 10, 1024, 768);

        Cpu cpu = new Cpu();

        Player player = new Player("luis lindo");

        randomWaterBottle = new WaterBottle[10];

        for (int i = 0; i < randomWaterBottle.length; i++) {

            int x = (int) (Math.random() * (1024 - 300 - 20)+150);   // 20 = - padding   300= offset
            int y = (int) (Math.ceil(Math.random() * 4));

            randomWaterBottle[i] = new WaterBottle(x, (y * 130) + 100 - 30);

        }



    }


}
