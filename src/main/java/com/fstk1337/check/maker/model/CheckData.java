package com.fstk1337.check.maker.model;

import com.fstk1337.check.maker.model.util.MoneyRounder;
import java.math.BigDecimal;
import java.util.List;


public class CheckData {
    private final List<CheckEntry> entries;

    public CheckData(List<CheckEntry> entries) {
        this.entries = entries;
    }

    public double getCost() {
        return MoneyRounder.round(calculateCost());
    }

    public double getDiscount() {
        return MoneyRounder.round(calculateDiscount());
    }

    public double getTaxable() {
        return MoneyRounder.round(calculateTaxable());
    }

    @Override
    public String toString() {
        String info = entries.stream()
                .map(CheckEntry::toString)
                .reduce("", (result, next) -> result + next + "\r\n");
        return 	info +
                "cost: " + getCost() +
                ", discount: " + getDiscount() +
                ", taxable: " + getTaxable();
    }

    private BigDecimal calculateCost() {
        return entries.stream()
                .map(entry -> BigDecimal.valueOf(entry.getProduct().price())
                        .multiply(BigDecimal.valueOf(entry.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateDiscount() {
        return entries.stream()
                .map(CheckEntry::getDiscount)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTaxable() {
        return entries.stream()
                .map(CheckEntry::getTotal)
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}