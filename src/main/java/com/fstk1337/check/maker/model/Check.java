package com.fstk1337.check.maker.model;

import com.fstk1337.check.maker.model.util.MoneyRounder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.math.BigDecimal;


public class Check {
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
        return shopInfo.toString() +
                "date: " + getCheckDate() + "\r\n" +
                "time: " + getCheckTime() + "\r\n" +
                checkData.toString() +
                ", tax: " + tax +
                ", TOTAL: " + total;
    }

    private CheckData createCheckData(List<CheckEntry> entries, double saleDiscountRate) {
        long countOnSaleEntries = entries.stream()
                .filter(entry -> entry.getProduct().onSale())
                .count();
        if (countOnSaleEntries > 5) {
            for (CheckEntry entry: entries) {
                if (entry.getProduct().onSale()) {
                    entry.updateDiscountAndTotal(saleDiscountRate);
                }
            }
        }
        return new CheckData(entries);
    }

    private double calculateTax() {
        BigDecimal tax = BigDecimal.valueOf(checkData.getTaxable()).multiply(BigDecimal.valueOf(taxRate));
        return MoneyRounder.round(tax);
    }

    private double calculateTotal() {
        BigDecimal total = BigDecimal.valueOf(checkData.getTaxable()).add(BigDecimal.valueOf(tax));
        return MoneyRounder.round(total);
    }
}