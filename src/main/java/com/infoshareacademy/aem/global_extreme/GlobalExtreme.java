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
    private CurrencyRepositoryHelper helper = new CurrencyRepositoryBin();
    private MyPrinter printer = new MyPrinter();


    public void run() {
        listAvailableCurrency.run();
        findCurrency();
        //print();

    }

    void findCurrency() {
        do {
            String availableCurrency = consoleReader.getString(printer.line1());
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
                menuInformation.readMenu();
                System.out.print("Wybierz interesującą Cię opcje: ");
                break;
            } else
                System.out.println("Waluta niedostępna, sprawdz dostępne waluty");
        } while (true);
    }



    private void extreme() {
        do {
            String choice = consoleReader.getString("Wprowadź jakie ekstremum Cię interesuje: \"min\" lub \"max\"\n" +
                    "możesz też wrócić do wyboru waluty wpisując: \"waluta\" ");
            String lowerSize = ignoreCase.lowerSize(choice).trim();
            if (lowerSize.equals("min") || lowerSize.equals("max")) {
                switch (lowerSize) {
                    case "min":
                        System.out.println(helper.getMin() + " " + helper.getDate(0));
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        singleCurrency.clear();
                        smallMenu();
                        break;
                    case "max":
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
            } else if (lowerSize.equals("waluta")) {
                findCurrency();
                break;
            } else System.out.println("Niepoprawna komenda!");
        } while (true);
    }


    private void smallMenu() {
        do {
            String string = consoleReader.getString("Jeżeli chcesz kontynuować wpisywanie ekstremów wpisz: \"dalej\"\n" +
                    "Jeżeli chcesz wyjść do menu wpisz: \"menu\"");
            if (string.equals("dalej")) {
                findCurrency();
                break;
            } else if (string.equals("menu")) {
                menuInformation.readMenu();
                System.out.println("Wpisz interesującą Cię opcje: ");
                break;
            } else {
                System.out.println("Niepoprawna komenda!");
            }
        } while (true);
    }

}
