package com.isa.aem.servlets;

import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.FileContentReader;
import com.isa.aem.LoadCurrencyNameCountryFlags;
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

@WebServlet(urlPatterns = "/currency-manager")
public class CurrencyManagerServlet extends HttpServlet {

    private Score score = new Score();
    private ScoreResult scoreResult = new ScoreResult();
    CurrencyRepository currencyRepository = new CurrencyRepository();
    private static final String DEFAULT_CURRENCY_HAVE = "PLN";
    private static final String DEFAULT_CURRENCY_WANT = "EUR";
    private static final Double DEFAULT_AMOUNT = 100.00;

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
            LocalDate dateHaveMax = currencyRepository.getMostRecentDateForChosenCurrencyName(DEFAULT_CURRENCY_HAVE);
            score.setDateExchange(dateHaveMax);
        }

        if (score.getMaxDate() == null) {
            score.setMaxDate(currencyRepository.getMostRecentDateForChosenCurrencyName(DEFAULT_CURRENCY_HAVE));
        }

        if (score.getMinDate() == null) {
            score.setMinDate(currencyRepository.getOldestDateForChosenCurrencyName(DEFAULT_CURRENCY_HAVE));
        }

        Template template = templateProvider
                .getTemplate(getServletContext(), "currency-manager-converter");

        Object userName = req.getSession().getAttribute("userName");
        Map<String, Object> model = new HashMap<>();
        model.put("singleCurrency", singleCurrency);
        model.put("score", score);
        model.put("logged", userName);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
        score.setMaxDate(currencyRepository.getMostRecentDateForChosenCurrencyName(haveCurrency));
        score.setMinDate(currencyRepository.getOldestDateForChosenCurrencyName(haveCurrency));

        doGet(req, resp);
    }
}
