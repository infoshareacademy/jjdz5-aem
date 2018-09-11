package com.isa.aem.calc;

import com.isa.aem.tools.ListAdder;
import com.isa.aem.tools.SingleCurrency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Algorithm {
    private PrepareCalculator prepareCalculator = new PrepareCalculator();
    protected BigDecimal algorithm() {
        BigDecimal amount = new BigDecimal(prepareCalculator.getAmount());
        BigDecimal first = new BigDecimal(prepareCalculator.getActualCurseOfFirstChoice());
        BigDecimal second = new BigDecimal(prepareCalculator.getActualCurseOfSecondChoice());

        return amount.multiply(first).divide(second, BigDecimal.ROUND_HALF_DOWN).setScale(4, RoundingMode.HALF_UP);
    }
}
