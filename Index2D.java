package assignments.Ex2;

public class Index2D implements Pixel2D {

    private int _x;
    private int _y;

    /**
     * Standard Constructor
     * @param w - x value(int)
     * @param h - y value(int)
     */
    public Index2D(int w, int h) {
        this._x = w;
        this._y = h;
    }

    /**
     * Copy Constructor
     * @param other - Original Point
     */
    public Index2D(Pixel2D other) {
        this._x = other.getX();
        this._y = other.getY();
    }


    @Override
    public int getX() {

        return 0;
    }

    @Override
    public int getY() {

        return 0;
    }

    @Override
    public double distance2D(Pixel2D p2) {

        return 0;
    }

    @Override
    public String toString() {
        String ans = null;

        return ans;
    }

    @Override
    public boolean equals(Object p) {
        boolean ans = true;

        return ans;
    }
}
