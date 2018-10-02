package com.isa.aem;

public class Main {
    public static void main(String[] args) {
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();

        MenuProject menu = new MenuProject();
        menu.menuPanel();


    }
}

