package com.isa.aem.servlets;

import com.isa.aem.AppProperties;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.api.CurrencyApiTranslator;
import com.isa.aem.api.OperationsOnDateRanges;
import com.isa.aem.data_loaders.CurrencyNameCountryFlagsLoader;
import com.isa.aem.dao.UserDao;
import com.isa.aem.data_loaders.PropertiesLoader;
import com.isa.aem.freemarker.TemplateName;
import com.isa.aem.freemarker.TemplateProvider;
import com.isa.aem.extremum.ExtremumObject;
import com.isa.aem.informationcollect.RecordCreator;
import com.isa.aem.model.Activity;
import com.isa.aem.extremum.ExtremumObject;
import com.isa.aem.model.User;
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

@WebServlet("/extremum")
public class ExtremumServlet extends HttpServlet {

    private CurrencyRepository currencyRepository = new CurrencyRepository();
    private ExchangeRateExtremum exchangeRateExtremum = new ExchangeRateExtremum();
    private ExtremumObject extremumObject = new ExtremumObject();
    private DataValidator dataValidator = new DataValidator();
    private Boolean dateFromAfterDateTo = Boolean.FALSE;
    private String defaultCurrencyName;
    private CurrencyApiTranslator currencyApiTranslator = new CurrencyApiTranslator();
    private CurrencyNameCountryFlagsLoader currencyNameCountryFlagsLoader = new CurrencyNameCountryFlagsLoader();
    private OperationsOnDateRanges operationsOnDateRanges = new OperationsOnDateRanges();
    private String radioChecked = "globalRadioChecked";
    private static final String CURRENCY_NAME_PARAMETER = "currencyName";
    private static final String DATE_FROM_PARAMETER = "dateFrom";
    private static final String DATE_TO_PARAMETER = "dateTo";
    private static final String USER_NAME_PARAMETER = "userName";
    private static final String EXTREMUM_RADIOS_PARAMETER = "extremumRadios";

    private static final String GLOBAL_RADIO_CHECK = "globalRadioChecked";
    private static final String LOCAL_RADIO_CHECK = "localRadioChecked";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private RecordCreator recordCreator;

    @Inject
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        currencyApiTranslator.importCurrencyFromApiToTheStaticList(operationsOnDateRanges.MIN_DATE_NBP_API);
        CurrencyRepository.setCurrencies(currencyApiTranslator.dateTableSingle);
        currencyNameCountryFlagsLoader = new CurrencyNameCountryFlagsLoader();
        AppProperties appProperties = PropertiesLoader.loadProperties();
        defaultCurrencyName = appProperties.getCurrencyNameEur();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template template = templateProvider
                .getTemplate(getServletContext(), TemplateName.EXTREMUM.getName());

        setDefaultCurrency();
        setDefaultDates();
        calculateExtremum();

        Object userName = req.getSession().getAttribute(USER_NAME_PARAMETER);

        Map<String, Object> model = new HashMap<>();
        model.put("currencyRepository", currencyRepository);
        model.put("extremumObject", extremumObject);
        model.put("dateFromAfterDateTo", dateFromAfterDateTo);
        model.put("radioChecked", radioChecked);
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
        extremumObject.setCurrencyName(currencyNameTab[0]);
        extremumObject.setDateFrom(LocalDate.parse(req.getParameter(DATE_FROM_PARAMETER)));
        extremumObject.setDateTo(LocalDate.parse(req.getParameter(DATE_TO_PARAMETER)));

        String[] radioStates = req.getParameterValues(EXTREMUM_RADIOS_PARAMETER);
        radioChecked = radioStates[0];

        calculateExtremum();

        doGet(req, resp);

        LocalDate dateFrom = LocalDate.parse(req.getParameter(DATE_FROM_PARAMETER));
        LocalDate dateTo = LocalDate.parse(req.getParameter(DATE_TO_PARAMETER));

        switchLocalGlobalExtremumAndTrack(
                radioChecked,
                req,
                dateFrom,
                dateTo,
                CURRENCY_NAME_PARAMETER);
    }

    private void setDefaultCurrency() {
        if (extremumObject.getCurrencyName() == null) {
            if (currencyRepository.containsCurrencyNameInCurrencyList(defaultCurrencyName)) {
                extremumObject.setCurrencyName(defaultCurrencyName);
            } else {
                extremumObject.setCurrencyName(currencyRepository.getFirstAvailableCurrencyName());
            }
        }
    }

    private void setDefaultDates() {
        if (extremumObject.getDateFrom() == null) {
            extremumObject.setDateFrom(currencyRepository.getFirstDateFromRepository());
        }

        if (extremumObject.getDateTo() == null) {
            extremumObject.setDateTo(currencyRepository.getLastDateFromRepository());
        }
    }

    private void calculateExtremum() {
        dateFromAfterDateTo = dataValidator.isDateFromAfterDateTo(extremumObject.getDateFrom(), extremumObject.getDateTo());
        if (!dateFromAfterDateTo) {
            extremumObject.setMinExtremum(exchangeRateExtremum.getMinExtremum(extremumObject.getCurrencyName(), extremumObject.getDateFrom(), extremumObject.getDateTo()));
            extremumObject.setMaxExtremum(exchangeRateExtremum.getMaxExtremum(extremumObject.getCurrencyName(), extremumObject.getDateFrom(), extremumObject.getDateTo()));
        }
    }

    private void switchLocalGlobalExtremumAndTrack(String s,
                                                   HttpServletRequest req,
                                                   LocalDate dateFrom,
                                                   LocalDate dateTo,
                                                   String currencyName) {

        if (isLocalExtremum(s)){
            trackingLocalExtremum(
                    req,
                    dateFrom,
                    dateTo,
                    currencyName);
        } else if (isGlobalExtremum(s)) {
            trackingGlobalExtremum(
                    req,
                    dateFrom,
                    currencyName);
        }
    }

    private void trackingLocalExtremum(HttpServletRequest req,
                                       LocalDate dateFrom,
                                       LocalDate dateTo,
                                       String currencyName) {

        Long id = recordCreator.findIdFromDataBaseByEmail(req);

        User user = userDao.findById(id);

        Activity localExtremeumActivity = recordCreator
                .createLocalExtremeumActivity(
                        dateFrom,
                        dateTo,
                        currencyName);

        List<Activity> activities = user.getActivities();
        activities.add(localExtremeumActivity);
        user.setActivities(activities);
        userDao.update(user);
    }

    private void trackingGlobalExtremum(HttpServletRequest req,
                                       LocalDate dateFrom,
                                       String currencyName) {

        Long id = recordCreator.findIdFromDataBaseByEmail(req);

        User user = userDao.findById(id);

        Activity globalExtremeumActivity = recordCreator
                .createGlobalExtremeumActivity(
                        dateFrom,
                        currencyName);

        List<Activity> activities = user.getActivities();
        activities.add(globalExtremeumActivity);
        user.setActivities(activities);
        userDao.update(user);
    }

    private Boolean isLocalExtremum(String s) {
        return s.equals(LOCAL_RADIO_CHECK);
    }

    private Boolean isGlobalExtremum(String s) {
        return s.equals(GLOBAL_RADIO_CHECK);
    }
}