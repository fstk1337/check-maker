package com.fstk1337.check.maker.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class CheckEntry {
	private int lineNumber;
	private Product product;
	private int quantity;
	private BigDecimal total;

	public CheckEntry(int lineNumber, Product product, int quantity) {
		this.lineNumber = lineNumber;
		this.product = product;
		this.quantity = quantity;
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

	public BigDecimal getTotal() {
		return total;
	}

	private BigDecimal calculateTotal() {
		MathContext mc = new MathContext(2);
		BigDecimal price = new BigDecimal(this.product.getPrice(), mc);
		BigDecimal quantity = new BigDecimal(this.quantity); 
		return quantity.multiply(price, mc);
	}
}