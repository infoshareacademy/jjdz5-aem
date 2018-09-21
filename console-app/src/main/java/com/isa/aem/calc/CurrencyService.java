package com.isa.aem.calc;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.tools.CurrencyRepositoryBin;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyService {

    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private CurrencyRepositoryBin currencyRepositoryBin = new CurrencyRepositoryBin();

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


    public boolean checkCurrencyExist(String s) {
        return (currencyRepositoryBin.containsCurrency(currencyRepository.getCurrencies(), s));
    }

}
