package com.isa.aem.servlets;


import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;
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

    private LocalExtremum localExtremum = new LocalExtremum();
    private List<Currency> minExtremum;
    private List<Currency> maxExtremum;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    @Inject
    private TemplateProvider templateProvider;
    public FileContentReader fileContentReader;
    public LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags;
    public CurrencyRepository currencyRepository;

    @Override
    public void init() throws ServletException {
        fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        currencyRepository = new CurrencyRepository();

        List<String> availableCurrencyNames = currencyRepository.getAvailableCurrencyNames();

        Template template = templateProvider
                .getTemplate(getServletContext(), "local-extremum");

        Map<String, Object> model = new HashMap<>();
        model.put("availableCurrencyNames", availableCurrencyNames);
        model.put("minExtremum", minExtremum);
        model.put("maxExtremum", maxExtremum);

        if (dateFrom == null) {
            dateFrom = currencyRepository.getFirstDateFromRepository();
        }

        if (dateTo == null) {
            dateTo = currencyRepository.getLastDateFromRepository();
        }


        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String chosenCurrencyName = req.getParameter("chosenCurrencyName");
        dateFrom = LocalDate.parse(req.getParameter("dateFrom"));
        dateTo = LocalDate.parse(req.getParameter("dateTo"));

        List<Currency> repositoryWithChosenCurrencyWithinChosenDateRange = currencyRepository.limitRepositoryToChosenCurrencyWithinChosenDateRange(chosenCurrencyName, dateFrom, dateTo);

        minExtremum = localExtremum.getMinExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);
        maxExtremum = localExtremum.getMaxExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);

        doGet(req, resp);
    }
}
