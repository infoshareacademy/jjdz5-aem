package com.isa.aem.tools;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.LocalExtremum;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsoleReader {

    private String chosenCurrencyName;
    private String dateStr;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    Scanner scanner = new Scanner(System.in);
    ConsolePrinter consolePrinter = new ConsolePrinter();
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


    public void runCurrency () {
        chooseCurrency(CurrencyRepository.getAvailableCurrencyNames());
    }


    public void chooseCurrency(List<String> availableCurrencyNames) {
        consolePrinter.printLn("Choose currency from available ones: " + availableCurrencyNames);
        String typedCurrency = getString();
        while (currencyDoesNotExists(CurrencyRepository.getCurrencies(), typedCurrency)){
            consolePrinter.printLn("Currency does not exist. Try again");
            typedCurrency = getString();
        }
        chosenCurrencyName = typedCurrency;
        System.out.println(chosenCurrencyName);
    }

    public boolean currencyDoesNotExists (List<Currency> list, String typedCurrency) {
        for (Currency currency : list) {
            if (currency.getName().equalsIgnoreCase(typedCurrency))
                return false;
        }
        return true;
    }

    public void runDates () {
        consolePrinter.printLn("Limit the date range for the currency of your choice. ");
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
        CurrencyRepository.limitRepositoryToChosenCurrencyWithinChosenDateRange(chosenCurrencyName, dateFrom, dateTo);
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