package com.fstk1337.check.maker.model;

import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;
import java.math.MathContext;


public class Check {
	private static final MathContext MC = new MathContext(2);

	private ShopInfo shop;
	private LocalDateTime issued;
	private List<CheckEntry> entries;
	private BigDecimal subtotal;
	private DiscountCard discountCard;
	private BigDecimal discount;
	private BigDecimal taxable;
	private double taxRate;
	private BigDecimal tax;
	private BigDecimal total;


	public Check(ShopInfo shop, List<CheckEntry> entries, double taxRate, DiscountCard discountCard) {
		this.shop = shop;
		this.issued = LocalDateTime.now();
		this.entries = entries;
		this.subtotal = calculateSubtotal();
		this.discountCard = discountCard;
		this.discount = calculateDiscount();
		this.taxable = calculateTaxable();
		this.taxRate = taxRate;
		this.tax = calculateTax();
		this.total = calculateTotal();
	}

	public Check(ShopInfo shop, List<CheckEntry> entries, double taxRate) {
		this.shop = shop;
		this.issued = LocalDateTime.now();
		this.entries = entries;
		this.subtotal = calculateSubtotal();
		this.discountCard = null;
		this.discount = calculateDiscount();
		this.taxable = calculateTaxable();
		this.taxRate = taxRate;
		this.tax = calculateTax();
		this.total = calculateTotal();
	}

	public ShopInfo getShop() {
		return shop;
	}

	public LocalDateTime getIssued() {
		return issued;
	}

	public List<CheckEntry> getEntries() {
		return entries;
	}

	public double getSubtotal() {
		return subtotal.doubleValue();
	}

	public double getDiscount() {
		return discount.doubleValue();
	}

	public double getTaxable() {
		return taxable.doubleValue();
	}

	public double getTaxRate() {
		return taxRate;
	}

	public double getTax() {
		return tax.doubleValue();
	}

	public double getTotal() {
		return total.doubleValue();
	}

	private BigDecimal calculateSubtotal() {
		BigDecimal subtotal = new BigDecimal(0);
		entries.forEach((entry) -> {
			subtotal.add(entry.getTotal(), MC);
		});
		return subtotal;
	}

	private BigDecimal calculateDiscount() {
		BigDecimal saleDiscountRate = calculateSaleDiscountRate();
		if (discountCard == null) return saleDiscountRate;
		BigDecimal discountRate = new BigDecimal(discountCard.getDiscountRate());
		return subtotal.multiply(discountRate.add(saleDiscountRate), MC);
	}

	private BigDecimal calculateSaleDiscountRate() {
		// TODO: insert sale logics
		return new BigDecimal(0);
	}

	private BigDecimal calculateTaxable() {
		return subtotal.subtract(discount, MC);
	}

	private BigDecimal calculateTax() {
		return taxable.multiply(new BigDecimal(taxRate), MC);
	}

	private BigDecimal calculateTotal() {
		return taxable.add(tax, MC);
	}
}