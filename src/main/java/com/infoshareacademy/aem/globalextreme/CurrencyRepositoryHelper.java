package com.infoshareacademy.aem.globalextreme;

import com.infoshareacademy.aem.Currency;

import java.time.LocalDate;
import java.util.List;


public interface CurrencyRepositoryHelper {

    Double getMin();
    Double getMax();
    boolean isContains(java.util.List<Currency> list, String s);
    boolean ContainsNumber(java.util.List<String> list, String s);
    List<LocalDate> getDate(Integer location);
}
