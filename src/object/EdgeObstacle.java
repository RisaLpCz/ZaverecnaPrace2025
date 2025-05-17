package object;

import controller.Settings;

import java.util.Set;

public class EdgeObstacle extends WorldObject {
    //private final int width; kdyz budu chtit aby se nezvecovala velikost
    //private final int height; kdyz budu chtit aby se nezvecovala velikost
    private int width;
    private int height;
    private int angle;
    private int spawnX;
    private boolean movingRight = true;

    public EdgeObstacle(int x, int y) {
        super(x, y);
        setWidth(Settings.EDGE_OBSTACLES_WIDTH);
        setHeight(Settings.EDGE_OBSTACLES_HEIGHT);
        setMovingRight(true);
        spawnX = x;
    }

    public EdgeObstacle(int x, int y, int width, int height, int angle) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.angle = angle;
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

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
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
