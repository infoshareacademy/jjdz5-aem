package com.isa.aem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocalExtreme {

    private List<Currency> currencies;
    private List<Currency> sortedCurrenciesByDateAsc;
    private List<Currency> chosenCurrencyWithinChosenDateRange;
    private LocalDate givenDate;
    private LocalDate chosenDateFrom;
    private LocalDate chosenDateTo;
    private List<String> avalableCurrencies = new ArrayList<>();
    ConsolePrinter consolePrinter = new ConsolePrinter();
    ConsoleReader consoleReader = new ConsoleReader();


    public LocalDate getDateFrom() {
        consolePrinter.printLocalExtremeWelcome();
        consolePrinter.printLn("Limit the date range for the currency of your choice. ");
        while (isWithinRange(givenDate) == false) {
            givenDate = LocalDate.parse(consoleReader.getString("Choose Date From: "));
            consolePrinter.printLn("The date you provide is out of range. Please try again:");
        }
        return chosenDateFrom = givenDate;
    }

    public LocalDate getDateTo() {
        return this.chosenDateTo = LocalDate.parse(consoleReader.getString("Choose Date From: "));
    }

    List<Currency> limitCurrenciesToChosenDateRange() {
        System.out.println(CurrencyRepository.getCurrenciesSortedByDateAsc());
        return chosenCurrencyWithinChosenDateRange;
    }


    public boolean isWithinRange(LocalDate givenDate) {
        for (Currency currency : CurrencyRepository.getCurrenciesSortedByDateAsc()) {
            if (!currency.getDate().equals(givenDate)) {
                return false;
            }
        }
        return true;
    }

    public List<String> getAvailableCurrencies() {
        for (Currency currency : CurrencyRepository.getCurrencies()) {
            if (!avalableCurrencies.contains(currency.getName())) {
                avalableCurrencies.add(currency.getName());
            } else {
                continue;
            }
        }
        return avalableCurrencies;
    }


}
