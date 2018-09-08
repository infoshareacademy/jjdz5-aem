package com.isa.aem;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleReader {

    Scanner scanner = new Scanner(System.in);
    ConsolePrinter consolePrinter = new ConsolePrinter();
    String dateStr;
    LocalDate date;
    LocalExtremum localExtremum = new LocalExtremum();


    public String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public String getString() {
        return scanner.nextLine();
    }

    public Integer getInteger(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    public Integer getInteger() {
        return scanner.nextInt();
    }

    public Double getDouble(String message) {
        System.out.println(message);
        return scanner.nextDouble();
    }

    public Double getDouble() {
        return scanner.nextDouble();
    }

    public LocalDate getDate(String sideOfRange) {
        dateStr = getString("Choose Date " + sideOfRange + ":");
        while (!localExtremum.isCorrectFormat(dateStr)) {
            consolePrinter.printLn("The date you provide is incorrect format. Please use format yyyy-MM-dd:");
            dateStr = getString("Choose Date " + sideOfRange + ":");
        }
        date = LocalDate.parse(dateStr);
        while (!localExtremum.isWithinRange(date)) {
            consolePrinter.printLn("The date you provide is out of range. Please try again:");
            dateStr = getString("Choose Date " + sideOfRange + ":");
        }
        return date;
    }

}
