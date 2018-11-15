package com.isa.aem.api.nbp;

import java.util.HashMap;
import java.util.Map;


public class CurrencyRate{

    private String currency;
    private String code;
    private Double mid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
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

    public Double getMid() {
        return mid;
    }

    public void setMid(Double mid) {
        this.mid = mid;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", mid=" + mid +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}