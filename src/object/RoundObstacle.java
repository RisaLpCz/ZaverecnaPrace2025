package object;

public class RoundObstacle extends WorldObject {
    //private final int radius;
    private int radius;
    private int angle;

    public RoundObstacle(int x, int y, int radius, int angle) {
        super(x, y);
        this.radius = radius;
        this.angle = angle;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}
