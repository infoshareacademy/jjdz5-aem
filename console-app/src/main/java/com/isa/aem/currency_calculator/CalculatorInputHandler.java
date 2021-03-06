package com.isa.aem.currency_calculator;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.utils.ConsolePrinter;
import com.isa.aem.utils.ConsoleReader;
import com.isa.aem.utils.DataValidator;

import java.time.LocalDate;

public class CalculatorInputHandler {

    private ConsoleReader consoleReader = new ConsoleReader();
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private DataValidator dataService = new DataValidator();
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    private static final String CORRECT_DATE_FORM = "^\\d{4}(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])$";
    private static final String ONLY_EIGHT_DIGITS = "[0-9]{8}";
    private String preparedDate;

    protected String getCurrencySelectedByUserOfConsole(String commandForUser) {
        String commandByUser = null;
        do {
            commandByUser = consoleReader.getString(commandForUser).trim().toUpperCase();
            if (currencyRepository.containsCurrencyNameInCurrencyList(commandByUser)) {
                return commandByUser;

            } else {
                System.out.println(consolePrinter.currencyDoesNotExist());
            }
        } while (!currencyRepository.containsCurrencyNameInCurrencyList(commandByUser));
        return null;
    }

    protected double amountGivenByUserService() {
        String strValue;
        String replace;
        Double amountGivenByUser = null;
        do {
            strValue = consoleReader.getString(consolePrinter.enterAmount()).trim();
            replace = strValue.replace(',', '.');
            if (isNumber(replace)) {
                amountGivenByUser = Double.parseDouble(replace);
            } else {
                System.out.println(consolePrinter.numberDoesNotExist());
            }
        } while (!isNumber(replace));
        return amountGivenByUser;
    }

    protected LocalDate dataService() {
        String strDate;
        LocalDate date = null;
        do {
            strDate = consoleReader.getString(consolePrinter.enterDate()).trim();
            preparedDate = dataService.preparingDateRemovingPunctuationMarks(strDate);
            if (checkIfItIsCorrectDataFormatAndOnlyEightDigits()) {
                date = dataService.dataParse(preparedDate);
            } else {
                System.out.println(consolePrinter.wrongDate());
            }
        } while (!(checkIfItIsCorrectDataFormatAndOnlyEightDigits()));
        return date;
    }

    public boolean isNumber(String strValue) {
        return strValue.matches("[0-9 .]+");
    }

    protected boolean checkIfCurrencyNameSelectedByUserContainsGivenDate(
            String firstNameOfCurrency, String secondNameOfCurrency, LocalDate date) {
        return currencyRepository.existCurrencyWithGivenDate(firstNameOfCurrency, date)
                && currencyRepository.existCurrencyWithGivenDate(secondNameOfCurrency, date);
    }

    private Boolean checkIfItIsCorrectDataFormatAndOnlyEightDigits() {
        return (preparedDate.matches(CORRECT_DATE_FORM)
                && preparedDate.matches(ONLY_EIGHT_DIGITS));
    }
}
