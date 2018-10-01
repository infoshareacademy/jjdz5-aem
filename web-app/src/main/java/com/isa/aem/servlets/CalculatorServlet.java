package com.isa.aem.servlets;

import com.isa.aem.*;
import com.isa.aem.Currency;
import com.isa.aem.calculatorMethod.ResultCalculator;
import com.isa.aem.calculatorMethod.Score;
import com.isa.aem.tools.DateService;
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
public class CalculatorServlet extends HttpServlet {

    private Score score=new Score();
    private static Integer LENGTH_OF_DATE=10;

    @Inject
    private TemplateProvider templateProvider;
    public FileContentReader fileContentReader;
    public LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags;
    public CurrencyRepository currencyRepository;


    @Override
    public void init()throws ServletException {
        fileContentReader=new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        currencyRepository=new CurrencyRepository();
        Set<Currency> currencyNameAndCountry = new HashSet<>();
        for (Currency cc : CurrencyRepository.getCurrencies()) {
            cc.setCurrencyNameCountryFlags(CurrencyNameCountryFlags.getCurrencies().get(cc.getName()));
            currencyNameAndCountry.add(new Currency(cc.getName(),cc.getCurrencyNameCountryFlags()));
        }

        List<Currency> singleCurrency= currencyRepository.getSortedCurrencySet(currencyNameAndCountry);

        Template template = templateProvider
                .getTemplate(getServletContext(), "currency-converter");

        Map<String, Object> model = new HashMap<>();
        model.put("singleCurrency", singleCurrency);
        model.put("resultCalculator", score);
        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateService dataService = new DateService();
        ResultCalculator resultCalculator=new ResultCalculator();

        String reqAmount = req.getParameter("amount");
        String reqHave = req.getParameter("have");
        String reqWant = req.getParameter("want");
        String reqDate = req.getParameter("date");
        Double calculatorAmount = Double.parseDouble(reqAmount);
        String[] calculatorCurrencyHaveTable = reqHave.split(" - ");
        String[] calculatorCurrencyWantTable = reqWant.split(" - ");
        String haveCurrency = calculatorCurrencyHaveTable[0];

        if(reqDate.length()==LENGTH_OF_DATE){
                LocalDate date = dataService.dataParse(reqDate.replace("-", ""));
                score=resultCalculator.resultCalculator(haveCurrency,date, calculatorCurrencyWantTable[0], calculatorAmount);
        }else{
            LocalDate date=  currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile(calculatorCurrencyWantTable[0]);
            LocalDate dateHave=  currencyRepository.getMostCurrentDateOfSelectedCurrencyFromTheFile(haveCurrency);

            if (date.equals(dateHave)){
                score=resultCalculator.resultCalculator(haveCurrency,date, calculatorCurrencyWantTable[0], calculatorAmount);
            }

        }

        doGet(req, resp);
    }
}
