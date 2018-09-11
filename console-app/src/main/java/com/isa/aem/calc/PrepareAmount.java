package com.isa.aem.calc;

import java.util.ArrayList;
import java.util.List;

public class PrepareAmount {
    private static final List<Double> amount = new ArrayList<>();

    public void add(Double valu) {
        amount.add(valu);
    }

    public static List<Double> getAmount() {
        return amount;
    }
}
