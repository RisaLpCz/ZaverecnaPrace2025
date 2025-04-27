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

        for (Ball ball : Controller.ballList) {
            graphics.setColor(Color.CYAN);
            graphics.fillOval(
                    (int) ball.getX() - ball.getRadius(),
                    (int) ball.getY() - ball.getRadius(),
                    ball.getRadius() * 2,
                    ball.getRadius() * 2);


            graphics.setColor(Color.RED);
            int diameter = (ball.getRadius() * 2);
            graphics.drawOval(
                    (int) (ball.getX() - ball.getRadius()),
                    (int) (ball.getY() - ball.getRadius()),
                    diameter,
                    diameter
            );
        }
    }
}
