package com.isa.aem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyRepository {

    private static List<String> avalableCurrencies = new ArrayList<>();

    public static LocalDate getFirstDateFromRepository() {
        return currencies.get(0).getDate();
    }

    public static LocalDate getLastDateFromRepository() {
        return currencies.get(CurrencyRepository.getCurrencies().size()-1).getDate();
    }

    public static List<String> getAvailableCurrencies() {
        for (Currency currency : currencies) {
            if (!avalableCurrencies.contains(currency.getName())) {
                avalableCurrencies.add(currency.getName());
            } else {
                continue;
            }
        }
        return avalableCurrencies;
    }

    private static List<Currency> currencies = new ArrayList<>();

    public static List<Currency> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(List<Currency> currencies) {
        Collections.sort(currencies);
        CurrencyRepository.currencies = currencies;
    }
}
