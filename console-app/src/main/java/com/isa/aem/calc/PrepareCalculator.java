package com.isa.aem.calc;

import com.isa.aem.tools.ListAdder;
import com.isa.aem.tools.SingleCurrency;

import java.time.LocalDate;

public class PrepareCalculator {

    private ListAdder listAdder = new ListAdder();
    private SingleCurrency singleCurrency = new SingleCurrency();
    private PrepareAmount amount = new PrepareAmount();


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
    protected String getNameSecond() {
        return singleCurrency.getSingleCurrencySecondChoice().get(singleCurrency.getSingleCurrencySecondChoice().size()-1).getName();
    }

    protected String getNameFirst() {
        return singleCurrency.getSingleCurrencyFirstChoice().get(singleCurrency.getSingleCurrencyFirstChoice().size()-1).getName();
    }

    protected double getActualCurseOfSecondChoice() {
        return singleCurrency.getSingleCurrencySecondChoice().get(singleCurrency.getSingleCurrencySecondChoice().size()-1).getClose();
    }

    protected double getAmount() {
        return amount.getAmount().get(0);
    }
}
