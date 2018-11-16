package com.isa.aem.rate_extremums;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.MenuInformation;
import com.isa.aem.utils.ConsolePrinter;
import com.isa.aem.utils.ConsoleReader;

import java.util.List;

public class GlobalExtremumConsoleHandler {

    private ConsoleReader consoleReader = new ConsoleReader();
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private MenuInformation menuInformation = new MenuInformation();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private ExchangeRateExtremum exchangeRateExtremum = new ExchangeRateExtremum();
    private static final String BACK_TO_MENU_STR = "0";
    private static final String BACK_TO_CURRENCY_SELECTION = "1";
    private String optionGivenByUser;

    public void run() {
        System.out.println(
                consolePrinter.globalExtremeTittle() +
                        consolePrinter.backToMenu() +
                        consolePrinter.nextLine());
        searchExtremum();
    }

    private void searchExtremum() {
        System.out.println(consolePrinter.listAvailableCurrencies());
        System.out.println(currencyRepository.getAvailableCurrencyNames());
        selectedCurrencyOrOptionsOfMenuGlobalExtremum();
    }

    private void selectedCurrencyOrOptionsOfMenuGlobalExtremum() {
        do {
            optionGivenByUser = consoleReader.getString(consolePrinter.nextLine() + consolePrinter.enterCurrencyOrCommand()).trim().toUpperCase();
            checkIfCommandOfUserIsTrue(optionGivenByUser);
        } while (containsCurrencyOrNumber(optionGivenByUser));
    }

    private void checkIfCommandOfUserIsTrue(String commandOfUser) {
        if (currencyRepository.containsCurrencyNameInCurrencyList(commandOfUser)) {
            printExtremeMenu(commandOfUser);
            selectedCurrencyOrOptionsOfMenuGlobalExtremum();
        } else if (BACK_TO_CURRENCY_SELECTION.equals(commandOfUser)) {
            System.out.println(consolePrinter.doubleNextLine());
            searchExtremum();
        } else if (BACK_TO_MENU_STR.equals(commandOfUser)) {
            System.out.print(
                    consolePrinter.doubleNextLine() +
                            consolePrinter.pointLine() +
                            consolePrinter.starsLine() +
                            consolePrinter.doubleNextLine());
            menuInformation.readMenu();
        } else {
            System.out.println(
                    consolePrinter.doubleNextLine() +
                            consolePrinter.error() +
                            consolePrinter.backToMenu() +
                            consolePrinter.nextLine() +
                            consolePrinter.currencyOrCommandDoesNotExist());
        }
    }

    protected boolean containsCurrencyOrNumber(String commandOfUser) {
        return !(currencyRepository.containsCurrencyNameInCurrencyList(commandOfUser)
                || BACK_TO_MENU_STR.equals(commandOfUser) || BACK_TO_CURRENCY_SELECTION.equals(commandOfUser));
    }

    protected void printExtremeMenu(String nameOfCurrency) {
        System.out.println(consolePrinter.backToMenu() + consolePrinter.backToCurrencySelection());

        System.out.println(consolePrinter.min() + " " + nameOfCurrency.toUpperCase() + ":");
        List<Currency> minRateOFExtremum = exchangeRateExtremum.getMinExtremum(nameOfCurrency, null, null);
        minRateOFExtremum.stream()
                .forEach(currency -> System.out.println(consolePrinter.emptySpace() + consolePrinter.emptySpace() + currency.getClose() + " [" + currency.getDate() + "]"));

        System.out.println(consolePrinter.nextLine() + consolePrinter.max() + " " + nameOfCurrency.toUpperCase() + ":");
        List<Currency> maxRateOFExtremum = exchangeRateExtremum.getMaxExtremum(nameOfCurrency, null, null);
        maxRateOFExtremum.stream()
                .forEach(currency -> System.out.println(consolePrinter.emptySpace() + consolePrinter.emptySpace() + currency.getClose() + " [" + currency.getDate() + "]"));
    }
}