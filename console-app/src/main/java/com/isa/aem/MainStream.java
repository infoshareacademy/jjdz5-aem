package com.isa.aem;

import com.isa.aem.calc.DateService;

import java.time.LocalDate;

public class MainStream {
    public static void main(String[] args) {
        CurrencyRepository currencyRepository = new CurrencyRepository();
        FileContentReader fileContentReader = new FileContentReader();
        DateService dateService = new DateService();
        fileContentReader.readFile();
        LocalDate localDate = dateService.dataParse("19930315");
        System.out.println(currencyRepository.checkIfExistCurrencyWithGivenDate("aud",localDate));
    }
}
