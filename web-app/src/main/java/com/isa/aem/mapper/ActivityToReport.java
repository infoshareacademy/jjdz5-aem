package com.isa.aem.mapper;

import com.isa.aem.model.ActionType;
import com.isa.aem.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActivityToReport {

    private String calculatorCurrencyWant;
    private String calculatorCurrencyHave;
    private String extremumCurrency;
    private Double amount;
    private BigDecimal exchangeRate;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate calculatorDate;
    private LocalDateTime actionDate;
    private LocalDateTime loggedInTime;
    private LocalDateTime loggedOutTime;
    private ActionType actionType;
    private User user;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalculatorCurrencyWant() {
        return calculatorCurrencyWant;
    }

    public void setCalculatorCurrencyWant(String calculatorCurrencyWant) {
        this.calculatorCurrencyWant = calculatorCurrencyWant;
    }

    public String getCalculatorCurrencyHave() {
        return calculatorCurrencyHave;
    }

    public void setCalculatorCurrencyHave(String calculatorCurrencyHave) {
        this.calculatorCurrencyHave = calculatorCurrencyHave;
    }

    public String getExtremumCurrency() {
        return extremumCurrency;
    }

    public void setExtremumCurrency(String extremumCurrency) {
        this.extremumCurrency = extremumCurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalDate getCalculatorDate() {
        return calculatorDate;
    }

    public void setCalculatorDate(LocalDate calculatorDate) {
        this.calculatorDate = calculatorDate;
    }

    public LocalDateTime getActionDate() {
        return actionDate;
    }

    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
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

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
