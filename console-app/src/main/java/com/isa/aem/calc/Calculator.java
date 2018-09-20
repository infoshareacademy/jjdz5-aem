package com.isa.aem.calc;

import com.isa.aem.tools.Checker;
import com.isa.aem.tools.ConsoleReader;
import com.isa.aem.tools.MyPrinter;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Calculator {

    @Inject
    CurrencyServis currencyServis;
    @Inject
    Algorithm algorithm;

    @Inject
    ConsoleReader consoleReader;
    @Inject
    MyPrinter myPrinter;
    @Inject
    Checker checker;
    private BigDecimal resultOfCalculateCourse;
    private BigDecimal resultOfCurrencyConversion;


    protected BigDecimal resultOfCurrencyConversionAlgorithm(Double amountGivenByUser) {
        return algorithm.currencyConversionAlgorithm(amountGivenByUser, currencyServis.getActualCurseOfFirstCurrencySelectedByUser(),
                currencyServis.getActualCurseOfSecondCurrencySelectedByUser());

    }

    protected BigDecimal resultOfCalculateCourseAlgorithm() {
        return algorithm.calculateCourseAlgorithm(currencyServis.getActualCurseOfFirstCurrencySelectedByUser(),
                currencyServis.getActualCurseOfSecondCurrencySelectedByUser());

    }

    protected String firstCurrencySelectedByUserService() {
        String firstCurrencySelectedByUser;
        do {
            firstCurrencySelectedByUser= consoleReader.getString(myPrinter.enterFirstCurrency()).trim().toUpperCase();
            if (currencyServis.checkCurrencyExist(firstCurrencySelectedByUser)) {
                currencyServis.addFirstCurrencySelectedByUserToList(firstCurrencySelectedByUser);
                currencyServis.sortingCurrenciesGivenByUserByDate(currencyServis.getFirstCurrencySelectedByUser());
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
            if (currencyServis.checkCurrencyExist(secondCurrencySelectedByUser)){
                currencyServis.addSecondCurrencySelectedByUserToList(secondCurrencySelectedByUser);
                currencyServis.sortingCurrenciesGivenByUserByDate(currencyServis.getSecondCurrencySelectedByUser());

            } else {
                System.out.println(myPrinter.nextLine());
                System.out.println(myPrinter.currencyUnexist());
            }
        }while (checker.existCurrency(secondCurrencySelectedByUser));
        return secondCurrencySelectedByUser;
    }

    double amountGivenByUserServis() {
        String strValue;
        String replace;
        Double amountGivenByUser = null;
        do {
            strValue = consoleReader.getString(myPrinter.enterAmount());
            replace = strValue.replace(',', '.');
            if (checker.checkIfItIsANumber(replace)){
                amountGivenByUser = Double.parseDouble(replace);
            } else {
                System.out.println(myPrinter.numberUnexist());
            }
        } while (!checker.checkIfItIsANumber(replace));
        return amountGivenByUser;
    }

    protected boolean checkIfChoiceByUserContainsGivenDate(LocalDate date) {
        return currencyServis.checkIfInFirstChoiceContainsGivenDate(date) && currencyServis.checkIfInSecondChoiceContainsGivenDate(date);
    }







}
