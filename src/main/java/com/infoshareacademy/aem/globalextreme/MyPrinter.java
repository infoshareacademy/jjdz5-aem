package com.infoshareacademy.aem.globalextreme;

public class MyPrinter {

    public String globalExtremeTittle() {
        return
                "     *********************************************\n" +
                "     *              Global Extreme               *\n" +
                "     *********************************************\n";
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

    public String command() {
        return
                "     Command: ";
    }

    public String line1_1(){
        return "     List available currency:";
    }

    public String listAblCur(){
        return "     List available currency:";
    }


    public String enterCurCom() {
        return
                "     Enter currency/command: ";
    }

    public String extremeMenu() {
        return
                "     Choose yours extreme:\n" +
                "                   2: min\n" +
                "                   9: max\n" +
                "     Command: ";
    }

    public String unknowCommand() {
        return
                "     UNKNOWN COMMAND.\n";
    }
}
