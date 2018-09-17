package com.isa.aem.tools;

import com.isa.aem.Currency;

import javax.faces.bean.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
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
    public boolean containsCurrency(List<Currency> list, String s) {
        for (Currency c : list) {
            if (c.getName().equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public LocalDate getMinDate() {
        Currency currency = singleCurrency.getSingleCurrency().get(0);
        return currency.getDate();
    }

    @Override
    public LocalDate getMaxDate() {
        singleCurrency.getSingleCurrency().get(singleCurrency.getSingleCurrency().size()-1);
        return null;
    }

    @Override
    public boolean containsDate(List<Currency> list, LocalDate date) {
        for (Currency c: list) {
            if (c.getDate().equals(date)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Double courseByDate(List<Currency> list, LocalDate date) {
        for (Currency c : list) {
            if (c.getDate().equals(date)){
                return c.getClose();
            }
        }
        return null;
    }
}