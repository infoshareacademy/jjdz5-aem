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
    private CurrencyExchangeRateRepository currencyExchangeRateRepository = new CurrencyExchangeRateRepository();
    private LocalDate maxDate;
    private List<CurrencyExchangeRate> tableListCurrencyObject = new ArrayList<>();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();

    public List<CurrencyExchangeRate> availableCurrencyObjects(String nameOfCurrency) {
        this.maxDate = currencyExchangeRateRepository.getMaxDateForSelectedCurrency(nameOfCurrency);
        currencyExchangeRateRepository.getSingleCurrencyWithMaxDate(maxDate);

        addCurrencyObject(nameOfCurrency);

        return tableListCurrencyObject;
    }

    public void setCurrencyExchangeRateRepository(CurrencyExchangeRateRepository currencyExchangeRateRepository) {
        this.currencyExchangeRateRepository = currencyExchangeRateRepository;
    }

    void addCurrencyObject(String nameOfCurrency) {
        for (Currency nameCurrencyWant : currencyExchangeRateRepository.getSingleCurrencyWithMaxDate(maxDate)) {
            BigDecimal value = currencyExchangeRateRepository.getExchangeValue(nameOfCurrency, nameCurrencyWant.getName(), maxDate);
            LocalDate dateMax = currencyExchangeRateRepository.getMaxDateForSelectedCurrency(nameCurrencyWant.getName());
            LocalDate dateMin = currencyExchangeRateRepository.getMinDateForSelectedCurrency(nameCurrencyWant.getName());
            String range = currencyExchangeRateRepository.getRangeOfSelectedCurrency(nameCurrencyWant.getName());
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
