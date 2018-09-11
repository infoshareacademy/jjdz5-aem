package com.isa.aem.calc;

import com.isa.aem.tools.*;

public class Amount {

    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();
    private Checker checker = new Checker();
    private PrepareAmount prepareAmount = new PrepareAmount();
    void getAmound() {
        do {
            String strValue = consoleReader.getString(myPrinter.enterAmound());
            if (checker.checkIfItIsANumber(strValue)){
                double amount = Double.parseDouble(strValue);
                prepareAmount.add(amount);
                break;
            }
            else {
                System.out.println(myPrinter.numberUnexist());
            }

        } while (true);
    }
}
