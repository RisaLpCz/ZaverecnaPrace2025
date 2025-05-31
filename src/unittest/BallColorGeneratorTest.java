package unittest;

import controller.BallColorGenerator;
import controller.Settings;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

public class BallColorGeneratorTest {

    @org.junit.Test
    @Test
    public void testGenerateUniqueColors() {
        BallColorGenerator generator = new BallColorGenerator();
        List<BallColorGenerator.Color> colors = generator.generateUniqueColors();

        assertEquals(Settings.BALL_NUMBER, colors.size());

        HashSet<BallColorGenerator.Color> uniqueColors = new HashSet<>(colors);
        assertEquals(colors.size(), uniqueColors.size());
    }
}
