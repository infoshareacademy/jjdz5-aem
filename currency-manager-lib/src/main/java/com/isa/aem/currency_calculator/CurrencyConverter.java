package com.isa.aem.currency_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConverter {

    private static final Integer SCALE_ROUND = 4;

    public BigDecimal currencyConversionAlgorithm(Double amountGivenByUser, Double firstRateOfCurrencyGivenByUser,
                                                  Double secondRateOfCurrencyGivenByUser) {
        BigDecimal amount = new BigDecimal(amountGivenByUser);
        BigDecimal firstRate = new BigDecimal(firstRateOfCurrencyGivenByUser);
        BigDecimal secondRate = new BigDecimal(secondRateOfCurrencyGivenByUser);
        BigDecimal division = firstRate.setScale(SCALE_ROUND, RoundingMode.HALF_UP)
                .divide(secondRate, BigDecimal.ROUND_HALF_UP).setScale(SCALE_ROUND, RoundingMode.HALF_UP);
        BigDecimal multiplication = division.setScale(SCALE_ROUND, RoundingMode.HALF_UP)
                .multiply(amount).setScale(SCALE_ROUND, RoundingMode.HALF_UP);

        return multiplication;
    }

    public BigDecimal calculateCourseAlgorithm(Double firstCourseOfCurrencyGivenByUser,
                                               Double secondCourseOfCurrencyGivenByUser) {
        BigDecimal first = new BigDecimal(firstCourseOfCurrencyGivenByUser);
        BigDecimal second = new BigDecimal(secondCourseOfCurrencyGivenByUser);

        return second.setScale(SCALE_ROUND, RoundingMode.HALF_UP)
                .divide(first, BigDecimal.ROUND_HALF_UP)
                .setScale(SCALE_ROUND, RoundingMode.HALF_UP);
    }
}
