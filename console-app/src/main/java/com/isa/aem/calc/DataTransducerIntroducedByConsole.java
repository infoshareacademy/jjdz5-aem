package com.isa.aem.calc;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.tools.*;

import java.time.LocalDate;

public class DataTransducerIntroducedByConsole {

    private CurrencyService currencyService = new CurrencyService();
    private AlgorithmCurrencyConversion algorithmCurrencyConversion = new AlgorithmCurrencyConversion();
    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private DateService dataService = new DateService();
    private CurrencyRepositoryHelper currencyRepositoryHelper = new CurrencyRepositoryBin();
    private CurrencyExist currencyExist = new CurrencyExist();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private String currencyNameSelectedByUser;


    protected String getCurrencySelectedByUserOfConsole(String commandForUser) {
        return consoleReader.getString(commandForUser).trim().toUpperCase();
    }

    protected Double currencySelectedByUserService(String commandForUser) {
        Double mostCurrentExchangedRateOFSelectedCurrencyFromTheFile = null;
        do {
            currencyNameSelectedByUser = getCurrencySelectedByUserOfConsole(commandForUser);
            if (currencyRepository.containsCurrencyNameInCurrencyList(currencyNameSelectedByUser)) {
                mostCurrentExchangedRateOFSelectedCurrencyFromTheFile = currencyRepository
                        .getMostCurrentExchangedRateOFSelectedCurrencyFromTheFile(currencyNameSelectedByUser);
            } else {
                System.out.println(myPrinter.currencyUnexist());
            }
        } while (existCurrency(currencyNameSelectedByUser));
        return mostCurrentExchangedRateOFSelectedCurrencyFromTheFile;
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

    protected boolean checkIfCurrencyNameSelectedByUserContainsGivenDate(
            String firstNameOfCurrency, String secondNameOfCurrency, LocalDate date) {
        return currencyRepository.checkIfExistCurrencyWithGivenDate(firstNameOfCurrency, date)
                && currencyRepository.checkIfExistCurrencyWithGivenDate(secondNameOfCurrency, date);
    }

    public String getCurrencyNameSelectedByUser() {
        return currencyNameSelectedByUser;
    }
}
