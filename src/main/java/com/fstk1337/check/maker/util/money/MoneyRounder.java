package com.fstk1337.check.maker.util.money;

import org.decimal4j.util.DoubleRounder;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyRounder {
    private static final RoundingMode roundingMode = RoundingMode.HALF_UP;
    private static final DoubleRounder rounder = new DoubleRounder(2);

    public static double round(BigDecimal bigDecimal) {
        return rounder.round(bigDecimal.doubleValue(), roundingMode);
    }
}
