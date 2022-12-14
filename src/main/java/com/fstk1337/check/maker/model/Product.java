package com.fstk1337.check.maker.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Product {
	private long id;
	private String name;
	private double price;
	private boolean onSale;

	public Product(long id, String name, double price, boolean onSale) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.onSale = onSale;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public boolean isOnSale() {
		return onSale;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return name + ", price: " + df.format(price) + ", onSale: " + onSale;
	}
}