package com.isa.aem;

import com.isa.aem.calculatorMethod.AvailableCurrencyTable;
import com.isa.aem.calculatorMethod.ScoreResult;

public class Main {

    public static void main(String[] args) {

       FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
      AvailableCurrencyTable availableCurrencyTable=new AvailableCurrencyTable();
      ScoreResult scoreResult=new ScoreResult();
        CurrencyRepository currencyRepository=new CurrencyRepository();
        String currencyInTable=null;
        if (currencyInTable == null) {
            currencyInTable="AUD";
            availableCurrencyTable.availableCurrencyObjects(currencyInTable);
            availableCurrencyTable.tableListCurrencyObject.forEach(s-> System.out.println(s.getValue()));
        }
    }

}
