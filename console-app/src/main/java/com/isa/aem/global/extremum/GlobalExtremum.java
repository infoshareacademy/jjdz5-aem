package com.isa.aem.global.extremum;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.MenuInformation;
import com.isa.aem.tools.ConsolePrinter;
import com.isa.aem.tools.ConsoleReader;

public class GlobalExtremum {

    private ConsoleReader consoleReader = new ConsoleReader();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private MenuInformation menuInformation = new MenuInformation();
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private GlobalExtremumInputHandler globalExtremumInputHandler = new GlobalExtremumInputHandler();

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
        System.out.println(currencyRepository.listAvailableCurrency());
        selectedCurrencyOrOptionsOfMenuGlobalExtremum();
    }

    private void selectedCurrencyOrOptionsOfMenuGlobalExtremum() {
        do {
            optionGivenByUser = consoleReader.getString(consolePrinter.nextLine() + consolePrinter.enterCurrencyOrCommand()).trim().toUpperCase();
            checkIfCommandOfUserIsTrue(optionGivenByUser);
        } while (globalExtremumInputHandler.containCurrencyAndNumber(optionGivenByUser));
    }

    private void checkIfCommandOfUserIsTrue(String commandOfUser) {
        if (currencyRepository.containsCurrencyNameInCurrencyList(commandOfUser)) {
            globalExtremumInputHandler.printExtremeMenu(commandOfUser);
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
}