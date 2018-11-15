package com.isa.aem.api.nbp;


import java.util.ArrayList;
import java.util.List;

public class CurrencyDetails {
    private String table = null;
    private String no = null;
    private String effectiveDate = null;
    private List<CurrencyRate> rates = new ArrayList<>();

    void setTable(String table) {
        this.table = table;
    }

    void setNo(String no) {
        this.no = no;
    }

    void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    List<CurrencyRate> getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "CurrencyDetails{" +
                "table='" + table + '\'' +
                ", no='" + no + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", rates=" + rates +
                '}';
    }
}
