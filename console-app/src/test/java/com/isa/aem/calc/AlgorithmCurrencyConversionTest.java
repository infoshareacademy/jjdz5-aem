package com.isa.aem.calc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator test")
class AlgorithmCurrencyConversionTest {
    private AlgorithmCurrencyConversion algorithmCurrencyConversion;

    @BeforeEach
    void setUp() {
        algorithmCurrencyConversion = new AlgorithmCurrencyConversion();
    }

    @Test
    @DisplayName("Should return correct number")
    void returnCorrectNumber() {
        Integer SCALE_ROUND = 4;
        Double amountGivenByUser = 2.1000;
        Double firstRate = 1.1000;
        Double secondRate = 2.2000;

        BigDecimal trueAmountCalculated = new BigDecimal(1.0500).setScale(SCALE_ROUND, RoundingMode.HALF_UP);;
        BigDecimal trueExchangeRate = new BigDecimal(2.0000).setScale(SCALE_ROUND, RoundingMode.HALF_UP);

        BigDecimal amountCalculated = algorithmCurrencyConversion.currencyConversionAlgorithm(
                amountGivenByUser, firstRate, secondRate);

        BigDecimal exchangeRate = algorithmCurrencyConversion.calculateCourseAlgorithm(
                firstRate, secondRate);

        assertEquals(trueAmountCalculated, amountCalculated);
        assertEquals(trueExchangeRate, exchangeRate);
    }
}