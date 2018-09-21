package com.isa.aem.globalextreme;

import com.isa.aem.*;
import com.isa.aem.tools.*;

public class GlobalExtreme {

    private ConsoleReader consoleReader = new ConsoleReader();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private ListAvailableCurrency listAvailableCurrency = new ListAvailableCurrency();
    private MenuInformation menuInformation = new MenuInformation();
    private SingleCurrency singleCurrency = new SingleCurrency();
    private MyPrinter myPrinter = new MyPrinter();
    private CurrencyExist currencyExist = new CurrencyExist();
    private CurrencyRepositoryHelper currencyRepositoryHelper = new CurrencyRepositoryBin();
    private DataTransducerIntroducedByConsole dataTransducerIntroducedByConsole = new DataTransducerIntroducedByConsole();


    private static final String BACK_TO_MENU_STR = "0";
    private static final Integer BACK_TO_MENU_INT = 0;
    private static final Integer BACK_TO_CURRENCY_SELECTION = 1;

    public void run() {
        System.out.println(myPrinter.globalExtremeTittle() + myPrinter.backToMenu() + myPrinter.nextLine());
        smallMenu();
    }

    void smallMenu() {
        String optionGivenByUser;
        do {
            System.out.println(myPrinter.listAblCur());
            listAvailableCurrency.print();
            optionGivenByUser = consoleReader.getString(myPrinter.nextLine() + myPrinter.enterCurCom()).trim().toUpperCase();
            menuOptions(optionGivenByUser);
        } while (dataTransducerIntroducedByConsole.containCurrencyAndNumber(optionGivenByUser));
    }

    private void menuOptions(String s) {
        if (currencyExist.checkCurrencyExist(s)) {
            foldingExtreme();
        }
        else if (s.equals(BACK_TO_MENU_STR)) {
            System.out.print(myPrinter.dubleNextLine() + myPrinter.pointLine() + myPrinter.starsLine() +
                    myPrinter.dubleNextLine());
            menuInformation.readMenu();
        }
        else
            System.out.println(myPrinter.dubleNextLine() + myPrinter.error() + myPrinter.backToMenu() +
                    myPrinter.nextLine() + myPrinter.curComUnexist());
    }

    private void foldingExtreme() {
        dataTransducerIntroducedByConsole.findExtreme();
        dataTransducerIntroducedByConsole.printExtremeMenu();
    }








    private void optionsOfFindExtreme(String commandOfConsole, Integer parseCommandOfConsole) {
        commandOfConsole = consoleReader.getString(myPrinter.command());
        if (menuCondition(commandOfConsole)) {
            currencySelection(commandOfConsole, parseCommandOfConsole);
        } else {
            System.out.println(myPrinter.dubleNextLine() + myPrinter.error() + myPrinter.dubleNextLine()
                    + myPrinter.unknowCommand());
            System.out.println(myPrinter.backToMenu() + myPrinter.bakcCurSel());
            optionsOfFindExtreme(commandOfConsole, parseCommandOfConsole);
        }
    }



    private void currencySelection(String commandOfConsole, Integer parseCommandOfConsole) {
        parseCommandOfConsole = Integer.parseInt(commandOfConsole);

        if (checkNavigationCommand(parseCommandOfConsole, BACK_TO_CURRENCY_SELECTION)) {
            System.out.println(myPrinter.dubleNextLine() + myPrinter.pointLine() + myPrinter.onlyPointLine() +
                    myPrinter.dubleNextLine() + myPrinter.backToMenu());
        } else if (checkNavigationCommand(parseCommandOfConsole, BACK_TO_MENU_INT)) {
            System.out.print(myPrinter.dubleNextLine() + myPrinter.pointLine() + myPrinter.starsLine() +
                    myPrinter.dubleNextLine());
            menuInformation.readMenu();
        } else {
            System.out.println(myPrinter.dubleNextLine() + myPrinter.error() + myPrinter.dubleNextLine()
                    + myPrinter.unknowCommand());
        }
    }





    public void addSingleCurrencyToList(String s) {
        for (Currency c : currencyRepository.getCurrencies()) {
            if (c.getName().equals(s)) {
                singleCurrency.add(c);
            }
        }
    }

    private boolean checkNavigationCommand(Integer inputValue, Integer condition) {
        return inputValue == condition;
    }

    private boolean menuCondition(String string) {
        if (string.matches("\\d{0,1}") && string.length() > 0) {
            return true;
        }
        return false;
    }
}