package com.isa.aem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyRepository {

    private static List<Currency> currencies = new ArrayList<>();
    private static List<Currency> sortedCurrenciesByDateAsc;

    public static List<Currency> getCurrencies() {
        return currencies;
    }

    public static List<Currency> getCurrenciesSortedByDateAsc() {
        return sortCurrenciesByDateAsc();
    }

    public static void setCurrencies(List<Currency> currencies) {
        CurrencyRepository.currencies = currencies;
    }

    public static List<Currency> sortCurrenciesByDateAsc() {
        Collections.sort(currencies);
        return sortedCurrenciesByDateAsc;
    }
}
