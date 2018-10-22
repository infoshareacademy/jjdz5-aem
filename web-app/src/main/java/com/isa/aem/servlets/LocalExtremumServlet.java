package com.isa.aem.servlets;


import com.isa.aem.*;
import com.isa.aem.freemarker.TemplateProvider;
import com.isa.aem.local.extremum.LocalExtremum;
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
    private String currencyName;
    private String defaultCurrencyName;
    private static final String CURRENCY_NAME_PARAMETER = "currencyName";
    private static final String DATE_FROM_PARAMETER = "dateFrom";
    private static final String DATE_TO_PARAMETER = "dateTo";
    private static final String USER_NAME_PARAMETER = "userName";


    @Inject
    private TemplateProvider templateProvider;
    public FileContentReader fileContentReader;
    public LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags;

    @Override
    public void init() throws ServletException {
        fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();

        AppProperties appProperties = PropertiesLoader.loadProperties();
        defaultCurrencyName = appProperties.getCurrencyNameEur();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> availableCurrencyNames = currencyRepository.getAvailableCurrencyNames();

        Template template = templateProvider
                .getTemplate(getServletContext(), "local-extremum");

        if (currencyRepository.containsCurrencyNameInCurrencyList(defaultCurrencyName)) {
            currencyName = defaultCurrencyName;
        } else {
            currencyName = currencyRepository.getFirstAvailableCurrencyName();
        }
        if (req.getParameter(CURRENCY_NAME_PARAMETER) != null) {
            currencyName = req.getParameter(CURRENCY_NAME_PARAMETER);
        }

        if (req.getParameter(DATE_FROM_PARAMETER) != null) {
            dateFrom = LocalDate.parse(req.getParameter(DATE_FROM_PARAMETER));
        } else {
            dateFrom = currencyRepository.getMostRecentDateMinusOneMonthForChosenCurrencyName(currencyName);
        }

        if (req.getParameter(DATE_TO_PARAMETER) != null) {
            dateTo = LocalDate.parse(req.getParameter(DATE_TO_PARAMETER));
        } else {
            dateTo = currencyRepository.getMostRecentDateForChosenCurrencyName(currencyName);
        }

        List<Currency> minExtremum = localExtremum.getMinExtremum(currencyName, dateFrom, dateTo);
        List<Currency> maxExtremum = localExtremum.getMaxExtremum(currencyName, dateFrom, dateTo);

        Object userName = req.getSession().getAttribute(USER_NAME_PARAMETER);
        Map<String, Object> model = new HashMap<>();
        model.put("currencyRepository", currencyRepository);
        model.put("availableCurrencyNames", availableCurrencyNames);
        model.put("currencyName", currencyName);
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
