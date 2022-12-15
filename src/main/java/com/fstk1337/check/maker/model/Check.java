package com.fstk1337.check.maker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;


public class Check {
	private static final MathContext MC = new MathContext(10);

	private ShopInfo shopInfo;
	private LocalDateTime issued;
	private CheckData checkData;
	private double taxRate;
	private double tax;
	private double total;


	public Check(ShopInfo shopInfo, List<CheckEntry> entries, double taxRate, double saleDiscountRate, DiscountCard discountCard) {
		this.shopInfo = shopInfo;
		this.issued = LocalDateTime.now();
		this.checkData = createCheckData(entries, discountCard, saleDiscountRate);
		this.taxRate = taxRate;
		this.tax = calculateTax();
		this.total = calculateTotal();
	}

	public ShopInfo getShopInfo() {
		return shopInfo;
	}

	public String getCheckDate() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(issued);
	}

	public String getCheckTime() {
		return DateTimeFormatter.ofPattern("HH:mm:ss").format(issued);
	}

	public CheckData getCheckData() {
		return checkData;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public double getTax() {
		return tax;
	}

	public double getTotal() {
		return total;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return shopInfo.toString() + 
				"date: " + getCheckDate() + "\r\n" +
				"time: " + getCheckTime() + "\r\n" + 
				checkData.toString() +
				", tax: " + df.format(tax) +
				", TOTAL: " + df.format(total);
	}

	private CheckData createCheckData(List<CheckEntry> entries, DiscountCard discountCard, double saleDiscountRate) {
		long countOnSaleEntries = entries.stream()
			.filter(entry -> entry.getProduct().isOnSale())
			.count();
		if (countOnSaleEntries > 5) {
			BigDecimal discountRate = new BigDecimal(saleDiscountRate);
			for (CheckEntry entry: entries) {
				if (entry.getProduct().isOnSale()) {
					BigDecimal total = new BigDecimal(entry.getTotal());
					BigDecimal oldDiscount = new BigDecimal(entry.getDiscount());
					BigDecimal newDiscount = total.add(oldDiscount).multiply(discountRate, MC).add(oldDiscount, MC);
					entry.updateDiscountAndTotal(newDiscount.doubleValue());
				}
			}
		}
		return new CheckData(entries, discountCard);
	}

	private double calculateTax() {
		BigDecimal taxable = new BigDecimal(checkData.getTotal());
		double tax = taxable.multiply(new BigDecimal(taxRate), MC).doubleValue();
		return (double)Math.round(tax * 100) / 100;
	}

	private double calculateTotal() {
		BigDecimal taxable = new BigDecimal(checkData.getTotal());
		double result = taxable.add(new BigDecimal(tax), MC).doubleValue();
		return (double)Math.round(result * 100) / 100;
	}
}