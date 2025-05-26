package object;

import controller.Settings;

public class EdgeObstacle extends WorldObject {
    private int width;
    private int height;
    private double angle;
    private double rotatingSpeed;
    private int spawnX;
    private boolean movingRight = true;

    public EdgeObstacle(int x, int y) {
        super(x, y);
        setWidth(Settings.EDGE_OBSTACLES_WIDTH);
        setHeight(Settings.EDGE_OBSTACLES_HEIGHT);
        setMovingRight(true);
        spawnX = x;
    }

    public EdgeObstacle(int x, int y, int width, int height, double rotatingSpeed) {
        super(x, y);
        setWidth(width);
        setHeight(height);
        setRotatingSpeed(rotatingSpeed);
        setAngle(0.0);
    }

    public void moveRectangles() {
        int maxOffset = 40;

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

    public void rotate() {
        angle += getRotatingSpeed();
        if (angle > 2 * Math.PI) {
            angle -= (2 * Math.PI);
        }
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
