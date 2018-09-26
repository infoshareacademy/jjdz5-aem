package com.isa.aem.servlets;

import com.isa.aem.*;
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
import java.util.HashMap;

@WebServlet("/hello-servlet-aem")
public class HelloServletAem extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;
    public FileContentReader fileContentReader;
    public LoadCurrencyNameCountryFlags loadCurrencyNameCountryFlags;
    public CurrencyRepository currencyRepository;

    public void init() {
        fileContentReader=new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        loadCurrencyNameCountryFlags = new LoadCurrencyNameCountryFlags();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        currencyRepository=new CurrencyRepository();

//        for (Currency cc: CurrencyRepository.getCurrencies()) {
//            cc.setCurrencyNameCountryFlags(CurrencyNameCountryFlags.getCurrencies().get(cc.getName()));
//
//        }
        writer.println(currencyRepository.listAvailableCurrency());

//
//        Template template = templateProvider
//                .getTemplate(getServletContext(), "currency-converter");
//        try {
//            template.process(new HashMap<>(), resp.getWriter());
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }

    }
}
