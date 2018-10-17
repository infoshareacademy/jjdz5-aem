package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;
import com.isa.aem.MenuProject;

import java.time.LocalDate;

public class MainTest {
    public static void main(String[] args) {

        CurrencyRepository  currencyRepository=new CurrencyRepository();
        FileContentReader  fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
        AvailableCurrencyMethod availableCurrencyMethod=new AvailableCurrencyMethod();

        // arrange
        String currency="PLN";
        System.out.println(currency);
        // act
        LocalDate result = availableCurrencyMethod.getMaxDateForSelectedCurrency(currency);
        System.out.println(result);
    }

}
