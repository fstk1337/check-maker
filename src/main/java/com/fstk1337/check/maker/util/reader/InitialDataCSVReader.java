package com.fstk1337.check.maker.util.reader;

import com.fstk1337.check.maker.model.DiscountCard;
import com.fstk1337.check.maker.model.Product;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InitialDataCSVReader {
    private static final CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

    public static List<DiscountCard> readCards(String fileName) {
        List<String[]> lines = readLines(fileName);
        return lines.stream()
                .map(InitialDataCSVReader::createCard)
                .collect(Collectors.toList());
    }

    public static List<Product> readProducts(String fileName) {
        List<String[]> lines = readLines(fileName);
        return  lines.stream()
                .map(InitialDataCSVReader::createProduct)
                .collect(Collectors.toList());
    }

    private static List<String[]> readLines(String fileName) {
        File file = new File(Objects.requireNonNull(InitialDataCSVReader.class.getClassLoader().getResource(fileName)).getFile());
        List<String[]> result;
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file, StandardCharsets.UTF_8))
                .withCSVParser(parser)
                .withSkipLines(1)
                .build()) {
            result = reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static DiscountCard createCard(String[] line) {
        long id = Long.parseLong(line[0]);
        int number = Integer.parseInt(line[1]);
        double discountRate = Double.parseDouble(line[2]);
        return new DiscountCard(id, number, discountRate);
    }

    private static Product createProduct(String[] line) {
        long id = Long.parseLong(line[0]);
        String name = line[1];
        double price = Double.parseDouble(line[2]);
        boolean onSale = Boolean.parseBoolean(line[3]);
        return new Product(id, name, price, onSale);
    }
}
