package object;

public class Ball {
    private final String name;
    private int radius;
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;
    //private long casDopadu;
    //private final Picture picture;
    //private final Song song;


    public Ball(String name, int radius, double x, double y) {
        this.name = name;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.velocityX = 0.5;
        this.velocityY = 2;
    }

    public void move() {
        x += velocityX;
        y += velocityY;
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
}
