package com.isa.aem.servlets;

import com.isa.aem.AppProperties;
import com.isa.aem.Currency;
import com.isa.aem.CurrencyNameCountryFlags;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.data_loaders.CurrencyNameCountryFlagsLoader;
import com.isa.aem.data_loaders.FileContentReader;
import com.isa.aem.data_loaders.PropertiesLoader;
import com.isa.aem.freemarker.TemplateName;
import com.isa.aem.freemarker.TemplateProvider;
import com.isa.aem.rate_extremums.ExchangeRateExtremum;
import com.isa.aem.utils.DataValidator;
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

@WebServlet("/local-extremum")
public class LocalExtremumServlet extends HttpServlet {

    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private ExchangeRateExtremum exchangeRateExtremum = new ExchangeRateExtremum();
    private CurrencyNameCountryFlags currencyNameCountryFlags = new CurrencyNameCountryFlags();
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String currencyName;
    private DataValidator dataValidator = new DataValidator();
    private Boolean isDateFromAfterDateTo = Boolean.FALSE;
    private String defaultCurrencyName;
    List<Currency> minExtremum;
    List<Currency> maxExtremum;
    private static final String CURRENCY_NAME_PARAMETER = "currencyName";
    private static final String DATE_FROM_PARAMETER = "dateFrom";
    private static final String DATE_TO_PARAMETER = "dateTo";
    private static final String USER_NAME_PARAMETER = "userName";

    @Inject
    private TemplateProvider templateProvider;
    public FileContentReader fileContentReader;
    public CurrencyNameCountryFlagsLoader currencyNameCountryFlagsLoader;

    @Override
    public void init() throws ServletException {
        fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        currencyNameCountryFlagsLoader = new CurrencyNameCountryFlagsLoader();

        AppProperties appProperties = PropertiesLoader.loadProperties();
        defaultCurrencyName = appProperties.getCurrencyNameEur();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Currency> currenciesWithFullNameAndFlag = currencyRepository.getCurrenciesWithFullNameAndFlag();

        Template template = templateProvider
                .getTemplate(getServletContext(), TemplateName.LOCAL_EXTREMUM.getName());

        if (currencyName == null) {
            if (currencyRepository.containsCurrencyNameInCurrencyList(defaultCurrencyName)) {
                currencyName = defaultCurrencyName;
            } else {
                currencyName = currencyRepository.getFirstAvailableCurrencyName();
            }
        }

        if (dateFrom == null) {
            dateFrom = currencyRepository.getNewestDateMinusOneMonthForChosenCurrencyName(currencyName);
        }

        if (dateTo == null) {
            dateTo = currencyRepository.getNewestDateForChosenCurrencyName(currencyName);
        }

        isDateFromAfterDateTo = dataValidator.isDateFromAfterDateTo(dateFrom, dateTo);
        if (!isDateFromAfterDateTo) {
            minExtremum = exchangeRateExtremum.getMinExtremum(currencyName, dateFrom, dateTo);
            maxExtremum = exchangeRateExtremum.getMaxExtremum(currencyName, dateFrom, dateTo);
        }

        Object userName = req.getSession().getAttribute(USER_NAME_PARAMETER);
        Map<String, Object> model = new HashMap<>();
        model.put("currencyRepository", currencyRepository);
        model.put("currencyName", currencyName);
        model.put("currenciesWithFullNameAndFlag", currenciesWithFullNameAndFlag);
        model.put("currencyNameCountryFlags", currencyNameCountryFlags);
        model.put("dateFrom", dateFrom);
        model.put("dateTo", dateTo);
        model.put("exchangeRateExtremum", exchangeRateExtremum);
        model.put("isDateFromAfterDateTo", isDateFromAfterDateTo);
        model.put("minExtremum", minExtremum);
        model.put("maxExtremum", maxExtremum);
        model.put("logged", userName);

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currencyNameParam = req.getParameter(CURRENCY_NAME_PARAMETER);
        String[] currencyNameTab = currencyNameParam.split(" - ");
        currencyName = currencyNameTab[0];
        dateFrom = LocalDate.parse(req.getParameter(DATE_FROM_PARAMETER));
        dateTo = LocalDate.parse(req.getParameter(DATE_TO_PARAMETER));

        isDateFromAfterDateTo = dataValidator.isDateFromAfterDateTo(dateFrom, dateTo);
        if (!isDateFromAfterDateTo) {
            minExtremum = exchangeRateExtremum.getMinExtremum(currencyName, dateFrom, dateTo);
            maxExtremum = exchangeRateExtremum.getMaxExtremum(currencyName, dateFrom, dateTo);
        }

        doGet(req, resp);
    }
}