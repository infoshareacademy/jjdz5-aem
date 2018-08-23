package com.infoshareacademy.aem;

import com.infoshareacademy.aem.global_extreme.GlobalExtreme;

public class Main {
    public static void main(String[] args) {

        FileContentReader fileContentReader = new FileContentReader();
        MenuProject menu = new MenuProject();
//        GlobalExtreme globalExtreme = new GlobalExtreme();
//        globalExtreme.run();

        fileContentReader.readFile();
        menu.menuPanel();

    }
}
