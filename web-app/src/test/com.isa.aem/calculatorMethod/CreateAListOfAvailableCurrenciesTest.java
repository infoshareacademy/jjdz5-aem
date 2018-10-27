package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateAListOfAvailableCurrenciesTest {
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private FileContentReader fileContentReader = new FileContentReader();
    private CreateAListOfAvailableCurrencies createAListOfAvailableCurrencies;

    @Before
    public void init() {
        loadFiles();
    }

    public void loadFiles() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
    }

    @Test
    @DisplayName("Should return list on size more then zero when currency is correct")

    public void returnsListWithSizeMorThenZeroIfCurrencyExist() {
        loadFiles();
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "PLN";
        // act
        Boolean result = createAListOfAvailableCurrencies.availableCurrencyObjects(currency).size() > 0;
        // assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should return list on size more then zero when currency is correct and write in lower case")

    public void returnsListWithSizeMorThenZeroIfCurrencyExistAndWhenIsWriteInLowerCase() {
        loadFiles();
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "pln";
        // act
        Boolean result = createAListOfAvailableCurrencies.availableCurrencyObjects(currency).size() > 0;
        // assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should return throw when currency have not correct name")

    public void returnsThrowIfCurrencyIsNotInTheFile() {
        loadFiles();
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "ww";
        // act
        Throwable exception = assertThrows(NoSuchElementException.class, () ->createAListOfAvailableCurrencies.availableCurrencyObjects(currency));
        // assert
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw when currency name is null")

    public void returnsThrowIfCurrencyNameIsEmpty() {
        loadFiles();
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = null;
        // act
        Throwable exception = assertThrows(NullPointerException.class, () ->createAListOfAvailableCurrencies.availableCurrencyObjects(currency));
        // assert
        assertEquals(null, exception.getMessage());
    }

    @Test
    @DisplayName("Should return list where size equals zero when currency is not on the list")

    public void returnsListWithSizeZeroIfCurrencyNotExist() {
        loadFiles();
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "zz";
        // act
        createAListOfAvailableCurrencies.addCurrencyObject(currency);
        Boolean result = createAListOfAvailableCurrencies.getTableListCurrencyObject().size() == 0;
        // assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should return list where size equals zero when currency is null")

    public void returnsListWithSizeZeroIfCurrencyNotIsNull() {
        loadFiles();
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = null;
        // act
        createAListOfAvailableCurrencies.addCurrencyObject(currency);
        Boolean result = createAListOfAvailableCurrencies.getTableListCurrencyObject().size() == 0;
        // assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should return list where size is more then zero when currency is correct")

    public void returnsListWithSizeMoreThenZeroIfCurrencyIsCorrect() {
        loadFiles();
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "EUR";
        // act
        createAListOfAvailableCurrencies.addCurrencyObject(currency);
        Boolean result = createAListOfAvailableCurrencies.getTableListCurrencyObject().size() == 0;
        // assert
        assertEquals(true, result);
    }
}
