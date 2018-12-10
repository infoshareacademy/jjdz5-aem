package com.isa.aem.informationcollect;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.isa.aem.model.ActionType;
import com.isa.aem.model.Activity;
import com.isa.aem.model.User;
import com.isa.aem.servlets.IdTokenVerifierAndParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class RecordCreator {

    protected static final String ID_TOKEN_PARAMETER = "id_token";
    protected static final String NAME_PARAMETER = "name";

    public User createUser(String name,
                           String email,
                           LocalDateTime loginTime,
                           Boolean isAdmin,
                           List<Activity> activity
    ) {
        User user = new User();

        user.setUserName(name);
        user.setEmail(email);
        user.setLoggedIn(loginTime);
        user.setAdmin(isAdmin);
        user.setActivity(activity);

        return user;
    }

    public Activity createCalculatorActivity(Double amount,
                                             String firstCurrency,
                                             String secondCurrency,
                                             LocalDate calculatorDate) {
        Activity activity = new Activity();
        activity.setAmount(amount);
        activity.setCalculatorCurrencyFirst(firstCurrency);
        activity.setCalculatorCurrencySecond(secondCurrency);
        activity.setCalculatorDate(calculatorDate);
        activity.setActionType(ActionType.CALCUALTOR);
        activity.setActionDate(LocalDateTime.now());

        return activity;
    }

    public Activity createExchangeRateActivity(BigDecimal rate) {
        Activity activity = new Activity();

        activity.setExchangeRate(rate);
        activity.setActionDate(LocalDateTime.now());
        activity.setActionType(ActionType.CALCUALTOR);

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

    public LocalDateTime getLoginDateTimeFromSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(
                        session.getCreationTime()),
                        ZoneId.systemDefault());
    }

    public String getNameByGoogle(HttpServletRequest req) {
        String idToken = req.getParameter(ID_TOKEN_PARAMETER);
        GoogleIdToken.Payload payLoad = null;
        try {
            payLoad = IdTokenVerifierAndParser.getPayload(idToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (String) payLoad.get(NAME_PARAMETER);
    }

    public String getEmailByGoogle(HttpServletRequest req) {
        String idToken = req.getParameter(ID_TOKEN_PARAMETER);
        GoogleIdToken.Payload payLoad = null;
        try {
            payLoad = IdTokenVerifierAndParser.getPayload(idToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payLoad.getEmail();

    }
}
