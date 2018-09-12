package com.isa.aem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class LocalExtremum {

    private List<Currency> currenciesWithinChosenDateRange = new ArrayList<>();
    private List<Double> min;
    private List<Double> max;
    private String givenDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    public List<Currency> limitCurrenciesToChosenDateRange(LocalDate dateFrom, LocalDate dateTo) {
        for (Currency currency : CurrencyRepository.getCurrencies()) {
            if ((dateFrom.isBefore(currency.getDate()) || dateFrom.equals(currency.getDate())) &&
                    (currency.getDate().isBefore(dateTo)) || currency.getDate().equals(dateTo)) {
                currenciesWithinChosenDateRange.add(currency);
            } continue;
        }
        System.out.println(currenciesWithinChosenDateRange);
        return currenciesWithinChosenDateRange;
    }


    public List<Currency> getMin (List<Currency> list, Currency currencyName ) {
        for (Currency currency : list) {
            currency.getClose();
        }
        return null;
    }

    public boolean isNotWithinRange(LocalDate givenDate) {
        if (givenDate.isBefore(CurrencyRepository.getFirstDateFromRepository()) || givenDate.isAfter(CurrencyRepository.getLastDateFromRepository())) {
            return true;
        } else {
            return false;
        }
    }
}
