package com.infoshareacademy.aem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GlobalExtreme {

    private ConsoleReader consoleReader = new ConsoleReader();
    private IgnoreCase ignoreCase = new IgnoreCase();
    private SortCurrency sortCurrency = new SortCurrency();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    ListAvailableCurrency listAvailableCurrency = new ListAvailableCurrency();
    private MenuInformation menuInformation = new MenuInformation();

    private List<Currency> singleCurrency = new ArrayList<>();


    public void run() {
        listAvailableCurrency.run();
        findCurrency();
        //print();
    }

    private void findCurrency() {
        do {
            String availableCurrency = consoleReader.getString("Wpisz dostępną walutę lub wpisz \"menu\" jeżeli chcesz wrócić do głównego menu");
            String s = ignoreCase.upperSize(availableCurrency);
            String S = ignoreCase.lowerSize(availableCurrency);

            if (isContains(currencyRepository.getCurrencies(), s)) {
                for (Currency c : currencyRepository.getCurrencies()) {
                    if (c.getName().equals(s)) {
                        singleCurrency.add(c);
                    }
                }
                sortSingleCurrency(singleCurrency);
                extreme();
                break;
            }
            else if (S.equals("menu")){
                menuInformation.readMenu();
                System.out.print("Wybierz interesującą Cię opcje: ");
                break;
            }
            else
                System.out.println("Waluta niedostępna, sprawdz dostępne waluty");
        } while (true);
    }


    class SortCurrency implements Comparator<Currency> {
        @Override
        public int compare(Currency o1, Currency o2) {
            return o1.getClose().compareTo(o2.getClose());
        }
    }

    private void sortSingleCurrency(List<Currency> list) {
        Collections.sort(list,sortCurrency);
    }


    private Double getMin() {
        Currency currency = singleCurrency.get(0);
        return currency.getClose();
    }

    private Double getMax() {
        Currency currency = singleCurrency.get((singleCurrency.size()) - 1);
        return currency.getClose();
    }

    private List<LocalDate> getData(int location) {
        List<LocalDate> date = new ArrayList<>();
        Currency currency = singleCurrency.get(location);
        for (Currency c: singleCurrency) {
            if (currency.getClose().equals(c.getClose())) {
                date.add(c.getDate());
            }
        }
           return date;
    }

    private void extreme() {
        do {
            String choice = consoleReader.getString("Wprowadź jakie ekstremum Cię interesuje: \"min\" lub \"max\"\n" +
                    "możesz też wrócić do wyboru waluty wpisując: \"waluta\" ");
            String lowerSize = ignoreCase.lowerSize(choice).trim();
            if (lowerSize.equals("min") || lowerSize.equals("max")) {
                switch (lowerSize) {
                    case "min":
                        System.out.println(getMin() + " " + getData(0));
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        singleCurrency.clear();
                        smallMenu();
                        break;
                    case "max":
                        System.out.println(getMax() + " " + getData(singleCurrency.size()-1));
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        singleCurrency.clear();
                        smallMenu();


                }
                break;
            }
            else if (lowerSize.equals("waluta")){
                findCurrency();
                break;
            }
            else System.out.println("Niepoprawna komenda!");
        }while (true);
    }
    private boolean isContains(List<Currency> list, String s) {
        for (Currency c: list) {
        if (c.getName().equals(s))
            return true;
        }
        return false;
    }

    private void smallMenu() {
        do {
            String string = consoleReader.getString("Jeżeli chcesz kontynuować wpisywanie ekstremów wpisz: \"dalej\"\n" +
                    "Jeżeli chcesz wyjść do menu wpisz: \"menu\"");
            if (string.equals("dalej")){
                findCurrency();
                break;
            }
            else if (string.equals("menu")){
                menuInformation.readMenu();
                System.out.println("Wpisz interesującą Cię opcje: ");
                break;
            }
            else {
                System.out.println("Niepoprawna komenda!");
            }
        } while (true);
    }

}
