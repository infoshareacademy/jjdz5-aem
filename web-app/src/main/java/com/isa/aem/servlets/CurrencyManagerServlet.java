package com.isa.aem.servlets;

import com.isa.aem.*;
import com.isa.aem.currency_calculator.CreateAListOfAvailableCurrencies;
import com.isa.aem.currency_calculator.Score;
import com.isa.aem.currency_calculator.ScoreResult;
import com.isa.aem.data_loaders.CurrencyNameCountryFlagsLoader;
import com.isa.aem.data_loaders.FileContentReader;
import com.isa.aem.data_loaders.PropertiesLoader;
import com.isa.aem.freemarker.TemplateName;
import com.isa.aem.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/currency-manager")
public class CurrencyManagerServlet extends CalculatorComponents {


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
}
