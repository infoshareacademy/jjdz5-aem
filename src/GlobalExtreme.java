import java.util.ArrayList;
import java.util.List;

public class GlobalExtreme {

    private FileContentReader fileContentReader = new FileContentReader();
    private ListAvailableCurrency listAvailableCurrency = new ListAvailableCurrency();
    private ConsoleReader consoleReader = new ConsoleReader();
    private IgnoreCase ignoreCase = new IgnoreCase();

    public void run() {
        findCurrency();
    }



    private void findCurrency() {
        String availableCurrency = consoleReader.getString("Enter available currency");
        String s = ignoreCase.upperSize(availableCurrency);
        System.out.println(s);
        List<Currency> singleCurrency = new ArrayList<>();
        for (Currency c:fileContentReader.getCurrencies()) {
            if (c.getName().equals(s)){
                singleCurrency.add(c);
            }
        }
        for (Currency c: singleCurrency) {
            System.out.println(c);
        }
    }

}
