package com.isa.aem.globalextreme;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.tools.CurrencyRepositoryBin;
import com.isa.aem.tools.CurrencyRepositoryHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExtremeService {

    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private CurrencyRepositoryHelper currencyRepositoryHelper = new CurrencyRepositoryBin();
    private List<Currency> currencySelectedByUser = new ArrayList<>();

    public List<Currency> getCurrencySelectedByUser() {
        return currencySelectedByUser;
    }

    private void filteringTheSelectedCurrencyFromTheListOfAllCurrencies(String nameOfCurrency, List<Currency> listSelectedByUser) {
        currencyRepository.getCurrencies().forEach(c -> {
            if (c.getName().equals(nameOfCurrency)) {
                listSelectedByUser.add(c);
            }
        });
    }

    protected void addCurrencySelectedByUserToList(String selectedByUser) {
        filteringTheSelectedCurrencyFromTheListOfAllCurrencies(selectedByUser, currencySelectedByUser);
    }


    protected void sortingCurrenciesGivenByUserAtExchangingRate(List<Currency> currenciesGivenByUse) {
        Collections.sort(currenciesGivenByUse, (o1, o2) -> o1.getClose().compareTo(o2.getClose()));
    }

    protected boolean checkCurrencyExist(String s) {
        return (currencyRepositoryHelper.containsCurrency(currencyRepository.getCurrencies(), s));
    }

    public Double getMin() {
        Currency currency = currencySelectedByUser.get(0);
        return currency.getClose();
    }

    public Double getMax() {
        Currency currency = currencySelectedByUser.get(currencySelectedByUser.size() - 1);
        return currency.getClose();
    }

    public List<LocalDate> getMinDate() {
        List<LocalDate> dateOfCurse = new ArrayList<>();
        Double currency = currencySelectedByUser.get(0).getClose();
        for (Currency c : currencySelectedByUser) {
            if (c.getClose().equals(currency)){
                dateOfCurse.add(c.getDate());
            }
        }
        return dateOfCurse;
    }

    public List<LocalDate> getMaxDate() {
        List<LocalDate> dateOfCurse = new ArrayList<>();
        Double currency = currencySelectedByUser.get(currencySelectedByUser.size()-1).getClose();
        for (Currency c : currencySelectedByUser) {
            if (c.getClose().equals(currency)){
                dateOfCurse.add(c.getDate());
            }
        }
        return dateOfCurse;
    }
}
