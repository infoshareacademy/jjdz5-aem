package com.isa.aem.api;

import com.isa.aem.api.nbp.CurrencyRates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryReaderNbp {
    JsonReaderNbpApi jsonReaderNbpApi1 = new JsonReaderNbpApi();
    DateMethod dateMethod = new DateMethod();
    public List<List<CurrencyRates>> historyListNbp = new ArrayList<>();
    LocalDate dateStart = dateMethod.MIN_DATE_NBP_API;
    LocalDate dateEnd = dateStart.plusDays(dateMethod.MAX_RANGE_NBP_API);

    public void loadAllCurrencyHistoryFromNbpApi() {
        Integer iCount = 1;
        Long daysToDownload = dateMethod.countDaysOfNbpHistory();
        while (iCount < dateMethod.countTheNumberOfRepetitionsMaxRangeNbp()) {
            if (daysToDownload > dateMethod.MAX_RANGE_NBP_API) {
                historyListNbp.add(jsonReaderNbpApi1.loadJsonToListWithTwoDates(dateStart.toString(), dateEnd.toString()));
                dateStart = dateMethod.nextDayStart(dateEnd);
                dateEnd = dateStart.plusDays(dateMethod.MAX_RANGE_NBP_API);
            }

            dateMethod.checkIfTheRestIsSmallerThenMaxRangeNbpApi(daysToDownload, dateStart, dateEnd, historyListNbp);
            daysToDownload = dateMethod.countRangeTheCheck(dateEnd);
            iCount++;
        }
        System.out.println(historyListNbp);
    }
}
