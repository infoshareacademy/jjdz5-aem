package com.isa.aem.rate_extremums;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.MenuInformation;
import com.isa.aem.helpers.ConsolePrinter;
import com.isa.aem.helpers.ConsoleReader;
import com.isa.aem.helpers.DataValidator;

import java.time.LocalDate;
import java.util.List;

public class LocalExtremumConsoleHandler {

    private String currencyName;
    private String typedDateString;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String typedMenuOptionString;
    ConsoleReader consoleReader = new ConsoleReader();
    ConsolePrinter consolePrinter = new ConsolePrinter();
    DataValidator dataValidator = new DataValidator();
    LocalExtremum localExtremum = new LocalExtremum();
    MenuInformation menuInformation = new MenuInformation();
    CurrencyRepository currencyRepository = new CurrencyRepository();


    public void run() {
        runCurrencySelection();
        runDatesSelection();
        runExtremum();
        chooseWhatNext();
    }

    public void runCurrencySelection() {
        chooseCurrency(currencyRepository.getAvailableCurrencyNames());
    }

    public void chooseCurrency(List<String> availableCurrencyNames) {
        consolePrinter.printLn("Choose currency from available ones: " + availableCurrencyNames);
        String typedCurrency = consoleReader.getString();
        while (currencyDoesNotExists(CurrencyRepository.getCurrencies(), typedCurrency)) {
            consolePrinter.printLn("Currency " + "\"" + typedCurrency + "\"" + " does not exist. Try again");
            typedCurrency = consoleReader.getString();
        }
        currencyName = typedCurrency;
    }

    public boolean currencyDoesNotExists(List<Currency> list, String typedCurrency) {
        for (Currency currency : list) {
            if (currency.getName().equalsIgnoreCase(typedCurrency))
                return false;
        }
        return true;
    }

    public void runDatesSelection() {
        consolePrinter.printLn("\nLimit the date range for the currency of your choice. ");
        chooseDateFrom();
        while (dataValidator.isNotWithinRange(dateFrom)) {
            consolePrinter.printLn("The date you provide is out of range. Choose date between: " +
                    currencyRepository.getFirstDateFromRepository() + " and " + currencyRepository.getLastDateFromRepository());
            chooseDateFrom();
        }
        chooseDateTo();
        while (dataValidator.isNotWithinRange(dateTo)) {
            consolePrinter.printLn("The date you provide is out of repository range. Choose date between: " +
                    currencyRepository.getFirstDateFromRepository() + " and " + currencyRepository.getLastDateFromRepository());
            chooseDateTo();
        }
    }

    public LocalDate chooseDateFrom() {
        typedDateString = consoleReader.getString("Choose Date From: ");
        while (dataValidator.isIncorrectDateFormat(typedDateString)) {
            consolePrinter.printLn("The date you provide has incorrect format. Please use format yyyy-MM-dd: ");
            typedDateString = consoleReader.getString("Choose Date From: ");
        }
        return dateFrom = LocalDate.parse(typedDateString);
    }

    public LocalDate chooseDateTo() {
        typedDateString = consoleReader.getString("Choose Date To: ");
        while (dataValidator.isIncorrectDateFormat(typedDateString)) {
            consolePrinter.printLn("The date you provide has incorrect format. Please use format yyyy-MM-dd: ");
            typedDateString = consoleReader.getString("Choose Date To: ");
        }
        return dateTo = LocalDate.parse(typedDateString);
    }


    public void runExtremum() {
        List<Currency> minExtremum = localExtremum.getMinExtremum(currencyName, dateFrom, dateTo);
        consolePrinter.printLn("\nMIN " + currencyName.toUpperCase() + ": ");
        minExtremum.stream()
                .forEach(currency -> System.out.println("    " + currency.getClose() + " [" + currency.getDate() + "]"));
        consolePrinter.printLn("MAX " + currencyName.toUpperCase() + ": ");
        List<Currency> maxExtremum = localExtremum.getMaxExtremum(currencyName, dateFrom, dateTo);
        maxExtremum.stream()
                .forEach(currency -> System.out.println("    " + currency.getClose() + " [" + currency.getDate() + "]"));
    }

    public void chooseWhatNext() {
        Boolean isChoiceCorrect = false;
        typedMenuOptionString = consoleReader.getString("\nType 1 to return back to Currency selection or 0 to return back to Menu: ");
        do {
            if (typedMenuOptionString.matches("\\d{0,1}") && typedMenuOptionString.length() > 0) {
                Integer chosenMenuOptionInt = Integer.parseInt(typedMenuOptionString);
                switch (chosenMenuOptionInt) {
                    case 0:
                        consolePrinter.printLn("");
                        menuInformation.readMenu();
                        isChoiceCorrect = true;
                        break;
                    case 1:
                        consolePrinter.printLn("");
                        run();
                        isChoiceCorrect = true;
                        break;
                    default:
                        consolePrinter.printLn("\"" + typedMenuOptionString + "\"" + " is not a valid option \n");
                        typedMenuOptionString = consoleReader.getString("Type 1 to return back to currency selection or 0 to return back to menu: ");
                        break;
                }
            } else {
                consolePrinter.printLn("\"" + typedMenuOptionString + "\"" + " is not a number \n");
                typedMenuOptionString = consoleReader.getString("Type 1 to return back to currency selection or 0 to return back to menu: ");
            }
        } while (isChoiceCorrect == false);
    }

}
