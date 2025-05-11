package object;

public class TriangleObstacle extends WorldObject {

    private int width;
    private int height;
    private Direction direction;

    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public TriangleObstacle(int x, int y, int width, int height, Direction direction) {
        super(x, y);
        setWidth(width);
        setHeight(height);
        this.direction = direction;
        calculateVertices();
    }

    private void calculateVertices() {
        int triangleX = getX();
        int triangleY = getY();

        switch (direction) {
            case UP -> {
                xPoints = new int[]{triangleX, triangleX + getWidth() / 2, triangleX + getWidth()};
                yPoints = new int[]{triangleY + getHeight(), triangleY, triangleY + getHeight()};
            }
            case DOWN -> {
                xPoints = new int[]{triangleX, triangleX + getWidth() / 2, triangleX + getWidth()};
                yPoints = new int[]{triangleY, triangleY + getHeight(), triangleY};
            }
            case LEFT -> {
                xPoints = new int[]{triangleX + getWidth(), triangleX, triangleX + getWidth()};
                yPoints = new int[]{triangleY, triangleY + getHeight() / 2, triangleY + getHeight()};
            }
            case RIGHT -> {
                xPoints = new int[]{triangleX, triangleX + getWidth(), triangleX};
                yPoints = new int[]{triangleY, triangleY + getHeight() / 2, triangleY + getHeight()};
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public int[] getxPoints() {
        return xPoints;
    }
}
