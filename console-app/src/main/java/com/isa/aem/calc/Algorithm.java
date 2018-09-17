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

    protected BigDecimal result() {
        BigDecimal amoun = new BigDecimal(amount);
        BigDecimal first = new BigDecimal(prepareCalculator.getActualCurseOfFirstChoice());
        BigDecimal second = new BigDecimal(prepareCalculator.getActualCurseOfSecondChoice());
        BigDecimal divide = first.divide(second, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
        BigDecimal multiply = divide.multiply(amoun).setScale(4,RoundingMode.HALF_UP);

        return multiply;
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
