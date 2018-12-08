package com.isa.aem.servlets;

import com.isa.aem.Currency;
import com.isa.aem.freemarker.TemplateName;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/calculator")
public class CalculatorServlet extends CalculatorComponentsServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Currency> singleCurrency = currencyRepository.getCurrenciesWithFullNameAndFlag();

        setDefaultValueOfCalculator();
        Template template = templateProvider
                .getTemplate(getServletContext(), TemplateName.CURRENCY_CONVERTER.getName());

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
