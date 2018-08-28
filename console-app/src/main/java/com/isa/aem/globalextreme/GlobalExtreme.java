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
    private static final List<String> number = Arrays.asList("0","1","2","9");

    private static final String ZERO = "0";

    public void run() {
        System.out.println(printer.globalExtremeTittle() + printer.backToMenu() + printer.nextLine());
        findCurrency();
    }

    void findCurrency() {
        String s;
        do {
            System.out.println(printer.listAblCur());
            listAvailableCurrency.run();
            String availableCurrency = consoleReader.getString(printer.nextLine() + printer.enterCurCom());
            s = ignoreCase.upperSize(availableCurrency).trim();
            //String trim = availableCurrency.trim();

            if (helper.isContains(currencyRepository.getCurrencies(), s)) {
                for (Currency c : currencyRepository.getCurrencies()) {
                    if (c.getName().equals(s)) {
                        singleCurrency.add(c);
                    }
                }
                singleCurrency.sortSingleCurrency();
                extreme();
                break;
            } else if (s.equals(ZERO)) {
                System.out.print(printer.dubleNextLine() + printer.pointLine() + printer.starsLine() +
                        printer.dubleNextLine());
                menuInformation.readMenu();
                break;
            } else
                System.out.println(printer.dubleNextLine() + printer.error() + printer.backToMenu() +
                        printer.nextLine() + printer.curComUnexist());

        } while (containCurrencAndNnmber(s));
    }

    private boolean containCurrencAndNnmber(String s ) {
        if (currencyRepository.getCurrencies().contains(s) || number.contains(s)){
            return false;
        }
        return true;
    }

    private void extreme() {
        String choice;
        int i;
        do {
            choice = consoleReader.getString(printer.backToMenu() + printer.bakcCurSel() +
                    printer.extremeMenu());

            if (menuCondition(choice)){
                i = Integer.parseInt(choice);
                if (switchCase(i)) {
                    switch (i) {
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
                } if (i == 1) {
                    System.out.println(printer.dubleNextLine() + printer.pointLine() + printer.onlyPointLine() +
                            printer.dubleNextLine() + printer.backToMenu());
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
            }
            else {
                System.out.println(printer.dubleNextLine() + printer.error() + printer.dubleNextLine()
                        + printer.unknowCommand());
                extreme();
            }
        } while (number.contains(choice));
    }

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

    private boolean menuCondition(String string) {
        if (string.matches("\\d{0,1}") && string.length() > 0) {
            return true;
        }
        return false;
    }

    private boolean switchCase(int i) {
        if (i == 2 || i == 9){
            return true;
        }
        return false;
    }
}