package com.isa.aem.calc;

import com.isa.aem.MenuInformation;
import com.isa.aem.tools.ConsoleReader;
import com.isa.aem.tools.ListAvailableCurrency;
import com.isa.aem.tools.MyPrinter;

import java.util.Arrays;
import java.util.List;

public class Calculator {
    private Algorithm algorithm = new Algorithm();
    private ListAvailableCurrency availableCurrency = new ListAvailableCurrency();
    private MyPrinter printer = new MyPrinter();
    private ConsoleReader consoleReader = new ConsoleReader();
    private MenuInformation menuInformation = new MenuInformation();

    private static final List<String> command = Arrays.asList("0", "1", "2");
    private static final String BACK_TO_MENU = "0";
    private static final String SIMPLE_CALCULATOR = "1";
    private static final String CALCULATOR_WITH_DATE = "2";


    public void run() {
        System.out.println(printer.calculatorTittle());
        smallMenu();
    }

    private void smallMenu() {
        String strCommand;
        do {
            strCommand = consoleReader.getString(printer.backToMenu() + printer.simpleCalculator() +
                    printer.calculatorWithDate() +  printer.command());
            if (strCommand.equals(BACK_TO_MENU)){
                System.out.print(printer.dubleNextLine() + printer.pointLine() + printer.starsLine() +
                        printer.dubleNextLine());
                menuInformation.readMenu();
            }
            if (strCommand.equals(SIMPLE_CALCULATOR)){
                foldingTheCalculator();
            }
        }while (!command.contains(strCommand));
    }

    private void foldingTheCalculator() {
        availableCurrency.print();
        algorithm.loadFromKeyboard();
        printEqual();
        printCurse();
    }

    private void printEqual() {
        System.out.println(printer.emptySpace() + algorithm.getAmount() + " " + algorithm.getFirstCurrency() + " = " +
                algorithm.result() + " " + algorithm.getSecondCurrency());
    }

    private void printCurse() {
        System.out.println(printer.emptySpace() + "Course " + algorithm.getFirstCurrency() + " = " + algorithm.course());
    }


}