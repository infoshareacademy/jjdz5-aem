package com.isa.aem;

import java.util.*;

public class CurrencyNameCountryFlags {
    private String name;
    private String country;
    private String flags;
    private String url;

    public CurrencyNameCountryFlags(String name, String country, String flags, String url) {
        this.name = name;
        this.country = country;
        this.flags = flags;
        this.url = url;
    }

    public CurrencyNameCountryFlags() {
    }

    @Override
    public String toString() {
        return "CurrencyNameCountryFlags{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", flags='" + flags + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyNameCountryFlags)) return false;
        CurrencyNameCountryFlags that = (CurrencyNameCountryFlags) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(country, that.country) &&
                Objects.equals(flags, that.flags) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, flags, url);
    }

    private static Map<String,CurrencyNameCountryFlags> currencies = new HashMap<>();

    public static Map<String,CurrencyNameCountryFlags> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(Map<String,CurrencyNameCountryFlags> currencies) {
        CurrencyNameCountryFlags.currencies = currencies;
    }


}
