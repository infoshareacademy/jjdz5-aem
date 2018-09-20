package com.isa.aem.calc;
import com.isa.aem.tools.*;

import javax.inject.Inject;

public class ConsoleSupport {
    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private CurrencyExist currencyExist = new CurrencyExist();
    private Checker checker = new Checker();

    @Inject
    DateServis dateServis; 


//    protected String firstCurrencySelectedByUserService() {
//        String strValue;
//        do {
//            strValue= consoleReader.getString(myPrinter.enterFirstCurrency()).trim().toUpperCase();
//            if (currencyExist.checkCurrencyExist(strValue)) {
//                prepareCalculator.prepareFirstChoice(strValue);
//            } else {
//                System.out.println(myPrinter.currencyUnexist());
//            }
//        } while (checker.existCurrency(strValue));
//        return strValue;
//    }
//
//    protected String secondCurrencySelectedByUserService() {
//        String strValue;
//        do {
//            strValue = consoleReader.getString(myPrinter.enterSecondCurrency()).trim().toUpperCase();
//            if (currencyExist.checkCurrencyExist(strValue)){
//                prepareCalculator.prepareSecondChoice(strValue);
//            } else {
//                System.out.println(myPrinter.nextLine());
//                System.out.println(myPrinter.currencyUnexist());
//            }
//        }while (checker.existCurrency(strValue));
//        return strValue;
//    }
//
//    double amountGivenByUserServis() {
//        String strValue;
//        String replace;
//        Double amount = null;
//        do {
//            strValue = consoleReader.getString(myPrinter.enterAmount());
//            replace = strValue.replace(',', '.');
//            if (checker.checkIfItIsANumber(replace)){
//                amount = Double.parseDouble(replace);
//            } else {
//                System.out.println(myPrinter.numberUnexist());
//            }
//        } while (!checker.checkIfItIsANumber(replace));
//        return amount;
//    }
//
//
//    LocalDate dateService() {
//        String strDate = consoleReader.getString(myPrinter.enterDate());
//        LocalDate date = null;
//        try {
//            date = this.dateServis.dataParse(strDate);
//        } catch (Exception ex) {
//            System.out.println(myPrinter.wrongDate());
//            this.dateServis.dataParse(strDate);
//        }
//        return date;
//    }

   
}