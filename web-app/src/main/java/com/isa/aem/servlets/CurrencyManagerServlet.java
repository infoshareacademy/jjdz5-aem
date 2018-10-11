package com.isa.aem.servlets;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.isa.aem.*;
import com.isa.aem.Currency;
import com.isa.aem.calculatorMethod.AvailableCurrencyTable;
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
import java.util.*;

@WebServlet(urlPatterns = "/currency-manager")
public class CurrencyManagerServlet extends HttpServlet {

    private Score score = new Score();
    private AvailableCurrencyTable availableCurrencyTable=new AvailableCurrencyTable();
    private ScoreResult scoreResult=new ScoreResult();
    CurrencyRepository currencyRepository=new CurrencyRepository();
    String currencyInTable;

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

        if(score.getAmount()==null){
            score.setAmount(100.00);
        }

        if(score.getCurrencyHave()==null){
            score.setCurrencyHave("PLN");
            currencyInTable="AUD";
            availableCurrencyTable.tableListCurrencyObject= availableCurrencyTable.availableCurrencyObjects(currencyInTable);
        }

        if (score.getCurrencyWant()==null){
            score.setCurrencyWant("EUR");
        }

        if (score.getDateExchange()==null){
            LocalDate dateHaveMax= currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile("PLN");
            score.setDateExchange(dateHaveMax);
        }

        if(score.getMaxDate()==null){
            score.setMaxDate(currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile("PLN"));
        }

        if(score.getMinDate()==null){
            score.setMinDate(currencyRepository.getMinCurrentDateOfSelectedCurrencyFromTheFile("PLN"));
        }

//        if (currencyInTable == null) {
//            currencyInTable="PLN";
//            availableCurrencyTable.availableCurrencyObjects(currencyInTable);
//        }

        Template template = templateProvider
                .getTemplate(getServletContext(), "currency-manager-converter");

        Object userName = req.getSession().getAttribute("userName");
        Map<String, Object> model = new HashMap<>();
        model.put("singleCurrency", singleCurrency);
        model.put("score", score);
        model.put("logged", userName);
        model.put("currencyInTable", currencyInTable);
        model.put("availableCurrencyTable", availableCurrencyTable.tableListCurrencyObject);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AvailableCurrencyTable availableCurrencyTable1=new AvailableCurrencyTable();
        String reqAmount = req.getParameter("amount");
        String reqHave = req.getParameter("have");
        String reqWant = req.getParameter("want");
        String reqDate = req.getParameter("date");
        Double calculatorAmount = Double.parseDouble(reqAmount);
        String[] calculatorCurrencyHaveTable = reqHave.split(" - ");
        String[] calculatorCurrencyWantTable = reqWant.split(" - ");
        String haveCurrency = calculatorCurrencyHaveTable[0];
        LocalDate date= score.scoreDate(reqDate,haveCurrency,calculatorCurrencyWantTable[0]);
        String currencyInTableNames=req.getParameter("currency_table");
     //   String[] currencyInTableName = currencyInTableNames.split(" - ");
        currencyInTable=haveCurrency;

        score=scoreResult.getScoreResult(haveCurrency, calculatorCurrencyWantTable[0], date, calculatorAmount);
        score.setMaxDate(currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile(haveCurrency));
        score.setMinDate(currencyRepository.getMinCurrentDateOfSelectedCurrencyFromTheFile(haveCurrency));
        availableCurrencyTable.tableListCurrencyObject= availableCurrencyTable1.availableCurrencyObjects(haveCurrency);

        doGet(req, resp);
    }
}
