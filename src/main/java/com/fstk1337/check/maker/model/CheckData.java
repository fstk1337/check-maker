package com.fstk1337.check.maker.model;

import java.math.MathContext;
import java.math.BigDecimal;
import java.util.List;
import java.text.DecimalFormat;


public class CheckData {
	private static final MathContext MC = new MathContext(10);

	private List<CheckEntry> entries;
	private DiscountCard discountCard;

	public CheckData(List<CheckEntry> entries, DiscountCard discountCard) {
		this.entries = entries;
		this.discountCard = discountCard;
	}

	public double getTotal() {
		double total = calculateSubtotal().subtract(calculateDiscount(), MC).doubleValue();
		return (double)Math.round(total * 100) / 100;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		String info = entries.stream()
			.map(CheckEntry::toString)
			.reduce("", (result, next) -> {
				return result + next + "\r\n";
			});
		return 	info + 
				"subtotal: " + df.format(calculateSubtotal()) +
				", discount: " + df.format(calculateDiscount()) +
				", taxable: " + df.format(getTotal());
	}

	private BigDecimal calculateSubtotal() {
		return entries.stream()
			.map(CheckEntry::getTotal)
			.map(BigDecimal::new)
			.reduce(new BigDecimal(0), (total, next) -> {
				return total.add(next, MC);
			});
	}

	private BigDecimal calculateDiscount() {
		if (discountCard == null) return new BigDecimal(0);
		BigDecimal discountRate = new BigDecimal(discountCard.getDiscountRate());
		return calculateSubtotal().multiply(discountRate, MC);
	}
}