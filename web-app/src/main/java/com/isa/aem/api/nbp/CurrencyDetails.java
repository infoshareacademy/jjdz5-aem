package com.isa.aem.api.nbp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDetails {
    @JsonProperty
    private String currency;
    @JsonProperty
    private String code;
    @JsonProperty("mid")
    private Double rate;

    public CurrencyDetails() {
    }

    public CurrencyDetails(String currency, String code, Double rate) {
        this.currency = currency;
        this.code = code;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CurrencyDetails{" +
                "currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rate=" + rate +
                '}';
    }
}