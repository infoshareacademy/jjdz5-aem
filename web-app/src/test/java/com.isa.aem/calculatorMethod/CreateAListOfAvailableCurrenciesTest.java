package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;
import com.isa.aem.calculatorMethod.CreateAListOfAvailableCurrencies;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;;

public class CreateAListOfAvailableCurrenciesTest {
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private FileContentReader fileContentReader = new FileContentReader();
    private CreateAListOfAvailableCurrencies createAListOfAvailableCurrencies;

    @Before
    public void init() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
    }

    @Test
    @DisplayName("Should return list on size more than zero when currency is correct")
    public void returnsListWithSizeMorThanZeroIfCurrencyExist() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "PLN";

        // act
        Boolean result = createAListOfAvailableCurrencies.availableCurrencyObjects(currency).size() > 0;

        // assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should return list on size more than zero when currency is correct and provided in lower case")
    public void returnsListWithSizeMorThanZeroIfCurrencyExistAndWhenIsProvidedInLowerCase() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "pln";

        // act
        Boolean result = createAListOfAvailableCurrencies.availableCurrencyObjects(currency).size() > 0;

        // assert
        assertEquals(true, result);
    }

    @Test(expected = NoSuchElementException.class)
    @DisplayName("Should return throw when currency have not correct name")
    public void returnsThrowIfCurrencyIsNotInTheFile() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "ww";

        // act
        createAListOfAvailableCurrencies.availableCurrencyObjects(currency);
    }

    @Test(expected = NullPointerException.class)
    @DisplayName("Should throw when currency name is null")
    public void returnsThrowIfCurrencyNameIsEmpty() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = null;

        // act
        createAListOfAvailableCurrencies.availableCurrencyObjects(currency);
    }

    @Test
    @DisplayName("Should return empty list when currency not exists")
    public void returnsListWithSizeZeroIfCurrencyNotExist() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "zz";

        // act
        createAListOfAvailableCurrencies.addCurrencyObject(currency);
        Boolean result = createAListOfAvailableCurrencies.getTableListCurrencyObject().isEmpty();

        // assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should return empty list where size equals zero when currency is null")
    public void returnsEmptyListIfCurrencyIsNull() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = null;

        // act
        createAListOfAvailableCurrencies.addCurrencyObject(currency);
        Boolean result = createAListOfAvailableCurrencies.getTableListCurrencyObject().isEmpty();

        // assert
        assertEquals(true, result);
    }

    @Test
    @DisplayName("Should return list where size is more than zero when currency is correct")
    public void returnsListWithSizeMoreThanZeroIfCurrencyIsCorrect() {
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "PLN";

        // act
        createAListOfAvailableCurrencies.addCurrencyObject(currency);
        createAListOfAvailableCurrencies.availableCurrencyObjects(currency);
        Boolean result = createAListOfAvailableCurrencies.getTableListCurrencyObject().size() > 0;

        // assert
        assertEquals(true, result);
    }
}
