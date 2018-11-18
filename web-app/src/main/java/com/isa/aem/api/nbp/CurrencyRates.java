package com.isa.aem.api.nbp;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRates {

    @JsonProperty("effectiveDate")
    private String effectiveDate;
    @JsonProperty
    private String table;
    @JsonProperty
    private String no;
    @JsonProperty("rates")
    private  List<CurrencyDetails> rates;

    public List<CurrencyDetails> getRates() {
        return rates;
    }

    public void setRates(List<CurrencyDetails> rates) {
        this.rates = rates;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "CurrencyRates{" +
                "rates=" + rates +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", table='" + table + '\'' +
                ", no='" + no + '\'' +
                '}';
    }
}