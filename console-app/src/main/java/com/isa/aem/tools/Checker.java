package com.isa.aem.tools;

public class Checker {
    private CurrencyExist currencyExist = new CurrencyExist();

    public boolean checkIfItIsANumber(String strValue) {
        return strValue.matches("[0-9 .]+");
    }

    public boolean existCurrency(String s) {
        if (currencyExist.checkCurrencyExist(s)) {
            return false;
        }
        return true;
    }
}
