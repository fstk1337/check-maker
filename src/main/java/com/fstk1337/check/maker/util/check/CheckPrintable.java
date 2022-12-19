package com.fstk1337.check.maker.util.check;

import com.fstk1337.check.maker.model.Check;

public class CheckPrintable {
    private final CheckHeader header;
    private final CheckTable table;
    private final CheckTotals totals;

    public CheckPrintable(Check check) {
        this.header = new CheckHeader(check.getShopInfo(), check.getIssued());
        this.table = new CheckTable(check.getCheckData().entries());
        this.totals = new CheckTotals(check.getCheckData().getTaxable(), check.getTaxRate(), check.getTax(), check.getTotal());
    }

    public CheckHeader getHeader() {
        return header;
    }

    public CheckTable getTable() {
        return table;
    }

    public CheckTotals getTotals() {
        return totals;
    }
}
