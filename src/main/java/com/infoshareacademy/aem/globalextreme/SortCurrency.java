package com.infoshareacademy.aem.globalextreme;

import com.infoshareacademy.aem.Currency;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCurrency implements Comparator<Currency> {

    SortCurrency sortCurrency = new SortCurrency();

    @Override
    public int compare(Currency o1, Currency o2) {
        return o1.getClose().compareTo(o2.getClose());
    }

    public void sortSingleCurrency(List<Currency> list) {
        Collections.sort(list, sortCurrency);
    }

}
