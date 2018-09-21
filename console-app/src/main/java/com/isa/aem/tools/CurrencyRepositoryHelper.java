package com.isa.aem.tools;

import com.isa.aem.Currency;

import java.time.LocalDate;
import java.util.List;


public interface CurrencyRepositoryHelper {

    boolean containsCurrency(java.util.List<Currency> list, String s);
    boolean containsDate(List<Currency> list, LocalDate date);
    Double courseByDate(List<Currency> list, LocalDate date);
}