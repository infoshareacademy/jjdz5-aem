package com.isa.aem.model;

import com.isa.aem.Currency;

import java.time.LocalDate;
import java.util.List;

public class ExtremumObject {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String currencyName;
    private List<Currency> minExtremum;
    private List<Currency> maxExtremum;


    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public List<Currency> getMinExtremum() {
        return minExtremum;
    }

    public void setMinExtremum(List<Currency> minExtremum) {
        this.minExtremum = minExtremum;
    }

    public List<Currency> getMaxExtremum() {
        return maxExtremum;
    }

    public void setMaxExtremum(List<Currency> maxExtremum) {
        this.maxExtremum = maxExtremum;
    }
}
