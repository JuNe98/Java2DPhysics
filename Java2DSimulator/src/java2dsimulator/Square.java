package java2dsimulator;

/**
 *
 * @author Julian
 */
public class Square implements Object2D{
    private int length;

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getCircles() {
        return this.length*this.length;
    }
}
