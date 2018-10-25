package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;

public class AvailableCurrencyMethodTest {
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private FileContentReader fileContentReader = new FileContentReader();
    private AvailableCurrencyMethod availableCurrencyMethod;

    public void loadFiles() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
    }

    @Test
    @DisplayName("Should return date before or equal Today's date")
    public void returnsMaxDateInFile() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "PLN";
        // act
        LocalDate result = availableCurrencyMethod.getMaxDateForSelectedCurrency(currency);
        // assert
        assertThat(result)
                .isBeforeOrEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Should return date before or equal Today's date when currency is in File even though it is saved with a lowercase letter")
    public void returnsMaxDateInFileWhenCurrencyIsInLowerCase() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "pln";
        // act
        LocalDate result = availableCurrencyMethod.getMaxDateForSelectedCurrency(currency);
        // assert
        assertThat(result)
                .isBeforeOrEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Should return message \"No value present\" if currency is not on the list")
    public void returnsFourTousendYearWhenCurrencyIsOutsideOfListInMax() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "AA";
        // act
        Throwable exception = assertThrows(NoSuchElementException.class, () -> availableCurrencyMethod.getMaxDateForSelectedCurrency(currency));
        // assert
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    @DisplayName("Should return date before 1900-01-01 if currency is in File")
    public void returnsMinDateInFile() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "pln";
        // act
        LocalDate result = availableCurrencyMethod.getMinDateForSelectedCurrency(currency);
        // assert
        assertThat(result)
                .isAfterOrEqualTo(LocalDate.of(1900, 01, 01));
    }

    @Test
    @DisplayName("Should return \"No value present\" if currency is not on the list")
    public void returnsMinSystemDateWhenCurrencyIsOutsideOfListInMax() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "AA";
        // act
        Throwable exception = assertThrows(NoSuchElementException.class, () -> availableCurrencyMethod.getMinDateForSelectedCurrency(currency));
        // assert
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    @DisplayName("Should return length>0 if currency is in file")
    public void returnsRangeOfCurrencyDiffrentThenMinAndMaxSystemDate() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "pln";
        // act
        Boolean result = availableCurrencyMethod.getRangeOfSelectedCurrency(currency).length() > 0;
        // assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should return \"No value present\" if currency not in file")
    public void returnsRangeMinAndMaxSystemDate() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "aa";
        // act
        Throwable exception = assertThrows(NoSuchElementException.class, () -> availableCurrencyMethod.getRangeOfSelectedCurrency(currency));
        // assert
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    @DisplayName("Should return exchange value if currencyWant and CurrencyHave is not empty")
    public void returnsExhangeValue() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currencyHave = "EUR";
        String currencyWant = "PLN";
        LocalDate dateMax = LocalDate.of(2018, 07, 27);
        // act
        BigDecimal result = availableCurrencyMethod.getExchangeValue(currencyHave, currencyWant, dateMax);
        // assert
        assertEquals(BigDecimal.valueOf(4.2945), result);
    }

    @Test
    @DisplayName("Should return \"No value present\" if currencyWant is not in file")
    public void returnsThrowsWhenCurrencyWantNotInFile() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currencyHave = "EUR";
        String currencyWant = "aa";
        LocalDate dateMax = LocalDate.of(2018, 07, 27);
        // act
        Throwable exception = assertThrows(NoSuchElementException.class, () -> availableCurrencyMethod.getExchangeValue(currencyHave, currencyWant, dateMax));
        // assert
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    @DisplayName("Should return \"No value present\" if currencyHave is not in file")
    public void returnsThrowsWhenCurrencyHaveNotInFile() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currencyHave = "aa";
        String currencyWant = "PLN";
        LocalDate dateMax = LocalDate.of(2018, 07, 27);
        // act
        Throwable exception = assertThrows(NoSuchElementException.class, () -> availableCurrencyMethod.getExchangeValue(currencyHave, currencyWant, dateMax));
        // assert
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    @DisplayName("Should return Throws null if LocalDate is empty")
    public void returnsThrowsWhenLocalDateIsEmpty() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currencyHave = "EUR";
        String currencyWant = "PLN";
        LocalDate dateMax = null;
        // act
        Throwable exception = assertThrows(NullPointerException.class, () -> availableCurrencyMethod.getExchangeValue(currencyHave, currencyWant, dateMax));
        // assert
        assertEquals(null, exception.getMessage());
    }

    @Test
    @DisplayName("Should return list with a size greater than zero")
    public void returnsListWithSizeMoreThenNull() {
        loadFiles();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        LocalDate dateMax = LocalDate.of(2018, 07, 27);
        // act
        Boolean result = availableCurrencyMethod.getSingleCurrencyWithMaxDate(dateMax).size() > 0;
        // assert
        assertEquals(true, result);
    }
}
