package com.isa.aem;

import com.isa.aem.api.HistoryReaderNbp;


public class Main {

    public static void main(String[] args) {
        HistoryReaderNbp historyReaderNbp=new HistoryReaderNbp();
        historyReaderNbp.loadHistoryCurrencyFromMod();

        System.out.println(historyReaderNbp.historyListNbp);
    }
}