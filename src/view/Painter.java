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

        ((Graphics2D) graphics).setStroke(new BasicStroke(10));

        for (RoundObstacle roundObstacle : Controller.roundHollowObstacles) {
            int x = (int) roundObstacle.getX();
            int y = (int) roundObstacle.getY();
            int screenY = (int) (y - Settings.cameraOffsetY);
            int radius = roundObstacle.getRadius();
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

        for (EdgeObstacle edgeObstacle : Controller.edgeObstacles) {
            int x = (int) edgeObstacle.getX();
            int y = (int) edgeObstacle.getY();
            int screenY = (int) (y - Settings.cameraOffsetY);
            int width = edgeObstacle.getWidth();
            int height = edgeObstacle.getHeight();

            graphics.fillRect(x, screenY, width, height);

        }
        /*for (int i = 0; i < Controller.roundHollowObstacles.size() - 2; i++) {
            int x = Controller.roundHollowObstacles.get(i).getX();
            int y = Controller.roundHollowObstacles.get(i).getY();
            int screenY = (int) (y - Settings.cameraOffsetY);
            int radius = Controller.roundHollowObstacles.get(i).getRadius();
            int diameter = Controller.roundHollowObstacles.get(i).getRadius() * 2;
            int gapAngle = 30;
            double angleDeg = Math.toDegrees(Controller.roundHollowObstacles.get(i).getAngle());

            int baseAngle1 = 90 + (gapAngle / 2);
            int baseAngle2 = 270 + (gapAngle / 2);

            int angle1Start = (int) (baseAngle1 + angleDeg);
            int angle2Start = (int) (baseAngle2 + angleDeg);

            int arcSweep = 180 - gapAngle;

            graphics.drawArc(x - radius, screenY - radius, diameter, diameter, angle1Start, arcSweep);
            graphics.drawArc(x - radius, screenY - radius, diameter, diameter, angle2Start, arcSweep);
        }
         */

        /*graphics.drawArc(Controller.roundHollowObstacles.getLast().getX() - Controller.roundHollowObstacles.getLast().getRadius(),
                (int) (Controller.roundHollowObstacles.getLast().getY() - Controller.roundHollowObstacles.getLast().getRadius() - Settings.cameraOffsetY),
                Controller.roundHollowObstacles.getLast().getRadius() * 2, Controller.roundHollowObstacles.getLast().getRadius() * 2, 0, 180 - (30 / 2)
        );
         */

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
