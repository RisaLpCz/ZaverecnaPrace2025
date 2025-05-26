package controller;

import object.Ball;
import object.EdgeObstacle;
import object.RoundObstacle;
import object.TriangleObstacle;

import java.util.ArrayList;
import java.util.Random;

public class Controller {

    public static ArrayList<Ball> ballList;
    public static ArrayList<TriangleObstacle> trianglesObstacles;
    public static ArrayList<RoundObstacle> roundFillObstacles;
    public static ArrayList<RoundObstacle> roundHollowObstacles;
    public static ArrayList<RoundObstacle> roundHollowObstacles2;
    public static ArrayList<EdgeObstacle> edgeObstacles;
    public static ArrayList<RoundObstacle> spawnCircle;
    public static ArrayList<EdgeObstacle> spinningCrosses;


    public static void initialization() {
        ballList = new ArrayList<>();
        trianglesObstacles = new ArrayList<>();
        roundFillObstacles = new ArrayList<>();
        roundHollowObstacles = new ArrayList<>();
        roundHollowObstacles2 = new ArrayList<>();
        edgeObstacles = new ArrayList<>();
        spawnCircle = new ArrayList<>();
        spinningCrosses = new ArrayList<>();
        createSpawnCircle();
        generateBalls();
        createTriangles();
        createFillCircles();
        createHollowCircles();
        createEdgeObstacles();
    }

    public static void startTime() {
        countGravity();
        moveObstacles();
        createSpinningCrosses();
        checkCollision();
        updateCamera();
    }

    public static void generateBalls() {
        BallsSpawnGenerator spawnGenerator = new BallsSpawnGenerator();
        int ballNumber = Settings.BALL_NUMBER;
        int ballRadius = Settings.BALL_RADIUS;


        for (int i = 0; i < ballNumber; i++) {
            boolean spawnBall = spawnGenerator.run();

            if (spawnBall) {
                ballList.add(new Ball(BallsSpawnGenerator.getX(), BallsSpawnGenerator.getY(), "", ballRadius));
            } else {
                break;
            }
        }
        System.out.println("Pocet nenakreslenych kruhu: " + (Settings.BALL_NUMBER - ballList.size()));
    }

    private static void moveObstacles() {
        for (Ball ball : ballList) {
            ball.move();
        }

        for (RoundObstacle roundObstacle : roundHollowObstacles) {
            roundObstacle.rotate();
        }
        for (RoundObstacle roundObstacle : roundHollowObstacles2) {
            roundObstacle.rotate();
        }


        for (EdgeObstacle edgeObstacle : edgeObstacles) {
            edgeObstacle.moveRectangles();
        }


        for (EdgeObstacle edgeObstacle : spinningCrosses) {
            edgeObstacle.rotate();
        }

    }

    private static void checkCollision() {
        for (Ball ball : ballList) {

            double ballX = ball.getX();
            double ballY = ball.getY();
            double ballRadius = ball.getRadius();
            double ballVelocityX = ball.getVelocityX();
            double ballVelocityY = ball.getVelocityY();

            if (ballY + ballRadius >= Settings.WORLD_HEIGHT + (Settings.BALL_RADIUS * 3)) {
                ball.setY(Settings.WORLD_HEIGHT - ballRadius + (Settings.BALL_RADIUS * 3));
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

        Settings.cameraOffsetY = lowestBallY - (double) (Settings.WINDOW_HEIGHT / 2);

        if (Settings.cameraOffsetY < 0) {
            Settings.cameraOffsetY = 0;
        }

        double maxOffset = Settings.WORLD_HEIGHT - Settings.WINDOW_HEIGHT + (Settings.BALL_RADIUS * 3);
        if (Settings.cameraOffsetY > maxOffset) {
            Settings.cameraOffsetY = maxOffset;
        }
    }

    private static void createSpawnCircle() {
        int x = (int) Settings.CIRCLEX;
        int y = (int) Settings.CIRCLEY;
        int radius = (int) Settings.CIRCLE_RADIUS;
        RoundObstacle roundObstacle = new RoundObstacle(x, y, radius);
        spawnCircle.add(roundObstacle);
    }

    private static void createTriangles() {
        int height = Settings.TRIANGLE_HEIGHT;
        int width = Settings.TRIANGLE_WIDTH;
        int numberOfTriangles = Settings.TRIANGLE_NUMBER;

        int startXL = Settings.WORLD_WIDTH - width;
        int startXR = 0;
        int startXM = (Settings.WORLD_WIDTH / 2) - (width / 2);
        int startY = 700;

        for (int i = 0; i < numberOfTriangles; i++) {
            int y = startY + i * height;

            TriangleObstacle triangleL = new TriangleObstacle(startXL, y, width, height, TriangleObstacle.Direction.LEFT);
            TriangleObstacle triangleR = new TriangleObstacle(startXR, y, width, height, TriangleObstacle.Direction.RIGHT);
            trianglesObstacles.add(triangleL);
            trianglesObstacles.add(triangleR);
        }

        int spacing = 50;
        int y = startY - 90;
        height = (width * width) / height;

        int heightSide = numberOfTriangles * height;
        int hegihtPerPair = 2 * height + spacing;

        int numberInMiddle = (int) Math.ceil((double) heightSide / hegihtPerPair * 2);

        for (int i = 0; i < numberInMiddle + 2; ) {
            TriangleObstacle upTriangle = new TriangleObstacle(startXM, y, width, height, TriangleObstacle.Direction.UP);
            trianglesObstacles.add(upTriangle);
            i++;
            y += height;

            if (i < numberInMiddle + 2) {
                TriangleObstacle downTriangle = new TriangleObstacle(startXM, y, width, height, TriangleObstacle.Direction.DOWN);
                trianglesObstacles.add(downTriangle);
                i++;
                y += height;
            }

            y += spacing;

        }
    }

    private static void createFillCircles() {
        int ballRadius = 40;
        int largeRadius = 95;
        int smallRadius = 78;
        int verticalGap = ballRadius * 2;
        int startY = 2000;
        int rowSpacing = largeRadius * 2 + smallRadius * 2 + verticalGap * 2;

        int windowWidth = Settings.WINDOW_WIDTH;
        int centerX = windowWidth / 2;

        int leftX = 0;
        int centerXBig = centerX;
        int rightX = windowWidth;

        int gapX = (centerXBig - leftX);
        int smallLeftX = leftX + gapX / 2;
        int smallRightX = centerXBig + gapX / 2;

        createRoundObstacleRow(startY, leftX, centerXBig, rightX, smallLeftX, smallRightX, largeRadius, smallRadius);

        int bottomY = startY + rowSpacing;
        createRoundObstacleRow(bottomY, leftX, centerXBig, rightX, smallLeftX, smallRightX, largeRadius, smallRadius);

    }

    private static void createRoundObstacleRow(int y, int leftX, int centerX, int rightX, int smallLeftX, int smallRightX, int largeRadius, int smallRadius) {
        roundFillObstacles.add(new RoundObstacle(leftX, y, largeRadius));
        roundFillObstacles.add(new RoundObstacle(centerX, y, largeRadius));
        roundFillObstacles.add(new RoundObstacle(rightX, y, largeRadius));

        int minGap = 40 * 2;
        int smallY = y + largeRadius + smallRadius + minGap;
        roundFillObstacles.add(new RoundObstacle(smallLeftX, smallY, smallRadius));
        roundFillObstacles.add(new RoundObstacle(smallRightX, smallY, smallRadius));
    }

    private static void createHollowCircles() {
        int radius = Settings.MOVING_CIRCLES_RADIUS;

        int startXRight = Settings.WORLD_WIDTH - radius;

        double dx = startXRight - radius;
        double dy = Math.sqrt((radius * 2) * (radius * 2) - dx * dx);

        int yLeft = 3200;

        for (int i = 0; i < 2; i++) {
            int yRight = (int) (yLeft + dy);

            roundHollowObstacles.add(new RoundObstacle(radius, yLeft, radius, Settings.CIRCLE_ROTATIONSPEED));
            roundHollowObstacles.add(new RoundObstacle(startXRight, yRight, radius, Settings.CIRCLE_ROTATIONSPEED));

            yLeft = yRight + (int) dy;
        }

        //otaceci velkeho kola s vicero vykasama a mesnim uvnitr nej
        int radiusL = Settings.WINDOW_WIDTH / 2;
        int radiusS = Settings.SMALL_INSIDE_CIRCLES_RADIUS;
        int y = 4500;

        roundHollowObstacles2.add(new RoundObstacle(radiusL, y, radiusL, Settings.CIRCLE_ROTATIONSPEED));
        roundHollowObstacles2.add(new RoundObstacle(radiusL, y, radiusS, Settings.CIRCLE_ROTATIONSPEED));
    }

    private static void createEdgeObstacles() {
        int width = Settings.EDGE_OBSTACLES_WIDTH;

        int startX = 0;
        int startY = 5200;

        int gapX = 85 * 2;
        int gapY = 50 * 2;

        int numberOfRows = Settings.EDGE_OBSTACLES_ROWS;
        int rectanglesPerRow = Settings.EDGE_OBSTACLES_NUMBER_IN_ROW;

        for (int i = 0; i < numberOfRows; i++) {
            int offsetX = (i % 2 == 0) ? 0 : -width / 2;

            for (int j = 0; j < rectanglesPerRow; j++) {
                int x = startX + offsetX + (gapX * j);
                int y = startY + (gapY * i);

                edgeObstacles.add(new EdgeObstacle(x, y));
            }
        }
    }

    private static void createSpinningCrosses() {
        int windowWidth = Settings.WORLD_WIDTH;

        int hWidth = Settings.SPINNING_OBSTACLE_WIDTH;
        int hHeight = Settings.SPINNING_OBSTACLE_HEIGHT;

        int vWidth = Settings.SPINNING_OBSTACLE_HEIGHT;
        int vHeight = Settings.SPINNING_OBSTACLE_WIDTH;

        double rotatingSpeed = Settings.CIRCLE_ROTATIONSPEED;
        double rotatingDirection;

        int wideGap = 57;
        int rowGap = wideGap + vHeight;
        int startY = 6100;

        int rows = Settings.SPINNING_OBSTACLE_ROWS;

        int leftX;
        int rightX;

        for (int i = 0; i < rows; i++) {
            int y = startY + i * rowGap;

            if (i % 2 == 0) {
                leftX = -(hWidth - hHeight) / 2;
                rightX = windowWidth - (hWidth - ((hWidth - hHeight) / 2));
                rotatingDirection = 1;
            } else {
                leftX = 57;
                rightX = windowWidth - hWidth - 57;
                rotatingDirection = -1;
            }

            spinningCrosses.add(new EdgeObstacle(leftX, y, hWidth, hHeight, rotatingDirection * rotatingSpeed));
            spinningCrosses.add(new EdgeObstacle(leftX + (hWidth - vWidth) / 2, y - (vHeight - hHeight) / 2, vWidth, vHeight, rotatingDirection * rotatingSpeed));

            spinningCrosses.add(new EdgeObstacle(rightX, y, hWidth, hHeight, rotatingDirection * rotatingSpeed));
            spinningCrosses.add(new EdgeObstacle(rightX + (hWidth - vWidth) / 2, y - (vHeight - hHeight) / 2, vWidth, vHeight, rotatingDirection * rotatingSpeed));
        }
    }


}