package com.isa.aem.calculatorMethod;

import com.isa.aem.FileContentReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;;

public class CreateAListOfAvailableCurrenciesTest {

    private FileContentReader fileContentReader = new FileContentReader();
    private CreateAListOfAvailableCurrencies createAListOfAvailableCurrencies;

    @Before
    public void init() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
    }

    @Test
    @DisplayName("Should return a non-empty list if currency exist")
    public void returnsNonEmptyListIfCurrencyExist() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

        // arrange
        String currency = "PLN";

        // act
        List result = createAListOfAvailableCurrencies.availableCurrencyObjects(currency);

        // assert
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("Should return a non-empty list if currency is correct and provided in lower case")
    public void returnsNonEmptyListIfCurrencyExistAndWhenIsProvidedInLowerCase() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

        // arrange
        String currency = "pln";

        // act
        List result = createAListOfAvailableCurrencies.availableCurrencyObjects(currency);

        // assert
        assertFalse(result.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    @DisplayName("Should throw NoSuchElementException if currency is not in the file")
    public void throwsNoSuchElementExceptionIfCurrencyIsNotInTheFile() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

        // arrange
        String currency = "ww";

        // act
        createAListOfAvailableCurrencies.availableCurrencyObjects(currency);
    }

    @Test(expected = NullPointerException.class)
    @DisplayName("Should throw exception NullPointerException if currency name is empty")
    public void throwsExceptionNoSuchElementExceptionIfCurrencyNameIsEmpty() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

        // arrange
        String currency = null;

        // act
        createAListOfAvailableCurrencies.availableCurrencyObjects(currency);
    }

    @Test
    @DisplayName("Should return empty list if currency not exists")
    public void returnsListEmptyListIfCurrencyNotExist() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

        // arrange
        String currency = "zz";

        // act
        createAListOfAvailableCurrencies.addCurrencyObject(currency);
        List result = createAListOfAvailableCurrencies.getTableListCurrencyObject();

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return empty list if currency is null")
    public void returnsEmptyListIfCurrencyIsNull() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

        // arrange
        String currency = null;

        // act
        createAListOfAvailableCurrencies.addCurrencyObject(currency);
        List result = createAListOfAvailableCurrencies.getTableListCurrencyObject();

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return a non-empty list if currency is correct")
    public void returnsNonEmptyListIfCurrencyIsCorrect() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

        // arrange
        String currency = "PLN";

        // act
        createAListOfAvailableCurrencies.addCurrencyObject(currency);
        createAListOfAvailableCurrencies.availableCurrencyObjects(currency);
        List result = createAListOfAvailableCurrencies.getTableListCurrencyObject();

        // assert
        assertFalse(result.isEmpty());
    }
}
