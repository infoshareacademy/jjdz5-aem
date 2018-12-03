package com.isa.aem;

import com.isa.aem.api.CurrencyReader;
import com.isa.aem.api.HistoryLoaderNbp;
import com.isa.aem.data_loaders.CurrencyNameCountryFlagsLoader;
import com.isa.aem.data_loaders.FileContentReader;

public class Main {

    public static void main(String[] args) {
        HistoryLoaderNbp historyLoaderNbp=new HistoryLoaderNbp();
        historyLoaderNbp.loadAllCurrencyHistoryFromNbpApi();
        FileContentReader fileContentReader=new FileContentReader();

        CurrencyReader currencyReader=new CurrencyReader();
        fileContentReader.addPLNToListCurrency();
        System.out.println(CurrencyRepository.getCurrencies());
        System.out.println(CurrencyRepository.getCurrencies().get(0));

    }
}