package unittest;

import controller.BallsSpawnGenerator;
import controller.Settings;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashSet;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BallsSpawnGeneratorTest {

    @Test
    public void testPointsWithinCircle() throws Exception {
        BallsSpawnGenerator generator = new BallsSpawnGenerator();
        Field freePointsField = BallsSpawnGenerator.class.getDeclaredField("freePoints");
        freePointsField.setAccessible(true);
        HashSet<?> freePoints = (HashSet<?>) freePointsField.get(generator);

        double circleRadius = Settings.CIRCLE_RADIUS;
        double centerX = Settings.CIRCLEX;
        double centerY = Settings.CIRCLEY;
        double radiusSquared = (circleRadius - Settings.BALL_RADIUS) * (circleRadius - Settings.BALL_RADIUS);

        for (Object obj : freePoints) {
            BallsSpawnGenerator.Point p = (BallsSpawnGenerator.Point) obj;
            double dx = p.x - centerX;
            double dy = p.y - centerY;
            double distSquared = dx * dx + dy * dy;
            assertTrue(distSquared <= radiusSquared, "Point outside allowed circle radius");
        }
    }

    @Test
    public void testUpdateUnuseablePoints() throws Exception {
        BallsSpawnGenerator generator = new BallsSpawnGenerator();
        Field freePointsField = BallsSpawnGenerator.class.getDeclaredField("freePoints");
        freePointsField.setAccessible(true);
        HashSet<BallsSpawnGenerator.Point> freePoints = (HashSet<BallsSpawnGenerator.Point>) freePointsField.get(generator);

        BallsSpawnGenerator.Point center = new BallsSpawnGenerator.Point(100, 100);
        BallsSpawnGenerator.Point closePoint = new BallsSpawnGenerator.Point(100 + Settings.BALL_RADIUS, 100);
        BallsSpawnGenerator.Point farPoint = new BallsSpawnGenerator.Point(200, 200);

        freePoints.clear();
        freePoints.add(center);
        freePoints.add(closePoint);
        freePoints.add(farPoint);

        var method = BallsSpawnGenerator.class.getDeclaredMethod("updateUnuseablePoints", BallsSpawnGenerator.Point.class);
        method.setAccessible(true);
        method.invoke(generator, center);

        assertFalse(freePoints.contains(closePoint));
        assertTrue(freePoints.contains(farPoint));
        assertTrue(freePoints.contains(center));
    }

    @Test
    public void testRunReturnsFalseWhenNoPoints() throws Exception {
        BallsSpawnGenerator generator = new BallsSpawnGenerator();

        Field freePointsField = BallsSpawnGenerator.class.getDeclaredField("freePoints");
        freePointsField.setAccessible(true);
        HashSet<?> freePoints = (HashSet<?>) freePointsField.get(generator);
        freePoints.clear();

        assertFalse(generator.run());
    }
}