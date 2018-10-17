package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
    @DisplayName("Should return error if currency is not on the list")
    public void returnsErrorWhenCurrencyIsOutsideOfList() {
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
        availableCurrencyMethod = new AvailableCurrencyMethod();
        // arrange
        String currency = "AA";
        // act

        Boolean result;
        try {
            availableCurrencyMethod.getMaxDateForSelectedCurrency(currency);
            result = true;
        } catch (Exception e) {
            result = false;
        }


        // assert
        assertEquals(result, false);
    }

}

