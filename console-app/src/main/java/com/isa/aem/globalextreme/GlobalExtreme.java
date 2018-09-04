package com.isa.aem.globalextreme;

import com.isa.aem.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GlobalExtreme {

    private ConsoleReader consoleReader = new ConsoleReader();
    private IgnoreCase ignoreCase = new IgnoreCase();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private ListAvailableCurrency listAvailableCurrency = new ListAvailableCurrency();
    private MenuInformation menuInformation = new MenuInformation();
    private SingleCurrency singleCurrency = new SingleCurrency();
    private CurrencyRepositoryBin helper = new CurrencyRepositoryBin();
    private MyPrinter printer = new MyPrinter();
    private static final List<String> number = Arrays.asList("0", "1");

    private static final String BACK_TO_MENU_STR = "0";
    private static final Integer BACK_TO_MENU_INT = 0;
    private static final Integer BACK_TO_CURRENCY_SELECTION = 1;

    public void run() {
        System.out.println(printer.globalExtremeTittle() + printer.backToMenu() + printer.nextLine());
        findCurrency();
    }

    private void findCurrency() {
        String s;
        do {
            System.out.println(printer.listAblCur());
            listAvailableCurrency.run();
            String commandOfConsole = consoleReader.getString(printer.nextLine() + printer.enterCurCom());
            s = ignoreCase.upperSize(commandOfConsole).trim();
            checkConditionsGlobalMenu(s);
        } while (containCurrencyAndNumber(s));
    }

    private void findExtreme() {
        String commandOfConsole = null;
        Integer parseCommandOfConsole = null;
        do {
            printExtremeMenu();
            singleCurrency.clear();
            optionsOfFindExtreme(commandOfConsole,parseCommandOfConsole);
        } while (number.contains(commandOfConsole));
    }

    private void optionsOfFindExtreme(String commandOfConsole, Integer parseCommandOfConsole) {
        commandOfConsole = consoleReader.getString(printer.command());
        if (menuCondition(commandOfConsole)) {
            currencySelection(commandOfConsole, parseCommandOfConsole);
        } else {
            System.out.println(printer.dubleNextLine() + printer.error() + printer.dubleNextLine()
                    + printer.unknowCommand());
            System.out.println(printer.backToMenu() + printer.bakcCurSel());
            optionsOfFindExtreme(commandOfConsole, parseCommandOfConsole);
        }
    }

    private void checkConditionsGlobalMenu(String s) {
        if (checkCurrencyExist(s)) {
            addSingleCurrencyToList(s);
            singleCurrency.sortSingleCurrency();
            findExtreme();
        } else if (s.equals(BACK_TO_MENU_STR)) {
            System.out.print(printer.dubleNextLine() + printer.pointLine() + printer.starsLine() +
                    printer.dubleNextLine());
            menuInformation.readMenu();
        } else
            System.out.println(printer.dubleNextLine() + printer.error() + printer.backToMenu() +
                    printer.nextLine() + printer.curComUnexist());
    }

    private void currencySelection(String commandOfConsole, Integer parseCommandOfConsole) {
        parseCommandOfConsole = Integer.parseInt(commandOfConsole);

        if (checkNavigationCommand(parseCommandOfConsole, BACK_TO_CURRENCY_SELECTION)) {
            System.out.println(printer.dubleNextLine() + printer.pointLine() + printer.onlyPointLine() +
                    printer.dubleNextLine() + printer.backToMenu());
            findCurrency();
        } else if (checkNavigationCommand(parseCommandOfConsole, BACK_TO_MENU_INT)) {
            System.out.print(printer.dubleNextLine() + printer.pointLine() + printer.starsLine() +
                    printer.dubleNextLine());
            menuInformation.readMenu();
        } else {
            System.out.println(printer.dubleNextLine() + printer.error() + printer.dubleNextLine()
                    + printer.unknowCommand());
        }
    }

    private void printExtremeMenu() {
        System.out.println(printer.backToMenu() + printer.bakcCurSel());
        System.out.println(printer.extreme());
        System.out.println(printer.nextLine() + printer.min());
        System.out.println(printer.emptySpace() + helper.getMin() + " " + helper.getDate(0));
        System.out.println(printer.nextLine() + printer.max());
        System.out.println(printer.emptySpace() + helper.getMax() + " " + helper.getDate(singleCurrency.getSingleCurrency().size() - 1));
    }

    public boolean checkCurrencyExist(String s) {
        if (helper.containsCurrency(currencyRepository.getCurrencies(), s)){
            return true;
        }
        return false;
    }

    private boolean containCurrencyAndNumber(String s) {
        if (helper.containsCurrency(currencyRepository.getCurrencies(), s) || number.contains(s)) {
            return false;
        }
        return true;
    }

    public void addSingleCurrencyToList(String s) {
        for (Currency c : currencyRepository.getCurrencies()) {
            if (c.getName().equals(s)) {
                singleCurrency.add(c);
            }
        }
    }

    private boolean checkNavigationCommand(Integer inputValue, Integer condition) {
        if (inputValue == condition){
            return true;
        }
        return false;
    }

    private boolean menuCondition(String string) {
        if (string.matches("\\d{0,1}") && string.length() > 0) {
            return true;
        }
        return false;
    }
}