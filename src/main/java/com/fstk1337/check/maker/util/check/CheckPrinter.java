package com.fstk1337.check.maker.util.check;

import com.fstk1337.check.maker.model.Check;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CheckPrinter {
    private static final int LINE_LENGTH = 60;
    private final CheckPrintable check;

    private CheckPrinter(CheckPrintable check) {
        this.check = check;
    }

    public static CheckPrinter of(Check check) {
        return new CheckPrinter(new CheckPrintable(check));
    }

    public void printAtConsole() {
        printHeader();
        printSeparator();
        printTable();
        printSeparator();
        printTotals();
    }

    private void printSeparator() {
        printNChars('-', LINE_LENGTH);
        newLine();
    }

    private void printHeader() {
        CheckHeader header = check.getHeader();
        printCenterLine(header.getHeading());
        printCenterLine(header.getShopName());
        printCenterLine(header.getShopAddress());
        printCenterLine(header.getShopPhoneNumber());
        newLine();
        print(header.getCashierInfo());
        printIndent(header.getCheckDate(), LINE_LENGTH - 32);
        printIndent(header.getCheckTime(), LINE_LENGTH - 32 + header.getCheckDate().length());
    }

    private void printTable() {
        CheckTable table = check.getTable();
        int[] maxChars = calculateMaxChars(table);
        int freeSpace = calculateFreeSpace(maxChars);
        printTableHeaders(table.getHeaders(), freeSpace, maxChars);
        newLine();
        printTableLines(table.getLines(), freeSpace, maxChars);
    }

    private int[] calculateMaxChars(CheckTable table) {
        List<String[]> allLines = new ArrayList<>(table.getLines());
        allLines.add(table.getHeaders());
        int size = allLines.get(0).length;
        int[] maxChars = new int[size];
        for (int i = 0; i < size; i++) {
            int id = i;
            maxChars[id] = allLines.stream()
                    .map(line -> line[id].length())
                    .max(Comparator.comparingInt(a -> a))
                    .get();
        }
        return maxChars;
    }

    private int calculateFreeSpace(int[] maxChars) {
        return Arrays.stream(maxChars)
                .reduce(LINE_LENGTH, (total, next) -> total - next);
    }

    private void printTableHeaders(String[] headers, int freeSpace, int[] maxChars) {
        printTableLine(headers, freeSpace, maxChars);
    }

    private void printTableLines(List<String[]> lines, int freeSpace, int[] maxChars) {
        lines.forEach(line -> printTableLine(line, freeSpace, maxChars));
        newLine();
    }

    private void printTableLine(String[] line, int freeSpace, int[] maxChars) {
        int indent = freeSpace / (maxChars.length - 1);
        for (int i = 0; i < line.length - 1; i++) {
            String item = line[i];
            int chars = maxChars[i];
            if (i == 0) {
                printCenter(item, chars);
            } else {
                printNChars(' ', indent);
                freeSpace -= indent;
            }
            if (i == 1) {
                printLeft(item, chars);
            }
            if (i > 1) {
                printRight(item, chars);
            }
        }
        printRight(line[line.length - 1], maxChars[line.length - 1] + freeSpace);
        newLine();
    }

    private void printTotals() {
        CheckTotals totals = check.getTotals();
        printLeftRight(totals.getTaxableHeading(), totals.getTaxable());
        printLeftRight(totals.getTaxHeading(), totals.getTax());
        printLeftRight(totals.getTotalHeading(), totals.getTotal());
    }

    private void printNChars(char chr, int N) {
        System.out.print(String.valueOf(chr).repeat(N));
    }

    private void newLine() {
        System.out.println();
    }

    private void print(String chars) {
        System.out.print(chars);
    }

    private void printIndent(String chars, int indent) {
        printNChars(' ', indent);
        System.out.println(chars);
    }

    private void printLeftRight(String leftChars, String rightChars) {
        int indent = LINE_LENGTH - leftChars.length() - rightChars.length();
        print(leftChars);
        printIndent(rightChars, indent);
    }

    private void printRight(String chars, int maxChars) {
        String charsToPrint = chars.length() > maxChars ? chars.substring(0, maxChars) : chars;
        while (charsToPrint.length() < maxChars) {
            charsToPrint = " ".concat(charsToPrint);
        }
        print(charsToPrint);
    }

    private void printLeft(String chars, int maxChars) {
        String charsToPrint = chars.length() > maxChars ? chars.substring(0, maxChars) : chars;
        while (charsToPrint.length() < maxChars) {
            charsToPrint = charsToPrint.concat(" ");
        }
        print(charsToPrint);
    }

    private void printCenter(String chars, int maxChars) {
        String charsToPrint = chars.length() > maxChars ? chars.substring(0, maxChars) : chars;
        while (charsToPrint.length() < maxChars) {
            if (maxChars - charsToPrint.length() == 1) {
                charsToPrint = charsToPrint.concat(" ");
            } else {
                charsToPrint = " ".concat(charsToPrint).concat(" ");
            }
        }
        print(charsToPrint);
    }

    private void printCenterLine(String chars) {
        int indent = (LINE_LENGTH - chars.length()) / 2;
        printIndent(chars, indent);
    }
}
