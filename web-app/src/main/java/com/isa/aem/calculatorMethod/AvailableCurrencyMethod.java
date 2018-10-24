package com.isa.aem.calculatorMethod;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.currency.calculator.AlgorithmCurrencyConversion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AvailableCurrencyMethod {
    private List<Currency> currencyInMaxDate;
    private LocalDate dateMax;
    private LocalDate dateMin;
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    public LocalDate getMaxDateForSelectedCurrency(String nameOfCurrency) {
        dateMax = currencyRepository.getMostRecentDateForChosenCurrencyName(nameOfCurrency.toUpperCase());
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
        AlgorithmCurrencyConversion algorithmCurrencyConversion = new AlgorithmCurrencyConversion();
        Double currencyHave = currencyRepository.getRateOfGivenDate(nameOfCurrency.toUpperCase(), dateMax);
        Double currencyWant = currencyRepository.getRateOfGivenDate(nameCurrencyWant.toUpperCase(), dateMax);
        BigDecimal curseValue = algorithmCurrencyConversion.calculateCourseAlgorithm(currencyWant, currencyHave);
        return curseValue;
    }

    public List<Currency> getSingleCurrencyWithMaxDate(LocalDate dateMax) {
        currencyInMaxDate = CurrencyRepository.getCurrencies().stream()
                .filter(currency -> currency.getDate().equals(dateMax))
                .collect(Collectors.toList());

        return currencyInMaxDate;
    }
}
