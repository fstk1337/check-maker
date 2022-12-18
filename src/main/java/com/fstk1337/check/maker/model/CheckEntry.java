package com.fstk1337.check.maker.model;

import com.fstk1337.check.maker.model.util.MoneyRounder;
import java.math.BigDecimal;

public class CheckEntry {
    private final int lineNumber;
    private final Product product;
    private final int quantity;
    private double discount;
    private double total;

    public CheckEntry(int lineNumber, Product product, int quantity, double discountRate) {
        this.lineNumber = lineNumber;
        this.product = product;
        this.quantity = quantity;
        this.discount = calculateDiscount(discountRate);
        this.total = calculateTotal();
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return MoneyRounder.round(calculateCost());
    }

    public double getDiscount() {
        return discount;
    }

    public void updateDiscountAndTotal(double discountRate) {
        this.discount = calculateDiscount(discountRate);
        this.total = calculateTotal();
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "[" + lineNumber + "]: " + product.toString() + ", quantity: " + quantity + ", cost: " + getCost() + ", discount: " + discount + ", TOTAL: " + total;
    }

    private double calculateCost() {
        return BigDecimal.valueOf(product.price())
                .multiply(BigDecimal.valueOf(quantity))
                .doubleValue();
    }

    private double calculateDiscount(double discountRate) {
        BigDecimal discount =  BigDecimal.valueOf(product.price())
                .multiply(BigDecimal.valueOf(quantity))
                .multiply(BigDecimal.valueOf(discountRate));
        return MoneyRounder.round(discount);
    }

    private double calculateTotal() {
        BigDecimal total = BigDecimal.valueOf(product.price())
                .multiply(BigDecimal.valueOf(quantity))
                .subtract(BigDecimal.valueOf(discount));
        return MoneyRounder.round(total);
    }
}