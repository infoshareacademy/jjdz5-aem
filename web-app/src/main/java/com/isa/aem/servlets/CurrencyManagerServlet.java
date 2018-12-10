package com.isa.aem.servlets;

import com.isa.aem.*;
import com.isa.aem.freemarker.TemplateName;
import com.isa.aem.informationcollect.RecordCreator;
import com.isa.aem.model.Activity;
import com.isa.aem.model.User;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/currency-manager")
public class CurrencyManagerServlet extends CalculatorComponentsServlet {

    @Inject
    RecordCreator recordCreator;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Currency> singleCurrency = currencyRepository.getCurrenciesWithFullNameAndFlag();

        setDefaultValueOfCalculator();
        Template template = templateProvider
                .getTemplate(getServletContext(), TemplateName.CURRENCY_MANAGER.getName());

        Object userName = req.getSession().getAttribute(USER_NAME_PARAMETER);
        Map<String, Object> model = new HashMap<>();
        model.put("singleCurrency", singleCurrency);
        model.put("score", score);
        model.put("logged", userName);
        model.put("currencyInTable", currencyInTable);
        model.put("availableCurrencyTable", createAListOfAvailableCurrencies.getTableListCurrencyObject());

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        calculateExchangeRate(req, resp);
        doGet(req, resp);
    }

    private void trackingUser(HttpServletRequest req) {

        Double amount  = Double.valueOf(req.getParameter(AMOUNT_PARAMETER));

        String currencyHave = req.getParameter(HAVE_PARAMETER);
        String currencyWant = req.getParameter(WANT_PARAMETER);
        LocalDate dateOfExchange = LocalDate.parse(req.getParameter(DATE_PARAMETER));

        Activity calculatorActivity = recordCreator.createCalculatorActivity(
                amount,
                currencyHave,
                currencyWant,
                dateOfExchange);

    }

    private void reqParameterToUser(HttpServletRequest req) {

    }
}
