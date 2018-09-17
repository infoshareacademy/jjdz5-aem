package com.isa.aem.calc;

import com.isa.aem.MenuInformation;
import com.isa.aem.tools.*;

public class ConsoleSupport {
    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private CurrencyExist currencyExist = new CurrencyExist();
    private Checker checker = new Checker();
    PrepareCalculator prepareCalculator = new PrepareCalculator();
    private MenuInformation menuInformation = new MenuInformation();

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

    protected void optionsSmallMenu() {
    }
}