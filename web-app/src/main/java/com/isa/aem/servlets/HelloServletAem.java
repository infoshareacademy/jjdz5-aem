package com.isa.aem.servlets;

import com.isa.aem.*;
import com.isa.aem.Currency;
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
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/hello-servlet-aem")
public class HelloServletAem extends HttpServlet {

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

        Set<Currency>singleCurrency= singleCurrency1.stream()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .distinct()
                .collect(Collectors.toSet());

        Template template = templateProvider
                .getTemplate(getServletContext(), "currency-converter");

        Map<String, Object> model = new HashMap<>();
        model.put("singleCurrency", singleCurrency);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}
