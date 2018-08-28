package com.isa.aem.globalextreme;

import com.isa.aem.Currency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRepositoryBin implements CurrencyRepositoryHelper {

    SingleCurrency singleCurrency = new SingleCurrency();

    @Override
    public Double getMin() {
        Currency currency = singleCurrency.getSingleCurrency().get(0);
        return currency.getClose();
    }

    @Override
    public Double getMax() {
        Currency currency = singleCurrency.getSingleCurrency().get(singleCurrency.getSingleCurrency().size() - 1);
        return currency.getClose();
    }

    @Override
    public boolean contains(List<Currency> list, String s) {
        for (Currency c : list) {
            if (c.getName().equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<LocalDate> getDate(Integer location) {
        List<LocalDate> date = new ArrayList<>();
        Currency currency = singleCurrency.getSingleCurrency().get(location);
        for (Currency c : singleCurrency.getSingleCurrency()) {
            if (currency.getClose().equals(c.getClose())) {
                date.add(c.getDate());
            }
        }
        return date;
    }
}