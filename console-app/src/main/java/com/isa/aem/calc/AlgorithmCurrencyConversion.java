package com.isa.aem.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Algorithm {

    protected BigDecimal currencyConversionAlgorithm(Double amountGivenByUser, Double firstCourseOfCurrencyGivenByUser,
                                                     Double secondCourseOfCurrencyGivenByUser) {
        BigDecimal amount = new BigDecimal(amountGivenByUser);
        BigDecimal first = new BigDecimal(firstCourseOfCurrencyGivenByUser);
        BigDecimal second = new BigDecimal(secondCourseOfCurrencyGivenByUser);
        BigDecimal divide = first.divide(second, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
        BigDecimal multiply = divide.multiply(amount).setScale(4,RoundingMode.HALF_UP);

        return multiply;
    }

    protected BigDecimal calculateCourseAlgorithm(Double firstCourseOfCurrencyGivenByUser,
                                                  Double secondCourseOfCurrencyGivenByUser) {
        BigDecimal first = new BigDecimal(firstCourseOfCurrencyGivenByUser);
        BigDecimal second = new BigDecimal(secondCourseOfCurrencyGivenByUser);

        return second.divide(first, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
    }
}
