package com.isa.aem.calc;

import com.isa.aem.tools.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Calculator {

    private CurrencyService currencyService = new CurrencyService();
    private Algorithm algorithm = new Algorithm();
    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private Checker checker = new Checker();
    private DateService dataService = new DateService();
    private CurrencyRepositoryHelper currencyRepositoryHelper = new CurrencyRepositoryBin();

    protected BigDecimal resultOfCurrencyConversionAlgorithm(Double amountGivenByUser) {
        return algorithm.currencyConversionAlgorithm(amountGivenByUser, currencyService.getActualCurseOfFirstCurrencySelectedByUser(),
                currencyService.getActualCurseOfSecondCurrencySelectedByUser());
    }

    protected BigDecimal resultOfCalculateCourseAlgorithm() {
        return algorithm.calculateCourseAlgorithm(currencyService.getActualCurseOfFirstCurrencySelectedByUser(),
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
        } while (checker.existCurrency(firstCurrencySelectedByUser));
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
        }while (checker.existCurrency(secondCurrencySelectedByUser));
        return secondCurrencySelectedByUser;
    }

    double amountGivenByUserService() {
        String strValue;
        String replace;
        Double amountGivenByUser = null;
        do {
            strValue = consoleReader.getString(myPrinter.enterAmount().trim());
            replace = strValue.replace(',', '.');
            if (checker.checkIfItIsANumber(replace)){
                amountGivenByUser = Double.parseDouble(replace);
            } else {
                System.out.println(myPrinter.numberUnexist());
            }
        } while (!checker.checkIfItIsANumber(replace));
        return amountGivenByUser;
    }

    LocalDate dataService() {
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

    protected boolean checkIfInFirstChoiceContainsGivenDate(LocalDate date) {
        return currencyRepositoryHelper.containsDate(currencyService.getFirstCurrencySelectedByUser(), date);
    }

    protected boolean checkIfInSecondChoiceContainsGivenDate(LocalDate date) {
        return currencyRepositoryHelper.containsDate(currencyService.getSecondCurrencySelectedByUser(), date);
    }

    protected boolean checkIfChoiceByUserContainsGivenDate(LocalDate date) {
        return checkIfInFirstChoiceContainsGivenDate(date) && checkIfInSecondChoiceContainsGivenDate(date);
    }
}
