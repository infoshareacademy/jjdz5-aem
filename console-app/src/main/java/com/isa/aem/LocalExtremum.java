package com.isa.aem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class LocalExtremum {

    private List<Currency> currenciesWithinChosenDateRange = new ArrayList<>();
    private String givenDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    public List<Currency> limitCurrenciesToChosenDateRange(LocalDate dateFrom, LocalDate dateTo) {
        for (Currency currency : CurrencyRepository.getCurrencies()) {
            if (dateFrom.equals(currency.getDate())){
                do {
                    currenciesWithinChosenDateRange.add(currency);
                } while (this.dateFrom.isBefore(dateTo));

            } continue;
        }
        System.out.println(currenciesWithinChosenDateRange);
        return currenciesWithinChosenDateRange;
    }

    public boolean isNotWithinRange(LocalDate givenDate) {
        if (givenDate.isBefore(CurrencyRepository.getFirstDateFromRepository()) || givenDate.isAfter(CurrencyRepository.getLastDateFromRepository())) {
            return true;
        } else {
            return false;
        }
    }
}
