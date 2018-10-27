package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @DisplayName("Should return list where size is more then zero")

    public void returnsListWithSizeMorThenZeroIfCurrencyExist() {
        loadFiles();
        createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
        // arrange
        String currency = "PLN";
        // act
        Boolean result = createAListOfAvailableCurrencies.availableCurrencyObjects(currency).size() > 1;
        // assert
        assertEquals(true, result);
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
}
