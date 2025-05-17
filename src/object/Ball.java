package object;

import java.util.InputMismatchException;

public class Ball extends WorldObject {
    private String name;
    private int radius;
    private double velocityX;
    private double velocityY;
    private long lastUpdateTime;
    //private final Picture picture;
    //private final Song song;


    public Ball(double x, double y, String name, int radius) {
        super(x, y);
        try {
            setName(name);
            this.radius = radius;
            this.velocityX = 0.5;
            this.velocityY = 2;
            setLastUpdateTime(System.currentTimeMillis());
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void move() {
        x += velocityX;
        y += velocityY;
    }

    public void setName(String name) throws InputMismatchException {
        if (name.matches("")) {
            this.name = name;
            //dodelat regex na mag pocet pismen a odsranit nektere znaky
        } else {
            throw new InputMismatchException("Invalid name entered");
        }
    }

    public String getName() {
        return name;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
