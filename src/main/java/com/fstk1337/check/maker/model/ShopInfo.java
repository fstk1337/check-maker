package com.fstk1337.check.maker.model;

public record ShopInfo(String name, String address, String phoneNumber, int cashierNumber) {
    @Override
    public String toString() {
        return "Shop: " + name + "\r\naddress: " + address + "\r\ntel.: " + phoneNumber + "\r\ncashier: No." + cashierNumber + "\r\n";
    }
}