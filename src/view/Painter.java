package view;

import controller.Controller;
import controller.Settings;
import object.Ball;
import object.EdgeObstacle;
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

        for (int i = 0; i < Controller.spawnCircle.size(); i++) {
            int lineWidth = 20;
            double x  = Controller.spawnCircle.get(i).getX();
            double y = Controller.spawnCircle.get(i).getY();
            int radius = Controller.spawnCircle.get(i).getRadius() + (lineWidth / 2);

            ((Graphics2D) graphics).setStroke(new BasicStroke(lineWidth));

            graphics.drawOval((int) x - radius, (int) (y - radius - Settings.cameraOffsetY), radius * 2, radius * 2);
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

        for (RoundObstacle roundObstacle : Controller.roundFillObstacles) {
            graphics.fillOval((int) (roundObstacle.getX() - roundObstacle.getRadius()),
                    (int) (roundObstacle.getY() - roundObstacle.getRadius() - Settings.cameraOffsetY),
                    roundObstacle.getRadius() * 2,
                    roundObstacle.getRadius() * 2);
        }

        ((Graphics2D) graphics).setStroke(new BasicStroke(Settings.LINE_WIDTH));

        for (EdgeObstacle edgeObstacle : Controller.edgeObstacles) {
            int x = (int) edgeObstacle.getX();
            int y = (int) edgeObstacle.getY();
            int screenY = (int) (y - Settings.cameraOffsetY);
            int width = edgeObstacle.getWidth();
            int height = edgeObstacle.getHeight();

            graphics.fillRect(x, screenY, width, height);

        }

        for (RoundObstacle roundObstacle : Controller.roundHollowObstacles) {
            int x = (int) roundObstacle.getX();
            int y = (int) roundObstacle.getY();
            int screenY = (int) (y - Settings.cameraOffsetY);
            int radius = roundObstacle.getRadius() + Settings.LINE_WIDTH / 2;
            int diameter = roundObstacle.getRadius() * 2;
            int gapAngle = 30;
            double angleDeg = Math.toDegrees(roundObstacle.getAngle());

            int baseAngle1 = 90 + (gapAngle / 2);
            int baseAngle2 = 270 + (gapAngle / 2);

            int angle1Start = (int) (baseAngle1 + angleDeg);
            int angle2Start = (int) (baseAngle2 + angleDeg);

            int arcSweep = 180 - gapAngle;

            graphics.drawArc(x - radius, screenY - radius, diameter, diameter, angle1Start, arcSweep);
            graphics.drawArc(x - radius, screenY - radius, diameter, diameter, angle2Start, arcSweep);
        }

        for (RoundObstacle roundObstacle : Controller.roundHollowObstacles2) {
            int x = (int) roundObstacle.getX();
            int y = (int) roundObstacle.getY();
            int screenY = (int) (y - Settings.cameraOffsetY);
            int radius = roundObstacle.getRadius() + Settings.LINE_WIDTH / 2;
            int diameter = radius * 2;
            double angleOffset = Math.toDegrees(roundObstacle.getAngle());

            double arcLength = 179;
            double circumference = 2 * Math.PI * radius;
            double arcAngle = (arcLength / circumference) * 360.0;
            int segmentCount = (int)(360 / (2 * arcAngle));

            for (int j = 0; j < segmentCount; j++) {
                double startAngle = j * 2 * arcAngle + angleOffset;
                graphics.drawArc(x - radius, screenY - radius, diameter, diameter,
                        (int) startAngle, (int) arcAngle);
            }
        }

        for (EdgeObstacle edgeObstacle : Controller.spinningCrosses) {
            int x = (int) edgeObstacle.getX();
            int y = (int) (edgeObstacle.getY() - Settings.cameraOffsetY);
            int width = edgeObstacle.getWidth();
            int height = edgeObstacle.getHeight();
            double angle = edgeObstacle.getAngle();

            graphics.fillRect(x, y, width, height);
        }

        ((Graphics2D) graphics).setStroke(new BasicStroke(2));

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
