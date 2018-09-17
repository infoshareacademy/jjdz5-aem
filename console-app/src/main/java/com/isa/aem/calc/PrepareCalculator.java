package com.isa.aem.calc;

import com.isa.aem.tools.ListAdder;
import com.isa.aem.tools.SingleCurrency;


public class PrepareCalculator {

    private ListAdder listAdder = new ListAdder();
    private SingleCurrency singleCurrency = new SingleCurrency();

    protected void prepareFirstChoice(String firstChoice) {
        addSingleCurrencyToListOfFirstChoice(firstChoice);
        sortFirstChoiceByDate();
    }
    protected void prepareSecondChoice(String secondChoice) {
        addSingleCurrencyToListOfSecondChoice(secondChoice);
        sortSecondChoiceByDate();
    }


    void sortFirstChoiceByDate() {
        singleCurrency.sortSingleCurrencyByDate(singleCurrency.getSingleCurrencyFirstChoice());
    }

    void sortSecondChoiceByDate() {
        singleCurrency.sortSingleCurrencyByDate(singleCurrency.getSingleCurrencySecondChoice());
    }

    void addSingleCurrencyToListOfFirstChoice(String firstChoice) {
        listAdder.addSingleCurrencyToList(firstChoice,singleCurrency.getSingleCurrencyFirstChoice());

    }

    void addSingleCurrencyToListOfSecondChoice(String secondChoice) {
        listAdder.addSingleCurrencyToList(secondChoice,singleCurrency.getSingleCurrencySecondChoice());
    }

    protected double getActualCurseOfFirstChoice() {
        return singleCurrency.getSingleCurrencyFirstChoice().get(singleCurrency.getSingleCurrencyFirstChoice().size()-1).getClose();
    }

    protected double getActualCurseOfSecondChoice() {
        return singleCurrency.getSingleCurrencySecondChoice().get(singleCurrency.getSingleCurrencySecondChoice().size()-1).getClose();
    }
}
