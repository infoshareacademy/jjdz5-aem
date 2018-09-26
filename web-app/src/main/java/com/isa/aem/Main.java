package com.isa.aem;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
        CurrencyRepository currencyRepository = new CurrencyRepository();
        Set<Currency> singleCurrency = new HashSet<>();
        for (Currency cc : CurrencyRepository.getCurrencies()) {
            cc.setCurrencyNameCountryFlags(CurrencyNameCountryFlags.getCurrencies().get(cc.getName()));
        }
   // singleCurrency.forEach(s-> System.out.println(s.getCurrencyNameCountryFlags().getCurrency()));


        singleCurrency.forEach(ss-> System.out.println(ss.getName() + "-" + ss.getCurrencyNameCountryFlags().getCurrency()));

    }


}
