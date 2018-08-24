package com.infoshareacademy.aem.globalextreme;

import java.util.Arrays;
import java.util.List;

public class CheckCondition {

    CurrencyRepositoryHelper helper = new CurrencyRepositoryBin();
    private static final  List<String> number = Arrays.asList("0","1","2","9");

    public boolean condition(String  s) {
       return helper.ContainsNumber(number, s);
    }
}
