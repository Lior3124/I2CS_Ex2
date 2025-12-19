package assignments.Ex2;

import java.util.Objects;

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

        return this._x;
    }

    @Override
    public int getY() {

        return this._y;
    }

    @Override
    public double distance2D(Pixel2D p2) {
        if (Objects.isNull(p2)) {
            throw new RuntimeException("p2 is null");
        }

        double dx = p2.getX() - this._x;
        double dy = p2.getY() - this._y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        String ans = null;
        ans =   "(" + this._x + "," + this._y + ")";
        return ans;
    }

    @Override
    public boolean equals(Object p) {
        if(!(p instanceof Index2D)) {
            return false;
        }
        Index2D other = (Index2D) p;
        boolean ans = true;
        if(this._x != other.getX()){
            ans = false;
        } else if (this._y != other.getY()) {
            ans = false;
        }
        return ans;
    }
}
