package com.isa.aem;

public class Main {
    public static void main(String[] args) {

        FileContentReader fileContentReader = new FileContentReader();
        MenuProject menu = new MenuProject();
        LocalExtreme localExtreme = new LocalExtreme();

        fileContentReader.readFile();
//        menu.menuPanel();

        localExtreme.limitCurrenciesToChosenDateRange();
        
        System.out.println(localExtreme.getAvailableCurrencies());


    }
}
