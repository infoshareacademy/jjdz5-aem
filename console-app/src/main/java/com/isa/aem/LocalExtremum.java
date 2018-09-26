package com.isa.aem;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LocalExtremum {

    private List<Currency> repoWithChosenCurrencyOnly;
    //private List<Currency> repoWithChosenCurrencyOnlyWithinChosenDateRange = new ArrayList<>();
    private List<Currency> min;
    private List<Currency> max;
    private String givenDate;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    public void runExtremum() {
        getMin(CurrencyRepository.getRepositoryWithChosenCurrencyWithinChosenDateRange());
        //getMax();
    }

    public List<Currency> getMin(List<Currency> repoWithChosenCurrencyOnlyWithinChosenDateRange) {
        System.out.println(repoWithChosenCurrencyOnlyWithinChosenDateRange.size());
        min = repoWithChosenCurrencyOnlyWithinChosenDateRange.stream()
                .sorted(Comparator.comparingDouble(Currency::getClose))
                .collect(Collectors.toList());
        min.forEach(currency -> System.out.println(currency.getName() + " " + currency.getClose() + " " + currency.getDate()));
                //.forEach(currency -> System.out.println(min));
                //.collect(Collectors.toList());
                //.min(Comparator.comparingDouble(Currency::getClose));
        return min;
    }

/*    public List<Double> getMax() {
        max.add(repoWithChosenCurrencyOnlyWithinChosenDateRange.get(repoWithChosenCurrencyOnlyWithinChosenDateRange.size() - 1).getClose());
        return max;
    }*/

    public boolean isNotWithinRange(LocalDate givenDate) {
        if (givenDate.isBefore(CurrencyRepository.getFirstDateFromRepository()) || givenDate.isAfter(CurrencyRepository.getLastDateFromRepository())) {
            return true;
        } else {
            return false;
        }
    }
}
