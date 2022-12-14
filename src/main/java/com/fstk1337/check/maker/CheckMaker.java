package com.fstk1337.check.maker;

import com.fstk1337.check.maker.model.*;

import java.util.List;
import java.util.ArrayList;

public class CheckMaker {
    static final ShopInfo SHOP_INFO = new ShopInfo("SUPERMEGASHOP 1337", "13-37 ELITE GOOSE STREET", "33-560-5433-243", 1486);
    static final double TAX_RATE = 0.13d;
    static final List<Product> PRODUCTS = new ArrayList<>();
    static final List<DiscountCard> DISCOUNT_CARDS = new ArrayList<>();

    static {
        PRODUCTS.add(new Product(1L, "apple", 1.1d, false));
        PRODUCTS.add(new Product(2L, "banana", 2.5d, false));
        PRODUCTS.add(new Product(3L, "peach", 2.74d, false));
        PRODUCTS.add(new Product(4L, "orange", 5.2d, false));
        PRODUCTS.add(new Product(5L, "cucumber", 4.22d, false));
        PRODUCTS.add(new Product(6L, "tomato", 7.33d, false));
        PRODUCTS.add(new Product(7L, "potato", 3.0d, false));
        PRODUCTS.add(new Product(8L, "pineapple", 3.8d, false));
        PRODUCTS.add(new Product(9L, "pomegranat", 1.41d, false));
        PRODUCTS.add(new Product(10L, "watermelon", 1.75d, false));
        PRODUCTS.add(new Product(11L, "lemon", 0.25d, false));
        PRODUCTS.add(new Product(12L, "bread", 4.0d, false));
        PRODUCTS.add(new Product(13L, "milk", 5.4d, false));
        PRODUCTS.add(new Product(14L, "butter", 4.7d, false));
        PRODUCTS.add(new Product(15L, "ketchup", 3.2d, false));
        PRODUCTS.add(new Product(16L, "macaroni", 2.22d, false));
        PRODUCTS.add(new Product(17L, "beer", 2.9d, false));
        PRODUCTS.add(new Product(18L, "salmon", 1.2d, false));
        PRODUCTS.add(new Product(19L, "eggs", 1.31d, false));
        PRODUCTS.add(new Product(20L, "carrot", 8.6d, false));
        PRODUCTS.add(new Product(21L, "yogurt", 6.62d, false));
        PRODUCTS.add(new Product(22L, "cookies", 10.0d, false));
        PRODUCTS.add(new Product(23L, "pie", 6.1d, false));
        PRODUCTS.add(new Product(24L, "cake", 11.2d, false));
        PRODUCTS.add(new Product(25L, "candies", 9.24d, false));
        DISCOUNT_CARDS.add(new DiscountCard(1L, 2335, 0.01d));
        DISCOUNT_CARDS.add(new DiscountCard(2L, 1337, 0.1d));
        DISCOUNT_CARDS.add(new DiscountCard(3L, 2471, 0.12d));
        DISCOUNT_CARDS.add(new DiscountCard(4L, 7544, 0.05d));
        DISCOUNT_CARDS.add(new DiscountCard(5L, 3971, 0.33d));
        DISCOUNT_CARDS.add(new DiscountCard(6L, 1124, 0.2d));
        DISCOUNT_CARDS.add(new DiscountCard(7L, 9143, 0.03d));
    }

    public static void main(String ...args) {
        List<CheckEntry> entries = new ArrayList<>();
        for (int i = 0; i < args.length - 1; i++) {
            long productId = Long.parseLong(args[i].split("-")[0]);
            int quantity = Integer.parseInt(args[i].split("-")[1]);
            Product product = PRODUCTS.stream()
                .filter(el -> el.getId() == productId)
                .findFirst()
                .orElse(null);
            entries.add(new CheckEntry((i + 1), product, quantity));
        }

        int discountCardNumber = Integer.parseInt(args[args.length - 1].split("-")[1]);
        DiscountCard discountCard = DISCOUNT_CARDS.stream()
            .filter(card -> card.getNumber() == discountCardNumber)
            .findFirst()
            .orElse(null);
        
        Check testCheck = new Check(SHOP_INFO, entries, TAX_RATE, discountCard);
        System.out.println(testCheck);
    }
}
