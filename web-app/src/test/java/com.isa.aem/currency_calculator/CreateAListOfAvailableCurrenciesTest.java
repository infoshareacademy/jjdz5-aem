package com.isa.aem.currency_calculator;

import com.isa.aem.data_loaders.FileContentReader;
import org.junit.Before;
import org.junit.Test;

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
    public void throwsNoSuchElementExceptionIfCurrencyIsNotInTheFile() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

        // arrange
        String currency = "ww";

        // act
        createAListOfAvailableCurrencies.availableCurrencyObjects(currency);
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionNoSuchElementExceptionIfCurrencyNameIsEmpty() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

        // arrange
        String currency = null;

        // act
        createAListOfAvailableCurrencies.availableCurrencyObjects(currency);
    }

    @Test
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
