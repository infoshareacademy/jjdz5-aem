package com.isa.aem.servlets;

import com.isa.aem.*;
import com.isa.aem.calculatorMethod.Score;
import com.isa.aem.calculatorMethod.ScoreResult;
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

@WebServlet(urlPatterns = "/calculator")
public class CalculatorServlet extends HttpServlet {

    private Score score = new Score();
    private ScoreResult scoreResult = new ScoreResult();
    CurrencyRepository currencyRepository = new CurrencyRepository();
    private String defaultCurrencyNameHave;
    private String defaultCurrencyNameWant;
    private static final Double DEFAULT_AMOUNT = 100.00;
    private static final String AMOUNT_PARAMETER = "amount";
    private static final String HAVE_PARAMETER = "have";
    private static final String WANT_PARAMETER = "want";
    private static final String DATE_PARAMETER = "date";

    @Inject
    private TemplateProvider templateProvider;
    public FileContentReader fileContentReader;
    public LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags;

    @Override
    public void init() throws ServletException {
        fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();

        AppProperties appProperties = PropertiesLoader.loadProperties();
        defaultCurrencyNameHave = appProperties.getCurrencyNamePln();
        defaultCurrencyNameWant = appProperties.getCurrencyNameEur();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Currency> singleCurrency = score.getSingleCurrency();

        if (score.getAmount() == null) {
            score.setAmount(DEFAULT_AMOUNT);
        }

        if (score.getCurrencyHave() == null) {
            score.setCurrencyHave(defaultCurrencyNameHave);
        }

        if (score.getCurrencyWant() == null) {
            score.setCurrencyWant(defaultCurrencyNameWant);
        }

        if (score.getDateExchange() == null) {
            LocalDate dateHaveMax = currencyRepository.getMostRecentDateForChosenCurrencyName(defaultCurrencyNameHave);
            score.setDateExchange(dateHaveMax);
        }

        if (score.getMaxDate() == null) {
            score.setMaxDate(currencyRepository.getMostRecentDateForChosenCurrencyName(defaultCurrencyNameHave));
        }

        if (score.getMinDate() == null) {
            score.setMinDate(currencyRepository.getOldestDateForChosenCurrencyName(defaultCurrencyNameHave));
        }

        Template template = templateProvider
                .getTemplate(getServletContext(), "currency-converter");

        Map<String, Object> model = new HashMap<>();
        model.put("singleCurrency", singleCurrency);
        model.put("score", score);
        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String reqAmount = req.getParameter(AMOUNT_PARAMETER);
        String reqHave = req.getParameter(HAVE_PARAMETER);
        String reqWant = req.getParameter(WANT_PARAMETER);
        String reqDate = req.getParameter(DATE_PARAMETER);
        Double calculatorAmount = Double.parseDouble(reqAmount);
        String[] calculatorCurrencyHaveTable = reqHave.split(" - ");
        String[] calculatorCurrencyWantTable = reqWant.split(" - ");
        String haveCurrency = calculatorCurrencyHaveTable[0];

        LocalDate date = score.scoreDate(reqDate, haveCurrency, calculatorCurrencyWantTable[0]);

        score = scoreResult.getScoreResult(haveCurrency, calculatorCurrencyWantTable[0], date, calculatorAmount);
        score.setMaxDate(currencyRepository.getMostRecentDateForChosenCurrencyName(haveCurrency));
        score.setMinDate(currencyRepository.getOldestDateForChosenCurrencyName(haveCurrency));

        doGet(req, resp);
    }
}
