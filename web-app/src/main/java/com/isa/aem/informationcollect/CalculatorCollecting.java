package com.isa.aem.informationcollect;

import com.isa.aem.model.ActionType;
import com.isa.aem.model.Activity;
import com.isa.aem.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CalculatorCollecting {

    public User createUser(String name,
                           String email,
                           LocalDateTime loginTime,
                           LocalDateTime logoutTime,
                           Boolean isAdmin,
                           Activity activity
                           ) {
        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setLoggedIn(loginTime);
        user.setLoggedOut(logoutTime);
        user.setAdmin(isAdmin);
        user.setActivity(activity);

        return user;
    }

    public Activity createCalculatorActivity(Double amount,
                                             String firstCurrency,
                                             String secondCurrency,
                                             LocalDate dateFrom,
                                             LocalDate dateTo) {
        Activity activity = new Activity();
        activity.setAmount(amount);
        activity.setCalculatorCurrencyFirst(firstCurrency);
        activity.setCalculatorCurrencySecond(secondCurrency);
        activity.setDateFrom(dateFrom);
        activity.setDateTo(dateTo);
        activity.setActionType(ActionType.CALCUALTOR);
        activity.setActionDate(LocalDateTime.now());

        return activity;
    }

    public Activity createLocalExtremeumActivity(LocalDate dateFrom,
                                                 LocalDate dateTo,
                                                 String currencyName) {
        Activity activity = new Activity();

        activity.setDateFrom(dateFrom);
        activity.setDateTo(dateTo);
        activity.setExtremumCurrency(currencyName);
        activity.setActionType(ActionType.LOCAL);
        activity.setActionDate(LocalDateTime.now());

        return activity;
    }

    public Activity createGlobalExtremeumActivity(LocalDate dateFrom,
                                                  String currencyName) {
        Activity activity = new Activity();

        activity.setDateFrom(dateFrom);
        activity.setDateTo(LocalDate.now());
        activity.setExtremumCurrency(currencyName);
        activity.setActionType(ActionType.GLOBAL);
        activity.setActionDate(LocalDateTime.now());

        return activity;
    }
}
