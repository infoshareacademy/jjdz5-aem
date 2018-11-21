package com.isa.aem.api;

import com.isa.aem.api.nbp.CurrencyRates;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateMethod {
    public static final LocalDate MIN_DATE_NBP_API = LocalDate.of(2002, 01, 01);
    public static final Integer MAX_DATE_RANGE_NBP_API = 93;
    public static final Integer ZOOM_END_DAY = 1;

    private JsonSchemeReaderNbpApi jsonSchemeReaderNbpApi = new JsonSchemeReaderNbpApi();

    public LocalDate readActualDateOnNbp() {
        LocalDate date = LocalDate.parse(jsonSchemeReaderNbpApi.loadActualJson().get(0).getEffectiveDate());
        return date;
    }

    public Long countDaysOfNbpHistory() {
        LocalDate actualDateNbp = readActualDateOnNbp();
        return DAYS.between(MIN_DATE_NBP_API, actualDateNbp);
    }

    public Long countTheNumberOfRepetitionsMaxRangeNbp() {
        return countDaysOfNbpHistory() / MAX_DATE_RANGE_NBP_API;
    }

    public LocalDate nextDayStart(LocalDate dateEnd) {
        return dateEnd.plusDays(ZOOM_END_DAY);
    }

    public LocalDate nextDayEndAddRangeTheRest(Long daysToDownload, LocalDate dateStart) {
        return dateStart.plusDays(daysToDownload);
    }

    public void checkIfTheRestIsSmallerThenMaxRangeNbpApi(Long daysToDownload, LocalDate dateStart, LocalDate dateEnd, List<List<CurrencyRates>> historyListNbp) {
        if (daysToDownload < MAX_DATE_RANGE_NBP_API) {
            dateStart = nextDayStart(dateEnd);
            dateEnd = nextDayEndAddRangeTheRest(daysToDownload, dateStart);
            historyListNbp.add(jsonSchemeReaderNbpApi.loadJsonToListWithTwoDates(dateStart.toString(), dateEnd.toString()));
        }
    }

    public Long countRangeToDownload(LocalDate dateEnd) {
        return countDaysOfNbpHistory() - DAYS.between(MIN_DATE_NBP_API, dateEnd.plusDays(1));
    }
}