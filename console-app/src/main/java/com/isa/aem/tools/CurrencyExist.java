package com.isa.aem.tools;

import com.isa.aem.CurrencyRepository;

public class CurrencyExist {
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private CurrencyRepositoryBin helper = new CurrencyRepositoryBin();


    public boolean checkCurrencyExist(String s) {
        return (helper.containsCurrency(currencyRepository.getCurrencies(), s));
    }
}
