package com.fstk1337.check.maker.util.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class InputDataFileReader {
    private final String inputString;

    private InputDataFileReader(String inputString) {
        this.inputString = inputString;
    }

    public static InputDataFileReader read(String fileName) {
        String inputString;
        File file = new File(Objects.requireNonNull(InputDataFileReader.class.getClassLoader().getResource(fileName)).getFile());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            inputString = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new InputDataFileReader(inputString);
    }

    public String[] getProductData() {
        return parseProductData(inputString.split(" "));
    }

    public String getCardInfo() {
        return parseCardInfo(inputString.split(" "));
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
