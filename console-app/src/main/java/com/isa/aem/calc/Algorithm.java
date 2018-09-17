package com.isa.aem.calc;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Algorithm {
    private PrepareCalculator prepareCalculator = new PrepareCalculator();
    private ConsoleSupport consoleSupport = new ConsoleSupport();
    private String firstCurrency;
    private double amount;
    private String secondCurrency;

    public void loadFromKeyboard() {
        firstCurrency = consoleSupport.firstCurrencyService();
        amount = consoleSupport.amountSercive();
        secondCurrency = consoleSupport.secondCurrencyService();
    }

    private BigDecimal algorithm(Double amount, Double firstCourse, Double secondCourse) {
        BigDecimal amoun = new BigDecimal(amount);
        BigDecimal first = new BigDecimal(firstCourse);
        BigDecimal second = new BigDecimal(secondCourse);
        BigDecimal divide = first.divide(second, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
        BigDecimal multiply = divide.multiply(amoun).setScale(4,RoundingMode.HALF_UP);

        return multiply;
    }

    protected BigDecimal equal() {
        return algorithm(this.amount, prepareCalculator.getActualCurseOfFirstChoice(), prepareCalculator.getActualCurseOfSecondChoice());
    }

    protected BigDecimal course() {
        BigDecimal first = new BigDecimal(prepareCalculator.getActualCurseOfFirstChoice());
        BigDecimal second = new BigDecimal(prepareCalculator.getActualCurseOfSecondChoice());

        return second.divide(first, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
    }

    public String getFirstCurrency() {
        return firstCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public String getSecondCurrency() {
        return secondCurrency;
    }
}
