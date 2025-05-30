package controller;

public class Settings {
    //OKNO/SVET
    public static final int WINDOW_WIDTH = 570;
    public static final int WINDOW_HEIGHT = 1000;
    public final static int WORLD_HEIGHT = 9500;
    public final static int WORLD_WIDTH = WINDOW_WIDTH;
    public static double cameraOffsetY = 0;

    //OBJEKTY

    //mice
    public static final int BALL_RADIUS = 40;
    public static final int BALL_NUMBER = 10;
    public static final double DISCOURAGEMENT = 1.01; //vymyslet vzorec podle poctu mic
    public static final double BALL_DISCOURAGEMENT = 0.9; //vymyslet vzorec podle poctu micu
    //pozice
    public static final double CIRCLE_RADIUS = 200;
    public static final double CIRCLEX = (double) WORLD_WIDTH / 2 ;
    public static final double CIRCLEY = BALL_RADIUS + 160;


    //trojuhelnikove prekazky
    public static final int TRIANGLE_WIDTH = 180;
    public static final int TRIANGLE_HEIGHT = 300;
    public static final int TRIANGLE_NUMBER = 3;


    //kruhove prekazky
    public static final double CIRCLE_ROTATIONSPEED = 0.05;
    public static final int MOVING_CIRCLES_RADIUS = 155;
    public static final int SMALL_INSIDE_CIRCLES_RADIUS = 120;
    public static final int LINE_WIDTH = 10;


    //Hranate prekazky
    public static final int EDGE_OBSTACLES_WIDTH = 90;
    public static final int EDGE_OBSTACLES_HEIGHT = 40;
    public static final double EDGE_OBSTACLES_MOVINGSPEED = 3;
    public static final int EDGE_OBSTACLES_ROWS = 8;
    public static final int EDGE_OBSTACLES_NUMBER_IN_ROW = 4;
    public static final int SPINNING_OBSTACLE_WIDTH = 105; //210
    public static final int SPINNING_OBSTACLE_HEIGHT = 25; //50
    public static final int SPINNING_OBSTACLE_ROWS = 4;



    //FYZIKA
    public static final double GRAVITACNIKONSTANTA = 9.81;




    //pokud budu delat graficke nastavnovani s listou muze se hodit
    //tim padem pak zmenit datovy typ v spawn generatoru (step a radius)
    //public static final double BALL_RADIUS = 40;
}
