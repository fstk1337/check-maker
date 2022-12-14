package com.fstk1337.check.maker;

import com.fstk1337.check.maker.model.*;

import java.util.List;
import java.util.ArrayList;

public class CheckMaker {
    static final double TAX_RATE = 0.13d;
    static final ShopInfo SHOP_INFO = new ShopInfo("SUPERMEGASHOP 1337", "13-37 ELITE GOOSE STREET", "33-560-5433-243", 1486);
    static final List<CheckEntry> ENTRIES = new ArrayList<>();
    static final DiscountCard DISCOUNT_CARD = new DiscountCard(1L, 2335, 0.1d);

    static {
        Product product01 = new Product(1L, "apple", 1.1d, false);
        Product product02 = new Product(2L, "banana", 2.5d, false);
        Product product03 = new Product(3L, "peach", 2.74d, false);
        Product product04 = new Product(4L, "orange", 5.2d, false);
        Product product05 = new Product(5L, "cucumber", 4.22d, false);
        Product product06 = new Product(6L, "tomato", 7.33d, false);
        Product product07 = new Product(7L, "potato", 3.0d, false);
        Product product08 = new Product(8L, "pineapple", 3.8d, false);
        Product product09 = new Product(9L, "pomegranat", 1.41d, false);
        Product product10 = new Product(10L, "watermelon", 1.75d, false);
        Product product11 = new Product(11L, "lemon", 0.25d, false);
        Product product12 = new Product(12L, "bread", 4.0d, false);
        Product product13 = new Product(13L, "milk", 5.4d, false);
        Product product14 = new Product(14L, "butter", 4.7d, false);
        Product product15 = new Product(15L, "ketchup", 3.2d, false);
        Product product16 = new Product(16L, "macaroni", 2.22d, false);
        Product product17 = new Product(17L, "beer", 2.9d, false);
        Product product18 = new Product(18L, "salmon", 1.2d, false);
        Product product19 = new Product(19L, "eggs", 1.31d, false);
        Product product20 = new Product(20L, "carrot", 8.6d, false);
        Product product21 = new Product(21L, "yogurt", 6.62d, false);
        Product product22 = new Product(22L, "cookies", 10.0d, false);
        Product product23 = new Product(23L, "pie", 6.1d, false);
        Product product24 = new Product(24L, "cake", 11.2d, false);
        Product product25 = new Product(25L, "candies", 9.24d, false);
        ENTRIES.add(new CheckEntry(1, product01, 1));
        ENTRIES.add(new CheckEntry(2, product02, 4));
        ENTRIES.add(new CheckEntry(3, product03, 10));
        ENTRIES.add(new CheckEntry(4, product04, 7));
        ENTRIES.add(new CheckEntry(5, product05, 6));
        ENTRIES.add(new CheckEntry(6, product06, 4));
        ENTRIES.add(new CheckEntry(7, product07, 4));
        ENTRIES.add(new CheckEntry(8, product08, 2));
        ENTRIES.add(new CheckEntry(9, product09, 1));
        ENTRIES.add(new CheckEntry(10, product10, 3));
        ENTRIES.add(new CheckEntry(11, product11, 1));
        ENTRIES.add(new CheckEntry(12, product12, 1));
        ENTRIES.add(new CheckEntry(13, product13, 2));
        ENTRIES.add(new CheckEntry(14, product14, 2));
        ENTRIES.add(new CheckEntry(15, product15, 9));
        ENTRIES.add(new CheckEntry(16, product16, 6));
        ENTRIES.add(new CheckEntry(17, product17, 7));
        ENTRIES.add(new CheckEntry(18, product18, 7));
        ENTRIES.add(new CheckEntry(19, product19, 8));
        ENTRIES.add(new CheckEntry(20, product20, 2));
        ENTRIES.add(new CheckEntry(21, product21, 3));
        ENTRIES.add(new CheckEntry(22, product22, 8));
        ENTRIES.add(new CheckEntry(23, product23, 4));
        ENTRIES.add(new CheckEntry(24, product24, 5));
        ENTRIES.add(new CheckEntry(25, product25, 2));
    }

    public static void main(String[] args) {
        Check testCheck = new Check(SHOP_INFO, ENTRIES, TAX_RATE, DISCOUNT_CARD);
        System.out.println(testCheck);
    }
}
