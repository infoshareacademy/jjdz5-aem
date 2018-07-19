import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListAvailableCurrency {

    private FileContentReader fileContentReader = new FileContentReader();
    private final Set<String> singleCurrency = new HashSet<>();

    public void run() {
        System.out.println(fileContentReader.getListOfCurrencies());
        listofCurrencies();
        printCurrencues(singleCurrency);

    }

    private void listofCurrencies() {
        for (Currency c:fileContentReader.getCurrencies()) {
            singleCurrency.add(c.getName());
        }
    }

    private void printCurrencues(Set<String> set) {
        for (String s:set) {
            System.out.println(s);
        }
    }
}
