package controller;

import object.Ball;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    public static ArrayList<Ball> ballList = new ArrayList<>();

    public static void startTime() {
        countGravity();
        moveBalls();
        checkCollision();
        updateCamera();
    }

    public static void napis585(int cislo) {
        for (int i = 1; i < cislo; i++) {
            if ((cislo + 10) % i == 0) {
                System.out.println(i);
            }
        }
    }

    public static void generateBalls() {
        Random random = new Random();
        BallsSpawnGenerator spawnGenerator = new BallsSpawnGenerator();
        int ballNumber = Settings.BALL_NUMBER;
        int ballRadius = Settings.BALL_RADIUS;


        for (int i = 0; i < ballNumber; i++) {
            boolean spawnBall = spawnGenerator.run();

            if (spawnBall) {
                ballList.add(new Ball("", ballRadius, BallsSpawnGenerator.getX(), BallsSpawnGenerator.getY()));
            } else {
                break;
            }
        }
        System.out.println("Pocet nenakreslenych kruhu: " + (Settings.BALL_NUMBER - ballList.size()));
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

            if (ballY + ballRadius >= Settings.WORLD_HEIGHT + 100) {
                ball.setY(Settings.WORLD_HEIGHT - ballRadius + 100);
                ball.setVelocityY(-ballVelocityY * Settings.DISCOURAGEMENT);
            }

            if (ballY - ballRadius <= 0) {
                ball.setY(ballRadius);
                ball.setVelocityY(-ballVelocityY * Settings.DISCOURAGEMENT);
            }

            if (ballX - ballRadius <= 0) {
                ball.setX(ballRadius);
                ball.setVelocityX(-ballVelocityX * Settings.DISCOURAGEMENT);
            }

            if (ballX + ballRadius >= Settings.WORLD_WIDTH) {
                ball.setX(Settings.WORLD_WIDTH - ballRadius);
                ball.setVelocityX(-ballVelocityX * Settings.DISCOURAGEMENT);
            }

        }

        for (int i = 0; i < ballList.size(); i++) {
            for (int j = i + 1; j < ballList.size(); j++) {
                Ball ball1 = ballList.get(i);
                Ball ball2 = ballList.get(j);

                double deltaX = ball2.getX() - ball1.getX();
                double deltaY = ball2.getY() - ball1.getY();
                double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                double sumRadius = ball1.getRadius() + ball2.getRadius();

                if (distance < sumRadius) {
                    if (distance == 0) {
                        distance = 0.01;
                        deltaX = 0.01;
                        deltaY = 0;
                    }

                    double noramliX = deltaX / distance;
                    double normaliY = deltaY / distance;

                    double relativeVelocityX = ball2.getVelocityX() - ball1.getVelocityX();
                    double relativePositionX = ball2.getVelocityY() - ball1.getVelocityY();

                    double impactSpeed = relativeVelocityX * noramliX + relativePositionX * normaliY;

                    if (impactSpeed < 0) {
                        double impulse = impactSpeed * Settings.BALL_DISCOURAGEMENT;

                        ball1.setVelocityX(ball1.getVelocityX() + noramliX * impulse);
                        ball1.setVelocityY(ball1.getVelocityY() + normaliY * impulse);
                        ball2.setVelocityX(ball2.getVelocityX() - noramliX * impulse);
                        ball2.setVelocityY(ball2.getVelocityY() - normaliY * impulse);
                    }

                    double overlap = 0.5 * (sumRadius - distance);
                    ball1.setX(ball1.getX() - overlap * noramliX);
                    ball1.setY(ball1.getY() - overlap * normaliY);
                    ball2.setX(ball2.getX() + overlap * noramliX);
                    ball2.setY(ball2.getY() + overlap * normaliY);
                }
            }
        }
    }

    private static void countGravity() {
        for (Ball ball : ballList) {
            double velocityY = ball.getVelocityY();
            long lastUpdateTime = ball.getLastUpdateTime();

            double timeElapsed = (System.currentTimeMillis() - lastUpdateTime) / 1000.0;
            double newVelocityY = velocityY + Settings.GRAVITACNIKONSTANTA * timeElapsed;

            ball.setVelocityY(newVelocityY);
            ball.setLastUpdateTime(System.currentTimeMillis());
        }
    }

    private static void updateCamera() {
        double lowestBallY = 0;

        for (Ball ball : ballList) {
            double ballBottom = ball.getY() + ball.getRadius();
            if (ballBottom > lowestBallY) {
                lowestBallY = ballBottom;
            }
        }

        Settings.cameraOffsetY = lowestBallY - (Settings.WINDOW_HEIGHT / 2);

        if (Settings.cameraOffsetY < 0) {
            Settings.cameraOffsetY = 0;
        }

        double maxOffset = Settings.WORLD_HEIGHT - Settings.WINDOW_HEIGHT + 100;
        if (Settings.cameraOffsetY > maxOffset) {
            Settings.cameraOffsetY = maxOffset;
        }
    }


}