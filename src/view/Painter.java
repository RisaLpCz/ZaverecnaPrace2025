package view;

import controller.Controller;
import controller.Settings;
import object.Ball;
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

        for (TriangleObstacle triangle : Controller.triangles) {
            int x = triangle.getX();
            int y = triangle.getY() - (int) Settings.cameraOffsetY;
            int a = triangle.getSideA();
            int b = triangle.getSideB();

            int[] xPoints = new int[3];
            int[] yPoints = new int[3];

            switch (triangle.getDirection()) {
                case UP:
                    xPoints = new int[]{x - a, x + a, x};
                    yPoints = new int[]{y + b, y + b, y};
                    break;
                case DOWN:
                    xPoints = new int[]{x - a, x + a, x};
                    yPoints = new int[]{y - b, y - b, y};
                    break;
                case LEFT:
                    xPoints = new int[]{x + b, x + b, x};
                    yPoints = new int[]{y - a, y + a, y};
                    break;
                case RIGHT:
                    xPoints = new int[]{x - b, x - b, x};
                    yPoints = new int[]{y - a, y + a, y};
                    break;
            }

            graphics2D.fillPolygon(xPoints, yPoints, 3);
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
