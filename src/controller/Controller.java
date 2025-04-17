package controller;

import object.Ball;
import view.Painter;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class Controller {

    public static ArrayList<Ball> ballList = new ArrayList<>();


    public static void startTime() {
        moveBalls();
        checkCollision();
    }

    static  {
        Random random = new Random();
        int ballNumber = 1;
        int ballRadius = 40;

        double[] ballPositonsX;
        double[] ballPositonsY;

        for (int i = 0; i < ballNumber; i++) {
            double randomPostionX = random.nextDouble();
            double randomPostionY = random.nextDouble();

            //pridat algoritmus na nahodne generovani pozic ballu
            /* if (randomPostionX ) {
                ballPositonsX[i] = randomPostionX;
            }
             */
        }

        for (int i = 0; i < ballNumber; i++) {
            //random.nextInt(Settings.WINDOW_WIDTH - (ballRadius * 2)
            ballList.add(new Ball("", ballRadius, Settings.WINDOW_WIDTH - 100, random.nextInt(190) + 10));
        }
    }

    private static void moveBalls() {
        for (Ball ball : ballList) {
                ball.move();
        }
    }

    private static void checkCollision() {
        for (Ball ball : ballList) {

            double ballX = ball.getX();
            double ballY = ball.getY();
            double ballRadius = ball.getRadius();
            double ballVelocityX = ball.getVelocityX();
            double ballVelocityY = ball.getVelocityY();
            double ballDiameter = ballRadius * 2;

            if (ballY + (ballDiameter) >= Settings.WINDOW_HEIGHT) {  // Podmínka pro dolní hranu
                ball.setY(Settings.WINDOW_HEIGHT - ballDiameter);  // Zarovná balíček na spodní hranu
                ball.setVelocityY(-ballVelocityY * Settings.ODRAZIVOST);
            }


            // Kolize s horní hranou - upraveno
            if (ballY - ballRadius <= 0) {
                ball.setY(ballRadius); // Nastavíme horní hranu míče na 0 (zarovnání)
                ball.setVelocityY(-ballVelocityY * Settings.ODRAZIVOST);
            }


            // Kolize s levou hranou - upraveno pro přesnou pozici
            if (ballX - ballRadius <= 0) {
                ball.setX(ballRadius);  // nastavíme míč přesně na hranu
                ball.setVelocityX(-ballVelocityX * Settings.ODRAZIVOST);
            }

            // Kolize s pravou hranou
            if (ballX + ballRadius >= Settings.WINDOW_WIDTH) {
                System.out.println("X: " + ballX + " radius " +  ballRadius + " dohromady: " + (ballX + ballRadius) + " a sirka okna: " + Settings.WINDOW_WIDTH);
                ball.setX(Settings.WINDOW_WIDTH - ballRadius);
                //ball.setX(Settings.WINDOW_WIDTH - ballRadius - (ballRadius / 2) + 3);  // nastavíme míč přesně na hranu
                ball.setVelocityX(0);
                System.out.println("nejpravejsi bod je na souradnici: " + (ballX + ballRadius));
                //ball.setVelocityX(-ballVelocityX * Settings.ODRAZIVOST);
            }

        }

        for (int i = 0; i < ballList.size(); i++) {
            for (int j = i + 1; j < ballList.size(); j++) {
                Ball ball1 = ballList.get(i);
                Ball ball2 = ballList.get(j);

                // Výpočet vzdálenosti mezi středy míčů
                double dx = ball2.getX() - ball1.getX();
                double dy = ball2.getY() - ball1.getY();
                double distance = Math.sqrt(dx * dx + dy * dy);

                // Součet poloměrů
                double sumRadius = ball1.getRadius() + ball2.getRadius();

                // Kontrola, zda došlo ke kolizi - přidána tolerance
                if (Math.abs(distance - sumRadius) < 1) {  // Kolize nastane pouze když se míče skutečně dotknou
                    // Normalizované vektory směru
                    double nx = dx / distance;
                    double ny = dy / distance;

                    // Relativní rychlost
                    double vx = ball2.getVelocityX() - ball1.getVelocityX();
                    double vy = ball2.getVelocityY() - ball1.getVelocityY();

                    // Výpočet impulzu
                    double impulsScale = (vx * nx + vy * ny) * Settings.ODRAZIVOST;

                    // Aplikace impulzu pouze pokud se míče přibližují
                    if (impulsScale < 0) {
                        ball1.setVelocityX(ball1.getVelocityX() + nx * impulsScale);
                        ball1.setVelocityY(ball1.getVelocityY() + ny * impulsScale);
                        ball2.setVelocityX(ball2.getVelocityX() - nx * impulsScale);
                        ball2.setVelocityY(ball2.getVelocityY() - ny * impulsScale);

                        // Minimální korekce pozic
                        double overlap = sumRadius - distance;
                        if (overlap > 0) {
                            double moveX = (overlap * nx) / 2;
                            double moveY = (overlap * ny) / 2;

                            ball1.setX(ball1.getX() - moveX);
                            ball1.setY(ball1.getY() - moveY);
                            ball2.setX(ball2.getX() + moveX);
                            ball2.setY(ball2.getY() + moveY);
                        }
                    }
                }
            }
        }
    }


}