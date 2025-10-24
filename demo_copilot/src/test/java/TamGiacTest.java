

import com.example.demo_copilot.TamGiac;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TamGiacTest {

    private final TamGiac tamGiac = new TamGiac();

    @Test
    void validTriangles_commonCases() {
        assertTrue(tamGiac.laTamGiac(3, 3, 3));       // equilateral
        assertTrue(tamGiac.laTamGiac(3, 4, 5));       // classic scalene
        assertTrue(tamGiac.laTamGiac(2.5, 3.1, 4.0)); // floating point
        assertTrue(tamGiac.laTamGiac(1e-9, 1e-9, 1e-9)); // very small but positive
    }

    @Test
    void degenerateCases_sumEqualsThird_shouldBeFalse() {
        assertFalse(tamGiac.laTamGiac(1, 2, 3)); // 1 + 2 == 3
        assertFalse(tamGiac.laTamGiac(2, 3, 5)); // 2 + 3 == 5
        // permutations
        assertFalse(tamGiac.laTamGiac(3, 1, 2));
        assertFalse(tamGiac.laTamGiac(5, 2, 3));
    }

    @Test
    void nonTriangle_oneSideTooLong_shouldBeFalse() {
        assertFalse(tamGiac.laTamGiac(5, 1, 1));
        assertFalse(tamGiac.laTamGiac(10, 4, 5));
        // permutations
        assertFalse(tamGiac.laTamGiac(1, 5, 1));
        assertFalse(tamGiac.laTamGiac(4, 10, 5));
    }

    @Test
    void nonPositiveSides_shouldBeFalse() {
        assertFalse(tamGiac.laTamGiac(0, 1, 1));    // zero side
        assertFalse(tamGiac.laTamGiac(0, 0, 0));    // all zero
        assertFalse(tamGiac.laTamGiac(-1, 2, 2));   // negative side
        assertFalse(tamGiac.laTamGiac(2, -1, 2));
        assertFalse(tamGiac.laTamGiac(2, 2, -1));
    }

    @Test
    void orderOfSidesDoesNotAffectResult() {
        assertTrue(tamGiac.laTamGiac(4, 5, 6));
        assertTrue(tamGiac.laTamGiac(6, 4, 5));
        assertTrue(tamGiac.laTamGiac(5, 6, 4));

        assertFalse(tamGiac.laTamGiac(1, 3, 4));
        assertFalse(tamGiac.laTamGiac(4, 1, 3));
        assertFalse(tamGiac.laTamGiac(3, 4, 1));
    }

    @Test
    void floatingPointBorderlineCases() {
        // values that are numerically on the boundary (degenerate) using small magnitudes
        double a = 1e-16;
        double b = 1e-16;
        double c = 2e-16;
        assertFalse(tamGiac.laTamGiac(a, b, c)); // a + b == c (within double precision)
        // slightly larger so strict inequality holds
        assertTrue(tamGiac.laTamGiac(a, b, c - 1e-20) || tamGiac.laTamGiac(a, b, c + 1e-20));
    }

    @Test
    void nonFiniteValues_shouldBeHandled() {
        // NaN anywhere should not form a triangle
        assertFalse(tamGiac.laTamGiac(Double.NaN, 1, 1));
        assertFalse(tamGiac.laTamGiac(1, Double.NaN, 1));
        assertFalse(tamGiac.laTamGiac(1, 1, Double.NaN));

        // infinities should not be treated as valid finite triangle sides
        assertFalse(tamGiac.laTamGiac(Double.POSITIVE_INFINITY, 1, 1));
        assertFalse(tamGiac.laTamGiac(1, Double.NEGATIVE_INFINITY, 1));
        assertFalse(tamGiac.laTamGiac(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY));
    }

    @Test
    void veryLargeValues() {
//        // large but equal sides (mathematically a triangle)
//        assertTrue(tamGiac.laTamGiac(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE));

//        // two very large and one small: still should satisfy the inequalities in double arithmetic
        assertTrue(tamGiac.laTamGiac(Double.MAX_VALUE, Double.MAX_VALUE, 1.0));
    }
}