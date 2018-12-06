package com.isa.aem;

import com.isa.aem.api.CurrencyApiTranslator;
import com.isa.aem.data_loaders.CurrencyNameCountryFlagsLoader;
import com.isa.aem.data_loaders.FileContentReader;

import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        CurrencyRepository currencyRepository=new CurrencyRepository();
        CurrencyApiTranslator currencyApiTranslator=new CurrencyApiTranslator();
        currencyApiTranslator.importCurrencyFromApiToTheStaticList();

//             FileContentReader fileContentReader = new FileContentReader();
//        fileContentReader.readFile();
//        fileContentReader.addPLNToListCurrency();

   CurrencyNameCountryFlagsLoader currencyNameCountryFlagsLoader=new CurrencyNameCountryFlagsLoader();
//
//        System.out.println(currencyNameCountryFlagsLoader.currencyInformation);
 //      System.out.println(currencyRepository.getCurrenciesWithFullNameAndFlag().get(0).getCurrencyNameCountryFlags().getCountry());
        List<Currency> singleCurrency = currencyRepository.getCurrenciesWithFullNameAndFlag();
       singleCurrency.forEach(s-> System.out.println(s.getCurrencyNameCountryFlags().getCountry()));




    }
}