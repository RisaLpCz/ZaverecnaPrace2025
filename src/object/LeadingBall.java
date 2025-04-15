package object;

public class LeadingBall extends WorldObject {
    private final int radius;
    //private Picture picture;
    //private Song song;

    public LeadingBall(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }
}
