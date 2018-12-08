package com.isa.aem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACTIVITIES")
public class Activity {

    private static final String UNIQUE_ID = "id";
    private static final String FIRST_CURRENCY_GIVEN_BY_USER_IN_CALCULATOR = "calculator_currency_first";
    private static final String SECOND_CURRENCY_GIVEN_BY_USER_IN_CALCULATOR = "calculator_currency_second";
    private static final String CURRENCY_GIVEN_BY_USER_IN_EXTREMUM = "extremum_currency";
    private static final String AMOUNT_GIVEN_BY_USER_TO_CALCULATOR = "amount";
    private static final String DATE_FROM_GIVEN_BY_USER = "date_from";
    private static final String DATE_TO_GIVEN_BY_USER = "date_to";
    private static final String DATE_WHEN_USER_MADE_ACTION = "action_date";
    private static final String ACTION_TYPE_BY_USER = "type";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UNIQUE_ID)
    private Long id;

    @Column(name = FIRST_CURRENCY_GIVEN_BY_USER_IN_CALCULATOR)
    private String calculatorCurrencyFirst;

    @Column(name = SECOND_CURRENCY_GIVEN_BY_USER_IN_CALCULATOR)
    private String calculatorCurrencySecond;

    @Column(name = CURRENCY_GIVEN_BY_USER_IN_EXTREMUM)
    private String extremumCurrency;

    @Column(name = AMOUNT_GIVEN_BY_USER_TO_CALCULATOR)
    private Double amount;

    @Column(name = DATE_FROM_GIVEN_BY_USER)
    private LocalDate dateFrom;

    @Column(name = DATE_TO_GIVEN_BY_USER)
    private LocalDate dateTo;

    @Column(name = DATE_WHEN_USER_MADE_ACTION)
    private LocalDateTime actionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = ACTION_TYPE_BY_USER)
    private ActionType actionType;

    public Activity(String calculatorCurrencyFirst,
                    String calculatorCurrencySecond,
                    String extremumCurrency,
                    Double amount,
                    LocalDate calculatorDateFrom,
                    LocalDate calculatorDateTo,
                    LocalDateTime actionDate,
                    ActionType actionType) {
        this.calculatorCurrencyFirst = calculatorCurrencyFirst;
        this.calculatorCurrencySecond = calculatorCurrencySecond;
        this.extremumCurrency = extremumCurrency;
        this.amount = amount;
        this.dateFrom = calculatorDateFrom;
        this.dateTo = calculatorDateTo;
        this.actionDate = actionDate;
        this.actionType = actionType;
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

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate calculatoDateFrom) {
        this.dateFrom = calculatoDateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate calculatoDateTo) {
        this.dateTo = calculatoDateTo;
    }

    public LocalDateTime getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getExtremumCurrency() {
        return extremumCurrency;
    }

    public void setExtremumCurrency(String extremumCurrency) {
        this.extremumCurrency = extremumCurrency;
    }
}
