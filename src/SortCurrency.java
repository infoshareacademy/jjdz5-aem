import java.util.Comparator;
import java.util.Currency;

public class SortCurrency implements Comparator<Currency> {

    @Override
    public int compare(Currency o1, Currency o2) {
        String currencyFirst = o1.getCurrencyCode();
        String currencySecond= o2.getCurrencyCode();

        return currencyFirst.compareTo(currencySecond);

    }
}
