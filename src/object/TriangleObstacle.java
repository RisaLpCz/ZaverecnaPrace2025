package object;

import java.util.ArrayList;

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
        setDirection(direction);
        calculateVertices();
    }

    private void calculateVertices() {
        int triangleX = (int) getX();
        int triangleY = (int) getY();

        switch (getDirection()) {
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

    public void collision(ArrayList<Ball> ballList) {
        for (Ball ball : ballList) {
            double ballX = ball.getX();
            double ballY = ball.getY();
            double radius = ball.getRadius();

            double closestX = ballX;
            double closestY = ballY;
            double minDistSquared = Double.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                int j = (i + 1) % 3;
                double[] closest = closestPointOnLineSegment(
                        xPoints[i], yPoints[i],
                        xPoints[j], yPoints[j],
                        ballX, ballY
                );

                double dx = ballX - closest[0];
                double dy = ballY - closest[1];
                double distSquared = dx * dx + dy * dy;

                if (distSquared < minDistSquared) {
                    minDistSquared = distSquared;
                    closestX = closest[0];
                    closestY = closest[1];
                }
            }

            if (minDistSquared < radius * radius) {
                double dx = ballX - closestX;
                double dy = ballY - closestY;
                double distance = Math.sqrt(minDistSquared);

                double nx = dx / distance;
                double ny = dy / distance;

                double dot = ball.getVelocityX() * nx + ball.getVelocityY() * ny;
                double projNormX = dot * nx;
                double projNormY = dot * ny;

                double slipX = ball.getVelocityX() - projNormX;
                double slipY = ball.getVelocityY() - projNormY;

                ball.setVelocityX(slipX);
                ball.setVelocityY(slipY);

                double overlap = radius - distance;
                ball.setX(ball.getX() + nx * overlap);
                ball.setY(ball.getY() + ny * overlap);
            }
        }
    }

    private double[] closestPointOnLineSegment(double x1, double y1, double x2, double y2, double px, double py) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        if (dx == 0 && dy == 0) {
            return new double[]{x1, y1};
        }

        double t = ((px - x1) * dx + (py - y1) * dy) / (dx * dx + dy * dy);
        t = Math.max(0, Math.min(1, t));

        return new double[]{x1 + t * dx, y1 + t * dy};
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
