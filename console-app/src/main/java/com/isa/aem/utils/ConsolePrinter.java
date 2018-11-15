package com.isa.aem.utils;

import javax.enterprise.inject.Default;

@Default
public class ConsolePrinter {

    public void printLn(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }

    public void printLocalExtremumWelcome(){
        System.out.println("\n" +
                "     **********************************************\n" +
                "     *               Local Extremum               *\n" +
                "     **********************************************\n" );
    }

    public String globalExtremeTittle() {
        return
                "     *********************************************\n" +
                "     *              Global Extremum               *\n" +
                "     *********************************************\n";
    }

    public String calculatorTittle() {
        return
                "     *********************************************\n" +
                "     *             Currency Calculator           *\n" +
                "     *********************************************\n";
    }

    public String simpleCalculator (){
        return
                "                                || 1: simple calculator\n";
    }

    public String calculatorWithDate (){
        return
                "                                || 2: calculator with date\n";
    }

    public String backToMenu (){
        return
                "                                || 0: back to menu\n";
    }

    public String nextLine() {
        return "\n";
    }

    public String doubleNextLine() {
        return "\n";
    }

    public String error() {
        return
                "     *****************   ERROR   *****************\n";
    }

    public String pointLine() {
        return
                ".................................................\n";
    }

    public String onlyPointLine() {
        return
                ".................................................";
    }

    public String starsLine() {
        return
                "*************************************************\n";
    }

    public String backToCurrencySelection() {
        return
                "                                || 1: back to currency selection \n\n";
    }

    public String currencyOrCommandDoesNotExist() {
        return
                "     CURRENCY OR COMMAND DOES NOT EXIST.\n";
    }

    public String currencyDoesNotExist() {
        return
                "     CURRENCY DOES NOT EXIST.\n";
    }

    public String numberDoesNotExist() {
        return
                "     THIS IS NOT NUMBER\n";
    }

    public String extreme() {
        return
                "     Extreme: ";
    }

    public String listAvailableCurrencies(){
        return "     List available currencies:";
    }


    public String enterFirstCurrency() {
        return
                "     Choose and enter your currency: ";
    }

    public String enterSecondCurrency() {
        return
                "     Choose and enter second currency: ";
    }

    public String enterAmount() {
        return
                "     Enter amount: ";
    }

    public String enterCurrencyOrCommand() {
        return
                "     Enter currency/command: ";
    }

    public String enterDate() {
        return
                "     Enter date (yyyymmdd): ";
    }

    public String unknownCommand() {
        return
                "     UNKNOWN COMMAND.\n";
    }

    public String rateDoesNotExistForThisDate() {
        return
                "     We have no exchange rate for this date \n";
    }

    public String wrongDate() {
        return
                "     WRONG DATE.\n";
    }

    public String command() {
        return
                "     \nCommand:";
    }

    public String emptySpace() {
        return
                "     ";
    }

    public String min() {
        return
                "     MIN";
    }

    public String max() {
        return
                "     MAX";
    }
}