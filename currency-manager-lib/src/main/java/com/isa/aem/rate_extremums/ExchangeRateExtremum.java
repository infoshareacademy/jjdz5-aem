package com.isa.aem.rate_extremums;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExchangeRateExtremum {

    public List<Currency> getMinExtremum(String currencyName, LocalDate dateFrom, LocalDate dateTo) {
        List<Currency> limitedRepositoryToChosenVaules = limitRepositoryToChosenValues(currencyName, dateFrom, dateTo);
        Double firstMinExtremum = findFirstMinExtremum(limitedRepositoryToChosenVaules);
        List<Currency> minExtremum = findDuplicatedExtremums(firstMinExtremum, limitedRepositoryToChosenVaules);
        return minExtremum;
    }

    public List<Currency> getMaxExtremum(String currencyName, LocalDate dateFrom, LocalDate dateTo) {
        List<Currency> limitedRepositoryToChosenVaules = limitRepositoryToChosenValues(currencyName, dateFrom, dateTo);
        Double firstMaxExtremum = findFirstMaxExtremum(limitedRepositoryToChosenVaules);
        List<Currency> maxExtremum = findDuplicatedExtremums(firstMaxExtremum, limitedRepositoryToChosenVaules);
        return maxExtremum;
    }

    public List<Currency> limitRepositoryToChosenValues(String currencyName, LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom == null || dateTo == null) {
            return CurrencyRepository.getCurrencies().stream()
                    .filter(currency -> currency.getName().equalsIgnoreCase(currencyName))
                    .collect(Collectors.toList());
        } else {
            return CurrencyRepository.getCurrencies().stream()
                    .filter(currency -> currency.getName().equalsIgnoreCase(currencyName))
                    .filter(currency -> currency.getDate().equals(dateFrom) || currency.getDate().isAfter(dateFrom))
                    .filter(currency -> currency.getDate().isBefore(dateTo) || currency.getDate().equals(dateTo))
                    .collect(Collectors.toList());
        }
    }

    private Double findFirstMinExtremum(List<Currency> currencies) {
        return currencies.stream()
                .min(Comparator.comparingDouble(Currency::getClose))
                .get()
                .getClose();
    }

    private Double findFirstMaxExtremum(List<Currency> currencies) {
        return currencies.stream()
                .max(Comparator.comparingDouble(Currency::getClose))
                .get()
                .getClose();
    }

    private List<Currency> findDuplicatedExtremums(Double firstExtremum, List<Currency> currencies) {
        return currencies.stream()
                .filter(currency -> firstExtremum.equals(currency.getClose()))
                .collect(Collectors.toList());
    }
}

