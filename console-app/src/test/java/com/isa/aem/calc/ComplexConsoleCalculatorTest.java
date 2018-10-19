package com.isa.aem.calc;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Returns data alidation")
class ComplexConsoleCalculatorTest {

    private CurrencyRepository currencyRepository;
    private FileContentReader fileContentReader;

    @BeforeEach
    void setUp() {
        currencyRepository = new CurrencyRepository();
        fileContentReader = new FileContentReader();

        fileContentReader.readFile();
    }

    @Test
    @DisplayName("Should return most current rate")
    void mostCurrentRate() {
        String currencyName = "EUR";
        Double rateTrue = 4.3117;

        Double mostCurrentRate = currencyRepository.getMostCurrentExchangedRateOfSelectedCurrencyFromTheFile(currencyName);
        assertEquals(rateTrue, mostCurrentRate);
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
}