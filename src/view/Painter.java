package view;

import controller.Controller;
import controller.Settings;
import object.Ball;
import object.RoundObstacle;
import object.TriangleObstacle;

import javax.swing.*;
import java.awt.*;

public class Painter extends JPanel {

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        setBackground(new Color(111, 210, 255));

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, Settings.WORLD_HEIGHT - (int) Settings.cameraOffsetY, Settings.WORLD_WIDTH, 20);

        graphics.setColor(Color.BLACK);
        for (int i = 0; i < Settings.WORLD_WIDTH; i+=20) {
            graphics.fillRect(i, Settings.WORLD_HEIGHT - (int) Settings.cameraOffsetY, 10, 10);
            graphics.fillRect(i + 10, (Settings.WORLD_HEIGHT + 10) - (int) Settings.cameraOffsetY, 10, 10);
        }

        for (TriangleObstacle triangleObstacle : Controller.trianglesObstacles) {
            int[] xPoints = triangleObstacle.getxPoints();
            int[] yPoints = triangleObstacle.getyPoints();

            int[] camerYPoints = new int[3];
            for (int i = 0; i < 3; i++) {
                camerYPoints[i] = yPoints[i] - (int) Settings.cameraOffsetY;
            }
            graphics.fillPolygon(xPoints, camerYPoints, 3);
        }

        for (RoundObstacle roundObstacle : Controller.roundObstacles) {
            graphics.drawOval(roundObstacle.getX() - roundObstacle.getRadius(),
                    (int) (roundObstacle.getY() - roundObstacle.getRadius() - Settings.cameraOffsetY),
                    roundObstacle.getRadius() * 2,
                    roundObstacle.getRadius() * 2);
        }

        for (Ball ball : Controller.ballList) {
            graphics.setColor(Color.CYAN);
            graphics.fillOval(
                    (int) (ball.getX() - ball.getRadius()),
                    (int) (ball.getY() - ball.getRadius() - Settings.cameraOffsetY),
                    ball.getRadius() * 2,
                    ball.getRadius() * 2
            );


            graphics.setColor(Color.RED);
            int diameter = (ball.getRadius() * 2);
            graphics.drawOval(
                    (int) (ball.getX() - ball.getRadius()),
                    (int) (ball.getY() - ball.getRadius() - Settings.cameraOffsetY),
                    diameter,
                    diameter
            );
        }
    }
}
