package com.isa.aem.calculatorMethod;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.calc.AlgorithmCurrencyConversion;
import com.isa.aem.tools.DataValidator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Score {
    private static Integer LENGTH_OF_DATE = 10;

    public BigDecimal score;
    public String currencyHave;
    public String currencyWant;
    public LocalDate dateExchange;
    public BigDecimal courseValue;
    public Double amount;
    public LocalDate minDate;
    public LocalDate maxDate;

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

    CurrencyRepository currencyRepository = new CurrencyRepository();
    AlgorithmCurrencyConversion algorithmCurrencyConversion = new AlgorithmCurrencyConversion();
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

        Boolean dateIsCorrect = reqDate.length() == LENGTH_OF_DATE && checkDateIfExistCurrencyWithGivenDate(haveCurrency, wantCurrency, dataService.dataParse(reqDate.replace("-", "")));

        if (dateIsCorrect) {
            LocalDate date = dataService.dataParse(reqDate.replace("-", ""));
            return date;
        }
        return currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile(haveCurrency);
    }

    public Boolean checkDateIfExistCurrencyWithGivenDate(String haveCurrency, String wantCurrency, LocalDate date) {
        Boolean dateHaveSelectedByUserExist = currencyRepository.checkIfExistCurrencyWithGivenDate(haveCurrency, date);
        Boolean dateWantSelectedByUserExist = currencyRepository.checkIfExistCurrencyWithGivenDate(wantCurrency, date);

        if (dateHaveSelectedByUserExist && dateWantSelectedByUserExist) {
            return true;
        }
        return false;
    }

    public Score resultCalculator(String haveCurrency, LocalDate date, String wantCurrency, Double calculatorAmount) {

        Double currencyHave = currencyRepository.getRateOfGivenDate(haveCurrency, date);
        Double currencyWant = currencyRepository.getRateOfGivenDate(wantCurrency, date);
        BigDecimal score1 = algorithmCurrencyConversion.currencyConversionAlgorithm(calculatorAmount, currencyHave, currencyWant);
        BigDecimal curseValue = algorithmCurrencyConversion.calculateCourseAlgorithm(currencyHave, currencyWant);
        return new Score(score1, haveCurrency, wantCurrency, date, curseValue, calculatorAmount);
    }
}
