package com.isa.aem.calc;

import com.isa.aem.tools.*;

public class SecondCurrency {
    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private CurrencyExist currencyExist = new CurrencyExist();
    private ListAvailableCurrency available = new ListAvailableCurrency();
    private PrepareSecondChoice prepareSecondChoice = new PrepareSecondChoice();
    private Checker checker = new Checker();

    protected void getSecondCurrency() {
        String strValue;
        do {
            System.out.println(myPrinter.dubleNextLine());
            available.run();
            strValue = consoleReader.getString(myPrinter.enterSecondCurrency()).toUpperCase().trim();
            System.out.println(strValue);
            if (currencyExist.checkCurrencyExist(strValue)){
                prepareSecondChoice.prepareSecondChoice(strValue);
            } else {
                System.out.println(myPrinter.nextLine());
                System.out.println(myPrinter.currencyUnexist());
            }
        }while (checker.existCurrency(strValue));
    }
}
