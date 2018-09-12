package com.isa.aem;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuProject {

    private MenuInformation menuInformation = new MenuInformation();
    private GlobalExtreme globalExtremeMenu = new GlobalExtreme();
    private ConsoleReader consoleReader = new ConsoleReader();
    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private LocalExtremum localExtremum = new LocalExtremum();

    public void menuPanel() {
        menuInformation.readMenu();
        pick();

    }

    private void pick() {


        System.out.println("Wybierz interesującą Cię metodę");

        // int choose=0;

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
                        System.out.println("Funkcja umożliwia użytkownikowi przeliczenie kursu walut w określonej walucie i po określonym kursie.");
                        Calculator calculator = new Calculator();
                        break;
                    case 3:
                        System.out.println("Ekstrema globalne,\n" +
                                "Po tym menu możesz poruszać się również wisując małe litery,\n" +
                                "Dostepne Waluty:");
                        globalExtremeMenu.run();
                        break;
                    case 4:
                        consolePrinter.printLn("\nEkstrema lokalne. Funkcja umożliwia wyświetlenie ekstremów danej waluty dla zadanego przedziału czasu");
                        consolePrinter.printLocalExtremeWelcome();
                        consoleReader.chooseCurrency();
                        consolePrinter.printLn("Limit the date range for the currency of your choice. ");
                        consoleReader.runDates();
                        //consoleReader.runLocalExtremum();
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



/*    private void runDates(ConsoleReader consoleReader) {
        boolean isDateIncorrect = true;
        LocalExtremum localExtremum = new LocalExtremum();
        while (isDateIncorrect) {
            LocalDate dateFrom = consoleReader.getDate("From");
            LocalDate dateTo = consoleReader.getDate("To");

            isDateIncorrect = localExtremum.isDateIncorrect();
            if (isDateIncorrect)
                continue;

            //getFromDate
            consoleReader.getDate("From");

            //getFromDate
            consoleReader.getDate("From");

            localExtremum.limitCurrenciesToChosenDateRange(dateFrom, dateTo);
        }
    }*/

}

