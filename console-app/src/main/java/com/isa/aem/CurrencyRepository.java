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
        return currencies;
    }

    public static void setCurrencies(List<Currency> currencies) {
        Collections.sort(currencies);
        CurrencyRepository.currencies = currencies;
    }
}
