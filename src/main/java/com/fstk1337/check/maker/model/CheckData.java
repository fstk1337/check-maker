package com.fstk1337.check.maker.model;

import com.fstk1337.check.maker.util.Money;
import java.util.List;


public class CheckData {
    private final List<CheckEntry> entries;

    public CheckData(List<CheckEntry> entries) {
        this.entries = entries;
    }

    public double getCost() {
        return calculateCost();
    }

    public double getDiscount() {
        return calculateDiscount();
    }

    public double getTaxable() {
        return calculateTaxable();
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

    private double calculateCost() {
        return entries.stream()
                .map(entry -> Money.of(entry.getProduct().price())
                        .multiply(entry.getQuantity()))
                .reduce(Money.of(0), Money::add)
                .getAmount();
    }

    private double calculateDiscount() {
        return entries.stream()
                .map(CheckEntry::getDiscount)
                .map(Money::of)
                .reduce(Money.of(0), Money::add)
                .getAmount();
    }

    private double calculateTaxable() {
         return entries.stream()
                 .map(CheckEntry::getTotal)
                 .map(Money::of)
                 .reduce(Money.of(0), Money::add)
                 .getAmount();
    }
}