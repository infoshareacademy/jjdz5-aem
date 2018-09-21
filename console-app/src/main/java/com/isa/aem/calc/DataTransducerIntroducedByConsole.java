package com.isa.aem.calc;

import com.isa.aem.tools.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DataTransducerIntroducedByConsole {

    private CurrencyService currencyService = new CurrencyService();
    private AlgorithmCurrencyConversion algorithmCurrencyConversion = new AlgorithmCurrencyConversion();
    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private DateService dataService = new DateService();
    private CurrencyRepositoryHelper currencyRepositoryHelper = new CurrencyRepositoryBin();
    private CurrencyExist currencyExist = new CurrencyExist();


    protected BigDecimal resultOfCurrencyConversionAlgorithm(Double amountGivenByUser) {
        return algorithmCurrencyConversion.currencyConversionAlgorithm(amountGivenByUser, currencyService.getActualCurseOfFirstCurrencySelectedByUser(),
                currencyService.getActualCurseOfSecondCurrencySelectedByUser());
    }

    protected BigDecimal resultOfCalculateCourseAlgorithm() {
        return algorithmCurrencyConversion.calculateCourseAlgorithm(currencyService.getActualCurseOfFirstCurrencySelectedByUser(),
                currencyService.getActualCurseOfSecondCurrencySelectedByUser());
    }

    protected String firstCurrencySelectedByUserService() {
        String firstCurrencySelectedByUser;
        do {
            firstCurrencySelectedByUser= consoleReader.getString(myPrinter.enterFirstCurrency()).trim().toUpperCase();
            if (currencyService.checkCurrencyExist(firstCurrencySelectedByUser)) {
                currencyService.addFirstCurrencySelectedByUserToList(firstCurrencySelectedByUser);
                currencyService.sortingCurrenciesGivenByUserByDate(currencyService.getFirstCurrencySelectedByUser());
            } else {
                System.out.println(myPrinter.currencyUnexist());
            }
        } while (existCurrency(firstCurrencySelectedByUser));
        return firstCurrencySelectedByUser;
    }

    protected String secondCurrencySelectedByUserService() {
        String secondCurrencySelectedByUser;
        do {
            secondCurrencySelectedByUser = consoleReader.getString(myPrinter.enterSecondCurrency()).trim().toUpperCase();
            if (currencyService.checkCurrencyExist(secondCurrencySelectedByUser)){
                currencyService.addSecondCurrencySelectedByUserToList(secondCurrencySelectedByUser);
                currencyService.sortingCurrenciesGivenByUserByDate(currencyService.getSecondCurrencySelectedByUser());

            } else {
                System.out.println(myPrinter.nextLine());
                System.out.println(myPrinter.currencyUnexist());
            }
        }while (existCurrency(secondCurrencySelectedByUser));
        return secondCurrencySelectedByUser;
    }

    protected double amountGivenByUserService() {
        String strValue;
        String replace;
        Double amountGivenByUser = null;
        do {
            strValue = consoleReader.getString(myPrinter.enterAmount().trim());
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
        String strDate = consoleReader.getString(myPrinter.enterDate().trim());
        LocalDate date = null;
        try {
            date = dataService.dataParse(strDate);
        } catch (Exception ex) {
            System.out.println(myPrinter.wrongDate());
            dataService();
        }
        return date;
    }

    public boolean checkIfItIsANumber(String strValue) {
        return strValue.matches("[0-9 .]+");
    }

    public boolean existCurrency(String s) {
        if (currencyExist.checkCurrencyExist(s)) {
            return false;
        }
        return true;
    }

    private boolean checkIfInFirstChoiceContainsGivenDate(LocalDate date) {
        return currencyRepositoryHelper.containsDate(currencyService.getFirstCurrencySelectedByUser(), date);
    }

    private boolean checkIfInSecondChoiceContainsGivenDate(LocalDate date) {
        return currencyRepositoryHelper.containsDate(currencyService.getSecondCurrencySelectedByUser(), date);
    }

    protected boolean checkIfChoiceByUserContainsGivenDate(LocalDate date) {
        return checkIfInFirstChoiceContainsGivenDate(date) && checkIfInSecondChoiceContainsGivenDate(date);
    }
}
