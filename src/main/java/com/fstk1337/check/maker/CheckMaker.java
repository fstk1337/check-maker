package com.fstk1337.check.maker;

import com.fstk1337.check.maker.model.*;
import com.fstk1337.check.maker.util.check.CheckCreator;
import com.fstk1337.check.maker.util.check.CheckPrinter;
import com.fstk1337.check.maker.util.reader.InitialDataCSVReader;

import java.util.List;

public class CheckMaker {
    static final ShopInfo SHOP_INFO = new ShopInfo("SUPERMASSIVE SHOP 1337", "13-37 ELITE GOOSE STREET", "33-560-5433-243", 1486);
    static final double TAX_RATE = 0.20d;
    static final double SALE_DISCOUNT_RATE = 0.10d;
    static final String INIT_PRODUCTS_FILENAME = "init-products.csv";
    static final String INIT_CARDS_FILENAME = "init-cards.csv";

    public static void main(String[] args) {
        List<Product> products = InitialDataCSVReader.readProducts(INIT_PRODUCTS_FILENAME);
        List<DiscountCard> discountCards = InitialDataCSVReader.readCards(INIT_CARDS_FILENAME);
        Arguments arguments = Arguments.of(args);
        CheckOptions options = new CheckOptions(SHOP_INFO, TAX_RATE, SALE_DISCOUNT_RATE);
        Check newCheck = new CheckCreator(products, discountCards).createCheck(arguments, options);
        CheckPrinter.of(newCheck).printToConsole();
        CheckPrinter.of(newCheck).printToFile("check.txt");
    }
}
