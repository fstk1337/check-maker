package com.fstk1337.check.maker.util.check;

public class CheckTotals {
    private static final String TAXABLE_HEADING = "TAXABLE";
    private static final String TOTAL_HEADING = "TOTAL";
    private final String taxable;
    private final String taxHeading;
    private final String tax;
    private final String total;

    public CheckTotals(double taxable, double taxRate, double tax, double total) {
        this.taxable = formatMoney(taxable);
        this.taxHeading = formatTaxHeading(taxRate);
        this.tax = formatMoney(tax);
        this.total = formatMoney(total);
    }

    public String getTaxableHeading() {
        return TAXABLE_HEADING;
    }

    public String getTotalHeading() {
        return TOTAL_HEADING;
    }

    public String getTaxable() {
        return taxable;
    }

    public String getTaxHeading() {
        return taxHeading;
    }

    public String getTax() {
        return tax;
    }

    public String getTotal() {
        return total;
    }

    private String formatMoney(double value) {
        return String.format("$%.2f", value);
    }

    private String formatTaxHeading(double taxRate) {
        return String.format("VAT %d%%", Math.round(taxRate * 100));
    }
}
