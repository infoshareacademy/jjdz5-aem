package com.isa.aem.helpers;

import com.isa.aem.Currency;

import java.util.Comparator;


public class SortCurrency implements Comparator<Currency> {


    @Override
    public int compare(Currency o1, Currency o2) {
        String currencyFirst = o1.getName();
        String currencySecond = o2.getName();

        return currencyFirst.compareTo(currencySecond);
    }
}
