package com.isa.aem.calc;

import com.isa.aem.FileContentReader;
import com.isa.aem.tools.ListAvailableCurrency;
import com.isa.aem.tools.MyPrinter;
import com.isa.aem.tools.SingleCurrency;

public class Calculator {
    private Amount amount = new Amount();
    private FirstCurrency firstCurrency  = new FirstCurrency();
    private SecondCurrency secondCurrency = new SecondCurrency();
    PrepareCalculator prepareCalculator = new PrepareCalculator();
    private Algorithm algorithm = new Algorithm();
    private ListAvailableCurrency available = new ListAvailableCurrency();
    private MyPrinter myPrinter = new MyPrinter();

    private static final Integer BACK_TO_MENU = 0;
    private static final Integer BACK_TO_PREVIOUS_MENU = 1;
    private static final Integer CALCULATE_WITH_DATE = 2;

    public Calculator() {
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();

    }

    public void run() {
        foldingTheCalculator();
    }

    public void foldingTheCalculator() {
        available.run();
        firstCurrency.getFirstCurrency();
        amount.getAmound();
        secondCurrency.getSecondCurrency();
        printEqual();
        printCurse();
    }

    private void printEqual() {
        System.out.println(myPrinter.emptySpace() + prepareCalculator.getAmount() + " " + prepareCalculator.getNameFirst() + " = " +
                algorithm.algorithm() + " " + prepareCalculator.getNameSecond());
    }

    private void printCurse() {
        System.out.println(myPrinter.emptySpace() + "Course " + prepareCalculator.getNameFirst() + " = " + algorithm.course());
    }






}