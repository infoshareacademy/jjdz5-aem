package com.isa.aem.calc;

import com.isa.aem.tools.ConsoleReader;
import com.isa.aem.tools.CurrencyRepositoryHelper;
import com.isa.aem.tools.MyPrinter;

import javax.inject.Inject;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateServis {

    @Inject
    CurrencyRepositoryHelper currencyRepositoryHelper;
    @Inject
    ConsoleReader consoleReader;
    @Inject
    MyPrinter myPrinter;

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

    LocalDate dataService() {
        String strDate = consoleReader.getString(myPrinter.enterDate());
        LocalDate date = null;
        try {
            date = dataParse(strDate);
        } catch (Exception ex) {
            System.out.println(myPrinter.wrongDate());
            dataParse(strDate);
        }
        return date;
    }
}
