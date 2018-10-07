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
    private DataTransducerIntroducedByConsole dataTransducerIntroducedByConsole = new DataTransducerIntroducedByConsole();

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
        System.out.println(consolePrinter.listAvailableCurrency());
        System.out.println(currencyRepository.listAvailableCurrency());
        selectedCurrencyOrOptionsOfMenuGlobalExtremum();
    }

    private void selectedCurrencyOrOptionsOfMenuGlobalExtremum() {
        do {
            optionGivenByUser = consoleReader.getString(consolePrinter.nextLine() + consolePrinter.enterCurCom()).trim().toUpperCase();
            chcekerIfCommandOfUserIsTrue(optionGivenByUser);
        } while (dataTransducerIntroducedByConsole.containCurrencyAndNumber(optionGivenByUser));
    }

    private void chcekerIfCommandOfUserIsTrue(String commandOfUser) {
        if (currencyRepository.containsCurrencyNameInCurrencyList(commandOfUser)) {
            dataTransducerIntroducedByConsole.printExtremeMenu(commandOfUser);
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
                            consolePrinter.curComUnexist());
        }
    }
}