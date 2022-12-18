package com.fstk1337.check.maker;

import com.fstk1337.check.maker.model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckMaker {
    static final ShopInfo SHOP_INFO = new ShopInfo("SUPERMASSIVE SHOP 1337", "13-37 ELITE GOOSE STREET", "33-560-5433-243", 1486);
    static final double TAX_RATE = 0.20d;
    static final double SALE_DISCOUNT_RATE = 0.10d;
    static final List<Product> PRODUCTS = new ArrayList<>();
    static final List<DiscountCard> DISCOUNT_CARDS = new ArrayList<>();

    static {
        PRODUCTS.add(new Product(1L, "apple", 1.1d, true));
        PRODUCTS.add(new Product(2L, "banana", 2.5d, false));
        PRODUCTS.add(new Product(3L, "peach", 2.74d, false));
        PRODUCTS.add(new Product(4L, "orange", 5.2d, false));
        PRODUCTS.add(new Product(5L, "cucumber", 4.22d, false));
        PRODUCTS.add(new Product(6L, "tomato", 7.33d, false));
        PRODUCTS.add(new Product(7L, "potato", 3.0d, false));
        PRODUCTS.add(new Product(8L, "pineapple", 3.8d, false));
        PRODUCTS.add(new Product(9L, "pomegranate", 1.41d, false));
        PRODUCTS.add(new Product(10L, "watermelon", 1.75d, false));
        PRODUCTS.add(new Product(11L, "lemon", 0.25d, false));
        PRODUCTS.add(new Product(12L, "bread", 4.0d, true));
        PRODUCTS.add(new Product(13L, "milk", 5.4d, false));
        PRODUCTS.add(new Product(14L, "butter", 4.7d, false));
        PRODUCTS.add(new Product(15L, "ketchup", 3.2d, false));
        PRODUCTS.add(new Product(16L, "macaroni", 2.22d, true));
        PRODUCTS.add(new Product(17L, "beer", 2.9d, false));
        PRODUCTS.add(new Product(18L, "salmon", 1.2d, false));
        PRODUCTS.add(new Product(19L, "eggs", 1.31d, true));
        PRODUCTS.add(new Product(20L, "carrot", 8.6d, false));
        PRODUCTS.add(new Product(21L, "yogurt", 6.62d, true));
        PRODUCTS.add(new Product(22L, "cookies", 10.0d, false));
        PRODUCTS.add(new Product(23L, "pie", 6.1d, false));
        PRODUCTS.add(new Product(24L, "cake", 11.2d, false));
        PRODUCTS.add(new Product(25L, "candies", 9.24d, true));
        DISCOUNT_CARDS.add(new DiscountCard(1L, 2335, 0.02d));
        DISCOUNT_CARDS.add(new DiscountCard(2L, 1337, 0.05d));
        DISCOUNT_CARDS.add(new DiscountCard(3L, 2471, 0.04d));
        DISCOUNT_CARDS.add(new DiscountCard(4L, 7544, 0.01d));
        DISCOUNT_CARDS.add(new DiscountCard(5L, 3971, 0.03d));
        DISCOUNT_CARDS.add(new DiscountCard(6L, 1124, 0.08d));
        DISCOUNT_CARDS.add(new DiscountCard(7L, 9143, 0.07d));
    }

    public static void main(String[] args) {
        Arguments arguments = Arguments.of(args);
        Check newCheck = createCheck(arguments.getProductData(), arguments.getCardInfo());
        System.out.println(newCheck);
    }

    private static Check createCheck(String[] productData, String cardInfo) {
        double discountRate = getDiscountRate(cardInfo);
        List<CheckEntry> entries = createEntries(productData, discountRate);
        return new Check(SHOP_INFO, entries, TAX_RATE, SALE_DISCOUNT_RATE);
    }

    private static double getDiscountRate(String cardInfo) {
        double discountRate = 0.0d;
        DiscountCard discountCard = getDiscountCard(cardInfo);
        if (discountCard != null) {
            discountRate = discountCard.discountRate();
        }
        return discountRate;
    }

    private static DiscountCard getDiscountCard(String cardInfo) {
        if (cardInfo == null) return null;
        int cardNumber = parseCardNumber(cardInfo);
        return findDiscountCardByNumber(cardNumber);
    }

    private static int parseCardNumber(String cardInfo) {
        return Integer.parseInt(cardInfo.split("-")[1]);
    }

    private static DiscountCard findDiscountCardByNumber(int cardNumber) {
        return DISCOUNT_CARDS.stream()
                .filter(card -> card.number() == cardNumber)
                .findFirst()
                .orElse(null);
    }

    private static List<CheckEntry> createEntries(String[] productData, double discountRate) {
        List<CheckEntry> entries = new ArrayList<>();
        for (String productInfo: productData) {
            long productId = parseProductId(productInfo);
            int quantity = parseProductQuantity(productInfo);
            Product product = findProductById(productId);
            entries.add(new CheckEntry((entries.size() + 1), product, quantity, discountRate));
        }
        return entries;
    }

    private static long parseProductId(String productInfo) {
        return Long.parseLong(productInfo.split("-")[0]);
    }

    private static int parseProductQuantity(String productInfo) {
        return Integer.parseInt(productInfo.split("-")[1]);
    }

    private static Product findProductById(long productId) {
        return PRODUCTS.stream()
                .filter(el -> el.id() == productId)
                .findFirst()
                .orElse(null);
    }

    static class Arguments {
        private final String[] productData;
        private final String cardInfo;

        private Arguments(String[] checkMakerArgs) {
            productData = parseProductData(checkMakerArgs);
            cardInfo = parseCardInfo(checkMakerArgs);
        }

        public static Arguments of(String[] checkMakerArgs) {
            return new Arguments(checkMakerArgs);
        }

        public String[] getProductData() {
            return productData;
        }

        public String getCardInfo() {
            return cardInfo;
        }

        private String[] parseProductData(String[] args) {
            if (args[args.length - 1].startsWith("card-")) {
                return Arrays.copyOfRange(args, 0, args.length - 1);
            }
            return args;
        }

        private String parseCardInfo(String[] args) {
            if (args[args.length - 1].startsWith("card-")) {
                return args[args.length - 1];
            }
            return null;
        }
    }
}
