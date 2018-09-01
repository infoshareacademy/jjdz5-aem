package com.isa.aem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocalExtreme {

    private LocalDate from;
    private LocalDate to;
    private List<String> avalableCurrencies = new ArrayList<>();

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
