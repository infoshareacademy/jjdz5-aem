package com.isa.aem.rate_extremums;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GlobalExtremum {

    ExtremumHelper extremumHelper = new ExtremumHelper();

    public List<Currency> getGlobalMinExtremum(String currencyName) {
        List<Currency> repositoryWithChosenCurrency = limitRepositoryToChosenCurrency(currencyName);
        Double firstMinExtremum = extremumHelper.findFirstMinExtremum(repositoryWithChosenCurrency);
        List<Currency> globalMinExtremum = extremumHelper.findDuplicatedExtremums(firstMinExtremum, CurrencyRepository.getCurrencies());
        return globalMinExtremum;
    }

    public List<Currency> getGlobalMaxExtremum(String currencyName) {
        List<Currency> repositoryWithChosenCurrency = limitRepositoryToChosenCurrency(currencyName);
        Double firstMaxExtremum = extremumHelper.findFirstMaxExtremum(repositoryWithChosenCurrency);
        List<Currency> globalMaxExtremum = extremumHelper.findDuplicatedExtremums(firstMaxExtremum, CurrencyRepository.getCurrencies());
        return globalMaxExtremum;
    }

    public List<Currency> limitRepositoryToChosenCurrency(String currencyName) {
        return CurrencyRepository.getCurrencies().stream()
                .filter(currency -> currency.getName().equalsIgnoreCase(currencyName))
                .collect(Collectors.toList());
    }


}
