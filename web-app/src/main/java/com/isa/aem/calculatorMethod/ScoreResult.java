package com.isa.aem.calculatorMethod;

import java.time.LocalDate;

public class ScoreResult {
    Score score = new Score();

    public Score getScoreResult(String haveCurrency, String wantCurrency, LocalDate date, Double calculatorAmount) {
        if (score.isDateExistInCurrencyWithGivenDate(haveCurrency, wantCurrency, date)) {
            return score.resultCalculator(haveCurrency, date, wantCurrency, calculatorAmount);
        } else {
            return score.resultCalculator(haveCurrency, date, wantCurrency, calculatorAmount);
        }

    }
}
