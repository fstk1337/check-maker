package com.fstk1337.check.maker.model;

import com.fstk1337.check.maker.util.money.Money;

public class CheckEntry {
    private final Product product;
    private final int quantity;
    private double discount;
    private double total;

    public CheckEntry(Product product, int quantity, double discountRate) {
        this.product = product;
        this.quantity = quantity;
        this.discount = calculateDiscount(discountRate);
        this.total = calculateTotal();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return calculateCost();
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotal() {
        return total;
    }

    public void updateDiscountAndTotal(double discountRate) {
        this.discount = calculateDiscount(discountRate);
        this.total = calculateTotal();
    }

    private double calculateCost() {
        return Money.of(product.price())
                .multiply(quantity)
                .getAmount();
    }

    private double calculateDiscount(double discountRate) {
        return Money.of(product.price())
                .multiply(quantity)
                .multiply(discountRate)
                .getAmount();
    }

    private double calculateTotal() {
        return Money.of(product.price())
                .multiply(quantity)
                .subtract(discount)
                .getAmount();
    }
}