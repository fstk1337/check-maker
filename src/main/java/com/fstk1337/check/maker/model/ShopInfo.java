package com.fstk1337.check.maker.model;

public class ShopInfo {
	private String name;
	private String address;
	private String phoneNumber;
	private int cashierNumber;

	public ShopInfo(String name, String address, String phoneNumber, int cashierNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.cashierNumber = cashierNumber;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int getCashierNumber() {
		return cashierNumber;
	}
}