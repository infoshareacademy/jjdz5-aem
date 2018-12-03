package com.isa.aem.api;


import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.api.nbp.CurrencyDetails;
import com.isa.aem.api.nbp.CurrencyRates;
import com.isa.aem.utils.DataValidator;
import javafx.beans.binding.StringBinding;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CurrencyReader {
    HistoryLoaderNbp historyLoaderNbp = new HistoryLoaderNbp();
  public  List<Currency> dateTable=new ArrayList<>();
   public List<Currency> ww =new ArrayList();
DataValidator dataValidator=new DataValidator();

    public CurrencyReader() {
        loadToCurrencyObject();
        CurrencyRepository currencyRepository=new CurrencyRepository();
        currencyRepository.setCurrencies(dateTable);
    }


    public List<Currency> loadToCurrencyObject() {
        historyLoaderNbp.loadAllCurrencyHistoryFromNbpApi();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        for(int i=0; i<historyLoaderNbp.historyListNbp.size();i++){
            for(CurrencyRates cc:historyLoaderNbp.historyListNbp.get(i) ){

                for (CurrencyDetails rate : cc.getRates()) {
                  LocalDate dat=  dataValidator.dataParse(cc.getEffectiveDate().replace("-", ""));
                    dateTable.add(new Currency(rate.getCode(), dat, rate.getRate(), rate.getRate(), rate.getRate(), rate.getRate(), i));
                }
            }

        }
        return dateTable;
    }


}
