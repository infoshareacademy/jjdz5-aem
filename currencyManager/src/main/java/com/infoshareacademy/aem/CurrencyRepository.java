package com.infoshareacademy.aem;

import com.infoshareacademy.aem.Currency;

import java.util.ArrayList;

public class CurrencyRepository {

    private static ArrayList<Currency> currencies = new ArrayList<>();

    public static ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(ArrayList<Currency> currencies) {
        CurrencyRepository.currencies = currencies;
    }
}
