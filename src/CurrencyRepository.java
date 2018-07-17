import java.util.ArrayList;

public class CurrencyRepository {

    private final static ArrayList<Currency> currencies = new ArrayList<>();


    public void addCurrency(Currency currency) {
        currencies.add(currency);
    }

    public ArrayList<Currency> find() {
        return currencies;
    }

    public void findName() {
        for (Currency c:currencies) {
            System.out.println(c.getName());
        }
    }
}
