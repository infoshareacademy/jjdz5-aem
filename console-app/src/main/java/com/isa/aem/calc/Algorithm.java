package com.isa.aem.calc;
import com.isa.aem.tools.CurrencyRepositoryBin;
import com.isa.aem.tools.SingleCurrency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Algorithm {
    private PrepareCalculator prepareCalculator = new PrepareCalculator();
    private ConsoleSupport consoleSupport = new ConsoleSupport();
    private CurrencyRepositoryBin currencyRepositoryBin = new CurrencyRepositoryBin();
    private SingleCurrency singleCurrency = new SingleCurrency();
    private String firstCurrency;
    private double amount;
    private String secondCurrency;
    private LocalDate date;

    public void loadFromKeyboard() {
        firstCurrency = consoleSupport.firstCurrencyService();
        amount = consoleSupport.amountSercive();
        secondCurrency = consoleSupport.secondCurrencyService();
    }

    protected void loadDateFromKeyboard() {
        date = consoleSupport.dataServis();
    }

    protected boolean checkFirst() {
        return currencyRepositoryBin.containsDate(singleCurrency.getSingleCurrencyFirstChoice(), date);
    }

    protected boolean checkSecond() {
        return currencyRepositoryBin.containsDate(singleCurrency.getSingleCurrencySecondChoice(), date);
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

    protected BigDecimal equalWithDate() {
        return algorithm(this.amount,currencyRepositoryBin.courseByDate(singleCurrency.getSingleCurrencyFirstChoice(), date),
                currencyRepositoryBin.courseByDate(singleCurrency.getSingleCurrencySecondChoice(), date));
    }

    protected BigDecimal equalCurseWithDate() {
        return course(prepareCalculator.getActualCurseOfFirstChoice(), prepareCalculator.getActualCurseOfSecondChoice());
    }

    protected BigDecimal equalCurse() {
        return course(prepareCalculator.getActualCurseOfFirstChoice(), prepareCalculator.getActualCurseOfSecondChoice());
    }

    protected BigDecimal course(Double first, Double second) {
        BigDecimal one = new BigDecimal(first);
        BigDecimal two = new BigDecimal(second);

        return two.divide(one, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
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
