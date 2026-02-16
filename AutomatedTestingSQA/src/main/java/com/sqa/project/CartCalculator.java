package com.sqa.project;

public class CartCalculator {

    // Adds product prices together
    public double calculateTotal(double[] prices) {
        double total = 0;
        for (double price : prices) {
            total += price;
        }
        return total;
    }

    // Applies a discount percentage (e.g., 10% off)
    public double applyDiscount(double total, double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        }
        return total - (total * discountPercent / 100);
    }

    // Calculates tax (e.g., 5% VAT)
    public double calculateWithTax(double total, double taxRate) {
        if (taxRate < 0) {
            throw new IllegalArgumentException("Tax rate cannot be negative");
        }
        return total + (total * taxRate / 100);
    }
}
