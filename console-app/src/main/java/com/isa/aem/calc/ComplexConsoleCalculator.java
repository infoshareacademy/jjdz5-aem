package com.isa.aem.calc;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.MenuInformation;
import com.isa.aem.tools.ConsoleReader;
import com.isa.aem.tools.ListAvailableCurrency;
import com.isa.aem.tools.MyPrinter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ComplexConsoleCalculator {

    private ListAvailableCurrency availableCurrency = new ListAvailableCurrency();
    private MyPrinter printer = new MyPrinter();
    private ConsoleReader consoleReader = new ConsoleReader();
    private MenuInformation menuInformation = new MenuInformation();
    private DataTransducerIntroducedByConsole dataTransducerIntroducedByConsole = new DataTransducerIntroducedByConsole();
    private MyPrinter myPrinter = new MyPrinter();
    private AlgorithmCurrencyConversion algorithmCurrencyConversion = new AlgorithmCurrencyConversion();
    private CurrencyRepository currencyRepository = new CurrencyRepository();

    private static final List<String> command = Arrays.asList("0", "1", "2");
    private static final String BACK_TO_MENU = "0";
    private static final String SIMPLE_CALCULATOR = "1";
    private static final String CALCULATOR_WITH_DATE = "2";

    private LocalDate date;
    private BigDecimal exchangingRate;
    private Double amountGivenByUser;
    private BigDecimal rate;
    private String firstNameOfCurrencySelectedByUser;
    private String secondNameOfCurrencySelectedByUser;

    public void run() {
        System.out.println(printer.calculatorTittle());
        smallMenu();
    }

    private void smallMenu() {
        String strCommand;
        do {
            strCommand = consoleReader.getString(printer.backToMenu() + printer.simpleCalculator() +
                    printer.calculatorWithDate() +  printer.command());
            menuOptions(strCommand);
        }while (!command.contains(strCommand));
    }

    private void foldingTheSimpleCalculator() {
        System.out.println(currencyRepository.listAvailableCurrency());
        Double rateOfFirstSelectedCurrency = dataTransducerIntroducedByConsole.currencySelectedByUserService(myPrinter.enterFirstCurrency());
        firstNameOfCurrencySelectedByUser = dataTransducerIntroducedByConsole.getCurrencyNameSelectedByUser();
        amountGivenByUser = dataTransducerIntroducedByConsole.amountGivenByUserService();
        Double rateOfSecondSelectedCurrency = dataTransducerIntroducedByConsole.currencySelectedByUserService(myPrinter.enterSecondCurrency());
        secondNameOfCurrencySelectedByUser = dataTransducerIntroducedByConsole.getCurrencyNameSelectedByUser();
        exchangingRate = algorithmCurrencyConversion
                .currencyConversionAlgorithm(
                        amountGivenByUser, rateOfFirstSelectedCurrency, rateOfSecondSelectedCurrency);
        rate = algorithmCurrencyConversion
                .calculateCourseAlgorithm(rateOfFirstSelectedCurrency, rateOfSecondSelectedCurrency);
        printEqual();
        printCurse();
    }

    private void foldingTheCalculatorWithData() {
        System.out.println(currencyRepository.listAvailableCurrency());
        Double rateOfFirstSelectedCurrency = dataTransducerIntroducedByConsole.currencySelectedByUserService(myPrinter.enterFirstCurrency());
        firstNameOfCurrencySelectedByUser = dataTransducerIntroducedByConsole.getCurrencyNameSelectedByUser();
        amountGivenByUser = dataTransducerIntroducedByConsole.amountGivenByUserService();
        Double rateOfSecondSelectedCurrency = dataTransducerIntroducedByConsole.currencySelectedByUserService(myPrinter.enterSecondCurrency());
        secondNameOfCurrencySelectedByUser = dataTransducerIntroducedByConsole.getCurrencyNameSelectedByUser();
        exchangingRate = algorithmCurrencyConversion
                .currencyConversionAlgorithm(
                        amountGivenByUser, rateOfFirstSelectedCurrency, rateOfSecondSelectedCurrency);
        rate = algorithmCurrencyConversion
                .calculateCourseAlgorithm(rateOfFirstSelectedCurrency, rateOfSecondSelectedCurrency);
        checkIfDateExist();
    }

    private void printEqual() {
        System.out.println(printer.emptySpace() + amountGivenByUser + " " + firstNameOfCurrencySelectedByUser + " = " +
                  exchangingRate + " " +secondNameOfCurrencySelectedByUser + " by date: " +
                currencyRepository.getMostCurrentDateOFSelectedCurrencyFromTheFile(firstNameOfCurrencySelectedByUser));
    }

    private void printCurse() {
        System.out.println(printer.emptySpace() + "Course " + firstNameOfCurrencySelectedByUser + " = " + rate + " by date: " +
                currencyRepository.getMostCurrentDateOFSelectedCurrencyFromTheFile(firstNameOfCurrencySelectedByUser));
    }

    private void menuOptions(String strCommand) {
        if (strCommand.equals(BACK_TO_MENU)) {
            System.out.print(printer.dubleNextLine() + printer.pointLine() + printer.starsLine() +
                    printer.dubleNextLine());
            menuInformation.readMenu();
        }
        else if (strCommand.equals(SIMPLE_CALCULATOR)){
                foldingTheSimpleCalculator();
                smallMenu();
        }
        else if (strCommand.equals(CALCULATOR_WITH_DATE)) {
            foldingTheCalculatorWithData();
            smallMenu();
        }
        else {
            System.out.println(printer.unknowCommand());
        }
    }

    private void checkIfDateExist() {
        do {
            date = dataTransducerIntroducedByConsole.dataService();
            if ((currencyRepository.checkIfExistCurrencyWithGivenDate(firstNameOfCurrencySelectedByUser, date) == false)){
                System.out.println(firstNameOfCurrencySelectedByUser + " have no date: " + date);
            }
            if ((currencyRepository.checkIfExistCurrencyWithGivenDate(secondNameOfCurrencySelectedByUser,date) == false)) {
                System.out.println(secondNameOfCurrencySelectedByUser + " have no date: " + date);
            }
            if (dataTransducerIntroducedByConsole.checkIfCurrencyNameSelectedByUserContainsGivenDate(
                    firstNameOfCurrencySelectedByUser, secondNameOfCurrencySelectedByUser, this.date)){
                printEqualWithDate();
                printCurseWithDate();
            }
            else {
                System.out.println(myPrinter.unexistDate());
                checkIfDateExist();
            }
        } while (!(dataTransducerIntroducedByConsole.checkIfCurrencyNameSelectedByUserContainsGivenDate(
                firstNameOfCurrencySelectedByUser, secondNameOfCurrencySelectedByUser, date)));
    }

    private void printEqualWithDate() {
        System.out.println(myPrinter.emptySpace() + amountGivenByUser + " " + firstNameOfCurrencySelectedByUser + " = " +
                exchangingRate + " " + secondNameOfCurrencySelectedByUser + " by date: " +
                currencyRepository.getMostCurrentDateOFSelectedCurrencyFromTheFile(firstNameOfCurrencySelectedByUser));
    }

    private void printCurseWithDate() {
        System.out.println(myPrinter.emptySpace() + "Course " + firstNameOfCurrencySelectedByUser + " = " + rate + " by date: " +
                currencyRepository.getMostCurrentDateOFSelectedCurrencyFromTheFile(firstNameOfCurrencySelectedByUser));
    }
}