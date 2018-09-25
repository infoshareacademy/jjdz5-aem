package com.isa.aem.globalextremum;

import com.isa.aem.*;
import com.isa.aem.tools.*;

public class GlobalExtremum {

    private ConsoleReader consoleReader = new ConsoleReader();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private MenuInformation menuInformation = new MenuInformation();
    private MyPrinter myPrinter = new MyPrinter();
    private DataTransducerIntroducedByConsole dataTransducerIntroducedByConsole = new DataTransducerIntroducedByConsole();

    private static final String BACK_TO_MENU_STR = "0";
    private static final String  BACK_TO_CURRENCY_SELECTION = "1";
    private String optionGivenByUser;

    public void run() {
        System.out.println(
                myPrinter.globalExtremeTittle() +
                myPrinter.backToMenu() +
                myPrinter.nextLine());
        searchExstremeum();
    }

    private void searchExstremeum() {
        System.out.println(myPrinter.listAblCur());
        System.out.println(currencyRepository.listAvailableCurrency());
        selectedCurrencyOrOptionsOfMenuGlobalExtremuem();
    }

    private void selectedCurrencyOrOptionsOfMenuGlobalExtremuem() {
        do {
            optionGivenByUser = consoleReader.getString(myPrinter.nextLine() + myPrinter.enterCurCom()).trim().toUpperCase();
            menuOptions(optionGivenByUser);
        } while (dataTransducerIntroducedByConsole.containCurrencyAndNumber(optionGivenByUser));
    }

    private void menuOptions(String commandOfUser) {
        if (currencyRepository.containsCurrencyNameInCurrencyList(commandOfUser)) {
            dataTransducerIntroducedByConsole.printExtremeMenu(commandOfUser);
            selectedCurrencyOrOptionsOfMenuGlobalExtremuem();
        }
        else if (commandOfUser.equals(BACK_TO_CURRENCY_SELECTION)){
            System.out.println(myPrinter.dubleNextLine());
           searchExstremeum();
        }
        else if (commandOfUser.equals(BACK_TO_MENU_STR)) {
            System.out.print(
                    myPrinter.dubleNextLine() +
                    myPrinter.pointLine() +
                    myPrinter.starsLine() +
                    myPrinter.dubleNextLine());
            menuInformation.readMenu();
        }
        else {
            System.out.println(
                    myPrinter.dubleNextLine() +
                            myPrinter.error() +
                            myPrinter.backToMenu() +
                            myPrinter.nextLine() +
                            myPrinter.curComUnexist());
        }
    }
}