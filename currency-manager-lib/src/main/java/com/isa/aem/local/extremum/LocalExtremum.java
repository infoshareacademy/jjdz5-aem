package com.isa.aem.local.extremum;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LocalExtremum {

    public List<Currency> getMinExtremum(String currencyName, LocalDate dateFrom, LocalDate dateTo) {
        List<Currency> repositoryWithChosenCurrencyWithinChosenDateRange = limitRepositoryToChosenCurrencyWithinChosenDateRange(currencyName, dateFrom, dateTo);
        Double firstMinExtremum = findFirstMinExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);
        List<Currency> minExtremum = findDuplicatedExtremums(firstMinExtremum, repositoryWithChosenCurrencyWithinChosenDateRange);
        return minExtremum;
    }

    public List<Currency> getMaxExtremum(String currencyName, LocalDate dateFrom, LocalDate dateTo) {
        List<Currency> repositoryWithChosenCurrencyWithinChosenDateRange = limitRepositoryToChosenCurrencyWithinChosenDateRange(currencyName, dateFrom, dateTo);
        Double firstMaxExtremum = findFirstMaxExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);
        List<Currency> maxExtremum = findDuplicatedExtremums(firstMaxExtremum, repositoryWithChosenCurrencyWithinChosenDateRange);
        return maxExtremum;
    }

    public List<Currency> limitRepositoryToChosenCurrencyWithinChosenDateRange(String currencyName, LocalDate dateFrom, LocalDate dateTo) {
        return CurrencyRepository.getCurrencies().stream()
                .filter(currency -> currency.getName().equalsIgnoreCase(currencyName))
                .filter(currency -> currency.getDate().equals(dateFrom) || currency.getDate().isAfter(dateFrom))
                .filter(currency -> currency.getDate().isBefore(dateTo) || currency.getDate().equals(dateTo))
                .collect(Collectors.toList());
    }

    public Double findFirstMinExtremum(List<Currency> currencies) {
        return currencies.stream()
                .min(Comparator.comparingDouble(Currency::getClose))
                .get()
                .getClose();
    }

    public Double findFirstMaxExtremum(List<Currency> currencies) {
        return currencies.stream()
                .max(Comparator.comparingDouble(Currency::getClose))
                .get()
                .getClose();
    }

    public List<Currency> findDuplicatedExtremums(Double firstExtremum, List<Currency> currencies) {
        return currencies.stream()
                .filter(currency -> firstExtremum.equals(currency.getClose()))
                .collect(Collectors.toList());
    }

    public Boolean isDateFromAfterDateTo(LocalDate dateFrom, LocalDate dateTo) {
        return dateFrom.isAfter(dateTo);
    }

    public Boolean isDateToBeforeDateFrom(LocalDate dateFrom, LocalDate dateTo) {
        return dateTo.isBefore(dateFrom);
    }
}

