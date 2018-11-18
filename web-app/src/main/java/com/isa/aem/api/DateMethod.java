package com.isa.aem.api;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateMethod {
    private static final LocalDate MIN_DATE_NBP_API = LocalDate.of(2012, 01, 01);
    private static final Integer MAX_RANGE_NBP_API = 93;

    private JsonReaderNbpApi jsonReaderNbpApi = new JsonReaderNbpApi();

    public LocalDate readActualDateOnNbp() {
        LocalDate date = LocalDate.parse(jsonReaderNbpApi.loadActualJson().get(0).getEffectiveDate());
        return date;
    }

    public Long countDaysOfNbpHistory() {
        LocalDate actualDateNbp = readActualDateOnNbp();
        return DAYS.between(MIN_DATE_NBP_API, actualDateNbp);
    }

    public Long countTheNumberOfRepetitionsMaxRangeNbp() {
        return countDaysOfNbpHistory() / MAX_RANGE_NBP_API;
    }

    public Long countModAboutMaxRangeNbp() {
        return countDaysOfNbpHistory() % MAX_RANGE_NBP_API;
    }
}
