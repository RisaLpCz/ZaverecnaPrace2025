package object;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a ball in the game world with properties such as position, velocity, radius, name, and number.
 * The ball can move and load names from a file.
 */
public class Ball extends WorldObject {
    private String name;
    private int number;
    private int radius;
    private double velocityX;
    private double velocityY;
    private long lastUpdateTime;
    private boolean hasFinished;
    private Random random = new Random();

    /**
     * Constructs a Ball with specified position, name, number, and radius.
     * Velocity is randomly initialized between 0 and 10 for both axes.
     * The lastUpdateTime is set to the current system time.
     * The ball is initially not finished.
     *
     * @param x      the initial x-coordinate
     * @param y      the initial y-coordinate
     * @param name   the name of the ball, must be 1-5 alphanumeric characters
     * @param number the unique number identifier of the ball
     * @param radius the radius of the ball
     */
    public Ball(double x, double y, String name, int number, int radius) {
        super(x, y);
        setName(name);
        setNumber(number);
        setRadius(radius);
        setVelocityX(random.nextDouble() * 10);
        setVelocityY(random.nextDouble() * 10);
        setLastUpdateTime(System.currentTimeMillis());
        setHasFinished(false);
    }

    /**
     * Moves the ball according to its current velocity.
     * Updates the x and y position by adding velocity components.
     */
    public void move() {
        x += velocityX;
        y += velocityY;
    }

    /**
     * Loads ball names from a file, expecting names to be comma-separated on a single line.
     *
     * @param filePath the path to the file containing ball names
     * @return an ArrayList of trimmed names read from the file
     */
    public static ArrayList<String> loadBallNames(String filePath) {
        ArrayList<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                String[] split = line.split(",");
                for (String name : split) {
                    names.add(name.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    /**
     * Sets the name of the ball if it matches 1 to 5 alphanumeric characters.
     * Otherwise sets default name "Number" and prints a warning message.
     *
     * @param name the new name of the ball
     */
    public void setName(String name) {
        if (name.matches("^[a-zA-Z0-9]{1,5}$")) {
            this.name = name;
        } else {
            this.name = "Number";
            System.out.println("Please enter a name with digits and letters only, minimum 1 character and maximum 5 characters");
        }
    }

    /**
     * Gets the name of the ball.
     *
     * @return the ball's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique number identifier of the ball.
     *
     * @return the ball's number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the unique number identifier of the ball.
     *
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the ball's radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the ball.
     *
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Gets the velocity component along the x-axis.
     *
     * @return the velocityX
     */
    public double getVelocityX() {
        return velocityX;
    }

    /**
     * Sets the velocity component along the x-axis.
     *
     * @param velocityX the velocityX to set
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * Gets the velocity component along the y-axis.
     *
     * @return the velocityY
     */
    public double getVelocityY() {
        return velocityY;
    }

    /**
     * Sets the velocity component along the y-axis.
     *
     * @param velocityY the velocityY to set
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * Gets the last time the ball was updated.
     *
     * @return the lastUpdateTime in milliseconds
     */
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * Sets the last update time of the ball.
     *
     * @param lastUpdateTime the timestamp to set
     */
    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * Checks if the ball has finished its task or race.
     *
     * @return true if finished, false otherwise
     */
    public boolean isHasFinished() {
        return hasFinished;
    }

    /**
     * Sets whether the ball has finished its task or race.
     *
     * @param hasFinished true if finished, false otherwise
     */
    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }
}
