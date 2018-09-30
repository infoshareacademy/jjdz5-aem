package com.isa.aem.local.extremum;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class LocalExtremum {

    private List<Currency> minExtremum;
    private List<Currency> maxExtremum;
    CurrencyRepository currencyRepository = new CurrencyRepository();


    public void runExtremum() {
        getMinExtremum(CurrencyRepository.getRepositoryWithChosenCurrencyWithinChosenDateRange());
        getMaxExtremum(CurrencyRepository.getRepositoryWithChosenCurrencyWithinChosenDateRange());
    }

    public List<Currency> getMinExtremum(List<Currency> repoWithChosenCurrencyOnlyWithinChosenDateRange) {
        Double firstMinExtremum = repoWithChosenCurrencyOnlyWithinChosenDateRange.stream()
                .min(Comparator.comparingDouble(Currency::getClose))
                .get().getClose();
        minExtremum = currencyRepository.findDuplicatedExtremums(firstMinExtremum);
        minExtremum.forEach(currency -> System.out.println(currency.getName() + " " + currency.getClose() + " " + currency.getDate()));
        return minExtremum;
    }

    public List<Currency> getMaxExtremum(List<Currency> repoWithChosenCurrencyOnlyWithinChosenDateRange) {
        Double firstMaxExtremum = repoWithChosenCurrencyOnlyWithinChosenDateRange.stream()
                .max(Comparator.comparingDouble(Currency::getClose))
                .get().getClose();
        maxExtremum = currencyRepository.findDuplicatedExtremums(firstMaxExtremum);
        maxExtremum.forEach(currency -> System.out.println(currency.getName() + " " + currency.getClose() + " " + currency.getDate()));
        return maxExtremum;
    }
}
