package controller;

import java.util.*;

/**
 * The {@code BallColorGenerator} class is responsible for generating a list of unique RGB colors.
 * Each color is randomly generated and guaranteed to be unique within the returned list.
 * <p>
 * The number of colors to generate is based on {@code Settings.BALL_NUMBER}.
 */
public class BallColorGenerator {

    /** Number of unique colors to generate, taken from application settings. */
    private int colorNumber = Settings.BALL_NUMBER;

    /** Set used to store unique color instances. */
    private Set<Color> colors = new HashSet<>();

    /** Random number generator for creating RGB values. */
    private Random random = new Random();

    /**
     * Generates a list of unique RGB colors. Each color is guaranteed to be distinct.
     *
     * @return an {@code ArrayList} of unique {@code Color} instances
     */
    public ArrayList<Color> generateUniqueColors() {
        while (colors.size() < colorNumber) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            colors.add(new Color(red, green, blue));
        }

        return new ArrayList<>(colors);
    }

    /**
     * Internal {@code Color} class representing an RGB color.
     * Each color consists of red, green, and blue components.
     * Instances are compared by value using overridden {@code equals} and {@code hashCode}.
     */
    public static class Color {
        private int red;
        private int green;
        private int blue;

        /**
         * Constructs a color from red, green, and blue components.
         *
         * @param red   the red component (0–255)
         * @param green the green component (0–255)
         * @param blue  the blue component (0–255)
         */
        public Color(int red, int green, int blue) {
            setRed(red);
            setGreen(green);
            setBlue(blue);
        }

        /** @return the red component */
        public int getRed() {
            return red;
        }

        /** @param red the red component to set (0–255) */
        public void setRed(int red) {
            this.red = red;
        }

        /** @return the green component */
        public int getGreen() {
            return green;
        }

        /** @param green the green component to set (0–255) */
        public void setGreen(int green) {
            this.green = green;
        }

        /** @return the blue component */
        public int getBlue() {
            return blue;
        }

        /** @param blue the blue component to set (0–255) */
        public void setBlue(int blue) {
            this.blue = blue;
        }

        /**
         * Checks if two colors are equal based on RGB values.
         *
         * @param o the object to compare
         * @return {@code true} if RGB components match, otherwise {@code false}
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Color color = (Color) o;
            return red == color.red && green == color.green && blue == color.blue;
        }

        /**
         * Computes the hash code based on RGB components.
         *
         * @return hash code for this color
         */
        @Override
        public int hashCode() {
            return Objects.hash(red, green, blue);
        }

        /**
         * Returns a human-readable string representation of the color.
         *
         * @return a string in the format "red: X, green: Y, blue: Z"
         */
        @Override
        public String toString() {
            return "red: " + red + ", green: " + green + ", blue: " + blue;
        }
    }
}
