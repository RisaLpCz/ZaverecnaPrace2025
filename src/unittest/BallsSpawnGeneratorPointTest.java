package unittest;

import controller.BallsSpawnGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BallsSpawnGeneratorPointTest {

    @Test
    public void testDistanceTo() {
        BallsSpawnGenerator.Point p1 = new BallsSpawnGenerator.Point(0, 0);
        BallsSpawnGenerator.Point p2 = new BallsSpawnGenerator.Point(3, 4);

        double distance = p1.distanceTo(p2);

        assertEquals(5.0, distance);
    }
}
