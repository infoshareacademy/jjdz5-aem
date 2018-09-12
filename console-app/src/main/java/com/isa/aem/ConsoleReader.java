package com.isa.aem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleReader {

    Scanner scanner = new Scanner(System.in);
    ConsolePrinter consolePrinter = new ConsolePrinter();
    String dateStr;
    LocalDate dateFrom;
    LocalDate dateTo;
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

    public void runDates () {
        getDateFrom();
        while (localExtremum.isNotWithinRange(dateFrom)) {
            consolePrinter.printLn("The date you provide is out of range. Choose date between: " +
                    CurrencyRepository.getFirstDateFromRepository() + " and " + CurrencyRepository.getLastDateFromRepository());
            getDateFrom();
        }
        getDateTo();
        while (localExtremum.isNotWithinRange(dateTo)) {
            consolePrinter.printLn("The date you provide is out of range. Choose date between: " +
                    CurrencyRepository.getFirstDateFromRepository() + " and " + CurrencyRepository.getLastDateFromRepository());
            getDateTo();
        }
        consolePrinter.printLn("Date from: " + dateFrom);
        consolePrinter.printLn("Date to: " + dateTo);
        localExtremum.limitCurrenciesToChosenDateRange(dateFrom, dateTo);
    }

    public LocalDate getDateFrom () {
        dateStr = getString("Choose Date From: ");
        while (isIncorrectFormat(dateStr)) {
            consolePrinter.printLn("The date you provide is incorrect format. Please use format yyyy-MM-dd:");
            dateStr = getString("Choose Date From: ");
        }
        return dateFrom = LocalDate.parse(dateStr);
    }

    public LocalDate getDateTo () {
        dateStr = getString("Choose Date To: ");
        while (isIncorrectFormat(dateStr)) {
            consolePrinter.printLn("The date you provide is incorrect format. Please use format yyyy-MM-dd:");
            dateStr = getString("Choose Date To: ");
        }
        return dateTo = LocalDate.parse(dateStr);
    }

    public boolean isIncorrectFormat(String givenDate) {
        try {
            LocalDate.parse(givenDate);
            return false;
        } catch (DateTimeParseException e){
            return true;
        }
    }
}
