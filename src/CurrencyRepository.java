import java.util.ArrayList;

public class CurrencyRepository {

    private static ArrayList<Currency> currencies = new ArrayList<>();

    public CurrencyRepository(){

    }

    public CurrencyRepository(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    public static ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(ArrayList<Currency> currencies) {
        CurrencyRepository.currencies = currencies;
    }

    public static void getName() {
        for (Currency c:currencies) {
            System.out.println(c.getName());
        }
    }
}
