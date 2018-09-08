package com.isa.aem;

import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalExtremum {

    private List<Currency> currenciesWithinChosenDateRange = new ArrayList<>();
    private String givenDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate firstDateFromRepository;
    private LocalDate lastDateFromRepository;
    private List<String> avalableCurrencies = new ArrayList<>();

    public List<Currency> limitCurrenciesToChosenDateRange(LocalDate dateFrom, LocalDate dateTo) {
        for (Currency currency : CurrencyRepository.getCurrenciesSortedByDateAsc()) {
            if (dateFrom.equals(currency.getDate())){
                do {
                    currenciesWithinChosenDateRange.add(currency);
                } while (this.dateFrom.isBefore(dateTo));

            } continue;
        }
        System.out.println(currenciesWithinChosenDateRange);
        return currenciesWithinChosenDateRange;
    }

    public boolean isCorrectFormat(String givenDate) {
        try {
            LocalDate.parse(givenDate);
            return true;
        } catch (DateTimeParseException e){
            return false;
        }
    }

    public boolean isWithinRange(LocalDate givenDate) {
        firstDateFromRepository = CurrencyRepository.getCurrenciesSortedByDateAsc().get(0).getDate();
        lastDateFromRepository = CurrencyRepository.getCurrenciesSortedByDateAsc().get(CurrencyRepository.getCurrenciesSortedByDateAsc().size()-1).getDate();
        if (givenDate.isBefore(firstDateFromRepository) || givenDate.isAfter(lastDateFromRepository)) {
            return false;
        } else {
            return true;
        }
    }

    public List<String> getAvailableCurrencies() {
        for (Currency currency : CurrencyRepository.getCurrencies()) {
            if (!avalableCurrencies.contains(currency.getName())) {
                avalableCurrencies.add(currency.getName());
            } else {
                continue;
            }
        }
        return avalableCurrencies;
    }


}
