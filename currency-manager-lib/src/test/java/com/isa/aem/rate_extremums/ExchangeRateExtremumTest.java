package com.isa.aem.rate_extremums;

import com.isa.aem.Currency;
import com.isa.aem.data_loaders.FileContentReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExchangeRateExtremumTest {

    private FileContentReader fileContentReader = new FileContentReader("sourceTestFilePath");
    private ExchangeRateExtremum exchangeRateExtremum = new ExchangeRateExtremum();

    @BeforeAll
    public void init() {
        fileContentReader.readFile();
    }

    @DisplayName("Global. One Min inside date range")
    @Test
    public void returnsOneMinimumWhenFoundGlobally() {

        List <Currency> expectedMinimum = Arrays.asList(
                new Currency("GBP", LocalDate.parse("2000-01-01"), 1.0000, 1.0000, 1.0000,4.4444, 0));
        List<Currency> result = exchangeRateExtremum.getMinExtremum("GBP", null, null);

        assertAll(() -> {
                    assertEquals(1, result.size());
                    assertEquals(expectedMinimum, result);
        });

    }

    @DisplayName("Global. One Max inside date range")
    @Test
    public void returnsOneMaximumWhenFoundGlobally() {

        List <Currency> expectedMinimum = Arrays.asList(
                new Currency("GBP", LocalDate.parse("2010-01-01"), 1.0000, 1.0000, 1.0000,6.6666, 0));
        List<Currency> result = exchangeRateExtremum.getMaxExtremum("GBP", null, null);

        assertAll(() -> {
            assertEquals(1, result.size());
            assertEquals(expectedMinimum, result);
        });
    }

    @DisplayName("Global. One Min on the edge of date range")
    @Test
    public void returnsOneMinimumWhenFoundOnTheEdgeOfGlobalRange() {

        List <Currency> expectedMinimum = Arrays.asList(
                new Currency("USD", LocalDate.parse("1990-01-01"), 1.0000, 1.0000, 1.0000,3.1234, 0));
        List<Currency> result = exchangeRateExtremum.getMinExtremum("USD", null, null);

        assertAll(() -> {
            assertEquals(1, result.size());
            assertEquals(expectedMinimum, result);
        });
    }

    @DisplayName("Global. One Max on the edge of date range")
    @Test
    public void returnsOneMaximumWhenFoundOnTheEdgeOfGlobalRange() {

        List <Currency> expectedMinimum = Arrays.asList(
                new Currency("USD", LocalDate.parse("2018-12-31"), 1.0000, 1.0000, 1.0000,7.0000, 0));
        List<Currency> result = exchangeRateExtremum.getMaxExtremum("USD", null, null);

        assertAll(() -> {
            assertEquals(1, result.size());
            assertEquals(expectedMinimum, result);
        });
    }

    @DisplayName("Global. More than one Min (three)")
    @Test
    public void returnMoreThanOneMinimumsWhenFoundGlobally() {

        List <Currency> expectedMinimum = Arrays.asList(
                new Currency("AUD", LocalDate.parse("1990-01-01"), 1.0000, 1.0000, 1.0000,1.0000, 0),
                new Currency("AUD", LocalDate.parse("1990-01-03"), 1.0000, 1.0000, 1.0000,1.0000, 0),
                new Currency("AUD", LocalDate.parse("1990-01-05"), 1.0000, 1.0000, 1.0000,1.0000, 0));
        List<Currency> result = exchangeRateExtremum.getMinExtremum("AUD", null, null);

        assertAll(() -> {
            assertEquals(3, result.size());
            assertEquals(expectedMinimum, result);
        });
    }

    @DisplayName("Global. More than one Max (three)")
    @Test
    public void returnMoreThanOneMaximumWhenFoundGlobally() {

        List <Currency> expectedMinimum = Arrays.asList(
                new Currency("EUR", LocalDate.parse("1990-01-04"), 1.0000, 1.0000, 1.0000,9.0000, 0),
                new Currency("EUR", LocalDate.parse("1990-01-08"), 1.0000, 1.0000, 1.0000,9.0000, 0),
                new Currency("EUR", LocalDate.parse("1990-01-09"), 1.0000, 1.0000, 1.0000,9.0000, 0));
        List<Currency> result = exchangeRateExtremum.getMaxExtremum("EUR", null, null);

        assertAll(() -> {
            assertEquals(3, result.size());
            assertEquals(expectedMinimum, result);
        });
    }

    @DisplayName("Local. More than one Min (two)")
    @Test
    public void returnMoreThanOneMinimumsWhenFoundLocally() {

        List <Currency> expectedMinimum = Arrays.asList(
                new Currency("AUD", LocalDate.parse("1990-01-03"), 1.0000, 1.0000, 1.0000,1.0000, 0),
                new Currency("AUD", LocalDate.parse("1990-01-05"), 1.0000, 1.0000, 1.0000,1.0000, 0));
        List<Currency> result = exchangeRateExtremum.getMinExtremum("AUD", LocalDate.parse("1990-01-02"), LocalDate.parse("1990-01-05"));

        assertAll(() -> {
            assertEquals(2, result.size());
            assertEquals(expectedMinimum, result);
        });
    }

    @DisplayName("Local. More than one Max (two)")
    @Test
    public void returnMoreThanOneMinimumWhenFoundLocally() {

        List <Currency> expectedMinimum = Arrays.asList(
                new Currency("EUR", LocalDate.parse("1990-01-04"), 1.0000, 1.0000, 1.0000,9.0000, 0),
                new Currency("EUR", LocalDate.parse("1990-01-08"), 1.0000, 1.0000, 1.0000,9.0000, 0));
        List<Currency> result = exchangeRateExtremum.getMaxExtremum("EUR", LocalDate.parse("1990-01-02"), LocalDate.parse("1990-01-08"));

        assertAll(() -> {
            assertEquals(2, result.size());
            assertEquals(expectedMinimum, result);
        });
    }

    @DisplayName("Global. No currency found in list")
    @Test
    public void noSuchElementExceptionIfElementNotFound() {

        Throwable exception = assertThrows(NoSuchElementException.class, () -> exchangeRateExtremum.getMinExtremum("HKD", null, null));

        assertEquals(exception.getMessage(), "No value present");
    }
}
