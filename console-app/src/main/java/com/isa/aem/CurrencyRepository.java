package com.isa.aem;

import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;
@Default
public class CurrencyRepository {

    private static List<Currency> currencies = new ArrayList<>();

    public static List<Currency> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(List<Currency> currencies) {
        CurrencyRepository.currencies = currencies;
    }

    public void grtdsddssd(CurrencyRepository currencyRepository){

    }

    public void add(Currency currency) {
        currencies.add(currency);
    }
}
