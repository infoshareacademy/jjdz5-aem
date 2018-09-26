package com.isa.aem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        MenuProject menu = new MenuProject();
//        menu.menuPanel();
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        CurrencyRepository currencyRepository=new CurrencyRepository();
        List<String> singleCurrency=currencyRepository.listAvailableCurrency();
        for (String c : singleCurrency) {
            System.out.println(c);
        }
        }
    }

