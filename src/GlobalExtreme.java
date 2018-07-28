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
            String availableCurrency = consoleReader.getString("Wpisz dostępną walutę");
            String s = ignoreCase.upperSize(availableCurrency);

            if (isContains(currencyRepository.getCurrencies(), s)) {
                for (Currency c : currencyRepository.getCurrencies()) {
                    if (c.getName().equals(s)) {
                        singleCurrency.add(c);
                    }
                }
                sortSingleCurrency(singleCurrency);
                extreme();
                break;
            } else
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

    private void print() {
        for (Currency c:singleCurrency) {
            System.out.println(c);
        }
    }

    private Double getMin() {
        Currency currency = singleCurrency.get(0);
        return currency.getClose();
    }

    private Double getMax() {
        Currency currency = singleCurrency.get((singleCurrency.size()) - 1);
        return currency.getClose();
    }

    private LocalDate getData(int location) {
        Currency currency = singleCurrency.get(location);
        return currency.getDate();
    }


    private void extreme() {
        do {
            String choice = consoleReader.getString("Wprowadź jakie ekstremum Cię interesuje: \"min\" lub \"max\"");
            String lowerSize = ignoreCase.lowerSize(choice);
            if (lowerSize.equals("min") || lowerSize.equals("max")) {
                switch (lowerSize) {
                    case "min":
                        System.out.println(getMin() + " " + getData(0));
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        menuInformation.readMenu();
                        break;
                    case "max":
                        System.out.println(getMax() + " " + getData(singleCurrency.size()-1));
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        menuInformation.readMenu();
                        break;
                }
                break;
            }
            else if (lowerSize.equals("back")){
                menuInformation.readMenu();
                break;
            }
            else System.out.println("niepoprawna komenda, wpisz: \"min\" lub \"max\"\n" +
                    "lub wróć do menu wpisując: \"back\"");
        }while (true);
    }
    private boolean isContains(List<Currency> list, String s) {
        for (Currency c: list) {
        if (c.getName().equals(s))
            return true;
        }
        return false;
    }

}
