package com.fstk1337.check.maker.util.check;

import com.fstk1337.check.maker.Arguments;
import com.fstk1337.check.maker.model.*;

import java.util.ArrayList;
import java.util.List;

public class CheckCreator {
    private final List<Product> products;
    private final List<DiscountCard> discountCards;

    private CheckCreator(List<Product> products, List<DiscountCard> discountCards) {
        this.products = products;
        this.discountCards = discountCards;
    }

    public static CheckCreator of(List<Product> products, List<DiscountCard> discountCards) {
        return new CheckCreator(products, discountCards);
    }

    public Check createCheck(Arguments arguments, CheckOptions options) {
        String[] productData = arguments.getProductData();
        String cardInfo = arguments.getCardInfo();
        double discountRate = getDiscountRate(cardInfo);
        List<CheckEntry> entries = createEntries(productData, discountRate);
        return new Check(options, entries);
    }

    private double getDiscountRate(String cardInfo) {
        double discountRate = 0.0d;
        DiscountCard discountCard = getDiscountCard(cardInfo);
        if (discountCard != null) {
            discountRate = discountCard.discountRate();
        }
        return discountRate;
    }

    private DiscountCard getDiscountCard(String cardInfo) {
        if (cardInfo == null) return null;
        int cardNumber = parseCardNumber(cardInfo);
        return findDiscountCardByNumber(cardNumber);
    }

    private static int parseCardNumber(String cardInfo) {
        return Integer.parseInt(cardInfo.split("-")[1]);
    }

    private DiscountCard findDiscountCardByNumber(int cardNumber) {
        return discountCards.stream()
                .filter(card -> card.number() == cardNumber)
                .findFirst()
                .orElse(null);
    }

    private List<CheckEntry> createEntries(String[] productData, double discountRate) {
        List<CheckEntry> entries = new ArrayList<>();
        for (String productInfo: productData) {
            long productId = parseProductId(productInfo);
            int quantity = parseProductQuantity(productInfo);
            Product product = findProductById(productId);
            entries.add(new CheckEntry(product, quantity, discountRate));
        }
        return entries;
    }

    private static long parseProductId(String productInfo) {
        return Long.parseLong(productInfo.split("-")[0]);
    }

    private static int parseProductQuantity(String productInfo) {
        return Integer.parseInt(productInfo.split("-")[1]);
    }

    private Product findProductById(long productId) {
        return products.stream()
                .filter(el -> el.id() == productId)
                .findFirst()
                .orElse(null);
    }
}
