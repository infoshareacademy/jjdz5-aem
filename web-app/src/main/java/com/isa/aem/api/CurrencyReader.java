package com.isa.aem.api;


import com.isa.aem.api.nbp.CurrencyDetails;
import com.isa.aem.api.nbp.CurrencyRates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyReader {
    HistoryLoaderNbp historyLoaderNbp = new HistoryLoaderNbp();

    public void loadToCurrencyObject() {
        historyLoaderNbp.loadAllCurrencyHistoryFromNbpApi();
        List<String> ww=new ArrayList<>();
//        historyLoaderNbp.historyListNbp.stream()
//                .map(s -> s.forEach(currencyRates -> currencyRates.getEffectiveDate()))
//                .collect(Collectors.toSet());
        for(int i=0; i<historyLoaderNbp.historyListNbp.size();i++){
            for(CurrencyRates cc:historyLoaderNbp.historyListNbp.get(i) ){
                ww.add(cc.getEffectiveDate());

            }
        }
        System.out.println(ww);
    }
}
