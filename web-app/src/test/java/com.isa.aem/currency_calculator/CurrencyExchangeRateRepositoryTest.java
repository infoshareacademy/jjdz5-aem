package com.isa.aem.currency_calculator;

import com.isa.aem.data_loaders.FileContentReader;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class CurrencyExchangeRateRepositoryTest {
    private FileContentReader fileContentReader = new FileContentReader();
    private CurrencyExchangeRateRepository currencyExchangeRateRepository;

    @Before
    public void init() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
    }

    @Test
    public void returnsDateBeforeOrEqualTodayDateForCorrectCurrencyName() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();
        // arrange
        String currency = "PLN";

        // act
        LocalDate result = currencyExchangeRateRepository.getMaxDateForSelectedCurrency(currency);

        // assert
        assertThat(result)
                .isBeforeOrEqualTo(LocalDate.now());
    }

    @Test
    public void returnsDateBeforeOrEqualTodayDateForCorrectCurrencyIfIsProvidedInLowerCase() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "pln";

        // act
        LocalDate result = currencyExchangeRateRepository.getMaxDateForSelectedCurrency(currency);

        // assert
        assertThat(result)
                .isBeforeOrEqualTo(LocalDate.now());
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionNoSuchElementExceptionIfCurrencyNotInTheFileForMethodGetMaxDateForSelectedCurrency() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "AA";

        // act
        currencyExchangeRateRepository.getMaxDateForSelectedCurrency(currency);
    }

    @Test
    public void returnsDateAfter19000101ForCorrectCurrencyName() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "PLN";

        // act
        LocalDate result = currencyExchangeRateRepository.getMinDateForSelectedCurrency(currency);

        // assert
        assertThat(result)
                .isAfterOrEqualTo(LocalDate.of(1900, 01, 01));
    }

    @Test
    public void returnsDateAfter19000101ForCorrectCurrencyIfIsProvidedInLowerCase() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "pln";

        // act
        LocalDate result = currencyExchangeRateRepository.getMinDateForSelectedCurrency(currency);

        // assert
        assertThat(result)
                .isAfterOrEqualTo(LocalDate.of(1900, 01, 01));
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionNoSuchElementExceptionIfCurrencyNotInTheFileForMethodGetMinDateForSelectedCurrency() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "AA";

        // act
        currencyExchangeRateRepository.getMinDateForSelectedCurrency(currency);
    }

    @Test
    public void returnsLengthMoreThanZeroIfCurrencyIsInTheFile() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "pln";

        // act
        int result = currencyExchangeRateRepository.getRangeOfSelectedCurrency(currency).length();

        // assert
        assertTrue(result > 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionNoSuchElementExceptionIfCurrencyIsNotInTheFileForMethodGetRangeOfSelectedCurrency() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "aa";

        // act
        currencyExchangeRateRepository.getRangeOfSelectedCurrency(currency);
    }

    @Test
    public void returnsExchangeValueIfCurrencyAndDateIsCorrect() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currencyHave = "EUR";
        String currencyWant = "PLN";
        LocalDate dateMax = LocalDate.of(2018, 07, 27);

        // act
        BigDecimal result = currencyExchangeRateRepository.getExchangeValue(currencyHave, currencyWant, dateMax);

        // assert
        assertEquals(BigDecimal.valueOf(4.2945), result);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionNoSuchElementExceptionIfCurrencyWantIsNotInTheFile() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currencyHave = "EUR";
        String currencyWant = "aa";
        LocalDate dateMax = LocalDate.of(2018, 07, 27);

        // act
        currencyExchangeRateRepository.getExchangeValue(currencyHave, currencyWant, dateMax);
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionNoSuchElementExceptionIfCurrencyHaveIsNotInTheFile() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currencyHave = "aa";
        String currencyWant = "PLN";
        LocalDate dateMax = LocalDate.of(2018, 07, 27);

        // act
        currencyExchangeRateRepository.getExchangeValue(currencyHave, currencyWant, dateMax);
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionNullPointerExceptionIfLocalDateIsEmpty() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currencyHave = "EUR";
        String currencyWant = "PLN";
        LocalDate dateMax = null;

        // act
        currencyExchangeRateRepository.getExchangeValue(currencyHave, currencyWant, dateMax);
    }

    @Test
    public void returnsNonEmptyListIfDateIsInThFile() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        LocalDate dateMax = LocalDate.of(2018, 07, 27);

        // act
        List result = currencyExchangeRateRepository.getSingleCurrencyWithMaxDate(dateMax);

        // assert
        assertFalse(result.isEmpty());
    }

    @Test
    public void returnsEmptyListWhenDateIsNotInThFile() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        LocalDate dateMax = LocalDate.of(100, 07, 27);

        // act
        Boolean result = currencyExchangeRateRepository.getSingleCurrencyWithMaxDate(dateMax).isEmpty();

        // assert
        assertEquals(true, result);
    }
}
