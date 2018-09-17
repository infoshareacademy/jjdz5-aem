package com.isa.aem.calc;
import com.isa.aem.tools.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConsoleSupport {
    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private CurrencyExist currencyExist = new CurrencyExist();
    private Checker checker = new Checker();
    private PrepareCalculator prepareCalculator = new PrepareCalculator();


    protected String firstCurrencyService() {
        String strValue;
        do {
            strValue= consoleReader.getString(myPrinter.enterFirstCurrency()).trim().toUpperCase();
            if (currencyExist.checkCurrencyExist(strValue)) {
                prepareCalculator.prepareFirstChoice(strValue);
            } else {
                System.out.println(myPrinter.currencyUnexist());
            }
        } while (checker.existCurrency(strValue));
        return strValue;
    }

    protected String secondCurrencyService() {
        String strValue;
        do {
            strValue = consoleReader.getString(myPrinter.enterSecondCurrency()).trim().toUpperCase();
            if (currencyExist.checkCurrencyExist(strValue)){
                prepareCalculator.prepareSecondChoice(strValue);
            } else {
                System.out.println(myPrinter.nextLine());
                System.out.println(myPrinter.currencyUnexist());
            }
        }while (checker.existCurrency(strValue));
        return strValue;
    }

    double amountSercive() {
        String strValue;
        String replace;
        Double amount = null;
        do {
            strValue = consoleReader.getString(myPrinter.enterAmount());
            replace = strValue.replace(',', '.');
            if (checker.checkIfItIsANumber(replace)){
                amount = Double.parseDouble(replace);
            } else {
                System.out.println(myPrinter.numberUnexist());
            }
        } while (!checker.checkIfItIsANumber(replace));
        return amount;
    }

    LocalDate dataParse(String strDate) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String replacePoint = strDate.replace(".", "");
            String replaceDash = replacePoint.replace("-", "");
            String replaceComma = replaceDash.replace(",", "");
            LocalDate parse = LocalDate.parse(replaceComma, dateTimeFormatter);

            return checkDayOfWeek(parse);
    }

    LocalDate dataServis() {
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