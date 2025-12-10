class Vector1by2 {
    private final double x;
    private final double y;
    
    // Constructor
    public Vector1by2(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    // Getters
    public double getX() { return x; }
    public double getY() { return y; }
    
    // Static dot product function
    public static double dot(Vector1by2 v1, Vector1by2 v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }
    
    @Override
    public String toString() {
        return String.format("[%.4f, %.4f]", x, y);
    }
}
