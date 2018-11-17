package com.isa.aem.api.nbp;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CurrencyDetails {
    @JsonProperty
    private String currency;
    @JsonProperty
    private String code;
    @JsonProperty("mid")
    private BigDecimal rate;

    public CurrencyDetails() {
    }

    public CurrencyDetails(String currency, String code, BigDecimal rate) {
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

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
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
