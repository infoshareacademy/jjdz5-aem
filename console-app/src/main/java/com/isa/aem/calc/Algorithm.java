package com.isa.aem.calc;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;
@ApplicationScoped
public class Algorithm {

    protected BigDecimal currencyConversionAlgorithm(Double amountGivenByUser, Double firstCourse, Double secondCourse) {
        BigDecimal amount = new BigDecimal(amountGivenByUser);
        BigDecimal first = new BigDecimal(firstCourse);
        BigDecimal second = new BigDecimal(secondCourse);
        BigDecimal divide = first.divide(second, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
        BigDecimal multiply = divide.multiply(amount).setScale(4,RoundingMode.HALF_UP);

        return multiply;
    }

    protected BigDecimal calculateCourseAlgorithm(Double first, Double second) {
        BigDecimal one = new BigDecimal(first);
        BigDecimal two = new BigDecimal(second);

        return two.divide(one, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
    }
}
