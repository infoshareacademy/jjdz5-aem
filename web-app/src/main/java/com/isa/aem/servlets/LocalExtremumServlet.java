package com.isa.aem.servlets;


import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;
import com.isa.aem.freemarker.TemplateProvider;
import com.isa.aem.local.extremum.LocalExtremum;
import com.isa.aem.tools.DataValidator;
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

@WebServlet("/local-extremum")
public class LocalExtremumServlet extends HttpServlet {

    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private LocalExtremum localExtremum = new LocalExtremum();
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String chosenCurrencyName;
    private static final String DEFAULT_CURRENCY_NAME = "EUR";

    @Inject
    private TemplateProvider templateProvider;
    public FileContentReader fileContentReader;
    public LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags;

    @Override
    public void init() throws ServletException {
        fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> availableCurrencyNames = currencyRepository.getAvailableCurrencyNames();

        Template template = templateProvider
                .getTemplate(getServletContext(), "local-extremum");

        if (currencyRepository.containsCurrencyNameInCurrencyList(DEFAULT_CURRENCY_NAME)) {
            chosenCurrencyName = DEFAULT_CURRENCY_NAME;
        } else {
            chosenCurrencyName = currencyRepository.getFirstAvailableCurrencyName();
        }
        if (req.getParameter(chosenCurrencyName) != null) {
            chosenCurrencyName = req.getParameter(chosenCurrencyName);
        }

        if (req.getParameter("dateFrom") != null) {
            dateFrom = LocalDate.parse(req.getParameter("dateFrom"));
        } else {
            dateFrom = currencyRepository.getMostRecentDateMinusOneMonthForChosenCurrencyName(chosenCurrencyName);
        }

        if (req.getParameter("dateTo") != null) {
            dateTo = LocalDate.parse(req.getParameter("dateTo"));
        } else {
            dateTo = currencyRepository.getMostRecentDateForChosenCurrencyName(chosenCurrencyName);
        }

        List<Currency> minExtremum = localExtremum.getMinExtremum(chosenCurrencyName, dateFrom, dateTo);
        List<Currency> maxExtremum = localExtremum.getMaxExtremum(chosenCurrencyName, dateFrom, dateTo);

        Object userName = req.getSession().getAttribute("userName");
        Map<String, Object> model = new HashMap<>();
        model.put("currencyRepository", currencyRepository);
        model.put("availableCurrencyNames", availableCurrencyNames);
        model.put("chosenCurrencyName", chosenCurrencyName);
        model.put("dateFrom", dateFrom);
        model.put("dateTo", dateTo);
        model.put("localExtremum", localExtremum);
        model.put("minExtremum", minExtremum);
        model.put("maxExtremum", maxExtremum);
        model.put("logged", userName);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
