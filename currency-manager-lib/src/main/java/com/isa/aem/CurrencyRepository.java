package com.isa.aem;

import javax.enterprise.inject.Default;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Default
public class CurrencyRepository {

    private static List<Currency> currencies = new ArrayList<>();

    public static List<Currency> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(List<Currency> currencies) {
        Collections.sort(currencies);
        CurrencyRepository.currencies = currencies;
    }

    public void add(Currency currency) {
        currencies.add(currency);
    }

    public Boolean containsCurrencyNameInCurrencyList(String name) {
        return currencies.stream()
                .anyMatch(currency -> currency.getName().equals(name));
    }

    public Boolean existCurrencyWithGivenDate(String name, LocalDate date) {
        return currencies.stream()
                .filter(currency -> name.equals(currency.getName()))
                .map(currency -> currency.getDate())
                .anyMatch(currency -> currency.equals(date));
    }

    public LocalDate getNewestDateForChosenCurrencyName(String nameOfCurrency) {
        return currencies.stream()
                .filter(currency -> nameOfCurrency.equals(currency.getName()))
                .map(currency -> currency.getDate())
                .max((o1, o2) -> o1.compareTo(o2))
                .get();
    }

    public LocalDate getOldestDateForChosenCurrencyName(String nameOfCurrency) {
        return currencies.stream()
                .filter(currency -> nameOfCurrency.equals(currency.getName()))
                .map(currency -> currency.getDate())
                .min((o1, o2) -> o1.compareTo(o2))
                .get();
    }

    public Double getNewestExchangeRateForChosenCurrencyName(String nameOfCurrency) {
        return currencies.stream()
                .filter(currency -> currency.getName().equals(nameOfCurrency))
                .max((o1, o2) -> o1.getDate().compareTo(o2.getDate()))
                .get()
                .getClose();
    }

    public Double getExchangeRateForGivenDate(String nameOfCurrency, LocalDate dateOfCurrency) {
        return currencies.stream()
                .filter(currency -> currency.getName().equals(nameOfCurrency))
                .filter(currency -> dateOfCurrency.equals(currency.getDate()))
                .findFirst()
                .get()
                .getClose();
    }

    public List<String> getAvailableCurrencyNames() {
        return currencies.stream()
                .sorted(Comparator.comparing(Currency::getName))
                .map(currency -> currency.getName())
                .distinct()
                .collect(Collectors.toList());
    }

    public String getFirstAvailableCurrencyName() {
        String firstAvailableCurrencyName = currencies.stream()
                .map(currency -> currency.getName())
                .findFirst()
                .get();
        return firstAvailableCurrencyName;
    }

    public List<Currency> getSortedCurrencySet(Set<Currency> SetOfCurrencyObject) {
        return SetOfCurrencyObject.stream()
                .sorted(Comparator.comparing(Currency::getName))
                .distinct()
                .collect(Collectors.toList());
    }

    public List<LocalDate> getEverySingleDateFromFile() {
        return currencies.stream()
                .map(currency -> currency.getDate())
                .distinct()
                .collect(Collectors.toList());
    }

    public LocalDate getFirstDateFromRepository() {
        return currencies.get(0).getDate();
    }

    public LocalDate getLastDateFromRepository() {
        return currencies.get(CurrencyRepository.getCurrencies().size() - 1).getDate();
    }

    public List<Currency> getCurrenciesWithFullNameAndFlag() {
        Set<Currency> currencyNameAndCountry = new HashSet<>();
        for (Currency cc : getCurrencies()) {
            cc.setCurrencyNameCountryFlags(CurrencyNameCountryFlags.getCurrencies().get(cc.getName()));
            Optional<CurrencyNameCountryFlags> currencyNameAndCountryOptional = Optional.ofNullable(CurrencyNameCountryFlags.getCurrencies().get(cc.getName()));
            if (currencyNameAndCountryOptional.isPresent()) {
                currencyNameAndCountry.add(new Currency(cc.getName(), cc.getCurrencyNameCountryFlags()));
            }
        }
        return getSortedCurrencySet(currencyNameAndCountry);
    }

    public Set<Currency> getCurrenciesWithFullNameAndFlagWithoutPln() {
        return  getCurrenciesWithFullNameAndFlag().stream()
                .filter(s -> !s.getName().equals("PLN"))
                .sorted(Comparator.comparing(Currency::getName))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}