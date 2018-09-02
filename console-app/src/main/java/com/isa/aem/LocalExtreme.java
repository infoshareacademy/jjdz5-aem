package com.isa.aem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalExtreme {

    private List<Currency> currencies;
    private List<Currency> sortedCurrenciesByDateAsc;
    private List<Currency> chosenCurrencyWithinChosenDateRange;
    private LocalDate chosenDateFrom;
    private LocalDate chosenDateTo;
    private List<String> avalableCurrencies = new ArrayList<>();
    ConsoleReader consoleReader = new ConsoleReader();




    List<Currency> limitCurrenciesToChosenDateRange() {
        System.out.println(CurrencyRepository.getCurrenciesSortedByDateAsc());
        return chosenCurrencyWithinChosenDateRange;
    }


    public LocalDate getDateFrom() {
        ConsolePrinter.printLn("Limit the date range for the currency of your choice. ");
        return this.chosenDateFrom = LocalDate.parse(consoleReader.getString("Choose Date From: "));
    }

    public LocalDate getDateTo() {
        ConsolePrinter.printLn("Limit the date range for the currency of your choice. ");
        return this.chosenDateTo = LocalDate.parse(consoleReader.getString("Choose Date From: "));
    }


//        for (Currency currency : CurrencyRepository.getCurrencies()) {
//        this.chosenDateFrom = currency.getDate();
//        if (chosenDateFrom != null) {
//
//        }
//    }


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
