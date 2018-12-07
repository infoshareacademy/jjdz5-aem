package com.isa.aem.servlets;

import com.isa.aem.AppProperties;
import com.isa.aem.CurrencyRepository;
import com.isa.aem.api.CurrencyApiTranslator;
import com.isa.aem.currency_calculator.CreateAListOfAvailableCurrencies;
import com.isa.aem.currency_calculator.Score;
import com.isa.aem.currency_calculator.ScoreResult;
import com.isa.aem.data_loaders.CurrencyNameCountryFlagsLoader;
import com.isa.aem.data_loaders.FileContentReader;
import com.isa.aem.data_loaders.PropertiesLoader;
import com.isa.aem.freemarker.TemplateProvider;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;


public class CalculatorComponentsServlet extends HttpServlet {

    protected Score score = new Score();
    protected ScoreResult scoreResult = new ScoreResult();
    CurrencyRepository currencyRepository = new CurrencyRepository();
    FileContentReader fileContentReader = new FileContentReader();
    CurrencyApiTranslator currencyApiTranslator = new CurrencyApiTranslator();
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
    protected CreateAListOfAvailableCurrencies createAListOfAvailableCurrencies = new CreateAListOfAvailableCurrencies();

    @Inject
    public TemplateProvider templateProvider;
    public CurrencyNameCountryFlagsLoader currencyNameCountryFlagsLoader;

    @Override
    public void init() throws ServletException {
        fileContentReader = new FileContentReader();
        currencyApiTranslator.importCurrencyFromApiToTheStaticList();
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
            createAListOfAvailableCurrencies.setTableListCurrencyObject(createAListOfAvailableCurrencies.availableCurrencyObjects(currencyInTable));
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

        } else if (ACTION_BUTTON_RANGE_CURRENCY.equals(action)) {
            CreateAListOfAvailableCurrencies createAListOfAvailableCurrencies1 = new CreateAListOfAvailableCurrencies();
            String currencyInTableNames = req.getParameter(CURRENCY_TABLE_PARAMETER);
            String[] currencyInTableName = currencyInTableNames.split(" - ");
            currencyInTable = currencyInTableName[0];
            createAListOfAvailableCurrencies.setTableListCurrencyObject(createAListOfAvailableCurrencies1.availableCurrencyObjects(currencyInTable));
        }
    }
}