package com.isa.aem.tools;

import javax.enterprise.inject.Default;

@Default
public class MyPrinter {

    public String globalExtremeTittle() {
        return
                "     *********************************************\n" +
                "     *              Global Extreme               *\n" +
                "     *********************************************\n";
    }

    public String calculatorTittle() {
        return
                "     *********************************************\n" +
                "     *                Calculator                 *\n" +
                "     *********************************************\n";
    }

    public String simpleCalculator (){
        return
                "                                || 1: simple calcualtor\n";
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

    public String dubleNextLine() {
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

    public String bakcCurSel() {
        return
                "                                || 1: back to currency selection \n\n";
    }

    public String curComUnexist() {
        return
                "     CURRENCY OR COMMAND DOES NOT EXIST.\n";
    }

    public String currencyUnexist() {
        return
                "     CURRENCY DOES NOT EXIST.\n";
    }

    public String numberUnexist() {
        return
                "     THIS IS NOT NUMBER\n";
    }

    public String extreme() {
        return
                "     Extreme: ";
    }

    public String listAvailableCurrency(){
        return "     List available currency:";
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

    public String enterCurCom() {
        return
                "     Enter currency/command: ";
    }

    public String enterDate() {
        return
                "     Enter date (yyyymmdd): ";
    }

    public String unknowCommand() {
        return
                "     UNKNOWN COMMAND.\n";
    }

    public String unexistDate() {
        return
                "     We have no curse with this date \n";
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