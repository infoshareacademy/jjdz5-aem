package com.isa.aem.globalextreme;

import com.isa.aem.Currency;

import java.time.LocalDate;
import java.util.List;


public interface CurrencyRepositoryHelper {

    Double getMin();
    Double getMax();
    boolean containsCurrency(java.util.List<Currency> list, String s);
    LocalDate getMinDate();
    LocalDate getMaxDate();
}