package com.isa.aem.model;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ACTIVITIES")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "calculator_currency_want")
    private String calculatorCurrencyWant;

    @Column(name = "calculator_currency_have")
    private String calculatorCurrencyHave;

    @Column(name = "extremum_currency")
    private String extremumCurrency;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "exchange_rate_by_pln")
    private BigDecimal exchangeRate;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(name = "calculator_date")
    private LocalDate calculatorDate;

    @Column(name = "action_date")
    private LocalDateTime actionDate;

    @Column(name = "logged_in_date_time")
    private LocalDateTime loggedInTime;

    @Column(name = "logged_out_date_time")
    private LocalDateTime loggedOutTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private ActionType actionType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Activity(String calculatorCurrencyWant,
                    String calculatorCurrencyHave,
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
        this.calculatorCurrencyWant = calculatorCurrencyWant;
        this.calculatorCurrencyHave = calculatorCurrencyHave;
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

    public void setCalculatorCurrencyWant(String calculatorCurrencyWant) {
        this.calculatorCurrencyWant = calculatorCurrencyWant;
    }

    public void setCalculatorCurrencyHave(String calculatorCurrencyHave) {
        this.calculatorCurrencyHave = calculatorCurrencyHave;
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

    public String getCalculatorCurrencyHave() {
        return calculatorCurrencyHave;
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
