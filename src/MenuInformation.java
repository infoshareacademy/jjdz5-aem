public class MenuInformation {
    public void programInformation(){
        System.out.println("    ****************************************");
        System.out.println("    *       INFORMACJE O PROGRAMIE         *");
        System.out.println("    ****************************************\n");
        System.out.println("Żyjemy w czasach, w których obracamy pieniędzmi nie tylko w walucie ojczystej. \n" +
                "Spotykamy się z taką sytuacją podczas przelewów bankowych, wycieczek zagranicznych oraz wyjazdów służbowych. \n" +
                "W takiej sytuacji chcemy w szybki sposób sprawdzić ile dana transakcja nas kosztuje. \n" +
                "W celu ułatwienia użytkownikowi przeliczenia kursu, stworzyliśmy Aplikację Kursu Walut. \n" +
                "Program udostępnia następujące metody: \n\n 2. Zapisanie pliku kursu walut na wskazanej lokalizacji \n" +
                "Umożliwia zapisanie pliku na dowolnej lokalizacji wskazanej przez użytkownika. \n\n" +
                "3. Wyświetlanie dostępnych  walut \n" +
                "Metoda prezentuje zakres czasowy walut znajdujących się w pliku. \n" +
                "Program umożliwia wypisanie aktualnego kursu walut na dzień wskazany przez użytkownika \n\n" +
                "4 . Wyświetl ekstrema globalne \n" +
                "Pokazuje użytkownikowi, kiedy wskazana waluta przyjmowała najwyższy oraz najniższy kurs \n\n" +
                "5. Wyświetl ekstrema lokalne \n" +
                "Pokazuje użytkownikowi, kiedy wskazana waluta przyjmowała najwyższy oraz najniższy kurs we wskazanym przez użytkownika zakresie czasowym\n\n" +
                "6. Kalkulator walut\n" +
                "Umożliwia użytkownikowi przeliczenie kursu walut w określonej walucie i po określonym kursie.");
    }

    public void currentCurrency(){
        CurrentCurrency currentCurrency=new CurrentCurrency();
        currentCurrency.currentCash();
    }
    public void readMenu(){
        System.out.println("    ****************************************");
        System.out.println("    *                 MENU                 *");
        System.out.println("    ****************************************\n");
        System.out.println("    1. Informacja o programie");
        System.out.println("    2. Zapisanie pliku kursu walut na wskazanej lokalizacji");
        System.out.println("    3. Wyświetlanie dostępnych  walut i aktualnego kursu na dzień wskazany przez użytkownika");
        System.out.println("    4. Wyświetl ekstrema globalne");
        System.out.println("    5. Wyświetl ekstrema lokalne");
        System.out.println("    6. Kalkulator walut");
        System.out.println("    7. Powrót do MENU");
        System.out.println("    0. Zakończ działanie aplikacji");
    }
}
