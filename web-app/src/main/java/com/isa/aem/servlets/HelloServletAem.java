package com.isa.aem.servlets;

import com.isa.aem.*;
import com.isa.aem.Currency;
import com.isa.aem.calc.AlgorithmCurrencyConversion;
import com.isa.aem.calc.DateService;
import com.isa.aem.freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/hello-servlet-aem")
public class HelloServletAem extends HttpServlet {

    private BigDecimal score=new BigDecimal(0.00);

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
        Set<Currency> singleCurrency1 = new HashSet<>();
        for (Currency cc : CurrencyRepository.getCurrencies()) {
            cc.setCurrencyNameCountryFlags(CurrencyNameCountryFlags.getCurrencies().get(cc.getName()));
            singleCurrency1.add(new Currency(cc.getName(),cc.getCurrencyNameCountryFlags()));
        }

        List<Currency>singleCurrency= singleCurrency1.stream()
                .sorted(Comparator.comparing(Currency::getName))
                .distinct()
                .collect(Collectors.toList());

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
        BigDecimal score1= algorithmCurrencyConversion.currencyConversionAlgorithm(calculatorAmount, currencyHave, currencyWant);
        score=score1;

        doGet(req, resp);
    }



}
