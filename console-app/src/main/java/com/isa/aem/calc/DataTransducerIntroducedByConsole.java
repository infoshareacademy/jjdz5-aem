package com.isa.aem.calc;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.tools.*;
import java.time.LocalDate;

public class DataTransducerIntroducedByConsole {

    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private DateService dataService = new DateService();
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    private static final String ONLY_EIGHT_DIGITS_IN_DATE_FORMAT = "^\\d{4}(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])$";

    protected String getCurrencySelectedByUserOfConsole(String commandForUser) {
        String commandByUser = null;
        do {
            commandByUser = consoleReader.getString(commandForUser).trim().toUpperCase();
            if (currencyRepository.containsCurrencyNameInCurrencyList(commandByUser)) {
                return commandByUser;

            } else {
                System.out.println(myPrinter.currencyUnexist());
            }
        } while (!currencyRepository.containsCurrencyNameInCurrencyList(commandByUser));
        return null;
    }

    protected double amountGivenByUserService() {
        String strValue;
        String replace;
        Double amountGivenByUser = null;
        do {
            strValue = consoleReader.getString(myPrinter.enterAmount()).trim();
            replace = strValue.replace(',', '.');
            if (checkIfItIsANumber(replace)){
                amountGivenByUser = Double.parseDouble(replace);
            } else {
                System.out.println(myPrinter.numberUnexist());
            }
        } while (!checkIfItIsANumber(replace));
        return amountGivenByUser;
    }

    protected LocalDate dataService() {
        String strDate;
        LocalDate date = null;
        do{
            strDate = consoleReader.getString(myPrinter.enterDate()).trim();
            String preparedDate = dataService.preparingDateRemovingPunctuationMarks(strDate);
            if (preparedDate.matches(ONLY_EIGHT_DIGITS_IN_DATE_FORMAT)) {
                date = dataService.dataParse(preparedDate);
            }
            else {
                System.out.println(myPrinter.wrongDate());
            }
        } while (!(strDate.matches(ONLY_EIGHT_DIGITS_IN_DATE_FORMAT)));
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
