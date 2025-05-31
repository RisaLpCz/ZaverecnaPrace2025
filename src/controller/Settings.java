package controller;

/**
 * The {@code Settings} class contains global constants and parameters used for configuring the game.
 * It includes settings for the game window, world dimensions, obstacles, ball properties, and physics.
 * These values are meant to be easily adjusted to tune gameplay behavior without modifying core logic.
 */
public class Settings {

    /** Width of the game window in pixels. */
    public static final int WINDOW_WIDTH = 570;

    /** Height of the game window in pixels. */
    public static final int WINDOW_HEIGHT = 1000;

    /** Total height of the game world (scrollable area). */
    public static final int WORLD_HEIGHT = 8000;

    /** Width of the game world. */
    public static final int WORLD_WIDTH = WINDOW_WIDTH;

    /** Vertical offset of the camera, used for scrolling. */
    public static double cameraOffsetY = 0;

    /** Radius of a ball in pixels. */
    public static final int BALL_RADIUS = 40;

    /** Number of balls spawned. */
    public static final int BALL_NUMBER = 10;

    /** General discouragement factor (can influence ball spread or behavior). */
    public static final double DISCOURAGEMENT = 1.01;

    /** Specific discouragement factor used for ball movement or interaction. */
    public static final double BALL_DISCOURAGEMENT = 0.9;

    /** Radius of the initial spawn circle for balls. */
    public static final double CIRCLE_RADIUS = 200;

    /** X-coordinate of the center of the spawn circle. */
    public static final double CIRCLEX = (double) WORLD_WIDTH / 2;

    /** Y-coordinate of the center of the spawn circle. */
    public static final double CIRCLEY = BALL_RADIUS + 160;

    /** Width of a triangular obstacle. */
    public static final int TRIANGLE_WIDTH = 180;

    /** Height of a triangular obstacle. */
    public static final int TRIANGLE_HEIGHT = 300;

    /** Number of triangle obstacle rows to generate. */
    public static final int TRIANGLE_NUMBER = 3;

    /** Rotation speed of rotating circle obstacles. */
    public static final double CIRCLE_ROTATIONSPEED = 0.05;

    /** Radius of large moving circular obstacles. */
    public static final int MOVING_CIRCLES_RADIUS = 155;

    /** Radius of small circles placed inside larger ones. */
    public static final int SMALL_INSIDE_CIRCLES_RADIUS = 120;

    /** Line width used for drawing circular shapes or borders. */
    public static final int LINE_WIDTH = 10;

    /** Width of rectangular (edge) obstacles. */
    public static final int EDGE_OBSTACLES_WIDTH = 90;

    /** Height of rectangular (edge) obstacles. */
    public static final int EDGE_OBSTACLES_HEIGHT = 40;

    /** Movement speed of edge obstacles. */
    public static final double EDGE_OBSTACLES_MOVINGSPEED = 3;

    /** Number of rows of edge obstacles. */
    public static final int EDGE_OBSTACLES_ROWS = 8;

    /** Number of edge obstacles per row. */
    public static final int EDGE_OBSTACLES_NUMBER_IN_ROW = 4;

    /** Width of horizontal bars used in spinning cross obstacles. */
    public static final int SPINNING_OBSTACLE_WIDTH = 105;

    /** Height of bars used in spinning cross obstacles. */
    public static final int SPINNING_OBSTACLE_HEIGHT = 25;

    /** Number of spinning cross obstacle rows. */
    public static final int SPINNING_OBSTACLE_ROWS = 4;

    /** Gravitational constant used for simulating gravity in the game world. */
    public static final double GRAVITACNIKONSTANTA = 9.81;
}
