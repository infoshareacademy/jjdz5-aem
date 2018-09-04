package com.isa.aem.globalextreme.forwebapp;

import com.isa.aem.globalextreme.*;

public class GlobalExtremeWebApp {

    GlobalExtreme globalExtreme = new GlobalExtreme();
    SingleCurrency singleCurrency = new SingleCurrency();
    CurrencyRepositoryHelper helper = new CurrencyRepositoryBin();

    private ListAvailableCurrency listAvailableCurrency = new ListAvailableCurrency();

    public void checkCurrencyExist(String currencyName) {
        if (globalExtreme.checkCurrencyExist(currencyName)) {
            globalExtreme.addSingleCurrencyToList(currencyName);
            singleCurrency.sortSingleCurrency();
        }
    }

    public void getMin() {
        helper.getMin();
    }

    public void getMax() {
        helper.getMax();
    }

    public void getDateMin() {
        helper.getDate(0);
    }

    public void getDateMax() {
        helper.getDate(singleCurrency.getSingleCurrency().size() - 1);
    }
}
