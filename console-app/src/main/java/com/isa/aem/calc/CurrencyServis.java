package com.isa.aem.calc;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.tools.CurrencyRepositoryHelper;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyServis {

    @Inject
    CurrencyRepository currencyRepository;
    @Inject
    CurrencyRepositoryHelper currencyRepositoryHelper;
    @Inject
    DateServis dateServis;
    private List<Currency> firstCurrencySelectedByUser = new ArrayList<>();
    private List<Currency> secondCurrencySelectedByUser = new ArrayList<>();

    public List<Currency> getFirstCurrencySelectedByUser() {
        return firstCurrencySelectedByUser;
    }

    public List<Currency> getSecondCurrencySelectedByUser() {
        return secondCurrencySelectedByUser;
    }

    private void filteringTheSelectedCurrencyFromTheListOfAllCurrencies(String nameOfCurrency, List<Currency> listSelectedByUser) {
        currencyRepository.getCurrencies().forEach(c -> {
            if (c.getName().equals(nameOfCurrency)) {
                listSelectedByUser.add(c);
            }
        });
    }

    protected void addFirstCurrencySelectedByUserToList(String selectedByUser) {
       filteringTheSelectedCurrencyFromTheListOfAllCurrencies(selectedByUser, firstCurrencySelectedByUser );
    }

    protected void addSecondCurrencySelectedByUserToList(String selectedByUser) {
        filteringTheSelectedCurrencyFromTheListOfAllCurrencies(selectedByUser, secondCurrencySelectedByUser);
    }

    protected double getActualCurseOfFirstCurrencySelectedByUser() {
        return firstCurrencySelectedByUser.get(firstCurrencySelectedByUser.size()-1).getClose();
    }

    protected double getActualCurseOfSecondCurrencySelectedByUser() {
        return secondCurrencySelectedByUser.get(secondCurrencySelectedByUser.size()-1).getClose();
    }

    protected void sortingCurrenciesGivenByUserAtExchangingRate(List<Currency> currenciesGivenByUse) {
        Collections.sort(currenciesGivenByUse, (o1, o2) -> o1.getClose().compareTo(o2.getClose()));
    }

    protected void sortingCurrenciesGivenByUserByDate(List<Currency> currenciesGivenByUse) {
        Collections.sort(currenciesGivenByUse,(o1, o2) -> o1.getDate().compareTo(o2.getDate()));
    }

    protected boolean checkIfInFirstChoiceContainsGivenDate(LocalDate date) {
        return currencyRepositoryHelper.containsDate(firstCurrencySelectedByUser, date);
    }

    protected boolean checkIfInSecondChoiceContainsGivenDate(LocalDate date) {
        return currencyRepositoryHelper.containsDate(secondCurrencySelectedByUser, date);
    }

    public boolean checkCurrencyExist(String s) {
        return (currencyRepositoryHelper.containsCurrency(currencyRepository.getCurrencies(), s));
    }

}
