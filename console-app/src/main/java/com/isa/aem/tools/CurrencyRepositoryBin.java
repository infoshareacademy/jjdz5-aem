package com.isa.aem.tools;

import com.isa.aem.Currency;

import javax.faces.bean.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class CurrencyRepositoryBin implements CurrencyRepositoryHelper {


    @Override
    public boolean containsCurrency(List<Currency> list, String s) {
        for (Currency c : list) {
            if (c.getName().equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsDate(List<Currency> list, LocalDate date) {
        for (Currency c: list) {
            if (c.getDate().equals(date)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Double courseByDate(List<Currency> list, LocalDate date) {
        for (Currency c : list) {
            if (c.getDate().equals(date)){
                return c.getClose();
            }
        }
        return null;
    }
}