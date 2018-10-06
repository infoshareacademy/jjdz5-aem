package com.isa.aem.local.extremum;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.util.Comparator;
import java.util.List;

public class LocalExtremum {

    private List<Currency> minExtremum;
    private List<Currency> maxExtremum;
    CurrencyRepository currencyRepository = new CurrencyRepository();

    public List<Currency> getMinExtremum(List<Currency> repoWithChosenCurrencyOnlyWithinChosenDateRange) {
        if (repoWithChosenCurrencyOnlyWithinChosenDateRange.isEmpty()) {
            return repoWithChosenCurrencyOnlyWithinChosenDateRange;
        }
        Double firstMinExtremum = repoWithChosenCurrencyOnlyWithinChosenDateRange.stream()
                .min(Comparator.comparingDouble(Currency::getClose))
                .get()
                .getClose();
        minExtremum = currencyRepository.findDuplicatedExtremums(firstMinExtremum);
        return minExtremum;
    }

    public List<Currency> getMaxExtremum(List<Currency> repoWithChosenCurrencyOnlyWithinChosenDateRange) {
        if (repoWithChosenCurrencyOnlyWithinChosenDateRange.isEmpty()) {
            return repoWithChosenCurrencyOnlyWithinChosenDateRange;
        }
        Double firstMaxExtremum = repoWithChosenCurrencyOnlyWithinChosenDateRange.stream()
                .max(Comparator.comparingDouble(Currency::getClose))
                .get()
                .getClose();
        maxExtremum = currencyRepository.findDuplicatedExtremums(firstMaxExtremum);
        return maxExtremum;
    }
}
