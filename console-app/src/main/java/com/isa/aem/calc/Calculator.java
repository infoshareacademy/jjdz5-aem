package com.isa.aem.calc;

import com.isa.aem.FileContentReader;
import com.isa.aem.tools.ConsoleReader;
import com.isa.aem.globalextreme.MyPrinter;
import com.isa.aem.tools.SingleCurrency;
import com.isa.aem.tools.ListAdder;

public class Calculator {

    private SingleCurrency singleCurrency = new SingleCurrency();
    private ListAdder listAdder = new ListAdder();
    private ConsoleReader consoleReader = new ConsoleReader();
    private MyPrinter myPrinter = new MyPrinter();

    private static final Integer BACK_TO_MENU = 0;
    private static final Integer BACK_TO_PREVIOUS_MENU = 0;

    public Calculator() {
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
    }

    public void run() {
        calculationWithDateOrWithout();
    }

    public void calculationWithDateOrWithout() {
        do {
            String strValue = consoleReader.getString(myPrinter.command());
            if () {
                double value = Double.parseDouble(strValue);

            } else {
                System.out.println("The value you entered is not a number");
            }
        }while ()
    }

    public void calculator() {

    }

    private double algorithm(double value) {

        return value * getActualCurseOfFirstChoice() / getActualCurseOfSecondChoice();
    }

     public void prepareFirstChoice(String firstChoice) {
        addSingleCurrencyToListOfFirstChoice(firstChoice);
        sortFirstChoiceByDate();
     }
     public void prepareSecondChoice(String secondChoice) {
        addSingleCurrencyToListOfSecondChoice(secondChoice);
        sortSecondChoiceByDate();
     }

    private void sortFirstChoiceByDate() {
        singleCurrency.sortSingleCurrencyByDate(singleCurrency.getSingleCurrencyFirstChoice());
    }

    private void sortSecondChoiceByDate() {
        singleCurrency.sortSingleCurrencyByDate(singleCurrency.getSingleCurrencySecondChoice());
    }

    private void addSingleCurrencyToListOfFirstChoice(String firstChoice) {
        listAdder.addSingleCurrencyToList(firstChoice,singleCurrency.getSingleCurrencyFirstChoice());

    }

    private void addSingleCurrencyToListOfSecondChoice(String secondChoice) {
        listAdder.addSingleCurrencyToList(secondChoice,singleCurrency.getSingleCurrencyFirstChoice());
    }

    private double getActualCurseOfFirstChoice() {
        return singleCurrency.getSingleCurrencyFirstChoice().get(singleCurrency.getSingleCurrencyFirstChoice().size()-1).getClose();
    }

    private double getActualCurseOfSecondChoice() {
        return singleCurrency.getSingleCurrencyFirstChoice().get(singleCurrency.getSingleCurrencyFirstChoice().size()-1).getClose();
    }

    private boolean checkIfItIsANumber(String strValue) {
        return strValue.matches("[0-9 .]+");
    }
}
