package com.isa.aem.api;

import com.isa.aem.api.nbp.CurrencyRates;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class OperationsOnDateRanges {
    public static final LocalDate MIN_DATE_NBP_API_ONE_YEAR = LocalDate.of(2018, 01, 01);
    public static final LocalDate MIN_DATE_NBP_API = LocalDate.of(2002, 01, 01);
    public static final Integer MAX_DATE_RANGE_NBP_API = 93;
    public static final Integer ZOOM_END_DAY = 1;

    private JsonSchemeReaderNbpApi jsonSchemeReaderNbpApi = new JsonSchemeReaderNbpApi();

    public LocalDate readActualDateOnNbp() {
        LocalDate date = LocalDate.parse(jsonSchemeReaderNbpApi.loadActualJson().get(0).getEffectiveDate());
        return date;
    }

    public Long countDaysOfNbpHistory(LocalDate startDate) {
        LocalDate actualDateNbp = readActualDateOnNbp();
        return DAYS.between(startDate, actualDateNbp);
    }

    public Long countTheNumberOfRepetitionsMaxRangeNbp(LocalDate startDate) {
        return countDaysOfNbpHistory(startDate) / MAX_DATE_RANGE_NBP_API;
    }

    public LocalDate nextDayStart(LocalDate endDate) {
        return endDate.plusDays(ZOOM_END_DAY);
    }

    public LocalDate nextDayEndAddRangeTheRest(Long daysToDownload, LocalDate startDate) {
        return startDate.plusDays(daysToDownload);
    }

    public void checkIfTheRestIsSmallerThenMaxRangeNbpApi(Long daysToDownload, LocalDate startDate, LocalDate endDate, List<List<CurrencyRates>> historyListNbp) {
        if (daysToDownload < MAX_DATE_RANGE_NBP_API) {
            startDate = nextDayStart(endDate);
            endDate = nextDayEndAddRangeTheRest(daysToDownload, startDate);
            historyListNbp.add(jsonSchemeReaderNbpApi.loadJsonToListWithTwoDates(startDate.toString(), endDate.toString()));
        }
    }

    public Long countRangeToDownload(LocalDate startDate, LocalDate endDate) {
        return countDaysOfNbpHistory(startDate) - DAYS.between(startDate, endDate.plusDays(1));
    }
}