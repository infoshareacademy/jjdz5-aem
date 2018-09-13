package com.isa.aem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocalExtremum {

    private List<Currency> repoWithChosenCurrencyOnly;
    private List<Currency> repoWithChosenCurrencyOnlyWithinChosenDateRange;
    private List<Double> min;
    private List<Double> max;
    private String givenDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;



    public List<Currency> limitRepositoryToChosenCurrency(String currencyName) {
        repoWithChosenCurrencyOnly = new ArrayList<>();
        for (Currency currency : CurrencyRepository.getCurrencies()) {
            if (currency.getName().equalsIgnoreCase(currencyName)) {
                repoWithChosenCurrencyOnly.add(currency);
            }continue;
        }
        System.out.println(repoWithChosenCurrencyOnly);
        return repoWithChosenCurrencyOnly;
    }

    public List<Currency> limitRepositoryToChosenDateRange(LocalDate dateFrom, LocalDate dateTo) {
        repoWithChosenCurrencyOnlyWithinChosenDateRange = new ArrayList<>();
        for (Currency currency : repoWithChosenCurrencyOnly) {
            if ((dateFrom.isBefore(currency.getDate()) || dateFrom.equals(currency.getDate())) &&
                    (currency.getDate().isBefore(dateTo)) || currency.getDate().equals(dateTo)) {
                repoWithChosenCurrencyOnlyWithinChosenDateRange.add(currency);
            } continue;
        }
        System.out.println(repoWithChosenCurrencyOnlyWithinChosenDateRange.size());
        return repoWithChosenCurrencyOnlyWithinChosenDateRange;
    }

    public void runExtremum() {
        getMin();
        //getMax();
    }

    public List<Double> getMin () {
        System.out.println(repoWithChosenCurrencyOnlyWithinChosenDateRange.size());
        min.add(repoWithChosenCurrencyOnlyWithinChosenDateRange.get(0).getClose());
        return min;
    }

    public List<Double> getMax () {
        max.add(repoWithChosenCurrencyOnlyWithinChosenDateRange.get(repoWithChosenCurrencyOnlyWithinChosenDateRange.size()-1).getClose());
        return max;
    }

    public boolean isNotWithinRange(LocalDate givenDate) {
        if (givenDate.isBefore(CurrencyRepository.getFirstDateFromRepository()) || givenDate.isAfter(CurrencyRepository.getLastDateFromRepository())) {
            return true;
        } else {
            return false;
        }
    }
}
