package com.isa.aem.calculatorMethod;


import com.isa.aem.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailableCurrencyTable {
    AvailableCurrencyMethod availableCurrencyMethod=new AvailableCurrencyMethod();
    LocalDate maxDate;
    List<AvailableCurrencyObject> tableListCurrencyObject=new ArrayList<>();
    CurrencyRepository currencyRepository = new CurrencyRepository();
    LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();

     public List<AvailableCurrencyObject> availableCurrencyObjects(String nameOfCurrency){

         this.maxDate= availableCurrencyMethod.currencyMaxDateInFile(nameOfCurrency);
         availableCurrencyMethod.getSingleCurrencyWithMaxDate(maxDate);

       for(Currency nameCurrencyWant :availableCurrencyMethod.getSingleCurrencyWithMaxDate(maxDate)){
           BigDecimal value=availableCurrencyMethod.changeValue(nameOfCurrency, nameCurrencyWant.getName(),maxDate);
           LocalDate dateMax=availableCurrencyMethod.currencyMaxDateInFile(nameCurrencyWant.getName());
           LocalDate dateMin=availableCurrencyMethod.currencyMinDateInFile(nameCurrencyWant.getName());
           String range= availableCurrencyMethod.currencyRange(nameCurrencyWant.getName());
           String name =nameCurrencyWant.getName();
           nameCurrencyWant.setCurrencyNameCountryFlags(CurrencyNameCountryFlags.getCurrencies().get(nameCurrencyWant.getName()));
           tableListCurrencyObject.add(new AvailableCurrencyObject(dateMin,dateMax,range,name,value,nameCurrencyWant.getCurrencyNameCountryFlags()));

       }

       return tableListCurrencyObject;
     }

}
