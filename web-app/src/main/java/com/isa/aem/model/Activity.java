package com.isa.aem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACTIVITIES")
public class Activity {

    private static final String UNIQU_ID = "id";
    private static final String FIRST_CURRENCY_GIVEN_BY_USER_IN_CALCULATOR = "calculator_currency_first";
    private static final String SECOND_CURRENCY_GIVEN_BY_USER_IN_CALCULATOR = "calculator_currency_second";
    private static final String AMOUNT_GIVEN_BY_USET_TO_CALCULATOR = "amount";
    private static final String DATE_FROM_GIVEN_BY_USER = "date_from";
    private static final String DATE_TO_GIVEN_BY_USER = "date_to";
    private static final String DATE_WHEN_USER_MADE_ACTION = "action_date";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UNIQU_ID)
    private Long id;

    @Column(name = FIRST_CURRENCY_GIVEN_BY_USER_IN_CALCULATOR)
    private String calculatorCurrencyFirst;

    @Column(name = SECOND_CURRENCY_GIVEN_BY_USER_IN_CALCULATOR)
    private String calculatorCurrencySecond;

    @Column(name = AMOUNT_GIVEN_BY_USET_TO_CALCULATOR)
    private Double amount;

    @Column(name = DATE_FROM_GIVEN_BY_USER)
    private LocalDate calculatoDateFrom;

    @Column(name = DATE_TO_GIVEN_BY_USER)
    private LocalDate calculatoDateTo;

    @Column(name = DATE_WHEN_USER_MADE_ACTION)
    private LocalDateTime actionDate;


    public Activity(
            String calculatorCurrencyFirst,
            String calculatorCurrencySecond,
            Double amount,
            LocalDate calculatoDateFrom,
            LocalDate calculatoDateTo,
            LocalDateTime actionDate) {
        this.calculatorCurrencyFirst = calculatorCurrencyFirst;
        this.calculatorCurrencySecond = calculatorCurrencySecond;
        this.amount = amount;
        this.calculatoDateFrom = calculatoDateFrom;
        this.calculatoDateTo = calculatoDateTo;
        this.actionDate = actionDate;
    }

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalculatorCurrencyFirst() {
        return calculatorCurrencyFirst;
    }

    public void setCalculatorCurrencyFirst(String calculatorCurrencyFirst) {
        this.calculatorCurrencyFirst = calculatorCurrencyFirst;
    }

    public String getCalculatorCurrencySecond() {
        return calculatorCurrencySecond;
    }

    public void setCalculatorCurrencySecond(String calculatorCurrencySecond) {
        this.calculatorCurrencySecond = calculatorCurrencySecond;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getCalculatoDateFrom() {
        return calculatoDateFrom;
    }

    public void setCalculatoDateFrom(LocalDate calculatoDateFrom) {
        this.calculatoDateFrom = calculatoDateFrom;
    }

    public LocalDate getCalculatoDateTo() {
        return calculatoDateTo;
    }

    public void setCalculatoDateTo(LocalDate calculatoDateTo) {
        this.calculatoDateTo = calculatoDateTo;
    }

    public LocalDateTime getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
    }
}
