package com.isa.aem.calc;

import com.isa.aem.tools.*;

public class Amount {

    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private Checker checker = new Checker();
    private PrepareAmount prepareAmount = new PrepareAmount();
    void getAmound() {
        String strValue;
        String replace;
        do {
            strValue = consoleReader.getString(myPrinter.enterAmound());
            replace = strValue.replace(',', '.');
            if (checker.checkIfItIsANumber(replace)){
                double amount = Double.parseDouble(replace);
                prepareAmount.add(amount);
                break;
            }
            else {
                System.out.println(myPrinter.numberUnexist());
            }
        } while (!checker.checkIfItIsANumber(replace));
    }
}
