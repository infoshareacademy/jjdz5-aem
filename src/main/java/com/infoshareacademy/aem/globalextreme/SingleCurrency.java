package com.infoshareacademy.aem.globalextreme;

import com.infoshareacademy.aem.Currency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SingleCurrency {


    private static final List<Currency> singleCurrency = new ArrayList<>();
    private SortCurrency  sortCurrency = new SortCurrency();

    public void add(Currency currency) {
        singleCurrency.add(currency);
    }

    public List<Currency> getSingleCurrency() {
        return singleCurrency;
    }

    public void clear() {
        singleCurrency.clear();
    }

    class SortCurrency implements Comparator<Currency> {

        @Override
        public int compare(Currency o1, Currency o2) {
            return o1.getClose().compareTo(o2.getClose());
        }
    }
    public void sortSingleCurrency() {
        Collections.sort(singleCurrency, sortCurrency);
    }
}
