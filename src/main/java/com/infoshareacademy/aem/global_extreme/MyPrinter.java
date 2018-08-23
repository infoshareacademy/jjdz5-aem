package com.infoshareacademy.aem.global_extreme;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;

public class MyPrinter {
    ListAvailableCurrency listAvailableCurrency =  new ListAvailableCurrency();

    public String line1(){
        return "     *********************************************\n" +
                "     *              Global Extreme               *\n" +
                "     *********************************************\n" +
                "                                || m: back to menu\n\n";
    }

    public String line1_1(){
        return "     List available currency:";
    }

    public String line2() {
        return  "\n" +
                "     Enter currency/command: ";
    }

    public String line3() {
        return "\n\n" +
                "     *****************   ERROR   *****************\n" +
                "                                || m: back to menu\n\n" +
                "     CURRENCY DOES NOT EXIST.\n";
    }

    public String line4() {
        return "\n\n" +
                ".................................................\n" +
                "*************************************************\n" +
                "\n\n";
    }

    public String line5() {
        return
                "                                 || m: back to menu\n" +
                "                                 || b: back to currency selection \n\n" +
                "     Choose yours extreme:\n" +
                "                   n: min\n" +
                "                   x: max\n" +
                        "     Command: ";
    }

    public String line6() {
        return "\n\n"+
                "                                 || m: back to menu\n";
    }

    public String line7() {
        return "\n\n"+
                "     *****************   ERROR   *****************\n" +
                "                                \n\n" +
                "     UNKNOWN COMMAND.\n";
    }

    public String line8() {
        return "\n" +
                "     .............................................\n" +
                "     .............................................\n" +
                "                                 || m: back to menu\n" +
                "|| b: back to currency selection \n\n";
    }



}
