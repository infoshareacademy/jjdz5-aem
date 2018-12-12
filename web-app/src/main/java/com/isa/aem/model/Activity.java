package com.isa.aem.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACTIVITIES")
public class Activity {

    private static final String ID = "id";
    private static final String ID_OF_USER = "user_id";
    private static final String CURRENCY_HAVE = "calculator_currency_have";
    private static final String CURRENCY_WANT = "calculator_currency_want";
    private static final String CURRENCY_GIVEN_BY_USER_IN_EXTREMUM = "extremum_currency";
    private static final String AMOUNT_GIVEN_BY_USER_TO_CALCULATOR = "amount";
    private static final String EXCHANGE_RATE_BY_PLN = "exchange_rate_by_pln";
    private static final String DATE_FROM_GIVEN_BY_USER = "date_from";
    private static final String DATE_TO_GIVEN_BY_USER = "date_to";
    private static final String CALCULATOR_DATE_GIVEN_BY_USER = "calculator_date";
    private static final String DATE_WHEN_USER_MADE_ACTION = "action_date";
    private static final String ACTION_TYPE = "action_type";
    private static final String DATE_AND_TIME_WHEN_USER_LOGGED_IN = "logged_in_date_time";
    private static final String DATE_AND_TIME_WHEN_USER_LOGGED_OUT = "logged_out_date_time";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = CURRENCY_HAVE)
    private String calculatorCurrencyWant;

    @Column(name = CURRENCY_WANT)
    private String calculatorCurrencyHave;

    @Column(name = CURRENCY_GIVEN_BY_USER_IN_EXTREMUM)
    private String extremumCurrency;

    @Column(name = AMOUNT_GIVEN_BY_USER_TO_CALCULATOR)
    private Double amount;

    @Column(name = EXCHANGE_RATE_BY_PLN)
    private BigDecimal exchangeRate;

    @Column(name = DATE_FROM_GIVEN_BY_USER)
    private LocalDate dateFrom;

    @Column(name = DATE_TO_GIVEN_BY_USER)
    private LocalDate dateTo;

    @Column(name = CALCULATOR_DATE_GIVEN_BY_USER)
    private LocalDate calculatorDate;

    @Column(name = DATE_WHEN_USER_MADE_ACTION)
    private LocalDateTime actionDate;

    @Column(name = DATE_AND_TIME_WHEN_USER_LOGGED_IN)
    private LocalDateTime loggedInTime;

    @Column(name = DATE_AND_TIME_WHEN_USER_LOGGED_OUT)
    private LocalDateTime loggedOutTime;

    @Enumerated(EnumType.STRING)
    @Column(name = ACTION_TYPE)
    private ActionType actionType;

    @ManyToOne
    @JoinColumn(name = ID_OF_USER)
    private User user;

    public Activity(String calculatorCurrencyFirst,
                    String calculatorCurrencySecond,
                    String extremumCurrency,
                    Double amount,
                    BigDecimal exchangeRate,
                    LocalDate calculatorDateFrom,
                    LocalDate calculatorDateTo,
                    LocalDate calculatorDate,
                    LocalDateTime actionDate,
                    LocalDateTime loggedInTime,
                    LocalDateTime loggedOutTime,
                    ActionType actionType,
                    User user) {
        this.calculatorCurrencyWant = calculatorCurrencyFirst;
        this.calculatorCurrencyHave = calculatorCurrencySecond;
        this.extremumCurrency = extremumCurrency;
        this.amount = amount;
        this.exchangeRate = exchangeRate;
        this.dateFrom = calculatorDateFrom;
        this.dateTo = calculatorDateTo;
        this.calculatorDate = calculatorDate;
        this.actionDate = actionDate;
        this.loggedInTime = loggedInTime;
        this.loggedOutTime = loggedOutTime;
        this.actionType = actionType;
        this.user = user;
    }

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalculatorCurrencyWant() {
        return calculatorCurrencyWant;
    }

    public void setCalculatorCurrencyWant(String calculatorCurrencyFirst) {
        this.calculatorCurrencyWant = calculatorCurrencyFirst;
    }

    public String getCalculatorCurrencyHave() {
        return calculatorCurrencyHave;
    }

    public void setCalculatorCurrencyHave(String calculatorCurrencySecond) {
        this.calculatorCurrencyHave = calculatorCurrencySecond;
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

    public LocalDate getCalculatorDate() {
        return calculatorDate;
    }

    public void setCalculatorDate(LocalDate calculatorDate) {
        this.calculatorDate = calculatorDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDateTime getLoggedInTime() {
        return loggedInTime;
    }

    public void setLoggedInTime(LocalDateTime loggedInTime) {
        this.loggedInTime = loggedInTime;
    }

    public LocalDateTime getLoggedOutTime() {
        return loggedOutTime;
    }

    public void setLoggedOutTime(LocalDateTime loggedOutTime) {
        this.loggedOutTime = loggedOutTime;
    }
}
