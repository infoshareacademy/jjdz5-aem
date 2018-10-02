package com.isa.aem;

public class Main {
    public static void main(String[] args) {

        MenuProject menu = new MenuProject();
        menu.menuPanel();
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
    }
}

