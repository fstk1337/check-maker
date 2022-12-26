package com.fstk1337.check.maker;

import java.util.Arrays;

public class Arguments {

    private final String[] productData;
    private final String cardInfo;

    public Arguments(String[] checkMakerArgs) {
        productData = parseProductData(checkMakerArgs);
        cardInfo = parseCardInfo(checkMakerArgs);
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
