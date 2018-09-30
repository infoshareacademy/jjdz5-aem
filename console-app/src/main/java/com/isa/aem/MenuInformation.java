package com.isa.aem;

public class MenuInformation {
    public void programInformation() {
        System.out.println("    ****************************************");
        System.out.println("    *       INFORMACJE O PROGRAMIE         *");
        System.out.println("    ****************************************\n");
        System.out.println("Żyjemy w czasach, w których obracamy pieniędzmi nie tylko w walucie ojczystej. \n" +
                "Spotykamy się z taką sytuacją podczas przelewów bankowych, wycieczek zagranicznych oraz wyjazdów służbowych. \n" +
                "W takiej sytuacji chcemy w szybki sposób sprawdzić ile dana transakcja nas kosztuje. \n" +
                "W celu ułatwienia użytkownikowi przeliczenia kursu, stworzyliśmy Aplikację Kursu Walut. \n" +
                "Program udostępnia następujące opcje: \n\n" +
                "1. Wyświetl dostępne waluty i aktualny kurs na dzień wskazany przez użytkownika \n" +
                "Opcja prezentuje zakres czasowy walut znajdujących się w pliku. \n" +
                "Program umożliwia wypisanie aktualnego kursu walut na dzień wskazany przez użytkownika \n\n" +
                "2. Kalkulator walut\n" +
                "Umożliwia użytkownikowi przeliczenie kursu walut w określonej walucie i po określonym kursie. \n\n" +
                "3 . Wyświetl ekstrema globalne \n" +
                "Pokazuje użytkownikowi, kiedy wskazana waluta przyjmowała najwyższy oraz najniższy kurs \n\n");
                "4 . Wyświetl ekstrema lokalne \n" +
                "Pokazuje użytkownikowi, kiedy wskazana waluta przyjmowała najwyższy oraz najniższy kurs w wybranym zakresie czasowym\n\n");
        readMenu();

    }

    public void currentCurrency() {
        CurrentCurrency currentCurrency = new CurrentCurrency();
        currentCurrency.currentCash();

    }

    public void readMenu() {
        System.out.println("    ****************************************");
        System.out.println("    *                 MENU                 *");
        System.out.println("    ****************************************\n");
        System.out.println("    1. Wyświetl dostępne  waluty i aktualny kurs na dzień wskazany przez użytkownika");
        System.out.println("    2. Kalkulator walut");
        System.out.println("    3. Wyświetl ekstrema globalne");
        System.out.println("    4. Wyświetl ekstrema lokalne");
        System.out.println("    5. Informacja o programie");
        System.out.println("    0. Zakończ działanie aplikacji");
    }

}
