import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GlobalExtreme {

    private FileContentReader fileContentReader = new FileContentReader();
    private ConsoleReader consoleReader = new ConsoleReader();
    private IgnoreCase ignoreCase = new IgnoreCase();
    private SortCurrency sortCurrency = new SortCurrency();

    private List<Currency> singleCurrency = new ArrayList<>();


    public void run() {
        findCurrency();
        //print();
    }

    private void findCurrency() {
        fileContentReader.readFile();
        String availableCurrency = consoleReader.getString("Wpisz dostępną walutę");
        String s = ignoreCase.upperSize(availableCurrency);

        if (isContains(fileContentReader.getListOfCurrencies(),s)) {
            for (Currency c : fileContentReader.getCurrencies()) {
                if (c.getName().equals(s)) {
                    singleCurrency.add(c);
                }
            }
            sortSingleCurrency(singleCurrency);
            extreme();
        }
        else
            System.out.println("Waluta niedostępna, sprawdz dostępne waluty");
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

    private void extreme() {
        do {
            String choice = consoleReader.getString("Wprowadź jakie ekstremum Cię interesuje: \"min\" lub \"max\"");
            String lowerSize = ignoreCase.lowerSize(choice);
            if (lowerSize.equals("min") || lowerSize.equals("max")) {
                switch (lowerSize) {
                    case "min":
                        System.out.println(getMin());
                        break;
                    case "max":
                        System.out.println(getMax());
                        break;
                }
                break;
            }
            else if (lowerSize.equals("back")){
                break;
            }
            else System.out.println("niepoprawna komenda, wpisz: \"min\" lub \"max\"\n" +
                    "lub wróć wpisując: \"back\"");
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
