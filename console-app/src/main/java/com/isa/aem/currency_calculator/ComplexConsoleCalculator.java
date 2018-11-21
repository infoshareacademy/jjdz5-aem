package com.isa.aem.currency_calculator;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.MenuInformation;
import com.isa.aem.utils.ConsolePrinter;
import com.isa.aem.utils.ConsoleReader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ComplexConsoleCalculator {

    private ConsolePrinter printer = new ConsolePrinter();
    private ConsoleReader consoleReader = new ConsoleReader();
    private MenuInformation menuInformation = new MenuInformation();
    private CalculatorInputHandler calculatorInputHandler = new CalculatorInputHandler();
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private CurrencyConverter currencyConverter = new CurrencyConverter();
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    private static final List<String> command = Arrays.asList("0", "1", "2");
    private static final String BACK_TO_MENU = "0";
    private static final String SIMPLE_CALCULATOR = "1";
    private static final String CALCULATOR_WITH_DATE = "2";

    private LocalDate date;
    private BigDecimal exchangingRate;
    private Double getAmountGivenByUser;
    private BigDecimal rate;
    private String getFirstNameOfCurrencySelectedByUser;
    private String getSecondNameOfCurrencySelectedByUser;

    public void run() {
        System.out.println(printer.calculatorTittle());
        selectionOptionsInMenuOfCalculator();
    }

    private void selectionOptionsInMenuOfCalculator() {
        String strCommand;
        do {
            strCommand = consoleReader.getString(printer.backToMenu() + printer.simpleCalculator() +
                    printer.calculatorWithDate() + printer.command());
            chcekerIfCommandOfUserIsTrue(strCommand);
        } while (!command.contains(strCommand));
    }

    private void foldingTheSimpleCalculator() {
        System.out.println(currencyRepository.getAvailableCurrencyNames());
        getFirstNameOfCurrencySelectedByUser = calculatorInputHandler
                .getCurrencySelectedByUserOfConsole(consolePrinter.enterFirstCurrency());
        getSecondNameOfCurrencySelectedByUser = calculatorInputHandler
                .getCurrencySelectedByUserOfConsole(consolePrinter.enterSecondCurrency());
        getAmountGivenByUser = calculatorInputHandler.amountGivenByUserService();
        Double rateOfFirstCurrencyNameSelectedByUser = currencyRepository
                .getNewestExchangeRateForChosenCurrencyName(getFirstNameOfCurrencySelectedByUser);
        Double rateOfSecondCurrencyNameSelectedByUser = currencyRepository
                .getNewestExchangeRateForChosenCurrencyName(getSecondNameOfCurrencySelectedByUser);
        exchangingRate = currencyConverter
                .currencyConversionAlgorithm(
                        getAmountGivenByUser, rateOfFirstCurrencyNameSelectedByUser, rateOfSecondCurrencyNameSelectedByUser);
        rate = currencyConverter
                .calculateCourseAlgorithm(rateOfFirstCurrencyNameSelectedByUser, rateOfSecondCurrencyNameSelectedByUser);
        printExchangingRate();
        printRate();
    }

    private void foldingTheCalculatorWithData() {
        System.out.println(currencyRepository.getAvailableCurrencyNames());
        getFirstNameOfCurrencySelectedByUser = calculatorInputHandler
                .getCurrencySelectedByUserOfConsole(
                        consolePrinter.enterFirstCurrency());
        getSecondNameOfCurrencySelectedByUser = calculatorInputHandler
                .getCurrencySelectedByUserOfConsole(
                        consolePrinter.enterSecondCurrency());
        getAmountGivenByUser = calculatorInputHandler
                .amountGivenByUserService();
        checkIfDateExist();
        Double rateOfFirstCurrencyNameSelectedByUserWithDate = currencyRepository
                .getExchangeRateForGivenDate(
                        getFirstNameOfCurrencySelectedByUser,
                        date);
        Double rateOfSecondCurrencyNameSelectedByUserWithDate = currencyRepository
                .getExchangeRateForGivenDate(
                        getSecondNameOfCurrencySelectedByUser,
                        date);
        exchangingRate = currencyConverter
                .currencyConversionAlgorithm(
                        getAmountGivenByUser,
                        rateOfFirstCurrencyNameSelectedByUserWithDate,
                        rateOfSecondCurrencyNameSelectedByUserWithDate);
        rate = currencyConverter
                .calculateCourseAlgorithm(
                        rateOfFirstCurrencyNameSelectedByUserWithDate,
                        rateOfSecondCurrencyNameSelectedByUserWithDate);
        printExchangingRateWithDate();
        printRateWithDate();
    }

    private void printExchangingRate() {
        System.out.println(
                printer.emptySpace() +
                        getAmountGivenByUser + " " +
                        getFirstNameOfCurrencySelectedByUser + " = " +
                        exchangingRate + " " +
                        getSecondNameOfCurrencySelectedByUser + " by date: " +
                        currencyRepository
                                .getNewestDateForChosenCurrencyName(getFirstNameOfCurrencySelectedByUser));
    }

    private void printRate() {
        System.out.println(
                printer.emptySpace() + "Rate " +
                        getFirstNameOfCurrencySelectedByUser + " = " +
                        rate + " by date: " +
                        currencyRepository
                                .getNewestDateForChosenCurrencyName(getFirstNameOfCurrencySelectedByUser));
    }

    private void printExchangingRateWithDate() {
        System.out.println(
                consolePrinter.emptySpace() +
                        getAmountGivenByUser + " " +
                        getFirstNameOfCurrencySelectedByUser + " = " +
                        exchangingRate + " " +
                        getSecondNameOfCurrencySelectedByUser + " by date: " +
                        date);
    }

    private void printRateWithDate() {
        System.out.println(
                consolePrinter.emptySpace() + "Rate " +
                        getFirstNameOfCurrencySelectedByUser + " = " +
                        rate + " by date: " +
                        date);
    }

    private void chcekerIfCommandOfUserIsTrue(String strCommand) {
        if (BACK_TO_MENU.equals(strCommand)) {
            System.out.print(
                    printer.doubleNextLine() +
                            printer.pointLine() +
                            printer.starsLine() +
                            printer.doubleNextLine());
            menuInformation.readMenu();
        } else if (SIMPLE_CALCULATOR.equals(strCommand)) {
            foldingTheSimpleCalculator();
            selectionOptionsInMenuOfCalculator();
        } else if (CALCULATOR_WITH_DATE.equals(strCommand)) {
            foldingTheCalculatorWithData();
            selectionOptionsInMenuOfCalculator();
        } else {
            System.out.println(printer.unknownCommand());
        }
    }

    private void checkIfDateExist() {
        do {
            date = calculatorInputHandler.dataService();
            if ((currencyRepository.existCurrencyWithGivenDate(getFirstNameOfCurrencySelectedByUser, date) == false)) {
                System.out.println(getFirstNameOfCurrencySelectedByUser + " have no date: " + date);
            }
            if ((currencyRepository.existCurrencyWithGivenDate(getSecondNameOfCurrencySelectedByUser, date) == false)) {
                System.out.println(getSecondNameOfCurrencySelectedByUser + " have no date: " + date);
            }
            if (calculatorInputHandler.checkIfCurrencyNameSelectedByUserContainsGivenDate(
                    getFirstNameOfCurrencySelectedByUser, getSecondNameOfCurrencySelectedByUser, this.date)) {
                break;
            } else {
                System.out.println(consolePrinter.rateDoesNotExistForThisDate());
                checkIfDateExist();
            }
        } while (!(calculatorInputHandler.checkIfCurrencyNameSelectedByUserContainsGivenDate(
                getFirstNameOfCurrencySelectedByUser, getSecondNameOfCurrencySelectedByUser, date)));
    }


}