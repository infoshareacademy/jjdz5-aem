package com.isa.aem.api;

import com.isa.aem.api.nbp.CurrencyRates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryReaderNbp {
    JsonReaderNbpApi jsonReaderNbpApi1 = new JsonReaderNbpApi();
    DateMethod dateMethod = new DateMethod();
    public List<List<CurrencyRates>> historyListNbp = new ArrayList<>();


    public void loadHistoryCurrencyFromMod() {
        LocalDate dateEnd=dateMethod.readActualDateOnNbp();
        LocalDate dateStart=dateEnd.minusDays(dateMethod.countModAboutMaxRangeNbp());
        historyListNbp.add(jsonReaderNbpApi1.loadJsonToListWithTwoDates(dateEnd.toString(), dateStart.toString()));
    }



}
