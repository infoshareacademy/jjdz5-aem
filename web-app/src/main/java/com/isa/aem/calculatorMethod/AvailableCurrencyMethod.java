package com.isa.aem.calculatorMethod;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.calc.AlgorithmCurrencyConversion;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class AvailableCurrencyMethod {
 public  List<Currency> currencyInMaxDate;
 public  LocalDate dateMax;
 public  LocalDate dateMin;
 public CurrencyRepository currencyRepository=new CurrencyRepository();

 public LocalDate currencyMaxDateInFile(String nameOfCurrency){
     LocalDate date =currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile(nameOfCurrency);
     DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
     String date1=date.format(dateTimeFormatter);
     dateMax = LocalDate.parse(date1, dateTimeFormatter);
     return dateMax;
 }

    public LocalDate currencyMinDateInFile(String nameOfCurrency){
        LocalDate date =currencyRepository.getMinCurrentDateOfSelectedCurrencyFromTheFile(nameOfCurrency);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date1=date.format(dateTimeFormatter);
        dateMin = LocalDate.parse(date1, dateTimeFormatter);
        return dateMin;
    }

    public String currencyRange(String nameOfCurrency){
        return currencyMinDateInFile(nameOfCurrency) + " - " + currencyMaxDateInFile(nameOfCurrency);
    }

    public BigDecimal changeValue(String nameOfCurrency, String nameCurrencyWant, LocalDate dateMax){
        AlgorithmCurrencyConversion algorithmCurrencyConversion=new AlgorithmCurrencyConversion();
        Double currencyHave = currencyRepository.getRateOfGivenDate(nameOfCurrency, dateMax);
        Double currencyWant = currencyRepository.getRateOfGivenDate(nameCurrencyWant, dateMax);
        BigDecimal curseValue = algorithmCurrencyConversion.calculateCourseAlgorithm(currencyWant, currencyHave);
        return curseValue;
    }

    public  List<Currency> getSingleCurrencyWithMaxDate(LocalDate dateMax) {
        currencyInMaxDate =CurrencyRepository.getCurrencies().stream()
                .filter(cc->cc.getDate().equals(dateMax))
                .collect(Collectors.toList());

        return  currencyInMaxDate;
    }
}
