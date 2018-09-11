package com.isa.aem.calc;

import com.isa.aem.FileContentReader;
import com.isa.aem.tools.SingleCurrency;

public class Calculator {
    private Amount amount = new Amount();
    private FirstCurrency firstCurrency  = new FirstCurrency();
    private SecondCurrency secondCurrency = new SecondCurrency();
    PrepareCalculator prepareCalculator = new PrepareCalculator();
    private Algorithm algorithm = new Algorithm();
    SingleCurrency singleCurrency = new SingleCurrency();

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
        firstCurrency.getFirstCurrency();
        secondCurrency.getSecondCurrency();
        amount.getAmound();
        System.out.println(algorithm.algorithm());
    }






}