package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyNameCountryFlags;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AvailableCurrencyObject {
   private LocalDate dateMin;
   private LocalDate dateMax;
   private String currencyRange;
   private String name;
   private BigDecimal value;
   private CurrencyNameCountryFlags currencyNameCountryFlags;

    public AvailableCurrencyObject(LocalDate dateMin, LocalDate dateMax, String currencyRange, String name, BigDecimal value, CurrencyNameCountryFlags currencyNameCountryFlags) {
        this.dateMin = dateMin;
        this.dateMax = dateMax;
        this.currencyRange = currencyRange;
        this.name = name;
        this.value = value;
        this.currencyNameCountryFlags = currencyNameCountryFlags;
    }

    public AvailableCurrencyObject() {

    }

    @Override
    public String toString() {
        return "AvailableCurrencyObject{" +
                "dateMin=" + dateMin +
                ", dateMax=" + dateMax +
                ", currencyRange='" + currencyRange + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", currencyNameCountryFlags=" + currencyNameCountryFlags +
                '}';
    }

    public LocalDate getDateMin() {
        return dateMin;
    }

    public void setDateMin(LocalDate dateMin) {
        this.dateMin = dateMin;
    }

    public LocalDate getDateMax() {
        return dateMax;
    }

    public void setDateMax(LocalDate dateMax) {
        this.dateMax = dateMax;
    }

    public String getCurrencyRange() {
        return currencyRange;
    }

    public void setCurrencyRange(String currencyRange) {
        this.currencyRange = currencyRange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public CurrencyNameCountryFlags getCurrencyNameCountryFlags() {
        return currencyNameCountryFlags;
    }

    public void setCurrencyNameCountryFlags(CurrencyNameCountryFlags currencyNameCountryFlags) {
        this.currencyNameCountryFlags = currencyNameCountryFlags;
    }
}
