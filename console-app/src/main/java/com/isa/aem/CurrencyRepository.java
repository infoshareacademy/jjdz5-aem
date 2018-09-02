package com.isa.aem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyRepository {

    private static List<Currency> currencies = new ArrayList<>();

    public static List<Currency> getCurrencies() {
        return currencies;
    }

    public static List<Currency> getCurrenciesSortedByDateAsc() {
        Collections.sort(currencies);
        return currencies;
    }

    public static void setCurrencies(List<Currency> currencies) {
        CurrencyRepository.currencies = currencies;
    }
}
