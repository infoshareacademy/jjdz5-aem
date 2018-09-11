package com.isa.aem.calc;

public class PrepareSecondChoice {
    private PrepareCalculator prepareCalculator = new PrepareCalculator();

    protected void prepareSecondChoice(String secondChoice) {
       prepareCalculator.addSingleCurrencyToListOfSecondChoice(secondChoice);
       prepareCalculator.sortSecondChoiceByDate();
    }
}
