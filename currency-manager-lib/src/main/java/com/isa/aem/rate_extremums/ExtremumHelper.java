package com.isa.aem.rate_extremums;

import com.isa.aem.Currency;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExtremumHelper {

    Double findFirstMinExtremum(List<Currency> currencies) {
        return currencies.stream()
                .min(Comparator.comparingDouble(Currency::getClose))
                .get()
                .getClose();
    }

    Double findFirstMaxExtremum(List<Currency> currencies) {
        return currencies.stream()
                .max(Comparator.comparingDouble(Currency::getClose))
                .get()
                .getClose();
    }

    List<Currency> findDuplicatedExtremums(Double firstExtremum, List<Currency> currencies) {
        return currencies.stream()
                .filter(currency -> firstExtremum.equals(currency.getClose()))
                .collect(Collectors.toList());
    }

    public Boolean isDateFromAfterDateTo(LocalDate dateFrom, LocalDate dateTo) {
        return dateFrom.isAfter(dateTo);
    }
}
