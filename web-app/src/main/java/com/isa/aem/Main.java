package com.isa.aem;

import com.isa.aem.api.CurrencyReader;
import com.isa.aem.api.HistoryLoaderNbp;

public class Main {

    public static void main(String[] args) {
        HistoryLoaderNbp historyLoaderNbp=new HistoryLoaderNbp();
        historyLoaderNbp.loadAllCurrencyHistoryFromNbpApi();

        CurrencyReader currencyReader=new CurrencyReader();
        currencyReader.loadToCurrencyObject();
    }
}