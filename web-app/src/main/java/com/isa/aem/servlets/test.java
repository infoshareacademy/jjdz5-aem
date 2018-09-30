package com.isa.aem.servlets;

import com.isa.aem.*;
import com.isa.aem.calc.AlgorithmCurrencyConversion;
import com.isa.aem.tools.DateService;
import com.isa.aem.freemarker.TemplateProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet(urlPatterns = "/welcome")
public class test extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;
    public FileContentReader fileContentReader;
    public LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags;
    public CurrencyRepository currencyRepository;

    @Override
    public void init() throws ServletException {
        fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();

    }



    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrencyRepository currencyRepository = new CurrencyRepository();
        AlgorithmCurrencyConversion algorithmCurrencyConversion = new AlgorithmCurrencyConversion();
        PrintWriter writer = resp.getWriter();
        String amount = req.getParameter("amount");
        String have = req.getParameter("have");
        String want = req.getParameter("want");
        String date = req.getParameter("date");
        Double calculatorAmount = Double.parseDouble(amount);
        String[] calculatorCurrencyHaveTable = have.split(" - ");
        String[] calculatorCurrencyWantTable = want.split(" - ");
        String haveCurrency = calculatorCurrencyHaveTable[0];

        DateService dataService = new DateService();
        LocalDate date1 = dataService.dataParse(date.replace("-", ""));

        Double currencyHave = currencyRepository.getRateOfGivenDate(haveCurrency, date1);
        Double currencyWant = currencyRepository.getRateOfGivenDate(calculatorCurrencyWantTable[0], date1);
        BigDecimal score = algorithmCurrencyConversion.currencyConversionAlgorithm(calculatorAmount, currencyHave, currencyWant);

        req.getSession().setAttribute("score", score);
        req.getSession().getAttribute("score");
        writer.println("<!DOCTYPE html><html><body></body></html>");
        resp.setContentType("text/html;charset=UTF-8");

        writer.println(score);

    }


}