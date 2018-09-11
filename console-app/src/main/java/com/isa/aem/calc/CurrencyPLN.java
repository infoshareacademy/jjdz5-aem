package com.isa.aem.calc;

import com.isa.aem.Currency;

import java.time.LocalDate;

public class CurrencyPLN extends Currency {


    public CurrencyPLN(String name, LocalDate date, Double open, Double high, Double low, Double close, Integer volume) {
        super(name, date, open, high, low, close, volume);
    }
}
