package com.isa.aem.currency.calculator;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static com.isa.aem.currency.calculator.ValidationResultAssertion.assertThat;


@DisplayName("Calculator input date validation")
class ComplexConsoleCalculatorTest {
    private CurrencyRepository currencyRepository;
    private FileContentReader fileContentReader;
    private ComplexConsoleCalculatorValidator validator;

    @BeforeEach
    void setUp() {
        currencyRepository = new CurrencyRepository();
        fileContentReader = new FileContentReader();
        validator = new ComplexConsoleCalculatorValidator();

        fileContentReader.readFile();
    }

    @Test
    @DisplayName("Should return most current rate")
    void mostCurrentRate() {
        String currencyName = "EUR";
        Double expectedRate = 4.3117;

        Double mostCurrentRate = currencyRepository.getMostRecentExchangedRateForChosenCurrencyName(currencyName);
        assertEquals(expectedRate, mostCurrentRate);

    }

    @Test
    @DisplayName("Should return error when rate is invalid or return true when currency name is incorrect")
    void mostCurrentRateIsInvalid () {
        String currencyName = "EUR";
        String incorrectName = "EUS";
        Double incorrectRate = 4.4444;
        Double expectedRate = 4.3117;

        ValidationResult result = validator.validationResultOfMostRecentRate(currencyRepository, currencyName, incorrectRate);

        assertThat(result)
                .hasErrors();
    }

    @Test
    @DisplayName("Should return rate of given date")
    void rateByDate() {
        String name = "EUR";
        String dateStr = "20140303";
        Double expectedRate = 4.1978;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(dateStr, dateTimeFormatter);

        Double rateOfGivenDate = currencyRepository.getRateOfGivenDate(name, date);
        assertEquals(expectedRate, rateOfGivenDate);
    }

    @Test
    @DisplayName("Should return error when rate is incorrect")
    void invalidRateByDate() {
        String correctName = "EUR";
        String correctDateStr = "20140303";
        Double incorrectRate = 4.1978;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate correctDate = LocalDate.parse(correctDateStr, dateTimeFormatter);

        ValidationResult correctNameDateIncorrectRate = validator
                .validateDateOf(currencyRepository,
                        correctName,
                        correctDate,
                        incorrectRate);
        assertThat(correctNameDateIncorrectRate)
                .hasErrors();

    }

}