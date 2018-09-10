package com.isa.aem.tools;

import java.util.Scanner;

public class ConsoleReader {

    Scanner scanner = new Scanner(System.in);

    public String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public Double getDouble(String message) {
        System.out.println(message);
        return scanner.nextDouble();
    }

    public String getString() {
        return scanner.nextLine();
    }

}