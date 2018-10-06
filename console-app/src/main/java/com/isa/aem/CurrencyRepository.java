package com.isa.aem;

import javax.enterprise.inject.Default;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Default
public class CurrencyRepository {

    private static List<Currency> currencies = new ArrayList<>();
    private static List<Currency> repositoryWithChosenCurrencyWithinChosenDateRange;


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

    public Boolean checkIfExistCurrencyWithGivenDate(String name, LocalDate date) {
        return currencies.stream()
                .filter(currency -> currency.getName().equals(name))
                .map(currency -> currency.getDate())
                .anyMatch(currency -> currency.equals(date));
    }

    public LocalDate getMostCurrentDateOfSelectedCurrencyFromTheFile(String nameOfCurrency) {
        return currencies.stream()
                .filter(currency -> currency.getName().equals(nameOfCurrency))
                .map(currency -> currency.getDate())
                .max((o1, o2) -> o1.compareTo(o2))
                .get();
    }

    public LocalDate getMinCurrentDateOfSelectedCurrencyFromTheFile(String nameOfCurrency) {
        return currencies.stream()
                .filter(currency -> currency.getName().equals(nameOfCurrency))
                .map(currency -> currency.getDate())
                .min((o1, o2) -> o1.compareTo(o2))
                .get();
    }


    public Double getMostCurrentExchangedRateOfSelectedCurrencyFromTheFile(String nameOfCurrency) {
        return currencies.stream()
                .filter(currency -> currency.getName().equals(nameOfCurrency))
                .max((o1, o2) -> o1.getDate().compareTo(o2.getDate()))
                .get()
                .getClose();
    }

    public Double getRateOfGivenDate(String nameOfCurrency, LocalDate dateOfCurrency) {
        return currencies.stream()
                .filter(currency -> currency.getName().equals(nameOfCurrency))
                .filter(currency -> currency.getDate().equals(dateOfCurrency))
                .findFirst()
                .get()
                .getClose();
    }


    public List<String> listAvailableCurrency() {
        return currencies.stream()
                .map(currency -> currency.getName())
                .distinct()
                .sorted((o1, o2) -> o1.compareTo(o2))
                .collect(Collectors.toList());
    }

    public List<Currency> getSortedCurrencySet(Set<Currency> SetOfCurrencyObject) {
        return SetOfCurrencyObject.stream()
                .sorted(Comparator.comparing(Currency::getName))
                .distinct()
                .collect(Collectors.toList());
    }

    public Double getMinRateOfExtremum(String nameOfCurrency) {
        return currencies.stream()
                .filter(currency -> currency.getName().equals(nameOfCurrency))
                .min((o1, o2) -> o1.getClose().compareTo(o2.getClose()))
                .get().getClose();
    }

    public List<LocalDate> findDuplicate(Double value) {
        return currencies.stream()
                .filter(currency -> currency.getClose().equals(value))
                .map(currency -> currency.getDate())
                .distinct()
                .collect(Collectors.toList());
    }

    public Double getMaxRateOfExtremum(String nameOfCurrency) {
        return currencies.stream()
                .filter(currency -> currency.getName().equals(nameOfCurrency))
                .max((o1, o2) -> o1.getDate().compareTo(o2.getDate()))
                .get().getClose();
    }

    public List<LocalDate> getEverySingleDateOfFile() {
        return currencies.stream()
                .map(currency -> currency.getDate())
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Currency> getRepositoryWithChosenCurrencyWithinChosenDateRange() {
        return repositoryWithChosenCurrencyWithinChosenDateRange;
    }

    public LocalDate getFirstDateFromRepository() {
        return currencies.get(0).getDate();
    }

    public LocalDate getLastDateFromRepository() {
        return currencies.get(CurrencyRepository.getCurrencies().size() - 1).getDate();
    }

    public LocalDate getLastMonthDateFromRepository() {
        return currencies.get(CurrencyRepository.getCurrencies().size() - 1).getDate().minusDays(30);
    }

    public static List<String> getAvailableCurrencyNames() {
        List<String> availableCurrencyNames = currencies.stream()
                .sorted(Comparator.comparing(Currency::getName))
                .map(currency -> currency.getName())
                .distinct()
                .collect(Collectors.toList());
        return availableCurrencyNames;
    }

    public static List<Currency> limitRepositoryToChosenCurrencyWithinChosenDateRange(String chosenCurrencyName, LocalDate dateFrom, LocalDate dateTo) {
        return repositoryWithChosenCurrencyWithinChosenDateRange = currencies.stream()
                .filter(currency -> currency.getName().equalsIgnoreCase(chosenCurrencyName))
                .filter(currency -> currency.getDate().equals(dateFrom) || currency.getDate().isAfter(dateFrom))
                .filter(currency -> currency.getDate().isBefore(dateTo) || currency.getDate().equals(dateTo))
                .collect(Collectors.toList());
    }

    public List<Currency> findDuplicatedExtremums(Double value) {
        return repositoryWithChosenCurrencyWithinChosenDateRange.stream()
                .filter(currency -> currency.getClose().equals(value))
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
