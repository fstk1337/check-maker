package com.fstk1337.check.maker.model;

public class DiscountCard {
	private Long id;
	private int number;
	private double discountRate;

	public DiscountCard(Long id, int number, double discountRate) {
		this.id = id;
		this.number = number;
		this.discountRate = discountRate;
	}

	public Long getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}

	public double getDiscountRate() {
		return discountRate;
	}
}