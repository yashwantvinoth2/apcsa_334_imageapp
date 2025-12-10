public class VectorMatrixTests {
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("=== VECTOR1BY2 PUBLIC INTERFACE TESTS ===\n");
        testVector1by2Interface();
        
        System.out.println("\n=== DOT PRODUCT UNIT TEST ===\n");
        testDotProduct();
        
        System.out.println("\n=== MATRIX2BY2 PUBLIC INTERFACE TESTS ===\n");
        testMatrix2by2Interface();
        
        System.out.println("\n=== MATRIX-VECTOR MULTIPLICATION UNIT TEST ===\n");
        testMatrixVectorMultiply();
        
        System.out.println("\n=== TEST SUMMARY ===");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
    }
    
    // Vector1by2 Interface Tests
    private static void testVector1by2Interface() {
        // Test constructor and getters
        Vector1by2 v = new Vector1by2(3.5, 4.2);
        assertEqual(v.getX(), 3.5, "Vector constructor and getX()");
        assertEqual(v.getY(), 4.2, "Vector constructor and getY()");
        
        // Test with different values
        Vector1by2 v2 = new Vector1by2(-2.0, 5.5);
        assertEqual(v2.getX(), -2.0, "Vector with negative X");
        assertEqual(v2.getY(), 5.5, "Vector with positive Y");
    }
    
    // Dot Product Test
    private static void testDotProduct() {
        // Test case: (3, 4) · (2, 5) = 3*2 + 4*5 = 6 + 20 = 26
        Vector1by2 v1 = new Vector1by2(3, 4);
        Vector1by2 v2 = new Vector1by2(2, 5);
        double result = Vector1by2.dot(v1, v2);
        assertEqual(result, 26.0, "Dot product (3,4)·(2,5)");
    }
    
    // Matrix2by2 Interface Tests
    private static void testMatrix2by2Interface() {
        // Test constructor and getters
        Matrix2by2 m = new Matrix2by2(1.0, 2.0, 3.0, 4.0);
        assertEqual(m.get00(), 1.0, "Matrix get00()");
        assertEqual(m.get01(), 2.0, "Matrix get01()");
        assertEqual(m.get10(), 3.0, "Matrix get10()");
        assertEqual(m.get11(), 4.0, "Matrix get11()");
        
        // Test getColumn
        Vector1by2 col0 = m.getColumn(0);
        assertEqual(col0.getX(), 1.0, "Column 0 first element");
        assertEqual(col0.getY(), 3.0, "Column 0 second element");
        
        Vector1by2 col1 = m.getColumn(1);
        assertEqual(col1.getX(), 2.0, "Column 1 first element");
        assertEqual(col1.getY(), 4.0, "Column 1 second element");
    }
    
    // Matrix-Vector Multiplication Test
    private static void testMatrixVectorMultiply() {
        // Test case: [2, 1] * [[1, 2], [3, 4]]
        // Result: [2*1 + 1*3, 2*2 + 1*4] = [5, 8]
        Vector1by2 v = new Vector1by2(2, 1);
        Matrix2by2 m = new Matrix2by2(1, 2, 3, 4);
        Vector1by2 result = Matrix2by2.multiply(v, m);
        assertEqual(result.getX(), 5.0, "Matrix-vector multiply result X");
        assertEqual(result.getY(), 8.0, "Matrix-vector multiply result Y");
    }
    
    // Test helper method
    private static void assertEqual(double actual, double expected, String testName) {
        if (Math.abs(actual - expected) < 1e-9) {
            System.out.println("✓ PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("✗ FAIL: " + testName + 
                " (expected: " + expected + ", got: " + actual + ")");
            testsFailed++;
        }
    }
}