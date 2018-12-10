package com.isa.aem.currency_calculator;

import com.isa.aem.data_loaders.FileContentReader;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;;

public class CurrencyListTableCreatorTest {

    private FileContentReader fileContentReader = new FileContentReader();
    private CurrencyListTableCreator currencyListTableCreator;

    @Before
    public void init() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
    }

    @Test
    public void returnsNonEmptyListIfCurrencyExist() {
        currencyListTableCreator = new CurrencyListTableCreator();

        // arrange
        String currency = "PLN";

        // act
        List result = currencyListTableCreator.availableCurrencyObjects(currency);

        // assert
        assertFalse(result.isEmpty());
    }

    @Test
    public void returnsNonEmptyListIfCurrencyExistAndWhenIsProvidedInLowerCase() {
        currencyListTableCreator = new CurrencyListTableCreator();

        // arrange
        String currency = "pln";

        // act
        List result = currencyListTableCreator.availableCurrencyObjects(currency);

        // assert
        assertFalse(result.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsNoSuchElementExceptionIfCurrencyIsNotInTheFile() {
        currencyListTableCreator = new CurrencyListTableCreator();

        // arrange
        String currency = "ww";

        // act
        currencyListTableCreator.availableCurrencyObjects(currency);
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionNoSuchElementExceptionIfCurrencyNameIsEmpty() {
        currencyListTableCreator = new CurrencyListTableCreator();

        // arrange
        String currency = null;

        // act
        currencyListTableCreator.availableCurrencyObjects(currency);
    }

    @Test
    public void returnsListEmptyListIfCurrencyNotExist() {
        currencyListTableCreator = new CurrencyListTableCreator();

        // arrange
        String currency = "zz";

        // act
        currencyListTableCreator.addCurrencyObject(currency);
        List result = currencyListTableCreator.getTableListCurrencyObject();

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void returnsEmptyListIfCurrencyIsNull() {
        currencyListTableCreator = new CurrencyListTableCreator();

        // arrange
        String currency = null;

        // act
        currencyListTableCreator.addCurrencyObject(currency);
        List result = currencyListTableCreator.getTableListCurrencyObject();

        // assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void returnsNonEmptyListIfCurrencyIsCorrect() {
        currencyListTableCreator = new CurrencyListTableCreator();

        // arrange
        String currency = "PLN";

        // act
        currencyListTableCreator.addCurrencyObject(currency);
        currencyListTableCreator.availableCurrencyObjects(currency);
        List result = currencyListTableCreator.getTableListCurrencyObject();

        // assert
        assertFalse(result.isEmpty());
    }
}
