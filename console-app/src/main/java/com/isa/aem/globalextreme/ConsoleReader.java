package com.isa.aem.globalextreme;

import java.util.Scanner;

public class ConsoleReader {

    Scanner scanner = new Scanner(System.in);

    public String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public String getString() {
        return scanner.nextLine();
    }

}