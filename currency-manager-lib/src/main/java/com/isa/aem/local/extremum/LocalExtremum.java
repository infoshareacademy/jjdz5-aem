package com.isa.aem.local.extremum;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LocalExtremum {

    private List<Currency> minExtremum;
    private List<Currency> maxExtremum;
    private List<Currency> repositoryWithChosenCurrencyWithinChosenDateRange;
    private Double firstMinExtremum;
    private Double firstMaxExtremum;


    public List<Currency> getMinExtremum(String chosenCurrencyName, LocalDate chosenDateFrom, LocalDate chosenDateTo) {
        repositoryWithChosenCurrencyWithinChosenDateRange = limitRepositoryToChosenCurrencyWithinChosenDateRange(chosenCurrencyName, chosenDateFrom, chosenDateTo);
        repositoryWithChosenCurrencyWithinChosenDateRange = returnEmptyListIfEmpty(repositoryWithChosenCurrencyWithinChosenDateRange);
        findFirstMinExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);
        minExtremum = findDuplicatedExtremums(firstMinExtremum, repositoryWithChosenCurrencyWithinChosenDateRange);
        return minExtremum;
    }

    public List<Currency> getMaxExtremum(String chosenCurrencyName, LocalDate chosenDateFrom, LocalDate chosenDateTo) {
        repositoryWithChosenCurrencyWithinChosenDateRange = limitRepositoryToChosenCurrencyWithinChosenDateRange(chosenCurrencyName, chosenDateFrom, chosenDateTo);
        repositoryWithChosenCurrencyWithinChosenDateRange = returnEmptyListIfEmpty(repositoryWithChosenCurrencyWithinChosenDateRange);
        findFirstMaxExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);
        maxExtremum = findDuplicatedExtremums(firstMaxExtremum, repositoryWithChosenCurrencyWithinChosenDateRange);
        return maxExtremum;
    }


    public List<Currency> limitRepositoryToChosenCurrencyWithinChosenDateRange(String chosenCurrencyName, LocalDate dateFrom, LocalDate dateTo) {
        return repositoryWithChosenCurrencyWithinChosenDateRange = CurrencyRepository.getCurrencies().stream()
                .filter(currency -> currency.getName().equalsIgnoreCase(chosenCurrencyName))
                .filter(currency -> currency.getDate().equals(dateFrom) || currency.getDate().isAfter(dateFrom))
                .filter(currency -> currency.getDate().isBefore(dateTo) || currency.getDate().equals(dateTo))
                .collect(Collectors.toList());
    }

    public List<Currency> returnEmptyListIfEmpty(List<Currency> currencies) {
        if (currencies.isEmpty()) {
            return currencies;
        }
        return currencies;
    }


    public Double findFirstMinExtremum(List<Currency> currencies) {
        return firstMinExtremum = currencies.stream()
                .min(Comparator.comparingDouble(Currency::getClose))
                .get()
                .getClose();
    }


    public Double findFirstMaxExtremum(List<Currency> currencies) {
        return firstMaxExtremum = currencies.stream()
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
        if (dateFrom.isAfter(dateTo)) {
            return true;
        }
        else return false;
    }

    public Boolean isDateToBeforeDateFrom(LocalDate dateFrom, LocalDate dateTo) {
        if (dateTo.isBefore(dateFrom)) {
            return true;
        }
        else return false;
    }
}

