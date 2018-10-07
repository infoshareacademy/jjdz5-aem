package com.isa.aem.global.extremum;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.tools.ConsolePrinter;

import java.util.Arrays;
import java.util.List;

public class DataTransducerIntroducedByConsole {

    private ConsolePrinter consolePrinter = new ConsolePrinter();

    private CurrencyRepository currencyRepository = new CurrencyRepository();

    private List<String> menuCommand = Arrays.asList("0", "1");

    protected boolean containCurrencyAndNumber(String commandOfUser) {
        return !(currencyRepository.containsCurrencyNameInCurrencyList(commandOfUser)
                || menuCommand.contains(commandOfUser));
    }

    protected void printExtremeMenu(String nameOfCurrency) {
        System.out.println(consolePrinter.backToMenu() + consolePrinter.bakcCurSel());
        System.out.println(consolePrinter.extreme());
        System.out.println(consolePrinter.nextLine() + consolePrinter.min());
        Double minRateOFExtremum = currencyRepository.getMinRateOfExtremum(nameOfCurrency);
        System.out.println(
                consolePrinter.emptySpace() +
                        nameOfCurrency + ": " +
                        minRateOFExtremum + " " +
                        currencyRepository.findDuplicate(minRateOFExtremum));
        System.out.println(consolePrinter.nextLine() + consolePrinter.max());
        Double maxRateOFExtremum = currencyRepository.getMaxRateOfExtremum(nameOfCurrency);
        System.out.println(
                consolePrinter.emptySpace() +
                        nameOfCurrency + ": " +
                        maxRateOFExtremum + " " +
                        currencyRepository.findDuplicate(maxRateOFExtremum));
    }
}
