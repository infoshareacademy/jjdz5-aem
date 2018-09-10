package com.isa.aem.tools;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.util.List;

public class ListAdder {

    CurrencyRepository currencyRepository = new CurrencyRepository();
    SingleCurrency singleCurrency = new SingleCurrency();

    public void addSingleCurrencyToList(String s, List<Currency> list) {
        for (Currency c : currencyRepository.getCurrencies()) {
            if (c.getName().equals(s)) {
                list.add(c);
            }
        }
    }


}
