package com.infoshareacademy.aem.global_extreme;

public class MyPrinter {
    ListAvailableCurrency listAvailableCurrency =  new ListAvailableCurrency();

    public String line1(){
        return "     *********************************************\n" +
                "     *              Global Extreme               *\n" +
                "     *********************************************\n" +
                "                                || m: back to menu\n";
    }

    public String line1_1(){
        return "     List available currency:";
    }

    public String line2() {
        return  "\n" +
                "     Enter currency: ";
    }

    public String line3() {
        return "\n\n\n\n" +
                "     *****************   ERROR   *****************\n" +
                "                                || m: back to menu\n\n" +
                "     Currency does not exist. Enter correct currency.\n";
    }

    public String line4() {
        return "\n\n" +
                ".................................................\n" +
                "*************************************************\n" +
                "\n\n";
    }


}
