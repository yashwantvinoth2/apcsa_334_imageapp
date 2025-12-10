class Matrix2by2 {
    private final double m00, m01;  // First row
    private final double m10, m11;  // Second row
    
    // Constructor
    public Matrix2by2(double m00, double m01, double m10, double m11) {
        this.m00 = m00;
        this.m01 = m01;
        this.m10 = m10;
        this.m11 = m11;
    }
    
    // Getters
    public double get00() { return m00; }
    public double get01() { return m01; }
    public double get10() { return m10; }
    public double get11() { return m11; }
    
    // Get column vectors (needed for multiplication)
    public Vector1by2 getColumn(int col) {
        if (col == 0) return new Vector1by2(m00, m10);
        if (col == 1) return new Vector1by2(m01, m11);
        throw new IllegalArgumentException("Column must be 0 or 1");
    }
    
    // Static matrix-vector multiplication function
    public static Vector1by2 multiply(Vector1by2 v, Matrix2by2 m) {
        // Row vector * Matrix: v * M
        // Result_x = v.x * m00 + v.y * m10
        // Result_y = v.x * m01 + v.y * m11
        return new Vector1by2(
            Vector1by2.dot(v, m.getColumn(0)),
            Vector1by2.dot(v, m.getColumn(1))
        );
    }
    
    @Override
    public String toString() {
        return String.format("[%.4f, %.4f]\n[%.4f, %.4f]", m00, m01, m10, m11);
    }
}