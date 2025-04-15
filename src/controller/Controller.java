package controller;

import object.Ball;
import view.Painter;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    public static ArrayList<Ball> ballList = new ArrayList<>();


    static  {
        Random random = new Random();
        int ballNumber = 4;
        int ballRadius = 40;

        for (int i = 0; i < ballNumber; i++) {
            ballList.add(new Ball("", ballRadius, random.nextInt(Settings.WINDOW_WIDTH - (ballRadius * 2)) + 20, random.nextInt(190) + 10, 0, 0));
        }
    }


}
