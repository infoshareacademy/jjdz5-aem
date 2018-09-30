package com.isa.aem.local.extremum;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.MenuProject;
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
    private Integer chosenMenuOption;
    ConsoleReader consoleReader= new ConsoleReader();
    ConsolePrinter consolePrinter = new ConsolePrinter();
    DataValidator dataValidator = new DataValidator();


    public void runCurrencySelection() {
        chooseCurrency(CurrencyRepository.getAvailableCurrencyNames());
    }

    public void chooseCurrency(List<String> availableCurrencyNames) {
        consolePrinter.printLn("Choose currency from available ones: " + availableCurrencyNames);
        String typedCurrency = consoleReader.getString();
        while (currencyDoesNotExists(CurrencyRepository.getCurrencies(), typedCurrency)){
            consolePrinter.printLn("Currency does not exist. Try again");
            typedCurrency = consoleReader.getString();
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

    public void runDatesSelection() {
        consolePrinter.printLn("Limit the date range for the currency of your choice. ");
        chooseDateFrom();
        while (isNotWithinRange(chosenDateFrom)) {
            consolePrinter.printLn("The date you provide is out of range. Choose date between: " +
                    CurrencyRepository.getFirstDateFromRepository() + " and " + CurrencyRepository.getLastDateFromRepository());
            chooseDateFrom();
        }
        chooseDateTo();
        while (isNotWithinRange(chosenDateTo)) {
            consolePrinter.printLn("The date you provide is out of range. Choose date between: " +
                    CurrencyRepository.getFirstDateFromRepository() + " and " + CurrencyRepository.getLastDateFromRepository());
            chooseDateTo();
        }
        CurrencyRepository.limitRepositoryToChosenCurrencyWithinChosenDateRange(chosenCurrencyName, chosenDateFrom, chosenDateTo);
    }

    public LocalDate chooseDateFrom() {
        typedDateString = consoleReader.getString("Choose Date From: ");
        while (dataValidator.isIncorrectDateFormat(typedDateString)) {
            consolePrinter.printLn("The date you provide is incorrect format. Please use format yyyy-MM-dd:");
            typedDateString = consoleReader.getString("Choose Date From: ");
        }
        return chosenDateFrom = LocalDate.parse(typedDateString);
    }

    public LocalDate chooseDateTo() {
        typedDateString = consoleReader.getString("Choose Date To: ");
        while (dataValidator.isIncorrectDateFormat(typedDateString)) {
            consolePrinter.printLn("The date you provide is incorrect format. Please use format yyyy-MM-dd:");
            typedDateString = consoleReader.getString("Choose Date To: ");
        }
        return chosenDateTo = LocalDate.parse(typedDateString);
    }



    public boolean isNotWithinRange(LocalDate givenDate) {
        if (givenDate.isBefore(CurrencyRepository.getFirstDateFromRepository()) || givenDate.isAfter(CurrencyRepository.getLastDateFromRepository())) {
            return true;
        } else {
            return false;
        }
    }

    public void chooseWhatNext() {
        typedMenuOptionString = consoleReader.getString("Line 94. Type 4 to return back to currency selection, 1 to return back to menu or 0 to exit");
        while (dataValidator.isNotInteger(typedMenuOptionString)) {
            consolePrinter.printLn("You have typed not a number. Type a number 1 or 0");
            typedMenuOptionString = consoleReader.getString("Line 97. Type 4 to return back to currency selection, 1 to return back to menu or 0 to exit");

        } chosenMenuOption = Integer.parseInt(typedMenuOptionString);
        while (chosenMenuOption != 1 && chosenMenuOption != 0) {
            consolePrinter.printLn("The number you typed is not a valid option");
            chosenMenuOption = consoleReader.getInteger("Line 102. Please type 4 to return back to currency selection, 1 to return back to menu or 0 to exit");
        }
        if(chosenMenuOption == 1) {
            runCurrencySelection();
        }
        else if(chosenMenuOption == 0) {
            consolePrinter.printLn("System will exit");
            System.exit(0);
        }
    }


}
