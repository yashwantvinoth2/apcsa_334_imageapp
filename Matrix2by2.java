
public class Matrix2by2 {
    // matrix elements in row-major order
    // [ m00  m01 ]
    // [ m10  m11 ]
    private final double m00, m01, m10, m11;

    /** Construct a matrix with explicit elements. */
    public Matrix2by2(double m00, double m01, double m10, double m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
    }

    /** Rotation matrix for angle theta (radians), standard linear algebra M * v. */
    public static Matrix2by2 rotation(double thetaRadians) {
        double cos = Math.cos(thetaRadians);
        double sin = Math.sin(thetaRadians);
        // Standard rotation matrix:
        // [ cos -sin ]
        // [ sin  cos ]
        return new Matrix2by2(cos, -sin, sin, cos);
    }

    /** 90 degrees counterclockwise (π/2) */
    public static Matrix2by2 rotation90CCW() { return rotation(Math.PI / 2.0); }

    /** 90 degrees clockwise (-π/2) */
    public static Matrix2by2 rotation90CW() { return rotation(-Math.PI / 2.0); }

    /** 180 degrees */
    public static Matrix2by2 rotation180() { return rotation(Math.PI); }

    /** Multiply matrix by a vector (M * v). */
    public Vector1by2 multiply(Vector1by2 v) {
        double newR = m00 * v.getRow() + m01 * v.getCol();
        double newC = m10 * v.getRow() + m11 * v.getCol();
        return new Vector1by2(newR, newC);
    }

    /** Matrix dot-like multiplication (matrix times matrix). */
    public Matrix2by2 multiply(Matrix2by2 other) {
        double a = m00 * other.m00 + m01 * other.m10;
        double b = m00 * other.m01 + m01 * other.m11;
        double c = m10 * other.m00 + m11 * other.m10;
        double d = m10 * other.m01 + m11 * other.m11;
        return new Matrix2by2(a, b, c, d);
    }

    @Override
    public String toString() {
        return String.format("[%.4f %.4f; %.4f %.4f]", m00, m01, m10, m11);
    }
}
