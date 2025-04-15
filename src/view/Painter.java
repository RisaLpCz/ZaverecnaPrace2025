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
             graphics.fillOval((int) ball.getX(), (int) ball.getY(), ball.getRadius(), ball.getRadius());
         }
     }
}
