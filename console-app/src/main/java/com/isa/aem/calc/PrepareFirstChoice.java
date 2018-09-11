package com.isa.aem.calc;

public class PrepareFirstChoice{
    private PrepareCalculator prepareCalculator = new PrepareCalculator();
    protected void prepareFirstChoice(String firstChoice) {
        prepareCalculator.addSingleCurrencyToListOfFirstChoice(firstChoice);
        prepareCalculator.sortFirstChoiceByDate();
    }
}
