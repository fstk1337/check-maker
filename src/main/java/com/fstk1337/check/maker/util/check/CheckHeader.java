package com.fstk1337.check.maker.util.check;

import com.fstk1337.check.maker.model.ShopInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckHeader {
    private static final String HEADING = "CASH RECEIPT";
    private final String heading;
    private final String shopName;
    private final String shopAddress;
    private final String shopPhoneNumber;
    private final String cashierInfo;
    private final String checkDate;
    private final String checkTime;

    public CheckHeader(ShopInfo shopInfo, LocalDateTime checkDateTime) {
        this.heading = HEADING;
        this.shopName = shopInfo.name();
        this.shopAddress = shopInfo.address();
        this.shopPhoneNumber = formatPhoneNumber(shopInfo.phoneNumber());
        this.cashierInfo = formatCashierInfo(shopInfo.cashierNumber());
        this.checkDate = parseCheckDate(checkDateTime);
        this.checkTime = parseCheckTime(checkDateTime);
    }

    public String getHeading() {
        return heading;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public String getShopPhoneNumber() {
        return shopPhoneNumber;
    }

    public String getCashierInfo() {
        return cashierInfo;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public String getCheckTime() {
        return checkTime;
    }

    private String formatPhoneNumber(String phoneNumber) {
        return "Tel.: " + phoneNumber;
    }

    private String formatCashierInfo(int cashierNumber) {
        return "CASHIER: No." + cashierNumber;
    }

    private String parseCheckDate(LocalDateTime checkDateTime) {
        return "DATE: " + DateTimeFormatter.ofPattern("dd/MM/yyyy").format(checkDateTime);
    }

    private String parseCheckTime(LocalDateTime checkDateTime) {
        return "TIME: " + DateTimeFormatter.ofPattern("HH:mm:ss").format(checkDateTime);
    }
}
