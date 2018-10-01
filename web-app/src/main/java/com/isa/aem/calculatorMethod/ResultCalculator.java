package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.calc.AlgorithmCurrencyConversion;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ResultCalculator {


    public Score resultCalculator(String haveCurrency, LocalDate date, String wantCurrency, Double calculatorAmount) {
        CurrencyRepository currencyRepository = new CurrencyRepository();
        AlgorithmCurrencyConversion algorithmCurrencyConversion = new AlgorithmCurrencyConversion();
        Double currencyHave = currencyRepository.getRateOfGivenDate(haveCurrency, date);
        Double currencyWant = currencyRepository.getRateOfGivenDate(wantCurrency, date);
        BigDecimal score1 = algorithmCurrencyConversion.currencyConversionAlgorithm(calculatorAmount, currencyHave, currencyWant);
        BigDecimal curseValue = algorithmCurrencyConversion.calculateCourseAlgorithm(currencyHave, currencyWant);
        return new Score(score1, haveCurrency, wantCurrency, date, curseValue);

    }
}
