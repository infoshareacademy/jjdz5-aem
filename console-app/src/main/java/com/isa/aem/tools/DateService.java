package com.isa.aem.tools;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateService {

    public LocalDate dataParse(String preparedDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parse = LocalDate.parse(preparedDate, dateTimeFormatter);

        return checkDayOfWeek(parse);
    }

    private LocalDate checkDayOfWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (DayOfWeek.SATURDAY == dayOfWeek ){
            return date.minusDays(1);
        }if (DayOfWeek.SUNDAY == dayOfWeek ){
            return date.minusDays(2);
        }
        else {
            return date;
        }
    }

    public String preparingDateRemovingPunctuationMarks(String dateStringFormat) {
        String replacePoint = dateStringFormat.replace(".", "");
        String replaceDash = replacePoint.replace("-", "");
        String replaceComma = replaceDash.replace(",", "");
        return replaceComma;
    }

    public static void main(String[] args) {
        DateService dateService = new DateService();
        System.out.println(dateService.preparingDateRemovingPunctuationMarks("2017-06-06"));
    }
}
