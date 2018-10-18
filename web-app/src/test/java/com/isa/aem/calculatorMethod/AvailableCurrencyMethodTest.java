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

public class AvailableCurrencyMethodTest {
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private FileContentReader fileContentReader = new FileContentReader();
    private AvailableCurrencyMethod availableCurrencyMethod;

    @Test
    @DisplayName("Should return date before or equal Today's date")
    public void returnsMaxDateInFile() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
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
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
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
    @DisplayName("Should return '4000-01-01' if currency is not on the list")
    public void returnsFourTousendYearWhenCurrencyIsOutsideOfListInMax() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "AA";
        // act
        LocalDate result = availableCurrencyMethod.getMaxDateForSelectedCurrency(currency);
        // assert
        assertEquals(LocalDate.of(4000, 01, 01), result);
    }

    @Test
    @DisplayName("Should return date before or equal Today's date if currency is in File")
    public void returnsMinDateInFile() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
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
    @DisplayName("Should return '1900-01-01' if currency is not on the list")
    public void returnsMinSystemDateWhenCurrencyIsOutsideOfListInMax() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "AA";
        // act
        LocalDate result = availableCurrencyMethod.getMinDateForSelectedCurrency(currency);
        // assert
        assertEquals(LocalDate.of(1900, 01, 01), result);
    }

    @Test
    @DisplayName("Should return range diffrent from \"1900-01-01 - 4000-01-01\" if currency is in file")
    public void returnsRangeOfCurrencyDiffrentThenMinAndMaxSystemDate() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "pln";
        // act
        Boolean result = availableCurrencyMethod.getRangeOfSelectedCurrency(currency).equalsIgnoreCase("1900-01-01 - 4000-01-01");
        // assert
        assertEquals(false, result);
    }

    @Test
    @DisplayName("Should return range \"1900-01-01 - 4000-01-01\" if currency not in file")
    public void returnsRangeMinAndMaxSystemDate() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "aa";
        // act
        Boolean result = availableCurrencyMethod.getRangeOfSelectedCurrency(currency).equalsIgnoreCase("1900-01-01 - 4000-01-01");
        // assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should return exchange value if currencyWant and CurrencyHave is not empty")
    public void returnsExhangeValue() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
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
    @DisplayName("Should return list with a size greater than zero")
    public void returnsListWithSizeMoreThenNull() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        LocalDate dateMax = LocalDate.of(2018, 07, 27);
        // act
        Boolean result = availableCurrencyMethod.getSingleCurrencyWithMaxDate(dateMax).size() > 0;
        // assert
        assertEquals(true, result);
    }
}

