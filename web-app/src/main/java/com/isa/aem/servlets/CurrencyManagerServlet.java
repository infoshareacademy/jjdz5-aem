package com.isa.aem.servlets;

import com.isa.aem.*;
import com.isa.aem.Currency;
import com.isa.aem.calculatorMethod.CreateAListOfAvailableCurrencies;
import com.isa.aem.calculatorMethod.Score;
import com.isa.aem.calculatorMethod.ScoreResult;
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
import java.util.*;

@WebServlet(urlPatterns = "/currency-manager")
public class CurrencyManagerServlet extends HttpServlet {

    private Score score = new Score();
    private CreateAListOfAvailableCurrencies createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();
    private ScoreResult scoreResult = new ScoreResult();
    CurrencyRepository currencyRepository = new CurrencyRepository();
    String currencyInTable;
    private static final Double DEFAULT_AMOUNT = 100.00;
    private static final String DEFAULT_CURRENCY_HAVE = "PLN";
    private static final String DEFAULT_CURRENCY_WANT = "EUR";
    private static final String ACTION_BATTON = "action";
    private static final String ACTION_BUTTON_CALCULATOR= "calculator";
    private static final String ACTION_BUTTON_RANGE_CURRENCY= "rangeCurrency";

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
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Currency> singleCurrency = score.getSingleCurrency();

        if (score.getAmount() == null) {
            score.setAmount(DEFAULT_AMOUNT);
        }

        if (score.getCurrencyHave() == null) {
            score.setCurrencyHave(DEFAULT_CURRENCY_HAVE);

        }

        if (score.getCurrencyWant() == null) {
            score.setCurrencyWant(DEFAULT_CURRENCY_WANT);
        }

        if (score.getDateExchange() == null) {
            LocalDate dateHaveMax = currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile(DEFAULT_CURRENCY_HAVE);
            score.setDateExchange(dateHaveMax);
        }

        if (score.getMaxDate() == null) {
            score.setMaxDate(currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile(DEFAULT_CURRENCY_HAVE));
        }

        if (score.getMinDate() == null) {
            score.setMinDate(currencyRepository.getMinCurrentDateOfSelectedCurrencyFromTheFile(DEFAULT_CURRENCY_HAVE));
        }

        if (currencyInTable == null) {
            currencyInTable = DEFAULT_CURRENCY_HAVE;
            createAListOfAvailableCurrencies.tableListCurrencyObject = createAListOfAvailableCurrencies.availableCurrencyObjects(currencyInTable);
        }

        Template template = templateProvider
                .getTemplate(getServletContext(), TemplateName.CURRENCY_MANAGER.getName());

        Object userName = req.getSession().getAttribute("userName");
        Map<String, Object> model = new HashMap<>();
        model.put("singleCurrency", singleCurrency);
        model.put("score", score);
        model.put("logged", userName);
        model.put("currencyInTable", currencyInTable);
        model.put("availableCurrencyTable", createAListOfAvailableCurrencies.tableListCurrencyObject);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_BATTON);

        if (ACTION_BUTTON_CALCULATOR.equals(action)) {
            String reqAmount = req.getParameter("amount");
            String reqHave = req.getParameter("have");
            String reqWant = req.getParameter("want");
            String reqDate = req.getParameter("date");
            Double calculatorAmount = Double.parseDouble(reqAmount);
            String[] calculatorCurrencyHaveTable = reqHave.split(" - ");
            String[] calculatorCurrencyWantTable = reqWant.split(" - ");
            String haveCurrency = calculatorCurrencyHaveTable[0];
            LocalDate date = score.scoreDate(reqDate, haveCurrency, calculatorCurrencyWantTable[0]);
            score = scoreResult.getScoreResult(haveCurrency, calculatorCurrencyWantTable[0], date, calculatorAmount);
            score.setMaxDate(currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile(haveCurrency));
            score.setMinDate(currencyRepository.getMinCurrentDateOfSelectedCurrencyFromTheFile(haveCurrency));

        } else if (ACTION_BUTTON_RANGE_CURRENCY.equals(action)) {
            CreateAListOfAvailableCurrencies createAListOfAvailableCurrencies1 = new CreateAListOfAvailableCurrencies();
            String currencyInTableNames = req.getParameter("currency_table");
            String[] currencyInTableName = currencyInTableNames.split(" - ");
            currencyInTable = currencyInTableName[0];
            createAListOfAvailableCurrencies.tableListCurrencyObject = createAListOfAvailableCurrencies1.availableCurrencyObjects(currencyInTable);

        }
        doGet(req, resp);
    }
}
