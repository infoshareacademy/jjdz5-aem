package com.isa.aem;

import com.isa.aem.api.DateMethod;
import com.isa.aem.api.HistoryReaderNbp;



public class Main {

    public static void main(String[] args) {
        DateMethod dateMethod=new DateMethod();
        HistoryReaderNbp historyReaderNbp=new HistoryReaderNbp();
        historyReaderNbp.loadAllCurrencyHistoryFromNbpApi();

    }

}