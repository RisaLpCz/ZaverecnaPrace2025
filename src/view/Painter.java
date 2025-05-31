package view;

import controller.BallColorGenerator;
import controller.Controller;
import controller.Settings;
import object.Ball;
import object.EdgeObstacle;
import object.RoundObstacle;
import object.TriangleObstacle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The {@code Painter} class is responsible for rendering the entire game scene onto the screen.
 * It draws the game background, static and dynamic obstacles, spawn circles, and all balls
 * with their associated names and numbers.
 *
 * <p>This class extends {@link JPanel} and overrides the {@code paintComponent} method
 * to customize drawing behavior using Java 2D graphics.</p>
 */
public class Painter extends JPanel {

    /**
     * Color generator for assigning unique colors to balls.
     */
    private BallColorGenerator generator = new BallColorGenerator();

    /**
     * List of unique colors assigned to balls, one per ball number.
     */
    private ArrayList<BallColorGenerator.Color> colors = generator.generateUniqueColors();

    /**
     * Custom painting logic for rendering the game environment.
     *
     * @param graphics the {@code Graphics} context used for drawing
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        setBackground(new Color(111, 210, 255));

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, Settings.WORLD_HEIGHT - (int) Settings.cameraOffsetY, Settings.WORLD_WIDTH, 20);

        graphics.setColor(Color.BLACK);
        for (int i = 0; i < Settings.WORLD_WIDTH; i += 20) {
            graphics.fillRect(i, Settings.WORLD_HEIGHT - (int) Settings.cameraOffsetY, 10, 10);
            graphics.fillRect(i + 10, (Settings.WORLD_HEIGHT + 10) - (int) Settings.cameraOffsetY, 10, 10);
        }

        for (int i = 0; i < Controller.spawnCircle.size(); i++) {
            int lineWidth = 20;
            int x = (int) Controller.spawnCircle.get(i).getX();
            int y = (int) Controller.spawnCircle.get(i).getY();
            int radius = Controller.spawnCircle.get(i).getRadius() + (lineWidth / 2);

            ((Graphics2D) graphics).setStroke(new BasicStroke(lineWidth));
            graphics.drawOval(x - radius, (int) (y - radius - Settings.cameraOffsetY), radius * 2, radius * 2);
        }

        for (TriangleObstacle triangleObstacle : Controller.trianglesObstacles) {
            int[] xPoints = triangleObstacle.getxPoints();
            int[] yPoints = triangleObstacle.getyPoints();

            int[] cameraYPoints = new int[3];
            for (int i = 0; i < 3; i++) {
                cameraYPoints[i] = yPoints[i] - (int) Settings.cameraOffsetY;
            }
            graphics.fillPolygon(xPoints, cameraYPoints, 3);
        }

        for (RoundObstacle roundObstacle : Controller.roundFillObstacles) {
            int x = (int) roundObstacle.getX();
            int y = (int) (roundObstacle.getY() - Settings.cameraOffsetY);
            int radius = roundObstacle.getRadius();

            graphics.fillOval(x - radius, (int) (y - radius - Settings.cameraOffsetY), radius * 2, radius * 2);
        }

        ((Graphics2D) graphics).setStroke(new BasicStroke(Settings.LINE_WIDTH));

        for (EdgeObstacle edgeObstacle : Controller.edgeObstacles) {
            int x = (int) edgeObstacle.getX();
            int y = (int) (edgeObstacle.getY() - Settings.cameraOffsetY);
            int width = edgeObstacle.getWidth();
            int height = edgeObstacle.getHeight();

            graphics.fillRect(x, y, width, height);
        }

        for (EdgeObstacle edgeObstacle : Controller.spinningCrosses) {
            int x = (int) edgeObstacle.getX();
            int y = (int) (edgeObstacle.getY() - Settings.cameraOffsetY);
            int width = edgeObstacle.getWidth();
            int height = edgeObstacle.getHeight();

            graphics.fillRect(x, y, width, height);
        }

        ((Graphics2D) graphics).setStroke(new BasicStroke(2));
        for (Ball ball : Controller.ballList) {
            BallColorGenerator.Color color = colors.get(ball.getNumber() - 1);
            graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));

            int x = (int) ball.getX();
            int y = (int) ball.getY();
            int radius = ball.getRadius();

            graphics.fillOval(x - radius, (int) (y - radius - Settings.cameraOffsetY), radius * 2, radius * 2);

            graphics.setColor(Color.BLACK);
            graphics.drawOval(x - radius, (int) (y - radius - Settings.cameraOffsetY), radius * 2, radius * 2);
        }

        for (Ball ball : Controller.ballList) {
            int x = (int) ball.getX();
            int y = (int) ball.getY();
            int radius = ball.getRadius();
            String name = ball.getName();
            int number = ball.getNumber();

            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString(name + " " + number, x - radius,(int) (y - radius - Settings.cameraOffsetY) - 20);
        }
    }
}
