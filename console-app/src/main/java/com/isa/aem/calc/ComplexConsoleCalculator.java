package com.isa.aem.calc;

import com.isa.aem.CurrencyRepository;
import com.isa.aem.MenuInformation;
import com.isa.aem.tools.ConsoleReader;
import com.isa.aem.tools.MyPrinter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ComplexConsoleCalculator {

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
    private Double getAmountGivenByUser;
    private BigDecimal rate;
    private String getFirstNameOfCurrencySelectedByUser;
    private String getSecondNameOfCurrencySelectedByUser;

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
        getFirstNameOfCurrencySelectedByUser = dataTransducerIntroducedByConsole
                .getCurrencySelectedByUserOfConsole(myPrinter.enterFirstCurrency());
        getSecondNameOfCurrencySelectedByUser = dataTransducerIntroducedByConsole
                .getCurrencySelectedByUserOfConsole(myPrinter.enterSecondCurrency());
        getAmountGivenByUser = dataTransducerIntroducedByConsole.amountGivenByUserService();
        Double rateOfFirstCurrencyNameSelectedByUser = currencyRepository
                .getMostCurrentExchangedRateOFSelectedCurrencyFromTheFile(getFirstNameOfCurrencySelectedByUser);
        Double rateOfSecondCurrencyNameSelectedByUser = currencyRepository
                .getMostCurrentExchangedRateOFSelectedCurrencyFromTheFile(getSecondNameOfCurrencySelectedByUser);
        exchangingRate = algorithmCurrencyConversion
                .currencyConversionAlgorithm(
                        getAmountGivenByUser, rateOfFirstCurrencyNameSelectedByUser, rateOfSecondCurrencyNameSelectedByUser);
        rate = algorithmCurrencyConversion
                .calculateCourseAlgorithm(rateOfFirstCurrencyNameSelectedByUser, rateOfSecondCurrencyNameSelectedByUser);
        printEqual();
        printCurse();
    }

    private void foldingTheCalculatorWithData() {
        System.out.println(currencyRepository.listAvailableCurrency());
        getFirstNameOfCurrencySelectedByUser = dataTransducerIntroducedByConsole
                .getCurrencySelectedByUserOfConsole(
                        myPrinter.enterFirstCurrency());
        getSecondNameOfCurrencySelectedByUser = dataTransducerIntroducedByConsole
                .getCurrencySelectedByUserOfConsole(
                        myPrinter.enterSecondCurrency());
        getAmountGivenByUser = dataTransducerIntroducedByConsole
                .amountGivenByUserService();
        checkIfDateExist();
        Double rateOfFirstCurrencyNameSelectedByUserWithDate = currencyRepository
                .getRateOfGivenDate(
                        getFirstNameOfCurrencySelectedByUser,
                        date);
        Double rateOfSecondCurrencyNameSelectedByUserWithDate = currencyRepository
                .getRateOfGivenDate(
                        getSecondNameOfCurrencySelectedByUser,
                        date);
        exchangingRate = algorithmCurrencyConversion
                .currencyConversionAlgorithm(
                        getAmountGivenByUser,
                        rateOfFirstCurrencyNameSelectedByUserWithDate,
                        rateOfSecondCurrencyNameSelectedByUserWithDate);
        rate = algorithmCurrencyConversion
                .calculateCourseAlgorithm(
                        rateOfFirstCurrencyNameSelectedByUserWithDate,
                        rateOfSecondCurrencyNameSelectedByUserWithDate);
        printEqualWithDate();
        printCurseWithDate();
    }

    private void printEqual() {
        System.out.println(
                printer.emptySpace() +
                        getAmountGivenByUser + " " +
                        getFirstNameOfCurrencySelectedByUser + " = " +
                exchangingRate + " " +
                        getSecondNameOfCurrencySelectedByUser + " by date: " +
                currencyRepository
                        .getMostCurrentDateOFSelectedCurrencyFromTheFile(getFirstNameOfCurrencySelectedByUser));
    }

    private void printCurse() {
        System.out.println(
                printer.emptySpace() + "Course " +
                        getFirstNameOfCurrencySelectedByUser + " = " +
                rate + " by date: " +
                currencyRepository
                        .getMostCurrentDateOFSelectedCurrencyFromTheFile(getFirstNameOfCurrencySelectedByUser));
    }

    private void printEqualWithDate() {
        System.out.println(
                myPrinter.emptySpace() +
                        getAmountGivenByUser + " " +
                        getFirstNameOfCurrencySelectedByUser + " = " +
                exchangingRate + " " +
                        getSecondNameOfCurrencySelectedByUser + " by date: " +
                currencyRepository
                        .getMostCurrentDateOFSelectedCurrencyFromTheFile(getFirstNameOfCurrencySelectedByUser));
    }

    private void printCurseWithDate() {
        System.out.println(
                myPrinter.emptySpace() + "Course " +
                        getFirstNameOfCurrencySelectedByUser + " = " +
                rate + " by date: " +
                currencyRepository
                        .getMostCurrentDateOFSelectedCurrencyFromTheFile(getFirstNameOfCurrencySelectedByUser));
    }

    private void menuOptions(String strCommand) {
        if (strCommand.equals(BACK_TO_MENU)) {
            System.out.print(
                    printer.dubleNextLine() +
                    printer.pointLine() +
                    printer.starsLine() +
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
            if ((currencyRepository.checkIfExistCurrencyWithGivenDate(getFirstNameOfCurrencySelectedByUser, date) == false)){
                System.out.println(getFirstNameOfCurrencySelectedByUser + " have no date: " + date);
            }
            if ((currencyRepository.checkIfExistCurrencyWithGivenDate(getSecondNameOfCurrencySelectedByUser,date) == false)) {
                System.out.println(getSecondNameOfCurrencySelectedByUser + " have no date: " + date);
            }
            if (dataTransducerIntroducedByConsole.checkIfCurrencyNameSelectedByUserContainsGivenDate(
                    getFirstNameOfCurrencySelectedByUser, getSecondNameOfCurrencySelectedByUser, this.date)){
                break;
            }
            else {
                System.out.println(myPrinter.unexistDate());
                checkIfDateExist();
            }
        } while (!(dataTransducerIntroducedByConsole.checkIfCurrencyNameSelectedByUserContainsGivenDate(
                getFirstNameOfCurrencySelectedByUser, getSecondNameOfCurrencySelectedByUser, date)));
    }


}