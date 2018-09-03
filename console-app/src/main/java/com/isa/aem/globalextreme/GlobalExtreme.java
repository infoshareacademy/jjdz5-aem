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
    private static final List<String> number = Arrays.asList("0", "1", "2", "9");

    private static final String BACK_TO_MENU = "0";

    public void run() {
        System.out.println(printer.globalExtremeTittle() + printer.backToMenu() + printer.nextLine());
        findCurrency();
    }

    void findCurrency() {
        String s;
        do {
            System.out.println(printer.listAblCur());
            listAvailableCurrency.run();
            String commandOFConsole = consoleReader.getString(printer.nextLine() + printer.enterCurCom());
            s = ignoreCase.upperSize(commandOFConsole).trim();
            checkConditionsGlobalMenu(s);
        } while (containCurrencyAndNumber(s));
    }

    private void checkConditionsGlobalMenu(String s) {
        if (checkCurrencyExist(s)) {
            addSingleCurrencyToList(s);
            singleCurrency.sortSingleCurrency();
            extreme();
        } else if (s.equals(BACK_TO_MENU)) {
            System.out.print(printer.dubleNextLine() + printer.pointLine() + printer.starsLine() +
                    printer.dubleNextLine());
            menuInformation.readMenu();
        } else
            System.out.println(printer.dubleNextLine() + printer.error() + printer.backToMenu() +
                    printer.nextLine() + printer.curComUnexist());
    }

    private void extreme() {
        String commandOfConsole;
        Integer parseCommandOfConsole;
        do {
            commandOfConsole = consoleReader.getString(printer.backToMenu() + printer.bakcCurSel() +
                    printer.extremeMenu());

            if (menuCondition(commandOfConsole)) {
                parseCommandOfConsole = Integer.parseInt(commandOfConsole);
                if (checkNumberOfExtremeOptions(parseCommandOfConsole)) {
                    switch (parseCommandOfConsole) {
                        case 2:
                            System.out.println(helper.getMin() + " " + helper.getDate(0));
                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            singleCurrency.clear();
                            smallMenu();
                            break;
                        case 9:
                            System.out.println(helper.getMax() + " " + helper.getDate(singleCurrency.getSingleCurrency().size() - 1));
                            try {
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            singleCurrency.clear();
                            smallMenu();
                            break;
                    }
                    break;
                }
                if (parseCommandOfConsole == 1) {
                    System.out.println(printer.dubleNextLine() + printer.pointLine() + printer.onlyPointLine() +
                            printer.dubleNextLine() + printer.backToMenu());
                    findCurrency();
                    break;
                } else if (parseCommandOfConsole == 0) {
                    System.out.print(printer.dubleNextLine() + printer.pointLine() + printer.starsLine() +
                            printer.dubleNextLine());
                    menuInformation.readMenu();
                    break;
                } else {
                    System.out.println(printer.dubleNextLine() + printer.error() + printer.dubleNextLine()
                            + printer.unknowCommand());
                }
            } else {
                System.out.println(printer.dubleNextLine() + printer.error() + printer.dubleNextLine()
                        + printer.unknowCommand());
                extreme();
            }
        } while (number.contains(commandOfConsole));
    }

    //private void

    private void smallMenu() {
        String string;
        int i;

        do {
            string = consoleReader.getString(printer.nextLine() + printer.pointLine() +
                    printer.pointLine() + printer.backToMenu() + printer.bakcCurSel() + printer.command());
            if (menuCondition(string)) {
                i = Integer.parseInt(string);
                if (i == 1) {
                    System.out.println(printer.dubleNextLine() + printer.pointLine() + printer.pointLine() +
                            printer.backToMenu() + printer.nextLine());
                    findCurrency();
                    break;
                } else if (i == 0) {
                    System.out.print(printer.dubleNextLine() + printer.pointLine() + printer.starsLine() +
                            printer.dubleNextLine());
                    menuInformation.readMenu();
                    break;
                } else {
                    System.out.println(printer.dubleNextLine() + printer.error() + printer.dubleNextLine()
                            + printer.unknowCommand());
                }
            } else {
                System.out.println(printer.dubleNextLine() + printer.error() + printer.dubleNextLine()
                        + printer.unknowCommand());
                smallMenu();
            }
        } while (number.contains(string));
    }

    private boolean checkCurrencyExist(String s) {
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

    private void addSingleCurrencyToList(String s) {
        for (Currency c : currencyRepository.getCurrencies()) {
            if (c.getName().equals(s)) {
                singleCurrency.add(c);
            }
        }
    }

    private boolean menuCondition(String string) {
        if (string.matches("\\d{0,1}") && string.length() > 0) {
            return true;
        }
        return false;
    }

    private boolean checkNumberOfExtremeOptions(int i) {
        if (i == 2 || i == 9) {
            return true;
        }
        return false;
    }

    public void readCommand(String s) {

    }
}