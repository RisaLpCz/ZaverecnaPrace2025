package object;

public class EdgeObstacle extends WorldObject {
    //private final int width; kdyz budu chtit aby se nezvecovala velikost
    //private final int height; kdyz budu chtit aby se nezvecovala velikost
    private int width;
    private int height;

    public EdgeObstacle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
