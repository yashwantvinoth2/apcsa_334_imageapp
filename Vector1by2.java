
public class Vector1by2 {
    private final double r; // row component (y-like)
    private final double c; // col component (x-like)

    /** Create a vector with row and column components. */
    public Vector1by2(double row, double col) {
        this.r = row;
        this.c = col;
    }

    /** Return row component. */
    public double getRow() { return r; }

    /** Return column component. */
    public double getCol() { return c; }

    /** Add two vectors. */
    public Vector1by2 add(Vector1by2 other) {
        return new Vector1by2(this.r + other.r, this.c + other.c);
    }

    /** Subtract other from this. */
    public Vector1by2 subtract(Vector1by2 other) {
        return new Vector1by2(this.r - other.r, this.c - other.c);
    }

    /** Scale this vector by a scalar. */
    public Vector1by2 scale(double s) {
        return new Vector1by2(this.r * s, this.c * s);
    }

    /** Dot product (static). */
    public static double dot(Vector1by2 a, Vector1by2 b) {
        return a.r * b.r + a.c * b.c;
    }

    /** Epsilon equality helper for tests. */
    public boolean equalsEps(Vector1by2 other, double eps) {
        return Math.abs(this.r - other.r) <= eps && Math.abs(this.c - other.c) <= eps;
    }

    @Override
    public String toString() {
        return String.format("Vector1by2(%.5f, %.5f)", r, c);
    }
}
