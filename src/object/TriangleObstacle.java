package object;

import java.util.ArrayList;

/**
 * Represents a triangular obstacle in the world with a specific direction.
 * It can detect and respond to collisions with balls.
 */
public class TriangleObstacle extends WorldObject {

    private int width;
    private int height;
    private Direction direction;

    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];

    /**
     * The direction the triangle is pointing.
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Constructs a TriangleObstacle at the specified position,
     * with given width, height and pointing direction.
     *
     * @param x         the x-coordinate of the triangle's top-left corner
     * @param y         the y-coordinate of the triangle's top-left corner
     * @param width     the width of the triangle
     * @param height    the height of the triangle
     * @param direction the direction the triangle is facing
     */
    public TriangleObstacle(int x, int y, int width, int height, Direction direction) {
        super(x, y);
        setWidth(width);
        setHeight(height);
        setDirection(direction);
        calculateVertices();
    }

    /**
     * Calculates the triangle's vertices based on its position, size, and direction.
     */
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

    /**
     * Detects and resolves collisions between the triangle obstacle and balls.
     * Adjusts balls' positions and velocities to prevent overlap and simulate sliding.
     *
     * @param ballList the list of balls to check for collision
     */
    //zdroj: https://www.jeffreythompson.org/collision-detection/poly-circle.php
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

    /**
     * Finds the closest point on a line segment to a given point.
     *
     * @param x1 start x-coordinate of the line segment
     * @param y1 start y-coordinate of the line segment
     * @param x2 end x-coordinate of the line segment
     * @param y2 end y-coordinate of the line segment
     * @param px the x-coordinate of the point
     * @param py the y-coordinate of the point
     * @return an array containing the closest point's coordinates [x, y]
     */

    //zdroj: https://www.jeffreythompson.org/collision-detection/poly-circle.php
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

    /**
     * Gets the width of the triangle.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the triangle.
     *
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets the height of the triangle.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the triangle.
     *
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets the direction the triangle is facing.
     *
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction the triangle is facing.
     *
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Gets the y-coordinates of the triangle vertices.
     *
     * @return the yPoints array
     */
    public int[] getyPoints() {
        return yPoints;
    }

    /**
     * Gets the x-coordinates of the triangle vertices.
     *
     * @return the xPoints array
     */
    public int[] getxPoints() {
        return xPoints;
    }
}
