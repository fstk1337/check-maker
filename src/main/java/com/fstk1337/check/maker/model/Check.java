package com.fstk1337.check.maker.model;

import com.fstk1337.check.maker.util.money.Money;

import java.time.LocalDateTime;
import java.util.List;


public class Check {
    private static final long ENTRIES_TO_APPLY_SALE_DISCOUNT = 5L;
    private final ShopInfo shopInfo;
    private final LocalDateTime issued;
    private final CheckData checkData;
    private final double taxRate;
    private final double tax;
    private final double total;

    public Check(ShopInfo shopInfo, List<CheckEntry> entries, double taxRate, double saleDiscountRate) {
        this.shopInfo = shopInfo;
        this.issued = LocalDateTime.now();
        this.checkData = createCheckData(entries, saleDiscountRate);
        this.taxRate = taxRate;
        this.tax = calculateTax();
        this.total = calculateTotal();
    }

    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public LocalDateTime getIssued() {
        return issued;
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

    private CheckData createCheckData(List<CheckEntry> entries, double saleDiscountRate) {
        long onSaleEntriesCount = entries.stream()
                .filter(entry -> entry.getProduct().onSale())
                .count();
        if (onSaleEntriesCount > ENTRIES_TO_APPLY_SALE_DISCOUNT) {
            updateEntries(entries, saleDiscountRate);
        }
        return new CheckData(entries);
    }

    private void updateEntries(List<CheckEntry> entries, double saleDiscountRate) {
        for (CheckEntry entry: entries) {
            if (entry.getProduct().onSale()) {
                entry.updateDiscountAndTotal(saleDiscountRate);
            }
        }
    }

    private double calculateTax() {
        return Money.of(checkData.getTaxable())
                .multiply(taxRate)
                .getAmount();
    }

    private double calculateTotal() {
        return Money.of(checkData.getTaxable())
                .add(tax)
                .getAmount();
    }
}