package com.isa.aem.calc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateService {

    protected LocalDate dataParse(String strDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String replacePoint = strDate.replace(".", "");
        String replaceDash = replacePoint.replace("-", "");
        String replaceComma = replaceDash.replace(",", "");
        LocalDate parse = LocalDate.parse(replaceComma, dateTimeFormatter);

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


}
