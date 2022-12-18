package com.fstk1337.check.maker.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private BigDecimal amount;

    public Money(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    public Money(long amount) {
        this.amount = BigDecimal.valueOf(amount);
    }
    public static Money of(double amount) {
        return new Money(amount);
    }

    public static Money of(long amount) {
        return new Money(amount);
    }

    public double getAmount() {
        return MoneyRounder.round(amount);
    }

    public Money add(double addend) {
        amount = amount.add(BigDecimal.valueOf(addend));
        return this;
    }

    public Money add(Money addend) {
        amount = amount.add(BigDecimal.valueOf(addend.getAmount()));
        return this;
    }

    public Money subtract(double subtrahend) {
        amount = amount.subtract(BigDecimal.valueOf(subtrahend));
        return this;
    }

    public Money multiply(double multiplicand) {
        amount = amount.multiply(BigDecimal.valueOf(multiplicand));
        return this;
    }

    public Money multiply(int multiplicand) {
        amount = amount.multiply(BigDecimal.valueOf(multiplicand));
        return this;
    }

    public Money divide(double divisor) {
        amount = amount.divide(BigDecimal.valueOf(divisor), RoundingMode.HALF_UP);
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(amount.doubleValue());
    }
}
