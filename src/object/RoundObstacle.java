package object;

public class RoundObstacle extends WorldObject {
    //private final int radius;
    private int radius;

    public RoundObstacle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
