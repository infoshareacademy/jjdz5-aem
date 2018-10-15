package com.isa.aem.calculatorMethod;

import com.isa.aem.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailableCurrencyTable {
    private AvailableCurrencyMethod availableCurrencyMethod = new AvailableCurrencyMethod();
    private LocalDate maxDate;
    public List<AvailableCurrency> tableListCurrencyObject = new ArrayList<>();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();

    public List<AvailableCurrency> availableCurrencyObjects(String nameOfCurrency) {
        this.maxDate = availableCurrencyMethod.getMaxDateForSelectedCurrency(nameOfCurrency);
        availableCurrencyMethod.getSingleCurrencyWithMaxDate(maxDate);

        for (Currency nameCurrencyWant : availableCurrencyMethod.getSingleCurrencyWithMaxDate(maxDate)) {
            BigDecimal value = availableCurrencyMethod.getExchangeValue(nameOfCurrency, nameCurrencyWant.getName(), maxDate);
            LocalDate dateMax = availableCurrencyMethod.getMaxDateForSelectedCurrency(nameCurrencyWant.getName());
            LocalDate dateMin = availableCurrencyMethod.getMinDateForSelectedCurrency(nameCurrencyWant.getName());
            String range = availableCurrencyMethod.getRangeOfSelectedCurrency(nameCurrencyWant.getName());
            String name = nameCurrencyWant.getName();
            nameCurrencyWant.setCurrencyNameCountryFlags(CurrencyNameCountryFlags.getCurrencies().get(nameCurrencyWant.getName()));
            tableListCurrencyObject.add(new AvailableCurrency(dateMin, dateMax, range, name, value, nameCurrencyWant.getCurrencyNameCountryFlags()));

        }

        return tableListCurrencyObject;
    }
}
