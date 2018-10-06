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
    private DataValidator dataValidator = new DataValidator();
    private LocalExtremum localExtremum = new LocalExtremum();
    private LocalDate dateFrom;
    private LocalDate dateTo;

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

        List<String> availableCurrencyNames = CurrencyRepository.getAvailableCurrencyNames();

        Template template = templateProvider
                .getTemplate(getServletContext(), "local-extremum");

        String chosenCurrencyName = req.getParameter("chosenCurrencyName");

        if(req.getParameter("dateFrom") != null) {
            dateFrom = LocalDate.parse(req.getParameter("dateFrom"));
        } else {
            dateFrom = currencyRepository.getLastMonthDateFromRepository();
            System.out.println(dateFrom);
        }

        if(req.getParameter("dateTo") != null) {
            dateTo = LocalDate.parse(req.getParameter("dateTo"));
        } else {
            dateTo = currencyRepository.getLastDateFromRepository();
        }



        List<Currency> repositoryWithChosenCurrencyWithinChosenDateRange = CurrencyRepository.limitRepositoryToChosenCurrencyWithinChosenDateRange(chosenCurrencyName, dateFrom, dateTo);

        List<Currency> minExtremum = localExtremum.getMinExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);
        List<Currency> maxExtremum = localExtremum.getMaxExtremum(repositoryWithChosenCurrencyWithinChosenDateRange);

        Map<String, Object> model = new HashMap<>();
        model.put("dataValidator", dataValidator);
        model.put("currencyRepository", currencyRepository);
        model.put("availableCurrencyNames", availableCurrencyNames);
        model.put("chosenCurrencyName", chosenCurrencyName);
        model.put("dateFrom", dateFrom);
        model.put("dateTo", dateTo);
        model.put("minExtremum", minExtremum);
        model.put("maxExtremum", maxExtremum);

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
