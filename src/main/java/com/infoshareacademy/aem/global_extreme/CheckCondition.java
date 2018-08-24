package com.infoshareacademy.aem.global_extreme;

import java.util.Arrays;
import java.util.List;

public class CheckCondition {

    CurrencyRepositoryHelper helper = new CurrencyRepositoryBin();
    private static final  List<Integer> number = Arrays.asList(0,1,2,9);

    public boolean condition(int i) {
       return helper.isContainsInt(number, i);
    }
}
