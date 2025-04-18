package controller;

import java.util.*;

public class BallsSpawnGenerator {

    private static double x;
    private static double y;

    private static final int WIDTH = Settings.WINDOW_WIDTH;
    private static final int HEIGHT = Settings.WINDOW_HEIGHT;
    private static final int RADIUS = Settings.BALL_RADIUS;
    private static final int STEP = 1;

    private HashSet<Point> freePoints;
    private HashSet<Point> usedPoints;
    private Random random = new Random();

    public void run() {
        generateFreePoints();  // 1. krok: připravíme všechny legální body
        usedPoints = new HashSet<>();

        if (!freePoints.isEmpty()) {
            // Vyber náhodný bod
            int randomIndex = random.nextInt(freePoints.size());
            Point randomPoint = freePoints.stream().skip(randomIndex).findFirst().orElse(null);
            if (randomPoint == null) {
                //pirdat exception
                System.out.println("Cannot find a spot for a ball! Please decrase the radius or number of balls");
            }
            usedPoints.add(randomPoint);
            //pirdat podminku ze randomPoint != null
            setX(randomPoint.x);
            setY(randomPoint.y);

            // Eliminace bodů, které jsou příliš blízko (méně než 2xRADIUS od nového kruhu)
            updateUnuseablePoints(randomPoint);
        }
        else {
            //udelat podminku
            System.out.println("Cannot find a spot for a ball! Please decrase the radius or number of balls");
        }
    }

    // Předem vyřadí body u hranic, aby se celý kruh vešel do prostoru
    private void generateFreePoints() {
        freePoints = new HashSet<>();

        for (double x = RADIUS; x <= WIDTH - RADIUS; x += STEP) {
            for (double y = RADIUS; y <= HEIGHT - RADIUS; y += STEP) {
                freePoints.add(new Point(x, y));
            }
        }
    }

    // Odstraní všechny body, které by byly uvnitř existujícího kruhu nebo by způsobily překryv
    private void updateUnuseablePoints(Point centerPoint) {
        HashSet<Point> toRemove = new HashSet<>();
        for (Point point : freePoints) {
            if (centerPoint.distanceTo(point) < 2 * RADIUS) {
                toRemove.add(point);
            }
        }
        freePoints.removeAll(toRemove);
    }

    public static double getY() {
        return y;
    }

    public static void setY(double y) {
        BallsSpawnGenerator.y = y;
    }

    public static double getX() {
        return x;
    }

    public static void setX(double x) {
        BallsSpawnGenerator.x = x;
    }

    private static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double distanceTo(Point nextPoint) {
            return Math.hypot(this.x - nextPoint.x, this.y - nextPoint.y);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Double.compare(x, point.x) == 0 && Double.compare(y, point.y) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(x) * 31 + Double.hashCode(y);
        }
    }
}
