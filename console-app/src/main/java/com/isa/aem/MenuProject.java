package com.isa.aem;

import com.isa.aem.currency_calculator.ComplexConsoleCalculator;
import com.isa.aem.data_loaders.FileContentReader;
import com.isa.aem.helpers.ConsolePrinter;
import com.isa.aem.rate_extremums.GlobalExtremumConsoleHandler;
import com.isa.aem.rate_extremums.LocalExtremumConsoleHandler;

import java.util.Scanner;

public class MenuProject {

    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private LocalExtremumConsoleHandler localExtremumConsoleHandler = new LocalExtremumConsoleHandler();
    private GlobalExtremumConsoleHandler globalExtremumConsoleHandler = new GlobalExtremumConsoleHandler();
    private MenuInformation menuInformation = new MenuInformation();
    private ComplexConsoleCalculator calculator = new ComplexConsoleCalculator();

    public MenuProject() {
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
    }

    public void menuPanel() {
        menuInformation.readMenu();
        pick();
    }

    private void pick() {
        System.out.println("Wybierz interesującą Cię opcję");

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
                        globalExtremumConsoleHandler.run();
                        break;
                    case 4:
                        consolePrinter.printLocalExtremumWelcome();
                        localExtremumConsoleHandler.run();
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