package com.isa.aem.calc;
import com.isa.aem.tools.CurrencyRepositoryBin;
import com.isa.aem.tools.SingleCurrency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Algorithm {

    private CurrencyRepositoryBin currencyRepositoryBin = new CurrencyRepositoryBin();
    private SingleCurrency singleCurrency = new SingleCurrency();
    private String firstCurrency;
    private double amount;
    private String secondCurrency;
    private LocalDate date;

//    public void loadFromKeyboard() {
//        firstCurrency = consoleSupport.firstCurrencySelectedByUserService();
//        amount = consoleSupport.amountService();
//        secondCurrency = consoleSupport.secondCurrencySelectedByUserService();
//    }
//
//    protected void loadDateFromKeyboard() {
//        date = consoleSupport.dateService();
//    }

    protected boolean checkFirst() {
        return currencyRepositoryBin.containsDate(singleCurrency.getSingleCurrencyFirstChoice(), date);
    }

    protected boolean checkSecond() {
        return currencyRepositoryBin.containsDate(singleCurrency.getSingleCurrencySecondChoice(), date);
    }

    protected BigDecimal currencyConversionAlgorithm(Double amountGivenByUser, Double firstCourse, Double secondCourse) {
        BigDecimal amount = new BigDecimal(amountGivenByUser);
        BigDecimal first = new BigDecimal(firstCourse);
        BigDecimal second = new BigDecimal(secondCourse);
        BigDecimal divide = first.divide(second, BigDecimal.ROUND_HALF_UP).setScale(4, RoundingMode.HALF_UP);
        BigDecimal multiply = divide.multiply(amount).setScale(4,RoundingMode.HALF_UP);

        return multiply;
    }


//    protected BigDecimal equalWithDate() {
//        return algorithm(this.amount,currencyRepositoryBin.courseByDate(singleCurrency.getSingleCurrencyFirstChoice(), date),
//                currencyRepositoryBin.courseByDate(singleCurrency.getSingleCurrencySecondChoice(), date));
//    }
//
//    protected BigDecimal equalCurseWithDate() {
//        return course(prepareCalculator.getActualCurseOfFirstChoice(), prepareCalculator.getActualCurseOfSecondChoice());
//    }
//
//    protected BigDecimal equalCurse() {
//        return course(prepareCalculator.getActualCurseOfFirstChoice(), prepareCalculator.getActualCurseOfSecondChoice());
//    }

    protected BigDecimal calculateCourseAlgorithm(Double first, Double second) {
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
