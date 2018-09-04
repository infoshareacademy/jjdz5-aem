package com.isa.aem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        givenDate = LocalDate.parse(consoleReader.getString("Choose Date From: "));
        while (isCorrectFormat(givenDate) == false) {
            consolePrinter.printLn("The date you provide is incorrect format. Please use format yyyy-MM-dd:");
            givenDate = LocalDate.parse(consoleReader.getString("Choose Date From: "));
        }
        while (isWithinRange(givenDate) == false) {
            consolePrinter.printLn("The date you provide is out of range. Please try again:");
            givenDate = LocalDate.parse(consoleReader.getString("Choose Date From: "));
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
        if (givenDate.isBefore(CurrencyRepository.getCurrenciesSortedByDateAsc().get(0).getDate()) ||
                givenDate.isAfter(CurrencyRepository.getCurrenciesSortedByDateAsc().get(CurrencyRepository.getCurrenciesSortedByDateAsc().size()-1).getDate())) {
            return false;
        } else
        return true;
    }

    public boolean isCorrectFormat(LocalDate givenDate) {
        Pattern pattern = Pattern.compile("([1-9]{1})([0-9]{3})\\-[0-2]{1}[1-2]{1}\\-[0-3]{1}[0-9]{1}");
        Matcher matcher = pattern.matcher(givenDate.toString());
        if (!matcher.matches()) {
            return false;
        }else {
            return true;
        }
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
