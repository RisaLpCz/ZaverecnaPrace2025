package object;

import java.util.ArrayList;

/**
 * Represents a circular obstacle in the world.
 * It can detect and respond to collisions with balls.
 */
public class RoundObstacle extends WorldObject {
    private int radius;

    /**
     * Constructs a RoundObstacle at the specified position with the given radius.
     *
     * @param x      the x-coordinate of the center
     * @param y      the y-coordinate of the center
     * @param radius the radius of the obstacle
     */
    public RoundObstacle(int x, int y, int radius) {
        super(x, y);
        setRadius(radius);
    }

    /**
     * Adjusts the position and velocity of balls near the obstacle's edge,
     * simulating a spawn collision effect.
     *
     * @param ballList the list of balls to check and adjust
     */
    public void spawnCollision(ArrayList<Ball> ballList) {
        double centerX = getX();
        double centerY = getY();
        double radius = getRadius();

        for (Ball ball : ballList) {
            double ballX = ball.getX();
            double ballY = ball.getY();
            double ballRadius = ball.getRadius();

            double dx = ballX - centerX;
            double dy = ballY - centerY;
            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance == 0) {
                dx = 1;
                dy = 0;
                distance = 1;
            }

            if (distance >= (radius - ballRadius) && distance <= (radius + ballRadius)) {

                double nx = dx / distance;
                double ny = dy / distance;

                double collisionX = centerX + nx * (radius - ballRadius);
                double collisionY = centerY + ny * (radius - ballRadius);

                ball.setX(collisionX);
                ball.setY(collisionY);

                double angle = Math.random() * 2 * Math.PI;
                double speed = 2 + Math.random() * 8;

                double vx = Math.cos(angle) * speed;
                double vy = Math.sin(angle) * speed;

                ball.setVelocityX(vx * 5);
                ball.setVelocityY(vy * -5);
            }
        }
    }

    /**
     * Detects and resolves collisions between the circular obstacle and balls.
     * Adjusts balls' positions and velocities to prevent overlap and simulate bounce.
     *
     * @param ballList the list of balls to check for collision
     */
    //zdroj: https://stackoverflow.com/questions/697188/fast-circle-collision-detection
    public void collision(ArrayList<Ball> ballList) {
        for (Ball ball : ballList) {
            double ballX = ball.getX();
            double ballY = ball.getY();
            double ballRadius = ball.getRadius();

            double dx = ballX - getX();
            double dy = ballY - getY();
            double distSquared = dx * dx + dy * dy;
            double combinedRadius = ballRadius + getRadius();

            if (distSquared < combinedRadius * combinedRadius) {
                double distance = Math.sqrt(distSquared);

                if (distance == 0) {
                    dx = 1;
                    dy = 0;
                    distance = 1;
                }

                double nx = dx / distance;
                double ny = dy / distance;

                double overlap = combinedRadius - distance;
                ball.setX(ballX + nx * overlap);
                ball.setY(ballY + ny * overlap);

                double dot = ball.getVelocityX() * nx + ball.getVelocityY() * ny;

                double projNormX = -dot * nx * 0.8;
                double projNormY = -dot * ny * 0.8;

                double slipX = ball.getVelocityX() - dot * nx;
                double slipY = ball.getVelocityY() - dot * ny;

                slipX *= 0.95;
                slipY *= 0.95;

                ball.setVelocityX(projNormX + slipX);
                ball.setVelocityY(projNormY + slipY);
            }
        }
    }

    /**
     * Gets the radius of the circular obstacle.
     *
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the circular obstacle.
     *
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
}
