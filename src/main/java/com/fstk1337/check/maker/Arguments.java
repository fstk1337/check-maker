package com.fstk1337.check.maker;

import com.fstk1337.check.maker.util.reader.InputDataFileReader;

public class Arguments {
    private final String[] productData;
    private final String cardInfo;

    private Arguments(String[] productData, String cardInfo) {
        this.productData = productData;
        this.cardInfo = cardInfo;
    }

    public static Arguments of(String[] checkMakerArgs) {
        InputDataFileReader reader = InputDataFileReader.read(checkMakerArgs[0]);
        return new Arguments(reader.getProductData(), reader.getCardInfo());
    }

    public String[] getProductData() {
        return productData;
    }

    public String getCardInfo() {
        return cardInfo;
    }
}
