package com.isa.aem.local.extremum;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.MenuInformation;
import com.isa.aem.tools.ConsolePrinter;
import com.isa.aem.tools.ConsoleReader;
import com.isa.aem.tools.DataValidator;

import java.time.LocalDate;
import java.util.List;

public class LocalExtremumService {

    private String chosenCurrencyName;
    private String typedDateString;
    private LocalDate chosenDateFrom;
    private LocalDate chosenDateTo;
    private String typedMenuOptionString;
    CurrencyRepository currencyRepository = new CurrencyRepository();
    ConsoleReader consoleReader = new ConsoleReader();
    ConsolePrinter consolePrinter = new ConsolePrinter();
    DataValidator dataValidator = new DataValidator();
    LocalExtremum localExtremum = new LocalExtremum();
    MenuInformation menuInformation = new MenuInformation();

    public void run() {
        runCurrencySelection();
        runDatesSelection();
        runExtremum();
        chooseWhatNext();
    }

    public void runCurrencySelection() {
        chooseCurrency(CurrencyRepository.getAvailableCurrencyNames());
    }

    public void chooseCurrency(List<String> availableCurrencyNames) {
        consolePrinter.printLn("Choose currency from available ones: " + availableCurrencyNames);
        String typedCurrency = consoleReader.getString();
        while (currencyDoesNotExists(CurrencyRepository.getCurrencies(), typedCurrency)) {
            consolePrinter.printLn("Currency " + "\"" + typedCurrency + "\"" + " does not exist. Try again");
            typedCurrency = consoleReader.getString();
        }
        chosenCurrencyName = typedCurrency;
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
        while (dataValidator.isNotWithinRange(chosenDateFrom)) {
            consolePrinter.printLn("The date you provide is out of range. Choose date between: " +
                    currencyRepository.getFirstDateFromRepository() + " and " + currencyRepository.getLastDateFromRepository());
            chooseDateFrom();
        }
        chooseDateTo();
        while (dataValidator.isNotWithinRange(chosenDateTo)) {
            consolePrinter.printLn("The date you provide is out of repository range. Choose date between: " +
                    currencyRepository.getFirstDateFromRepository() + " and " + currencyRepository.getLastDateFromRepository());
            chooseDateTo();
        }
        currencyRepository.limitRepositoryToChosenCurrencyWithinChosenDateRange(chosenCurrencyName, chosenDateFrom, chosenDateTo);
    }

    public LocalDate chooseDateFrom() {
        typedDateString = consoleReader.getString("Choose Date From: ");
        while (dataValidator.isIncorrectDateFormat(typedDateString)) {
            consolePrinter.printLn("The date you provide has incorrect format. Please use format yyyy-MM-dd: ");
            typedDateString = consoleReader.getString("Choose Date From: ");
        }
        return chosenDateFrom = LocalDate.parse(typedDateString);
    }

    public LocalDate chooseDateTo() {
        typedDateString = consoleReader.getString("Choose Date To: ");
        while (dataValidator.isIncorrectDateFormat(typedDateString)) {
            consolePrinter.printLn("The date you provide has incorrect format. Please use format yyyy-MM-dd: ");
            typedDateString = consoleReader.getString("Choose Date To: ");
        }
        return chosenDateTo = LocalDate.parse(typedDateString);
    }



    public void runExtremum() {
        List<Currency> minExtremum = localExtremum.getMinExtremum(currencyRepository.getRepositoryWithChosenCurrencyWithinChosenDateRange());
        consolePrinter.printLn("\nMIN");
        minExtremum.stream()
                .forEach(currency -> System.out.println("  " + currency.getName() + ": " + currency.getClose() + " [" + currency.getDate() + "]"));
        consolePrinter.printLn("MAX");
        List<Currency> maxExtremum = localExtremum.getMaxExtremum(currencyRepository.getRepositoryWithChosenCurrencyWithinChosenDateRange());
        maxExtremum.stream()
                .forEach(currency -> System.out.println("  " + currency.getName() + ": " + currency.getClose() + " [" + currency.getDate() + "]"));
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
