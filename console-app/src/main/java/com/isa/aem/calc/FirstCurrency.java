package com.isa.aem.calc;

import com.isa.aem.tools.*;

public class FirstCurrency {
    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private CurrencyExist currencyExist = new CurrencyExist();
    private ListAvailableCurrency availalbe = new ListAvailableCurrency();
    private PrepareFirstChoice prepareFirstChoice = new PrepareFirstChoice();
    private Checker checker = new Checker();

    void getFirstCurrency() {
        String strValue;
        do {
            availalbe.run();
            strValue = consoleReader.getString(myPrinter.enterFirstCurrency()).toUpperCase().trim();
            if (currencyExist.checkCurrencyExist(strValue)) {
                prepareFirstChoice.prepareFirstChoice(strValue);
            } else {
                System.out.println(myPrinter.nextLine());
                System.out.println(myPrinter.currencyUnexist());
            }
        } while (checker.existCurrency(strValue));

    }
}