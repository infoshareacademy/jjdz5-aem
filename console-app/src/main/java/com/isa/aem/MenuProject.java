package com.isa.aem;

import com.isa.aem.calc.ComplexConsoleCalculator;
import com.isa.aem.global.extremum.GlobalExtremum;
import com.isa.aem.local.extremum.LocalExtremum;
import com.isa.aem.local.extremum.LocalExtremumService;
import com.isa.aem.tools.ConsolePrinter;
import com.isa.aem.tools.ConsoleReader;

import java.util.Scanner;

public class MenuProject {

    ConsoleReader consoleReader = new ConsoleReader();
    ConsolePrinter consolePrinter = new ConsolePrinter();
    LocalExtremum localExtremum = new LocalExtremum();
    LocalExtremumService localExtremumService = new LocalExtremumService();


    public MenuProject() {
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
    }

    MenuInformation menuInformation = new MenuInformation();
    private GlobalExtremum globalExtremeMenu = new GlobalExtremum();
    private ComplexConsoleCalculator calculator = new ComplexConsoleCalculator();

    public void menuPanel() {
        menuInformation.readMenu();
        pick();
    }

    private void pick() {
        System.out.println("Wybierz interesującą Cię metodę");

        do {
            Scanner scanner = new Scanner(System.in);

            String choose = scanner.nextLine();

            if (choose.matches("\\d{0,9}") && choose.length() > 0) {
                Integer choose1 = Integer.parseInt(choose);
                switch (choose1) {
                    case 1:
                        menuInformation.currentCurrency();
                        break;
                    case 2:
                        calculator.run();
                        break;
                    case 3:
                        System.out.println("\n\n\n\n     ");
                        globalExtremeMenu.run();
                        break;
                    case 4:
                        consolePrinter.printLocalExtremumWelcome();
                        localExtremumService.run();
                        break;
                    case 5:
                        menuInformation.programInformation();
                        break;
                    case 0:
                        System.out.println("Dziękujemy za skorzystanie z programu\n");
                        System.out.println("     ****************************************");
                        System.out.println("     Koniec programu\n\n");
                        System.exit(0);

                    default:
                        System.out.println("MenuProject nie posiada numeru " + choose + ". Podaj ponownie numer opcji, którą chcesz wywołać");
                        break;
                }

            } else {
                System.out.println("MenuProject nie posiada numeru " + choose + ". Podaj ponownie numer opcji, którą chcesz wywołać");
            }
        } while (true);


    }

}

