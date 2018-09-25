package com.isa.aem.globalextreme;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.tools.*;
import java.util.Arrays;
import java.util.List;

public class DataTransducerIntroducedByConsole {

    private MyPrinter myPrinter = new MyPrinter();
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    private List<String> menuCommand = Arrays.asList("0", "1");

    protected boolean containCurrencyAndNumber(String commandOfUser) {
        return  !(currencyRepository.containsCurrencyNameInCurrencyList(commandOfUser)
                || menuCommand.contains(commandOfUser));
    }

    protected void printExtremeMenu(String nameOfCurrency) {
        System.out.println(myPrinter.backToMenu() + myPrinter.bakcCurSel());
        System.out.println(myPrinter.extreme());
        System.out.println(myPrinter.nextLine() + myPrinter.min());
        Double minRateOFExtremum = currencyRepository.getMinRateOfExtremum(nameOfCurrency);
        System.out.println(
                myPrinter.emptySpace() +
                nameOfCurrency + ": " +
                minRateOFExtremum + " " +
                currencyRepository.findDuplicate(minRateOFExtremum));
        System.out.println(myPrinter.nextLine() + myPrinter.max());
        Double maxRateOFExtremum = currencyRepository.getMaxRateOfExtremum(nameOfCurrency);
        System.out.println(
                myPrinter.emptySpace() +
                nameOfCurrency + ": " +
                maxRateOFExtremum + " " +
                currencyRepository.findDuplicate(maxRateOFExtremum));
    }
}
