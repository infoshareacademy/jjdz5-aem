package com.isa.aem.servlets;


import com.isa.aem.CurrencyRepository;
import com.isa.aem.data_loaders.FileContentReader;
import com.isa.aem.data_loaders.CurrencyNameCountryFlagsLoader;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/global-extremum")
public class GlobalExtremumServlet extends HttpServlet {

    @Inject
    private TemplateProvider templateProvider;
    public FileContentReader fileContentReader;
    public CurrencyNameCountryFlagsLoader currencyNameCountryFlagsLoader;
    public CurrencyRepository currencyRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        currencyRepository = new CurrencyRepository();

        List<String> availableCurrencyNames = currencyRepository.getAvailableCurrencyNames();

        Template template = templateProvider
                .getTemplate(getServletContext(), TemplateName.GLOBAL_EXTREMUM.getName());

        Object userName = req.getSession().getAttribute("userName");
        Map<String, Object> model = new HashMap<>();
        model.put("availableCurrencyNames", availableCurrencyNames);
        model.put("logged", userName);


        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
