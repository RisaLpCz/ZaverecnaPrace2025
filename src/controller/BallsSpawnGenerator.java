package controller;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.util.*;

public class BallsSpawnGenerator {

    private static double x;
    private static double y;

    private static final int WIDTH = Settings.WINDOW_WIDTH;
    private static final int HEIGHT = Settings.WINDOW_HEIGHT - 600;
    private static final int RADIUS = Settings.BALL_RADIUS;
    private static final int STEP = 1;

    private HashSet<Point> freePoints;
    private Random random = new Random();

    public BallsSpawnGenerator() {
        //generateFreePoints();
        generateCircleFreePoints();
    }

    public boolean run() {
        if (!freePoints.isEmpty()) {
            int index = random.nextInt(freePoints.size());
            //Point randomPoint = freePoints.stream().skip(index).findFirst().orElse(null); //pokud bude program bezet na vice vlaknech (zatim nepotrebuji)
            Point randomPoint = freePoints.stream().skip(index).findFirst().orElseThrow(IllegalStateException::new);

            setX(randomPoint.x);
            setY(randomPoint.y);

            updateUnuseablePoints(randomPoint);
            return true;
        } else {
            //vyjimka
            System.out.println("Žádné volné místo pro další kruh. Sniž počet nebo poloměr.");
            return false;
        }
    }

    private void generateSquareFreePoints() {
        freePoints = new HashSet<>();

        for (double x = RADIUS; x <= WIDTH - RADIUS; x += STEP) {
            for (double y = RADIUS; y <= HEIGHT - RADIUS; y += STEP) {
                freePoints.add(new Point(x, y));
            }
        }
    }

    private void generateCircleFreePoints() {
        freePoints = new HashSet<>();

        double circleRadius = Settings.CIRCLE_RADIUS;
        double circleCentreX = Settings.CIRCLEX;
        double circleCentreY = Settings.CIRCLEY;

        double usableRadius = circleRadius - RADIUS;
        double radiusSquared = usableRadius * usableRadius;

        for (double x = circleCentreX - usableRadius; x <= circleCentreX + usableRadius; x += STEP) {
            for (double y = circleCentreY - usableRadius; y <= circleCentreY + usableRadius; y += STEP) {
                double distanceSquared = (x - circleCentreX) * (x - circleCentreX) + (y - circleCentreY) * (y - circleCentreY);
                if (distanceSquared <= radiusSquared) {
                    freePoints.add(new Point(x, y));
                }
            }
        }


    }

    private void updateUnuseablePoints(Point centerPoint) {
        HashSet<Point> toRemove = new HashSet<>();

        for (Point point : freePoints) {
            if (centerPoint.distanceTo(point) < 2 * RADIUS) {
                toRemove.add(point);
            }
        }
        freePoints.removeAll(toRemove);
    }

    public static double getX() {
        return x;
    }

    public static void setX(double x) {
        BallsSpawnGenerator.x = x;
    }

    public static double getY() {
        return y;
    }

    public static void setY(double y) {
        BallsSpawnGenerator.y = y;
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

        @Override
        public String toString() {
            return "x: " + x + " y: " + y;
        }
    }
}
