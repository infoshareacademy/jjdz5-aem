package com.isa.aem.rate_extremums;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LocalExtremum {

    ExtremumHelper extremumHelper = new ExtremumHelper();

    public List<Currency> getMinExtremum(String currencyName, LocalDate dateFrom, LocalDate dateTo) {
        List<Currency> repositoryWithChosenCurrencyWithinChosenDateRange = limitRepositoryToChosenCurrencyWithinChosenDateRange(currencyName, dateFrom, dateTo);
        Double firstMinExtremum = extremumHelper.findFirstMinExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);
        List<Currency> minExtremum = extremumHelper.findDuplicatedExtremums(firstMinExtremum, repositoryWithChosenCurrencyWithinChosenDateRange);
        return minExtremum;
    }

    public List<Currency> getMaxExtremum(String currencyName, LocalDate dateFrom, LocalDate dateTo) {
        List<Currency> repositoryWithChosenCurrencyWithinChosenDateRange = limitRepositoryToChosenCurrencyWithinChosenDateRange(currencyName, dateFrom, dateTo);
        Double firstMaxExtremum = extremumHelper.findFirstMaxExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);
        List<Currency> maxExtremum = extremumHelper.findDuplicatedExtremums(firstMaxExtremum, repositoryWithChosenCurrencyWithinChosenDateRange);
        return maxExtremum;
    }

    public List<Currency> limitRepositoryToChosenCurrencyWithinChosenDateRange(String currencyName, LocalDate dateFrom, LocalDate dateTo) {
        return CurrencyRepository.getCurrencies().stream()
                .filter(currency -> currency.getName().equalsIgnoreCase(currencyName))
                .filter(currency -> currency.getDate().equals(dateFrom) || currency.getDate().isAfter(dateFrom))
                .filter(currency -> currency.getDate().isBefore(dateTo) || currency.getDate().equals(dateTo))
                .collect(Collectors.toList());
    }


}

