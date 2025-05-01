package controller;

public class Settings {
    public static final double GRAVITACNIKONSTANTA = 9.81;
    public static final int WINDOW_WIDTH = 570;
    public static final int WINDOW_HEIGHT = 1000;
    public final static int WORLD_HEIGHT = 1500;
    public final static int WORLD_WIDTH = WINDOW_WIDTH;

    public static double cameraOffsetY = 0;


    public static final int BALL_RADIUS = 40;
    public static final int BALL_NUMBER = 30;
    public static final double DISCOURAGEMENT = 1.05; //vymyslet vzorec podle poctu mic
    public static final double BALL_DISCOURAGEMENT = 0.9; //vymyslet vzorec podle poctu micu


    //pokud budu delat graficke nastavnovani s listou muze se hodit
    //tim padem pak zmenit datovy typ v spawn generatoru (step a radius)
    //public static final double BALL_RADIUS = 40;
}
