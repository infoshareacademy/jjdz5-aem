package com.isa.aem;

import java.time.LocalDate;

public class Currency {

    private String name;
    private LocalDate date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Integer volume;
    private CurrencyNameCountryFlags currencyNameCountryFlags;

    public Currency() {
    }

    public Currency(String name) {
        this.name = name;
    }

    public Currency(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public Currency(String name, LocalDate date, Double open, Double high, Double low, Double close, Integer volume) {
        this.name = name;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return getName() + ',' + getDate() + ',' + getOpen() + ',' + getHigh() + ',' + getLow() + ',' + getClose() + ',' + getVolume();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public CurrencyNameCountryFlags getCurrencyNameCountryFlags() {
        return currencyNameCountryFlags;
    }

    public void setCurrencyNameCountryFlags(CurrencyNameCountryFlags currencyNameCountryFlags) {
        this.currencyNameCountryFlags = currencyNameCountryFlags;
    }
}

