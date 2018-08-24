package com.infoshareacademy.aem.global_extreme;

import com.infoshareacademy.aem.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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


    public void run() {
        System.out.println(printer.globalExtremeTittle() + printer.backToMenu() + printer.nextLine());
        findCurrency();
    }

    void findCurrency() {
        do {
            System.out.println(printer.listAblCur());
            listAvailableCurrency.run();
            String availableCurrency = consoleReader.getString(printer.nextLine() + printer.enterCurCom());
            String s = ignoreCase.upperSize(availableCurrency).trim();
            String trim = availableCurrency.trim();

            if (helper.isContains(currencyRepository.getCurrencies(), s)) {
                for (Currency c : currencyRepository.getCurrencies()) {
                    if (c.getName().equals(s)) {
                        singleCurrency.add(c);
                    }
                }
                singleCurrency.sortSingleCurrency();
                extreme();
                break;
            } else if (trim.equals("0")) {
                System.out.print(printer.dubleNextLine() + printer.pointLine() + printer.starsLine() +
                        printer.dubleNextLine());
                menuInformation.readMenu();
                break;
            } else
                System.out.println(printer.dubleNextLine() + printer.error() + printer.backToMenu() +
                        printer.nextLine() + printer.curComUnexist());
        } while (true);
    }

    private void extreme() {
        do {
            String choice = consoleReader.getString(printer.backToMenu() + printer.bakcCurSel() +
                    printer.extremeMenu());

            if (choice.matches("\\d{0,9}") && choice.length() > 0){
                int i = Integer.parseInt(choice);
                if (i == 2 || i == 9) {
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
                    }
                    break;
                } else if (i == 1) {
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
        } while (true);
    }

    private void smallMenu() {
        do {
            String string = consoleReader.getString(printer.nextLine() + printer.pointLine() +
                    printer.pointLine() + printer.backToMenu() + printer.bakcCurSel() + printer.command());
            if (string.matches("\\d{0,9}") && string.length() > 0) {
                int i = Integer.parseInt(string);
                if (i == 1) {
                    System.out.println(printer.dubleNextLine() + printer.pointLine() + printer.pointLine() +
                            printer.backToMenu() + printer.nextLine());
                    findCurrency();
                    break;
                } else if (i == 0) {
                    menuInformation.readMenu();
                    break;
                } else {
                    System.out.println(printer.dubleNextLine() + printer.error() + printer.dubleNextLine()
                            + printer.unknowCommand());
                }
            }
        } while (true);
    }

}
