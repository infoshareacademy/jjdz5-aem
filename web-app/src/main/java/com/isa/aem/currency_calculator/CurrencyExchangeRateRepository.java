package com.isa.aem.currency_calculator;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyExchangeRateRepository {
    private List<Currency> currencyInMaxDate;
    private LocalDate dateMax;
    private LocalDate dateMin;
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    public LocalDate getMaxDateForSelectedCurrency(String nameOfCurrency) {
        dateMax = currencyRepository.getNewestDateForChosenCurrencyName(nameOfCurrency.toUpperCase());
        return dateMax;
    }

    public LocalDate getMinDateForSelectedCurrency(String nameOfCurrency) {
        dateMin = currencyRepository.getOldestDateForChosenCurrencyName(nameOfCurrency.toUpperCase());
        return dateMin;
    }

    public String getRangeOfSelectedCurrency(String nameOfCurrency) {
        return getMinDateForSelectedCurrency(nameOfCurrency.toUpperCase()) + " - " + getMaxDateForSelectedCurrency(nameOfCurrency.toUpperCase());
    }

    public BigDecimal getExchangeValue(String nameOfCurrency, String nameCurrencyWant, LocalDate dateMax) {
        CurrencyConverter currencyConverter = new CurrencyConverter();
        Double currencyHave = currencyRepository.getExchangeRateForGivenDate(nameOfCurrency.toUpperCase(), dateMax);
        Double currencyWant = currencyRepository.getExchangeRateForGivenDate(nameCurrencyWant.toUpperCase(), dateMax);
        BigDecimal curseValue = currencyConverter.calculateCourseAlgorithm(currencyWant, currencyHave);
        return curseValue;
    }

    public List<Currency> getSingleCurrencyWithMaxDate(LocalDate dateMax) {
        currencyInMaxDate = CurrencyRepository.getCurrencies().stream()
                .filter(currency -> currency.getDate().equals(dateMax))
                .collect(Collectors.toList());

        return currencyInMaxDate;
    }
}
