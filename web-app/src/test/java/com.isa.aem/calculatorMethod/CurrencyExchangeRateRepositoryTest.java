package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

public class CurrencyExchangeRateRepositoryTest {
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private FileContentReader fileContentReader = new FileContentReader();
    private CurrencyExchangeRateRepository currencyExchangeRateRepository;

    @Before
    public void init() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
    }

    @Test
    @DisplayName("Should return date before or equal Today's date")
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
    @DisplayName("Should return date before or equal Today's date if currency is in File even though it is provided with a lowercase letter")
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
    @DisplayName("Should throw exception NoSuchElementException if currency is not on the list")
    public void throwsExceptionNoSuchElementExceptionIfCurrencyNotInTheFileForMethodGetMaxDateForSelectedCurrency() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "AA";

        // act
        currencyExchangeRateRepository.getMaxDateForSelectedCurrency(currency);
    }

    @Test
    @DisplayName("Should return date after 1900-01-01 if currency is in File")
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
    @DisplayName("Should return date after 1900-01-01 if currency is in File even though it is provided with a lowercase letter")
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
    @DisplayName("Should throw exception NoSuchElementException if currency is not in the File")
    public void throwsExceptionNoSuchElementExceptionIfCurrencyNotInTheFileForMethodGetMinDateForSelectedCurrency() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "AA";

        // act
        currencyExchangeRateRepository.getMinDateForSelectedCurrency(currency);
    }

    @Test
    @DisplayName("Should return length more than zero if currency is in file")
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
    @DisplayName("Should throw exception NoSuchElementException if currency not in file")
    public void throwsExceptionNoSuchElementExceptionIfCurrencyIsNotInTheFileForMethodGetRangeOfSelectedCurrency() {
        currencyExchangeRateRepository = new CurrencyExchangeRateRepository();

        // arrange
        String currency = "aa";

        // act
        currencyExchangeRateRepository.getRangeOfSelectedCurrency(currency);
    }

    @Test
    @DisplayName("Should return exchange value if currencyWant and CurrencyHave is not empty and is in File")
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
    @DisplayName("Should throw exception NoSuchElementException if currencyWant is not in file")
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
    @DisplayName("Should throw exception NoSuchElementException if currencyHave is not in file")
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
    @DisplayName("Should throw exception NullPointerException if LocalDate is empty")
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
    @DisplayName("Should return a non-empty list if date is in the file")
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
    @DisplayName("Should return empty list when LocalDate doesn't exist in the file")
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
