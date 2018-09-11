package com.isa.aem.tools;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.util.Set;
import java.util.TreeSet;

public class ListAvailableCurrency {

    private static final Set<String> singleCurrency = new TreeSet<>();
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    public void run() {
        listofCurrencies();
        printCurrencues(singleCurrency);
    }

    public static Set<String> getSingleCurrency() {
        return singleCurrency;
    }

    private void listofCurrencies() {
        for (Currency c : currencyRepository.getCurrencies()) {
            singleCurrency.add(c.getName());
        }
    }

    private void printCurrencues(Set<String> set) {
        System.out.println(set);
    }
}