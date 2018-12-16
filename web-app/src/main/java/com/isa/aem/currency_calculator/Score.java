package com.isa.aem.currency_calculator;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.utils.DataValidator;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Score {
    private static Integer LENGTH_OF_DATE = 10;
    private BigDecimal score;
    private String currencyHave;
    private String currencyWant;
    private LocalDate dateExchange;
    private BigDecimal courseValue;
    private Double amount;
    private LocalDate minDate;
    private LocalDate maxDate;

    public LocalDate getMinDate() {
        return minDate;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }

    public CurrencyRepository currencyRepository = new CurrencyRepository();
    CurrencyConverter currencyConverter = new CurrencyConverter();
    DataValidator dataService = new DataValidator();

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                ", currencyHave='" + currencyHave + '\'' +
                ", currencyWant='" + currencyWant + '\'' +
                ", dateExchange=" + dateExchange +
                ", courseValue=" + courseValue +
                ", amount=" + amount +
                '}';
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Score() {
    }

    public Score(BigDecimal score, String currencyHave, String currencyWant, LocalDate dateExchange, BigDecimal courseValue, Double amount) {
        this.score = score;
        this.currencyHave = currencyHave;
        this.currencyWant = currencyWant;
        this.dateExchange = dateExchange;
        this.courseValue = courseValue;
        this.amount = amount;
    }

    public BigDecimal getCourseValue() {
        return courseValue;
    }

    public void setCourseValue(BigDecimal courseValue) {
        this.courseValue = courseValue;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getCurrencyHave() {
        return currencyHave;
    }

    public void setCurrencyHave(String currencyHave) {
        this.currencyHave = currencyHave;
    }

    public String getCurrencyWant() {
        return currencyWant;
    }

    public void setCurrencyWant(String currencyWant) {
        this.currencyWant = currencyWant;
    }

    public LocalDate getDateExchange() {
        return dateExchange;
    }

    public void setDateExchange(LocalDate dateExchange) {
        this.dateExchange = dateExchange;
    }

    public LocalDate scoreDate(String reqDate, String haveCurrency, String wantCurrency) {

        Boolean isDateCorrect = reqDate.length() == LENGTH_OF_DATE && isDateExistInCurrencyWithGivenDate(haveCurrency, wantCurrency, dataService.dataParse(reqDate.replace("-", "")));

        if (isDateCorrect) {
            LocalDate date = dataService.dataParse(reqDate.replace("-", ""));
            return date;
        }
        return currencyRepository.getNewestDateForChosenCurrencyName(haveCurrency);
    }

    public Boolean isDateExistInCurrencyWithGivenDate(String haveCurrency, String wantCurrency, LocalDate date) {
        Boolean isDateHaveSelectedByUserExist = currencyRepository.existCurrencyWithGivenDate(haveCurrency, date);
        Boolean isDateWantSelectedByUserExist = currencyRepository.existCurrencyWithGivenDate(wantCurrency, date);

        if (isDateHaveSelectedByUserExist && isDateWantSelectedByUserExist) {
            return true;
        }
        return false;
    }

    public Score resultCalculator(String haveCurrency, LocalDate date, String wantCurrency, Double calculatorAmount) {

        Double currencyHave = currencyRepository.getExchangeRateForGivenDate(haveCurrency, date);
        Double currencyWant = currencyRepository.getExchangeRateForGivenDate(wantCurrency, date);
        BigDecimal score1 = currencyConverter.currencyConversionAlgorithm(calculatorAmount, currencyHave, currencyWant);
        BigDecimal curseValue = currencyConverter.calculateCourseAlgorithm(currencyWant, currencyHave);
        return new Score(score1, haveCurrency, wantCurrency, date, curseValue, calculatorAmount);
    }

}
