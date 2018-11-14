package com.isa.aem.helpers;

import com.isa.aem.CurrencyRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DataValidator {

    CurrencyRepository currencyRepository = new CurrencyRepository();

    public LocalDate dataParse(String preparedDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parse = LocalDate.parse(preparedDate, dateTimeFormatter);

        return checkDayOfWeek(parse);
    }

    private LocalDate checkDayOfWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (DayOfWeek.SATURDAY == dayOfWeek) {
            return date.minusDays(1);
        }
        if (DayOfWeek.SUNDAY == dayOfWeek) {
            return date.minusDays(2);
        } else {
            return date;
        }
    }

    public String preparingDateRemovingPunctuationMarks(String dateStringFormat) {
        String replacePoint = dateStringFormat.replace(".", "");
        String replaceDash = replacePoint.replace("-", "");
        String replaceComma = replaceDash.replace(",", "");
        return replaceComma;
    }

    public boolean isIncorrectDateFormat(String givenDate) {
        try {
            LocalDate.parse(givenDate);
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }

    public boolean isNotWithinRange(LocalDate givenDate) {
        if (givenDate.isBefore(currencyRepository.getFirstDateFromRepository()) || givenDate.isAfter(currencyRepository.getLastDateFromRepository())) {
            return true;
        } else {
            return false;
        }
    }
}
