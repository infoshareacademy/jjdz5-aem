package com.isa.aem.tools;

import javax.enterprise.context.ApplicationScoped;
import java.util.Scanner;
@ApplicationScoped
public class ConsoleReader {

    Scanner scanner = new Scanner(System.in);

    public String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}