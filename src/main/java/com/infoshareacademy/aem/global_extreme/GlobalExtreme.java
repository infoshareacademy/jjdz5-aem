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
        System.out.println(printer.line1());
        findCurrency();


    }

    void findCurrency() {
        do {

            System.out.println(printer.line1_1());
            listAvailableCurrency.run();
            String availableCurrency = consoleReader.getString(printer.line2());
            String s = ignoreCase.upperSize(availableCurrency).trim();
            String S = ignoreCase.lowerSize(availableCurrency).trim();

            if (helper.isContains(currencyRepository.getCurrencies(), s)) {
                for (Currency c : currencyRepository.getCurrencies()) {
                    if (c.getName().equals(s)) {
                        singleCurrency.add(c);
                    }
                }
                singleCurrency.sortSingleCurrency();
                extreme();
                break;
            } else if (S.equals("m")) {
                System.out.print(printer.line4());
                menuInformation.readMenu();
                break;
            } else
                System.out.println(printer.line3());
        } while (true);
    }



    private void extreme() {
        do {
            String choice = consoleReader.getString(printer.line5());
            String lowerSize = ignoreCase.lowerSize(choice).trim();
            if (lowerSize.equals("n") || lowerSize.equals("x")) {
                switch (lowerSize) {
                    case "n":
                        System.out.println(helper.getMin()+" " + helper.getDate(0));
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        singleCurrency.clear();
                        smallMenu();
                        break;
                    case "x":
                        System.out.println(helper.getMax() + " " + helper.getDate(singleCurrency.getSingleCurrency().size()-1));
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        singleCurrency.clear();
                        smallMenu();


                }
                break;
            } else if (lowerSize.equals("b")) {
                System.out.println(printer.line6());
                findCurrency();
                break;
            }else if (lowerSize.equals("m")) {
                System.out.print(printer.line4());
                menuInformation.readMenu();
                break;
            } else System.out.println(printer.line7());
        } while (true);
    }


    private void smallMenu() {
        do {
            String string = consoleReader.getString(printer.line8());
            String S = ignoreCase.lowerSize(string).trim();
            if (S.equals("b")) {
                System.out.println(printer.line9());
                findCurrency();
                break;
            } else if (S.equals("m")) {
                menuInformation.readMenu();
                //System.out.println("Wpisz interesującą Cię opcje: ");
                break;
            } else {
                System.out.println(printer.line7());
            }
        } while (true);
    }

}
