package view;

import controller.Controller;
import controller.Settings;
import object.Ball;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Painter extends JPanel {

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, Settings.WORLD_HEIGHT - (int) Settings.cameraOffsetY, Settings.WORLD_WIDTH, 20);

        graphics.setColor(Color.BLACK);
        for (int i = 0; i < Settings.WORLD_WIDTH; i+=20) {
            graphics.fillRect(i, Settings.WORLD_HEIGHT - (int) Settings.cameraOffsetY, 10, 10);
            graphics.fillRect(i + 10, (Settings.WORLD_HEIGHT + 10) - (int) Settings.cameraOffsetY, 10, 10);
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
