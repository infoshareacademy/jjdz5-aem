package com.isa.aem.calc;

import com.isa.aem.MenuInformation;
import com.isa.aem.tools.ConsoleReader;
import com.isa.aem.tools.ListAvailableCurrency;
import com.isa.aem.tools.MyPrinter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
@ApplicationScoped
public class CalculatorOfConsole {
    private Algorithm algorithm = new Algorithm();
    private ListAvailableCurrency availableCurrency = new ListAvailableCurrency();
    private MyPrinter printer = new MyPrinter();
    private ConsoleReader consoleReader = new ConsoleReader();
    private MenuInformation menuInformation = new MenuInformation();

    @Inject
    Calculator calculator;
    @Inject
    DateService dateService;
    @Inject
    MyPrinter myPrinter;

    private static final List<String> command = Arrays.asList("0", "1", "2");
    private static final String BACK_TO_MENU = "0";
    private static final String SIMPLE_CALCULATOR = "1";
    private static final String CALCULATOR_WITH_DATE = "2";
    private LocalDate date;

    private String firstCurrencySelectedByUser;
    private String secondCurrencySelectedByUser;
    private Double amountGivenByUser;


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
        availableCurrency.print();
        amountGivenByUser = calculator.amountGivenByUserService();
        firstCurrencySelectedByUser = calculator.firstCurrencySelectedByUserService();
        secondCurrencySelectedByUser = calculator.secondCurrencySelectedByUserService();
        printEqual();
        printCurse();
    }

    private void foldingTheCalculatorWithData() {
        availableCurrency.print();
        amountGivenByUser = calculator.amountGivenByUserService();
        firstCurrencySelectedByUser = calculator.firstCurrencySelectedByUserService();
        secondCurrencySelectedByUser = calculator.secondCurrencySelectedByUserService();
        checkIfDateExist();
    }

    private void printEqual() {
        System.out.println(printer.emptySpace() + amountGivenByUser + " " + firstCurrencySelectedByUser + " = " +
                calculator.resultOfCurrencyConversionAlgorithm(amountGivenByUser) + " " + secondCurrencySelectedByUser);
    }

    private void printCurse() {
        System.out.println(printer.emptySpace() + "Course " + firstCurrencySelectedByUser + " = " + calculator.resultOfCalculateCourseAlgorithm());
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

    protected void dataService() {
        date = dateService.dataService();
    }

    protected void checkIfDateExist() {
        do {
            dataService();
            if (calculator.checkIfChoiceByUserContainsGivenDate(date)){
                printEqualWithDate();
                printCurseWithDate();
            }
            else {
                System.out.println(myPrinter.unexistDate());
                checkIfDateExist();
            }
        } while (!(calculator.checkIfChoiceByUserContainsGivenDate(date)));
    }

    private void printEqualWithDate() {
        System.out.println(myPrinter.emptySpace() + amountGivenByUser + " " + firstCurrencySelectedByUser + " = " +
                calculator.resultOfCurrencyConversionAlgorithm(amountGivenByUser) + " " + secondCurrencySelectedByUser);
    }

    private void printCurseWithDate() {
        System.out.println(myPrinter.emptySpace() + "Course " + firstCurrencySelectedByUser + " = " + calculator.resultOfCalculateCourseAlgorithm());
    }
}