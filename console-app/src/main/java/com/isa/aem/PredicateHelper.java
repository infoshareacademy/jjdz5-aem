package com.isa.aem;

import java.util.function.Predicate;

public class PredicateHelper {

    public static Predicate <Currency> checkIfMoreThanOne(Double value) {
        return currency -> currency.getClose().equals(value);
    }
}
