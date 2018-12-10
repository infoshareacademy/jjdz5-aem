package com.isa.aem.api;

import com.isa.aem.api.nbp.CurrencyRates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryLoaderNbp {
    JsonSchemeReaderNbpApi jsonSchemeReaderNbpApi = new JsonSchemeReaderNbpApi();
    DateMethod dateMethod = new DateMethod();
    public List<List<CurrencyRates>> historyListNbp = new ArrayList<>();


    public List<List<CurrencyRates>> loadAllCurrencyHistoryFromNbpApi(LocalDate dateStart1) {
        LocalDate dateStart = dateStart1;
        LocalDate dateEnd = dateStart.plusDays(dateMethod.MAX_DATE_RANGE_NBP_API);
        Integer iCount = 0;
        Long daysToDownload = dateMethod.countDaysOfNbpHistory(dateStart1);
        while (iCount < dateMethod.countTheNumberOfRepetitionsMaxRangeNbp(dateStart1)) {
                       if (daysToDownload > dateMethod.MAX_DATE_RANGE_NBP_API) {
                historyListNbp.add(jsonSchemeReaderNbpApi.loadJsonToListWithTwoDates(dateStart.toString(), dateEnd.toString()));
                dateStart = dateMethod.nextDayStart(dateEnd);
                dateEnd = dateStart.plusDays(dateMethod.MAX_DATE_RANGE_NBP_API);
            }
            dateMethod.checkIfTheRestIsSmallerThenMaxRangeNbpApi(daysToDownload, dateStart, dateEnd, historyListNbp);
            daysToDownload = dateMethod.countRangeToDownload(dateStart1,dateEnd);
            iCount++;
        }
        return historyListNbp;
    }
}