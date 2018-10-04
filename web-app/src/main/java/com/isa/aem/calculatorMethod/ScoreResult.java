package com.isa.aem.calculatorMethod;

import java.time.LocalDate;

public class ScoreResult {
    Score score=new Score();

    public Score getScoreResult(String haveCurrency, String wantCurrency, LocalDate date, Double calculatorAmount){
        if(score.checkDateIfExistCurrencyWithGivenDate(haveCurrency, wantCurrency, date)){
            score = score.resultCalculator(haveCurrency, date, wantCurrency, calculatorAmount);
        }else {
            score = score.resultCalculator(haveCurrency, date, wantCurrency, calculatorAmount);
        }
        return score;
    }
}
