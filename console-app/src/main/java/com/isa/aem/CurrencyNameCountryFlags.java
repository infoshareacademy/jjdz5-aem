package com.isa.aem;

import java.util.*;

public class CurrencyNameCountryFlags {

    private String name;
    private String currency;
    private String country;
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyNameCountryFlags)) return false;
        CurrencyNameCountryFlags that = (CurrencyNameCountryFlags) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(country, that.country) &&
                Objects.equals(url, that.url);
    }

    @Override
    public String toString() {
        return "CurrencyNameCountryFlags{" +
                "name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", country='" + country + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CurrencyNameCountryFlags(String name, String currency, String country, String url) {
        this.name = name;
        this.currency = currency;
        this.country = country;
        this.url = url;
    }

    public CurrencyNameCountryFlags() {
    }

    private static Map<String,CurrencyNameCountryFlags> currencies = new HashMap<>();

    public static Map<String,CurrencyNameCountryFlags> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(Map<String,CurrencyNameCountryFlags> currencies) {
        CurrencyNameCountryFlags.currencies = currencies;
    }


}
