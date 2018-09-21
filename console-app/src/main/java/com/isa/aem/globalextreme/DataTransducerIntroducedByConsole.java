package com.isa.aem.globalextreme;

import com.isa.aem.tools.*;

import java.util.Arrays;
import java.util.List;

public class DataTransducerIntroducedByConsole {

    private MyPrinter myPrinter = new MyPrinter();
    private CurrencyRepositoryHelper currencyRepositoryHelper = new CurrencyRepositoryBin();
    private ConsoleReader consoleReader = new ConsoleReader();
    private ExtremeService extremeService = new ExtremeService();
    private CurrencyExist currencyExist = new CurrencyExist();

    private List<String> menuCommand = Arrays.asList("0", "1");

    protected void findExtreme() {
        String currencySelectedByUser;
        do {
            currencySelectedByUser = consoleReader.getString(myPrinter.command());
            if (currencyExist.checkCurrencyExist(currencySelectedByUser)) {
                extremeService.addCurrencySelectedByUserToList(currencySelectedByUser);
                extremeService.sortingCurrenciesGivenByUserAtExchangingRate(extremeService.getCurrencySelectedByUser());
            }
            else {
                System.out.println(myPrinter.currencyUnexist());
            }
        } while (!currencyExist.checkCurrencyExist(currencySelectedByUser));
    }

    protected boolean containCurrencyAndNumber(String command) {
        if (currencyRepositoryHelper.containsCurrency(extremeService.getCurrencySelectedByUser(), command) || menuCommand.contains(command)) {
            return false;
        }
        return true;
    }

    protected void printExtremeMenu() {
        System.out.println(myPrinter.backToMenu() + myPrinter.bakcCurSel());
        System.out.println(myPrinter.extreme());
        System.out.println(myPrinter.nextLine() + myPrinter.min());
        System.out.println(myPrinter.emptySpace() + extremeService.getMin() + " " + extremeService.getMinDate());
        System.out.println(myPrinter.nextLine() + myPrinter.max());
        System.out.println(myPrinter.emptySpace() + extremeService.getMax() + " " + extremeService.getMaxDate());
    }






}
