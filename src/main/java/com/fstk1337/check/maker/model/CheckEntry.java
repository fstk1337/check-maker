package com.fstk1337.check.maker.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class CheckEntry {
	private static final double SALE_DISCOUNT = 0.1d;

	private int lineNumber;
	private Product product;
	private int quantity;
	private double discount;
	private double total;

	public CheckEntry(int lineNumber, Product product, int quantity) {
		this.lineNumber = lineNumber;
		this.product = product;
		this.quantity = quantity;
		this.discount = 0.0d;
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

	public double getDiscount() {
		return discount;
	}

	public void updateDiscountAndTotal(double discount) {
		this.discount = discount;
		this.total = calculateTotal();
	}

	public double getTotal() {
		return total;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return "[" + lineNumber + "]: " + product.toString() + ", quantity: " + quantity + ", discount: " + df.format(discount) + ", TOTAL: " + df.format(total);
	}

	private double calculateTotal() {
		MathContext mc = new MathContext(10);
		BigDecimal price = new BigDecimal(product.getPrice(), mc);
		double result = new BigDecimal(quantity).multiply(price, mc).subtract(new BigDecimal(discount), mc).doubleValue();
		return (double)Math.round(result * 100) / 100;
	}
}