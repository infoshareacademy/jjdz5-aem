package com.isa.aem.calc;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.tools.*;
import java.time.LocalDate;

public class DataTransducerIntroducedByConsole {

    private ConsoleReader consoleReader = new ConsoleReader();
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private DataValidator dataService = new DataValidator();
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    protected String getCurrencySelectedByUserOfConsole(String commandForUser) {
        String commandByUser = null;
        do {
            commandByUser = consoleReader.getString(commandForUser).trim().toUpperCase();
            if (currencyRepository.containsCurrencyNameInCurrencyList(commandByUser)) {
                return commandByUser;

            } else {
                System.out.println(consolePrinter.currencyUnexist());
            }
        } while (!currencyRepository.containsCurrencyNameInCurrencyList(commandByUser));
        return null;
    }

    protected double amountGivenByUserService() {
        String strValue;
        String replace;
        Double amountGivenByUser = null;
        do {
            strValue = consoleReader.getString(consolePrinter.enterAmount().trim());
            replace = strValue.replace(',', '.');
            if (checkIfItIsANumber(replace)){
                amountGivenByUser = Double.parseDouble(replace);
            } else {
                System.out.println(consolePrinter.numberUnexist());
            }
        } while (!checkIfItIsANumber(replace));
        return amountGivenByUser;
    }

    protected LocalDate dataService() {
        String strDate = consoleReader.getString(consolePrinter.enterDate().trim());
        LocalDate date = null;
        try {
            date = dataService.dataParse(strDate);
        } catch (Exception ex) {
            System.out.println(consolePrinter.wrongDate());
            dataService();
        }
        return date;
    }

    public boolean checkIfItIsANumber(String strValue) {
        return strValue.matches("[0-9 .]+");
    }

    protected boolean checkIfCurrencyNameSelectedByUserContainsGivenDate(
            String firstNameOfCurrency, String secondNameOfCurrency, LocalDate date) {
        return currencyRepository.checkIfExistCurrencyWithGivenDate(firstNameOfCurrency, date)
                && currencyRepository.checkIfExistCurrencyWithGivenDate(secondNameOfCurrency, date);
    }
}
