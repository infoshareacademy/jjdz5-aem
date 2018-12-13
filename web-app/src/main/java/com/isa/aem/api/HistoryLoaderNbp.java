package com.isa.aem.api;

import com.isa.aem.api.nbp.CurrencyRates;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryLoaderNbp {
    JsonSchemeReaderNbpApi jsonSchemeReaderNbpApi = new JsonSchemeReaderNbpApi();
    OperationsOnDateRanges operationsOnDateRanges = new OperationsOnDateRanges();
    public List<List<CurrencyRates>> historyListNbp = new ArrayList<>();


    public List<List<CurrencyRates>> loadAllCurrencyHistoryFromNbpApi(LocalDate minDateStart) {
        LocalDate startDate = minDateStart;
        LocalDate endDate = startDate.plusDays(operationsOnDateRanges.MAX_DATE_RANGE_NBP_API);
        Integer i = 0;
        Long daysToDownload = operationsOnDateRanges.countDaysOfNbpHistory(minDateStart);
        while (i < operationsOnDateRanges.countTheNumberOfRepetitionsMaxRangeNbp(minDateStart)) {
            if (daysToDownload > operationsOnDateRanges.MAX_DATE_RANGE_NBP_API) {
                historyListNbp.add(jsonSchemeReaderNbpApi.loadJsonToListWithTwoDates(startDate.toString(), endDate.toString()));
                startDate = operationsOnDateRanges.nextDayStart(endDate);
                endDate = startDate.plusDays(operationsOnDateRanges.MAX_DATE_RANGE_NBP_API);
            }
            operationsOnDateRanges.checkIfTheRestIsSmallerThenMaxRangeNbpApi(daysToDownload, startDate, endDate, historyListNbp);
            daysToDownload = operationsOnDateRanges.countRangeToDownload(minDateStart, endDate);
            i++;
        }
        return historyListNbp;
    }
}