package com.isa.aem.calculatorMethod;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyNameCountryFlags;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.LoadCurrencyNameCountryFlags;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateAListOfAvailableCurrencies {
    private AvailableCurrencyMethod availableCurrencyMethod = new AvailableCurrencyMethod();
    private LocalDate maxDate;
    private List<CurrencyExchangeRate> tableListCurrencyObject = new ArrayList<>();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();

    public List<CurrencyExchangeRate> availableCurrencyObjects(String nameOfCurrency) {
        this.maxDate = availableCurrencyMethod.getMaxDateForSelectedCurrency(nameOfCurrency);
        availableCurrencyMethod.getSingleCurrencyWithMaxDate(maxDate);

        addCurrencyObject(nameOfCurrency);

        return tableListCurrencyObject;
    }

    public void setAvailableCurrencyMethod(AvailableCurrencyMethod availableCurrencyMethod) {
        this.availableCurrencyMethod = availableCurrencyMethod;
    }

    void addCurrencyObject(String nameOfCurrency) {
        for (Currency nameCurrencyWant : availableCurrencyMethod.getSingleCurrencyWithMaxDate(maxDate)) {
            BigDecimal value = availableCurrencyMethod.getExchangeValue(nameOfCurrency, nameCurrencyWant.getName(), maxDate);
            LocalDate dateMax = availableCurrencyMethod.getMaxDateForSelectedCurrency(nameCurrencyWant.getName());
            LocalDate dateMin = availableCurrencyMethod.getMinDateForSelectedCurrency(nameCurrencyWant.getName());
            String range = availableCurrencyMethod.getRangeOfSelectedCurrency(nameCurrencyWant.getName());
            String name = nameCurrencyWant.getName();
            nameCurrencyWant.setCurrencyNameCountryFlags(CurrencyNameCountryFlags.getCurrencies().get(nameCurrencyWant.getName()));
            tableListCurrencyObject.add(new CurrencyExchangeRate(dateMin, dateMax, range, name, value, nameCurrencyWant.getCurrencyNameCountryFlags()));

        }
    }

    public List<CurrencyExchangeRate> getTableListCurrencyObject() {
        return tableListCurrencyObject;
    }

    public void setTableListCurrencyObject(List<CurrencyExchangeRate> tableListCurrencyObject) {
        this.tableListCurrencyObject = tableListCurrencyObject;
    }
}
