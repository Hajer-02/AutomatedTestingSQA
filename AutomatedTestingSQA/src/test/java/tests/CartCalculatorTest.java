package tests;

import com.sqa.project.CartCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartCalculatorTest {

    @Test
    public void testCalculateTotal() {
        CartCalculator calc = new CartCalculator();
        double[] prices = {10.0, 20.0, 30.0};
        assertEquals(60.0, calc.calculateTotal(prices), 0.001);
    }

    @Test
    public void testApplyDiscount() {
        CartCalculator calc = new CartCalculator();
        double total = 100.0;
        double discounted = calc.applyDiscount(total, 10);
        assertEquals(90.0, discounted, 0.001);
    }

    @Test
    public void testCalculateWithTax() {
        CartCalculator calc = new CartCalculator();
        double total = 100.0;
        double withTax = calc.calculateWithTax(total, 5);
        assertEquals(105.0, withTax, 0.001);
    }

    @Test
    public void testInvalidDiscount() {
        CartCalculator calc = new CartCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.applyDiscount(100, 150));
    }

    @Test
    public void testNegativeTax() {
        CartCalculator calc = new CartCalculator();
        assertThrows(IllegalArgumentException.class, () -> calc.calculateWithTax(100, -5));
    }
}
