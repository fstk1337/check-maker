package com.fstk1337.check.maker.model;

public record Product(long id, String name, double price, boolean onSale) {
    @Override
    public String toString() {
        return name + ", price: " + price + ", onSale: " + onSale;
    }
}