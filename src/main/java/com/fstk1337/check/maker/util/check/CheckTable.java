package com.fstk1337.check.maker.util.check;

import com.fstk1337.check.maker.model.CheckEntry;

import java.util.List;
import java.util.stream.Collectors;

public class CheckTable {
    private static final String[] HEADERS = {"QTY", "DESCRIPTION", "PRICE", "DSCNT", "TOTAL"};
    private final List<String[]> lines;

    public CheckTable(List<CheckEntry> entries) {
        this.lines = parseCheckEntriesToLines(entries);
    }

    public String[] getHeaders() {
        return HEADERS;
    }

    public List<String[]> getLines() {
        return lines;
    }

    private List<String[]> parseCheckEntriesToLines(List<CheckEntry> entries) {
        return entries.stream()
                .map(this::parseCheckEntryToLine)
                .collect(Collectors.toList());
    }

    private String[] parseCheckEntryToLine(CheckEntry entry) {
        String quantity = String.valueOf(entry.getQuantity());
        String description = entry.getProduct().name();
        String price = String.format("$%.2f", entry.getProduct().price());
        String discount = String.format("$%.2f", entry.getDiscount());
        String total = String.format("$%.2f", entry.getTotal());
        return new String[]{quantity, description, price, discount, total};
    }
}
