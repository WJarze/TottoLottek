package data;

public class GeneratorConditions {
    private int origin;
    private  int bounds;
    private int size;

    public GeneratorConditions(int origin , int bounds , int size) {
        this.origin = origin;
        this.bounds = bounds;
        this.size = size;
    }

    public int getOrigin() {
        return origin;
    }

    public int getBounds() {
        return bounds;
    }

    public int getSize() {
        return size;
    }
}
