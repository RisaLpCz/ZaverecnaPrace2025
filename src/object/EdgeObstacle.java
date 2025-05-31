package object;

import controller.Settings;

import java.util.ArrayList;

/**
 * Represents a rectangular obstacle that can move horizontally and rotate.
 * It interacts with balls by detecting and handling collisions.
 */
public class EdgeObstacle extends WorldObject {
    private int width;
    private int height;
    private double angle;
    private double rotatingSpeed;
    private int spawnX;
    private boolean movingRight = true;

    /**
     * Constructs an EdgeObstacle at the specified position with default width and height from settings.
     *
     * @param x the initial x-coordinate
     * @param y the initial y-coordinate
     */
    public EdgeObstacle(int x, int y) {
        super(x, y);
        setWidth(Settings.EDGE_OBSTACLES_WIDTH);
        setHeight(Settings.EDGE_OBSTACLES_HEIGHT);
        setMovingRight(true);
        spawnX = x;
    }

    /**
     * Constructs an EdgeObstacle with specific dimensions and rotation speed.
     *
     * @param x the initial x-coordinate
     * @param y the initial y-coordinate
     * @param width the width of the obstacle
     * @param height the height of the obstacle
     * @param rotatingSpeed the speed of rotation in radians per update
     */
    public EdgeObstacle(int x, int y, int width, int height, double rotatingSpeed) {
        super(x, y);
        setWidth(width);
        setHeight(height);
        setRotatingSpeed(rotatingSpeed);
        setAngle(0.0);
    }

    /**
     * Moves the obstacle horizontally back and forth within a fixed offset range.
     */
    public void moveRectangles() {
        int maxOffset = 50;

        if (isMovingRight()) {
            x += Settings.EDGE_OBSTACLES_MOVINGSPEED;
            if (x >= getSpawnX() + maxOffset) {
                setMovingRight(false);
            }
        } else {
            x -= Settings.EDGE_OBSTACLES_MOVINGSPEED;
            if (x <= getSpawnX() - maxOffset) {
                setMovingRight(true);
            }
        }
    }

    /**
     * Detects and resolves collisions between the obstacle and balls.
     * Applies collision physics and adjusts balls' positions and velocities.
     *
     * @param ballList the list of balls to check for collision
     */
    //zdroj: https://medium.com/@dot32/circle-vs-rectangle-collision-30cfb74e7f3b
    public void collision(ArrayList<Ball> ballList) {
        for (Ball ball : ballList) {
            double ballX = ball.getX();
            double ballY = ball.getY();
            double ballRadius = ball.getRadius();

            double rectX = getX();
            double rectY = getY();
            double rectWidth = getWidth();
            double rectHeight = getHeight();

            double closestX = close(ballX, rectX, rectX + rectWidth);
            double closestY = close(ballY, rectY, rectY + rectHeight);

            double dx = ballX - closestX;
            double dy = ballY - closestY;
            double distanceSquared = dx * dx + dy * dy;

            if (distanceSquared < ballRadius * ballRadius) {
                double distance = Math.sqrt(distanceSquared);

                if (distance == 0) {
                    dx = 0;
                    dy = -1;
                    distance = 1;
                }

                double nx = dx / distance;
                double ny = dy / distance;

                double overlap = ballRadius - distance;
                ball.setX(ballX + nx * overlap);
                ball.setY(ballY + ny * overlap);

                double dot = ball.getVelocityX() * nx + ball.getVelocityY() * ny;

                double projNormX = -dot * nx * 0.8;
                double projNormY = -dot * ny * 0.8;

                double slipX = ball.getVelocityX() - dot * nx;
                double slipY = ball.getVelocityY() - dot * ny;

                slipX *= 0.95;
                slipY *= 0.95;

                ball.setVelocityX(projNormX + slipX);
                ball.setVelocityY(projNormY + slipY);
            }
        }
    }

    /**
     * Clamps a value between a minimum and maximum.
     *
     * @param value the value to clamp
     * @param min the minimum limit
     * @param max the maximum limit
     * @return the clamped value
     */
    private double close(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getRotatingSpeed() {
        return rotatingSpeed;
    }

    public void setRotatingSpeed(double rotatingSpeed) {
        this.rotatingSpeed = rotatingSpeed;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }
}
