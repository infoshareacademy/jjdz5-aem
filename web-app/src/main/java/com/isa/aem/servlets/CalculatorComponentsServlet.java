package com.isa.aem.servlets;

import com.isa.aem.AppProperties;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.api.CurrencyApiTranslator;
import com.isa.aem.api.OperationsOnDateRanges;
import com.isa.aem.currency_calculator.CurrencyListTableCreator;
import com.isa.aem.currency_calculator.Score;
import com.isa.aem.currency_calculator.ScoreResult;
import com.isa.aem.dao.ActivityDao;
import com.isa.aem.dao.UserDao;
import com.isa.aem.data_loaders.CurrencyNameCountryFlagsLoader;
import com.isa.aem.data_loaders.PropertiesLoader;
import com.isa.aem.freemarker.TemplateProvider;
import com.isa.aem.informationcollect.RecordCreator;
import com.isa.aem.model.Activity;
import com.isa.aem.model.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;


public class CalculatorComponentsServlet extends HttpServlet {

    protected Score score = new Score();
    protected ScoreResult scoreResult = new ScoreResult();
    CurrencyRepository currencyRepository = new CurrencyRepository();
    CurrencyApiTranslator currencyApiTranslator = new CurrencyApiTranslator();
    OperationsOnDateRanges operationsOnDateRanges = new OperationsOnDateRanges();
    protected String defaultCurrencyNameHave;
    protected String defaultCurrencyNameWant;
    protected static final Double DEFAULT_AMOUNT = 100.00;
    protected static final String AMOUNT_PARAMETER = "amount";
    protected static final String HAVE_PARAMETER = "have";
    protected static final String WANT_PARAMETER = "want";
    protected static final String DATE_PARAMETER = "date";
    protected static final String CURRENCY_TABLE_PARAMETER = "currency_table";
    protected static final String USER_NAME_PARAMETER = "userName";
    String currencyInTable;
    protected static final String ACTION_BUTTON = "action";
    protected static final String ACTION_BUTTON_CALCULATOR = "calculator";
    protected static final String ACTION_BUTTON_RANGE_CURRENCY = "rangeCurrency";
    protected CurrencyListTableCreator currencyListTableCreator = new CurrencyListTableCreator();

    @Inject
    public TemplateProvider templateProvider;
    public CurrencyNameCountryFlagsLoader currencyNameCountryFlagsLoader;

    @Inject
    private RecordCreator recordCreator;

    @Inject
    private UserDao userDao;

    @Inject
    private ActivityDao activityDao;

    @Override
    public void init() throws ServletException {
        currencyApiTranslator.importCurrencyFromApiToTheStaticList(operationsOnDateRanges.MIN_DATE_NBP_API_ONE_YEAR);
        currencyRepository.getCurrencies();
        currencyNameCountryFlagsLoader = new CurrencyNameCountryFlagsLoader();

        AppProperties appProperties = PropertiesLoader.loadProperties();
        defaultCurrencyNameHave = appProperties.getCurrencyNamePln();
        defaultCurrencyNameWant = appProperties.getCurrencyNameEur();
    }

    public void setDefaultValueOfCalculator() {

        if (score.getAmount() == null) {
            score.setAmount(DEFAULT_AMOUNT);
        }

        if (score.getCurrencyHave() == null) {
            score.setCurrencyHave(defaultCurrencyNameHave);
        }

        if (score.getCurrencyWant() == null) {
            score.setCurrencyWant(defaultCurrencyNameWant);
        }

        if (score.getDateExchange() == null) {
            LocalDate dateHaveMax = currencyRepository.getNewestDateForChosenCurrencyName(defaultCurrencyNameHave);
            score.setDateExchange(dateHaveMax);
        }

        if (score.getMaxDate() == null) {
            score.setMaxDate(currencyRepository.getNewestDateForChosenCurrencyName(defaultCurrencyNameHave));
        }

        if (score.getMinDate() == null) {
            score.setMinDate(currencyRepository.getOldestDateForChosenCurrencyName(defaultCurrencyNameHave));
        }

        if (currencyInTable == null) {
            currencyInTable = defaultCurrencyNameHave;
            currencyListTableCreator.setTableListCurrencyObject(currencyListTableCreator.availableCurrencyObjects(currencyInTable));
        }
    }

    public void calculateExchangeRate(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter(ACTION_BUTTON);

        if (ACTION_BUTTON_CALCULATOR.equals(action)) {
            String reqAmount = req.getParameter(AMOUNT_PARAMETER);
            String reqHave = req.getParameter(HAVE_PARAMETER);
            String reqWant = req.getParameter(WANT_PARAMETER);
            String reqDate = req.getParameter(DATE_PARAMETER);
            Double calculatorAmount = Double.parseDouble(reqAmount);
            String[] calculatorCurrencyHaveTable = reqHave.split(" - ");
            String[] calculatorCurrencyWantTable = reqWant.split(" - ");
            String haveCurrency = calculatorCurrencyHaveTable[0];
            LocalDate date = score.scoreDate(reqDate, haveCurrency, calculatorCurrencyWantTable[0]);
            score = scoreResult.getScoreResult(haveCurrency, calculatorCurrencyWantTable[0], date, calculatorAmount);
            score.setMaxDate(currencyRepository.getNewestDateForChosenCurrencyName(haveCurrency));
            score.setMinDate(currencyRepository.getOldestDateForChosenCurrencyName(haveCurrency));

            checkLoginAndTrackCalculator(req,
                    calculatorAmount,
                    reqHave,
                    reqWant);

        } else if (ACTION_BUTTON_RANGE_CURRENCY.equals(action)) {
            CurrencyListTableCreator currencyListTableCreator1 = new CurrencyListTableCreator();
            String currencyInTableNames = req.getParameter(CURRENCY_TABLE_PARAMETER);
            String[] currencyInTableName = currencyInTableNames.split(" - ");
            currencyInTable = currencyInTableName[0];
            currencyListTableCreator.setTableListCurrencyObject(currencyListTableCreator1.availableCurrencyObjects(currencyInTable));

            checkLoginAndTrackRate(req);
        }
    }

    private void userLoginTrackingRate(HttpServletRequest req) {

        Long id = recordCreator.findIdFromDataBaseByEmail(req);

        User user = userDao.findById(id);

        Activity exchangeRateActivity = recordCreator.createExchangeRateActivity(
                currencyInTable);

        List<Activity> activities = user.getActivities();
        activities.add(exchangeRateActivity);
    }

    private void userLoginTrackingCalculator(HttpServletRequest req,
                                             Double calculatorAmount,
                                             String reqHave,
                                             String reqWant) {

        Long id = recordCreator.findIdFromDataBaseByEmail(req);
        LocalDate dateOfExchange = LocalDate.parse(req.getParameter(DATE_PARAMETER));

        User user = userDao.findById(id);

        Activity calculatorActivity = recordCreator.createCalculatorActivity(
                calculatorAmount,
                reqHave,
                reqWant,
                dateOfExchange);

        List<Activity> activities = user.getActivities();
        activities.add(calculatorActivity);
    }

    private void userLogoutTrackingRate(HttpServletRequest req) {

        Activity exchangeRateActivity = recordCreator.createExchangeRateActivity(
                currencyInTable);

        activityDao.save(exchangeRateActivity);
    }

    private void userLogoutTrackingCalculator(HttpServletRequest req,
                                             Double calculatorAmount,
                                             String reqHave,
                                             String reqWant) {

        LocalDate dateOfExchange = LocalDate.parse(req.getParameter(DATE_PARAMETER));

        Activity calculatorActivity = recordCreator.createCalculatorActivity(
                calculatorAmount,
                reqHave,
                reqWant,
                dateOfExchange);

        activityDao.save(calculatorActivity);
    }

    private void checkLoginAndTrackCalculator(HttpServletRequest req,
                                              Double calculatorAmount,
                                              String reqHave,
                                              String reqWant) {

        Object logged = req.getSession().getAttribute("userName");
        if (logged == null) {
            userLogoutTrackingCalculator(req, calculatorAmount, reqHave, reqWant);
        } else {
            userLoginTrackingCalculator(req, calculatorAmount, reqHave, reqWant);
        }
    }

    private void checkLoginAndTrackRate(HttpServletRequest req) {

        Object logged = req.getSession().getAttribute("userName");
        if (logged == null) {
            userLogoutTrackingRate(req);
        }else {
            userLoginTrackingRate(req);
        }
    }
}