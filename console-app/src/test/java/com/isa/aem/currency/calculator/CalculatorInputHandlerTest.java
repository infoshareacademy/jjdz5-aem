package com.isa.aem.currency.calculator;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Validation input data")
class CalculatorInputHandlerTest {

    private CalculatorInputHandler calculatorInputHandler;
    private FileContentReader fileContentReader;
    private CurrencyRepository currencyRepository;

    @BeforeEach
    void setUp() {
        calculatorInputHandler = new CalculatorInputHandler();
        fileContentReader = new FileContentReader();
        currencyRepository = new CurrencyRepository();

        fileContentReader.readFile();
    }

    @Test
    @DisplayName("Should return correct currency name from list")
    void nameIsValid() {
        String currencyName = "AUD";
        String incorrectCurrencyName = "sss";
        Boolean containsName = currencyRepository.containsCurrencyNameInCurrencyList(currencyName);
        Boolean notContainsName = currencyRepository.containsCurrencyNameInCurrencyList(incorrectCurrencyName);

        assertTrue(containsName);
        assertFalse(notContainsName);
    }

    @Test
    @DisplayName("Should have error when is not number")
    void validationNumber() {

        String number = "3257";
        String notNumber = "";
        boolean isANumber = calculatorInputHandler.isNumber(number);
        boolean isANotNumber = calculatorInputHandler.isNumber(notNumber);

        assertFalse(isANotNumber);
        assertTrue(isANumber);
    }

    @Test
    @DisplayName("Should return truth when currency contains given date")
    void currencyContainsDate() {
        String firstCurrencyName = "AUD";
        String secondCurrencyName = "EUR";
        String containsDateStr = "20140317";
        String notContainDateStr = "22140317";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate correctDate = LocalDate.parse(containsDateStr, dateTimeFormatter);
        LocalDate incorrectDate = LocalDate.parse(notContainDateStr, dateTimeFormatter);

        boolean containsDate = calculatorInputHandler
                .checkIfCurrencyNameSelectedByUserContainsGivenDate(
                        firstCurrencyName, secondCurrencyName, correctDate);
        boolean notContainsDate = calculatorInputHandler
                .checkIfCurrencyNameSelectedByUserContainsGivenDate(
                        firstCurrencyName, secondCurrencyName, incorrectDate);

        assertTrue(containsDate);
        assertFalse(notContainsDate);
    }

    @Test
    @DisplayName("Should return truth when is only 8 digits and date form is correct")
    void dateValid() {
        String correctDateStr = "20140303";
        String incorrectDateStr1 = "201433";
        String incorrectDateStr2 = "20143030";

        Boolean correctDate = calculatorInputHandler
                .checkIfItIsCorrectDataFormatAndOnlyEightDigits(correctDateStr);
        Boolean incorrectDate1 = calculatorInputHandler
                .checkIfItIsCorrectDataFormatAndOnlyEightDigits(incorrectDateStr1);
        Boolean incorrectDate2 = calculatorInputHandler
                .checkIfItIsCorrectDataFormatAndOnlyEightDigits(incorrectDateStr2);

        assertTrue(correctDate);
        assertFalse(incorrectDate1);
        assertFalse(incorrectDate2);
    }
}