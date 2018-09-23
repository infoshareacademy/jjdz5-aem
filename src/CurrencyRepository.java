package com.isa.aem;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRepository {

    private static List<Currency> currencies = new ArrayList<>();

    public static List<Currency> getCurrencies() {
        return currencies;
    }

    public static void setCurrencies(List<Currency> currencies) {
        CurrencyRepository.currencies = currencies;



    }
    public static List<Currency> getCurrencies(LocalDate startDate, Period period)
    {

    }
}
