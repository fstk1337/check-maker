package com.fstk1337.check.maker.util.check;

import com.fstk1337.check.maker.model.Check;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CheckPrinter {
    private static final int LINE_LENGTH = 60;
    private final CheckPrintable check;
    private final StringBuilder buffer = new StringBuilder();

    private CheckPrinter(CheckPrintable check) {
        this.check = check;
    }

    public static CheckPrinter of(Check check) {
        return new CheckPrinter(new CheckPrintable(check));
    }

    public void printToConsole() {
        fillBuffer();
        System.out.println(buffer);
        clearBuffer();
    }

    public void printToFile(String fileName) {
        fillBuffer();
        printCheckToFile(fileName);
        clearBuffer();
    }

    private void printCheckToFile(String fileName) {
        try {
            Files.write(Paths.get(fileName), buffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillBuffer() {
        addHeader();
        addSeparator();
        addTable();
        addSeparator();
        addTotals();
    }

    private void clearBuffer() {
        buffer.setLength(0);
    }

    private void addSeparator() {
        addNChars('-', LINE_LENGTH);
        addNewLine();
    }

    private void addHeader() {
        CheckHeader header = check.getHeader();
        addCenterLine(header.getHeading());
        addCenterLine(header.getShopName());
        addCenterLine(header.getShopAddress());
        addCenterLine(header.getShopPhoneNumber());
        addNewLine();
        add(header.getCashierInfo());
        addIndent(header.getCheckDate(), LINE_LENGTH - 32);
        addIndent(header.getCheckTime(), LINE_LENGTH - 32 + header.getCheckDate().length());
    }

    private void addTable() {
        CheckTable table = check.getTable();
        int[] maxChars = calculateMaxChars(table);
        int freeSpace = calculateFreeSpace(maxChars);
        addTableHeaders(table.getHeaders(), freeSpace, maxChars);
        addNewLine();
        addTableLines(table.getLines(), freeSpace, maxChars);
    }

    private void addTotals() {
        CheckTotals totals = check.getTotals();
        addLeftRight(totals.getTaxableHeading(), totals.getTaxable());
        addLeftRight(totals.getTaxHeading(), totals.getTax());
        addLeftRight(totals.getTotalHeading(), totals.getTotal());
    }

    private int[] calculateMaxChars(CheckTable table) {
        List<String[]> allLines = new ArrayList<>(table.getLines());
        allLines.add(table.getHeaders());
        int size = allLines.get(0).length;
        int[] maxChars = new int[size];
        for (int i = 0; i < size; i++) {
            int id = i;
            int max = allLines.stream()
                    .map(line -> line[id].length())
                    .max(Comparator.comparingInt(a -> a))
                    .get();
            if (id == 1) {
                max = Math.min(LINE_LENGTH - 30, max);
            }
            maxChars[id] = max;
        }
        return maxChars;
    }

    private int calculateFreeSpace(int[] maxChars) {
        return Arrays.stream(maxChars)
                .reduce(LINE_LENGTH, (total, next) -> total - next);
    }

    private void addTableHeaders(String[] headers, int freeSpace, int[] maxChars) {
        addTableLine(headers, freeSpace, maxChars);
    }

    private void addTableLines(List<String[]> lines, int freeSpace, int[] maxChars) {
        lines.forEach(line -> addTableLine(line, freeSpace, maxChars));
        addNewLine();
    }

    private void addTableLine(String[] line, int freeSpace, int[] maxChars) {
        int indent = freeSpace / (maxChars.length - 1);
        for (int i = 0; i < line.length - 1; i++) {
            String item = line[i];
            int chars = maxChars[i];
            if (i == 0) {
                addCenter(item, chars);
            } else {
                addNChars(' ', indent);
                freeSpace -= indent;
            }
            if (i == 1) {
                addLeft(item, chars);
            }
            if (i > 1) {
                addRight(item, chars);
            }
        }
        addRight(line[line.length - 1], maxChars[line.length - 1] + freeSpace);
        addNewLine();
    }

    private void addNChars(char chr, int N) {
        buffer.append(String.valueOf(chr).repeat(N));
    }

    private void add(String chars) {
        buffer.append(chars);
    }

    private void addNewLine() {
        buffer.append(System.lineSeparator());
    }

    private void addLine(String chars) {
        add(chars);
        addNewLine();
    }

    private void addIndent(String chars, int indent) {
        addNChars(' ', indent);
        addLine(chars);
    }

    private void addLeftRight(String leftChars, String rightChars) {
        int indent = LINE_LENGTH - leftChars.length() - rightChars.length();
        add(leftChars);
        addIndent(rightChars, indent);
    }

    private void addRight(String chars, int maxChars) {
        String charsToPrint = chars.length() > maxChars ? chars.substring(0, maxChars) : chars;
        while (charsToPrint.length() < maxChars) {
            charsToPrint = " ".concat(charsToPrint);
        }
        add(charsToPrint);
    }

    private void addLeft(String chars, int maxChars) {
        String charsToPrint = chars.length() > maxChars ? chars.substring(0, maxChars) : chars;
        while (charsToPrint.length() < maxChars) {
            charsToPrint = charsToPrint.concat(" ");
        }
        add(charsToPrint);
    }

    private void addCenter(String chars, int maxChars) {
        String charsToPrint = chars.length() > maxChars ? chars.substring(0, maxChars) : chars;
        while (charsToPrint.length() < maxChars) {
            if (maxChars - charsToPrint.length() == 1) {
                charsToPrint = charsToPrint.concat(" ");
            } else {
                charsToPrint = " ".concat(charsToPrint).concat(" ");
            }
        }
        add(charsToPrint);
    }

    private void addCenterLine(String chars) {
        int indent = (LINE_LENGTH - chars.length()) / 2;
        addIndent(chars, indent);
    }
}
