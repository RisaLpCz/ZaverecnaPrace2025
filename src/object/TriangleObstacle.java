package object;

public class TriangleObstacle extends WorldObject{
    public enum Direction{
        UP, DOWN, LEFT, RIGHT
    }
    private int sideA;
    private int sideB;
    private Direction direction;

    public TriangleObstacle(int x, int y, int sideA, int sideB, Direction direction) {
        super(x, y);
        this.sideA = sideA;
        this.sideB = sideB;
        this.direction = direction;
    }

    public int getSideA() {
        return sideA;
    }

    public void setSideA(int sideA) {
        this.sideA = sideA;
    }

    public int getSideB() {
        return sideB;
    }

    public void setSideB(int sideB) {
        this.sideB = sideB;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
