package controller;

public class Settings {
    public static final double GRAVITACNIKONSTANTA = 9.81;
    public static final int WINDOW_WIDTH = 585;
    public static final int WINDOW_HEIGHT = 1000;
    public static int CAMERAOFFSETY = 1500;


    public static final int BALL_RADIUS = 40;
    public static final int BALL_NUMBER = 30;
    public static final double DISCOURAGEMENT = 1.05; //vymyslet vzorec podle poctu mic
    public static final double BALL_DISCOURAGEMENT = 0.9; //vymyslet vzorec podle poctu micu


    //pokud budu delat graficke nastavnovani s listou muze se hodit
    //tim padem pak zmenit datovy typ v spawn generatoru (step a radius)
    //public static final double BALL_RADIUS = 40;
}
