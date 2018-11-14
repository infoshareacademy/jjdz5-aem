package com.isa.aem.helpers;

import java.util.Scanner;

public class ConsoleReader {

    Scanner scanner = new Scanner(System.in);

    public String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public String getString() {
        return scanner.nextLine();
    }

    public Integer getInteger(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }

    public Integer getInteger() {
        return scanner.nextInt();
    }

    public Double getDouble(String message) {
        System.out.println(message);
        return scanner.nextDouble();
    }

    public Double getDouble() {
        return scanner.nextDouble();
    }


}