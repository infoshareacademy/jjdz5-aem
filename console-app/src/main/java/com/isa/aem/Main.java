package com.isa.aem;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileContentReader fileContentReader = new FileContentReader();
        MenuProject menu = new MenuProject();
        fileContentReader.readFile("sourceFilePath");
        menu.menuPanel();
    }
}
