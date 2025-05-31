package controller;

import java.util.*;

/**
 * The {@code BallsSpawnGenerator} class is responsible for generating valid spawn positions
 * for balls within a specified area (either a square or a circular region). It ensures
 * that balls do not overlap and that all generated positions respect spacing constraints.
 *
 * The spawn area is usually defined by a circle centered in the game window, leaving
 * enough room for all balls based on their radius and count.
 */
public class BallsSpawnGenerator {

    /** Current selected X-coordinate for the next ball spawn. */
    private static double x;

    /** Current selected Y-coordinate for the next ball spawn. */
    private static double y;

    private static final int WIDTH = Settings.WINDOW_WIDTH;
    private static final int HEIGHT = Settings.WINDOW_HEIGHT - 600;
    private static final int RADIUS = Settings.BALL_RADIUS;
    private static final int STEP = 1;

    /** Set of available spawn points that are not yet occupied. */
    private HashSet<Point> freePoints;

    /** Random generator used for picking spawn points. */
    private Random random = new Random();

    /**
     * Constructs a new {@code BallsSpawnGenerator}, generating a set of free
     * points in a circular pattern (by default).
     */
    public BallsSpawnGenerator() {
        generateCircleFreePoints();
        // generateSquareFreePoints();
    }

    /**
     * Picks a random valid point from the available spawn points and marks
     * surrounding points as used to maintain spacing. Updates the static
     * x and y fields with the selected coordinates.
     *
     * @return {@code true} if a valid point was found; {@code false} if no more valid positions are available.
     */
    public boolean run() {
        if (!freePoints.isEmpty()) {
            int index = random.nextInt(freePoints.size());
            Point randomPoint = freePoints.stream().skip(index).findFirst().orElseThrow(IllegalStateException::new);

            setX(randomPoint.x);
            setY(randomPoint.y);

            updateUnuseablePoints(randomPoint);
            return true;
        } else {
            System.out.println("No free space for another ball. Reduce count or radius.");
            return false;
        }
    }

    /**
     * Generates a set of free points within a rectangular area.
     * Not used by default.
     */
    private void generateSquareFreePoints() {
        freePoints = new HashSet<>();
        for (double x = RADIUS; x <= WIDTH - RADIUS; x += STEP) {
            for (double y = RADIUS; y <= HEIGHT - RADIUS; y += STEP) {
                freePoints.add(new Point(x, y));
            }
        }
    }

    /**
     * Generates a set of free points inside a circular region defined in {@link Settings}.
     * Ensures all points are at least {@code RADIUS} away from the boundary.
     */
    private void generateCircleFreePoints() {
        freePoints = new HashSet<>();

        double circleRadius = Settings.CIRCLE_RADIUS;
        double circleCentreX = Settings.CIRCLEX;
        double circleCentreY = Settings.CIRCLEY;

        double usableRadius = circleRadius - RADIUS;
        double radiusSquared = usableRadius * usableRadius;

        for (double x = circleCentreX - usableRadius; x <= circleCentreX + usableRadius; x += STEP) {
            for (double y = circleCentreY - usableRadius; y <= circleCentreY + usableRadius; y += STEP) {
                double distanceSquared = (x - circleCentreX) * (x - circleCentreX)
                        + (y - circleCentreY) * (y - circleCentreY);
                if (distanceSquared <= radiusSquared) {
                    freePoints.add(new Point(x, y));
                }
            }
        }
    }

    /**
     * Removes from the set all points that are within the minimum distance from a newly
     * selected spawn point to ensure balls do not overlap.
     *
     * @param centerPoint the point around which to clear a radius
     */
    private void updateUnuseablePoints(Point centerPoint) {
        HashSet<Point> toRemove = new HashSet<>();
        for (Point point : freePoints) {
            if (centerPoint.distanceTo(point) < 2 * RADIUS) {
                toRemove.add(point);
            }
        }
        freePoints.removeAll(toRemove);
    }

    /**
     * @return the currently selected X-coordinate for the next spawn.
     */
    public static double getX() {
        return x;
    }

    /**
     * Sets the X-coordinate for the next spawn.
     * @param x the x-coordinate to set
     */
    public static void setX(double x) {
        BallsSpawnGenerator.x = x;
    }

    /**
     * @return the currently selected Y-coordinate for the next spawn.
     */
    public static double getY() {
        return y;
    }

    /**
     * Sets the Y-coordinate for the next spawn.
     * @param y the y-coordinate to set
     */
    public static void setY(double y) {
        BallsSpawnGenerator.y = y;
    }

    /**
     * Internal class representing a point in 2D space used for spawn positioning.
     */
    public static class Point {
        public final double x, y;

        /**
         * Constructs a point at the given coordinates.
         *
         * @param x the x-coordinate
         * @param y the y-coordinate
         */
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Calculates the Euclidean distance to another point.
         *
         * @param nextPoint the other point
         * @return distance between this point and {@code nextPoint}
         */
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
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "x: " + x + " y: " + y;
        }
    }
}
