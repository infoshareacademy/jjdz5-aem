package com.isa.aem.tools;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.time.LocalDate;
import java.util.List;

public class CurrencyPLN extends Currency {

    CurrencyRepository currencyRepository = new CurrencyRepository();

    public CurrencyPLN(String name, LocalDate date, Double open, Double high, Double low, Double close, Integer volume) {
        super(name, date, open, high, low, close, volume);
    }

    public CurrencyPLN() {

    }

    public void addPLNToListCurrency() {
        List<LocalDate> everySingleDateOfFile = currencyRepository.getEverySingleDateOfFile();
        for (LocalDate date : everySingleDateOfFile){
            CurrencyPLN currencyPLN = new CurrencyPLN(
                    "PLN", date, 1.0,1.0,1.0,1.0,1);
            currencyRepository.add(currencyPLN);
        }
    }
}
