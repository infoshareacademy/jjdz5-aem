package com.isa.aem.api;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.api.nbp.CurrencyDetails;
import com.isa.aem.api.nbp.CurrencyRates;
import com.isa.aem.data_loaders.FileContentReader;
import com.isa.aem.utils.DataValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConversionApiOnCurrencyObject {
    HistoryLoaderNbp historyLoaderNbp = new HistoryLoaderNbp();
    public List<Currency> dateTable = new ArrayList<>();
    DataValidator dataValidator = new DataValidator();

    public void importCurrencyFromApiToTheStaticList() {
        historyLoaderNbp.loadAllCurrencyHistoryFromNbpApi();
        parseApiTableOnCurrencyTable();
        CurrencyRepository currencyRepository = new CurrencyRepository();
        currencyRepository.setCurrencies(dateTable);
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.addPLNToListCurrency();
    }

    public List<Currency> parseApiTableOnCurrencyTable() {
        historyLoaderNbp.loadAllCurrencyHistoryFromNbpApi();
        for (int iterator = 0; iterator < historyLoaderNbp.historyListNbp.size(); iterator++) {
            for (CurrencyRates currencyRates : historyLoaderNbp.historyListNbp.get(iterator)) {
                for (CurrencyDetails currencyDetails : currencyRates.getRates()) {
                    LocalDate dateExchange = dataValidator.dataParse(currencyRates.getEffectiveDate().replace("-", ""));
                    dateTable.add(new Currency(currencyDetails.getCode(), dateExchange, currencyDetails.getRate(), currencyDetails.getRate(), currencyDetails.getRate(), currencyDetails.getRate(), 0));
                }
            }
        }
        return dateTable;
    }
}