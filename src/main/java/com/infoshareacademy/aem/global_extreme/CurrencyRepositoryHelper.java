package com.infoshareacademy.aem.global_extreme;

import com.infoshareacademy.aem.Currency;

import java.time.LocalDate;
import java.util.List;


public interface CurrencyRepositoryHelper {

    Double getMin();
    Double getMax();
    boolean isContains(java.util.List<Currency> list, String s);
    boolean isContainsInt(java.util.List<Integer> list, int i);
    List<LocalDate> getDate(Integer location);
}
