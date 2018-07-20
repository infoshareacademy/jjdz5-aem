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
        print();
    }


    private void menu() {

    }

    private void findCurrency() {
        fileContentReader.readFile();
        String availableCurrency = consoleReader.getString("Enter available currency");
        String s = ignoreCase.upperSize(availableCurrency);


        for (Currency c:fileContentReader.getCurrencies()) {
            if (c.getName().equals(s)){
                singleCurrency.add(c);
            }
        }
        for (Currency c: singleCurrency) {
            System.out.println(c);
        }
        sortSingleCurrency(singleCurrency);
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

}
