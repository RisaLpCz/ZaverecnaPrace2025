package object;

/**
 * Abstract base class for all objects in the game world.
 * Provides basic position properties (x and y coordinates).
 */
public abstract class WorldObject {
    protected double x;
    protected double y;

    /**
     * Constructs a WorldObject at the specified coordinates.
     *
     * @param x the x-coordinate of the object
     * @param y the y-coordinate of the object
     */
    public WorldObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the object.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the object.
     *
     * @param x the new x-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the object.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the object.
     *
     * @param y the new y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }
}
