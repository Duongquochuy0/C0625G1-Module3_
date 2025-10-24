//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CalculatorTest {
//
//    private final Calculator calculator = new Calculator();
//
//    @Test
//    void testAdd() {
//        assertEquals(5, calculator.add(2, 3));
//        assertEquals(-1, calculator.add(2, -3));
//    }
//
//    @Test
//    void testSubtract() {
//        assertEquals(1, calculator.subtract(3, 2));
//        assertEquals(5, calculator.subtract(2, -3));
//    }
//
//    @Test
//    void testMultiply() {
//        assertEquals(12, calculator.multiply(4, 3));
//        assertEquals(0, calculator.multiply(4, 0));
//    }
//
//    @Test
//    void testDivide() {
//        assertEquals(5, calculator.divide(10, 2));
//        assertEquals(-2, calculator.divide(4, -2));
//    }
//
//    @Test
//    void testDivideByZeroThrows() {
//        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
//    }
//}