package com.isa.aem.globalextreme;

import com.isa.aem.Currency;
import com.isa.aem.tools.SingleCurrency;

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
}