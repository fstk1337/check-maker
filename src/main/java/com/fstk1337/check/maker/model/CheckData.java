package com.fstk1337.check.maker.model;

import com.fstk1337.check.maker.util.money.Money;
import java.util.List;


public record CheckData(List<CheckEntry> entries) {

    public double getCost() {
        return calculateCost();
    }

    public double getDiscount() {
        return calculateDiscount();
    }

    public double getTaxable() {
        return calculateTaxable();
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