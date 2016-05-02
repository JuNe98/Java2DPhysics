package java2dsimulator;

/**
 *
 * @author Julian
 */
public class Triangle implements Object2D{
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getCircles() {
        int x = 0;
        for (int i = length; i > 0; i--) {
            x += i;
        }
        return x;
    }
}
