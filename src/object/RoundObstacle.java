package object;

public class RoundObstacle extends WorldObject {
    private int radius;
    private double angle;
    private double rotationSpeed;

    public RoundObstacle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    public RoundObstacle(int x, int y, int radius, double rotationSpeed) {
        super(x, y);
        setRadius(radius);
        setRotationSpeed(rotationSpeed);
        setAngle(0);
    }

    public void rotate() {
        angle += getRotationSpeed();
        if (angle > 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
}
