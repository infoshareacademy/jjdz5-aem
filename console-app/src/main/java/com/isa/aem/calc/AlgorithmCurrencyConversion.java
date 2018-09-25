package com.isa.aem.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AlgorithmCurrencyConversion {

    private static final Integer SCALE_ROUND = 1;

    protected BigDecimal currencyConversionAlgorithm(Double amountGivenByUser, Double firstRateOfCurrencyGivenByUser,
                                                     Double secondRateOfCurrencyGivenByUser) {
        BigDecimal amount = new BigDecimal(amountGivenByUser);
        BigDecimal firstRate = new BigDecimal(firstRateOfCurrencyGivenByUser);
        BigDecimal secondRate = new BigDecimal(secondRateOfCurrencyGivenByUser);
        BigDecimal division = firstRate.divide(secondRate, BigDecimal.ROUND_HALF_UP).setScale(SCALE_ROUND, RoundingMode.HALF_UP);
        BigDecimal multiplication = division.multiply(amount).setScale(4,RoundingMode.HALF_UP);

        return multiplication;
    }

    protected BigDecimal calculateCourseAlgorithm(Double firstCourseOfCurrencyGivenByUser,
                                                  Double secondCourseOfCurrencyGivenByUser) {
        BigDecimal first = new BigDecimal(firstCourseOfCurrencyGivenByUser);
        BigDecimal second = new BigDecimal(secondCourseOfCurrencyGivenByUser);

        return second.divide(first, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
    }
}
