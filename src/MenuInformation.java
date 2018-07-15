public class MenuInformation {
    public void programInformation(){
        System.out.println("    ****************************************");
        System.out.println("    *       INFORMACJE O PROGRAMIE         *");
        System.out.println("    ****************************************\n");
        System.out.println("Żyjemy w czasach, w których obracamy pieniędzmi nie tylko w walucie ojczystej. \n" +
                "Spotykamy się z taką sytuacją podczas przelewów bankowych, wycieczek zagranicznych oraz wyjazdów służbowych. \n" +
                "Warto w takiej sytuacji w szybki sposób sprawdzić ile taka tranzakcja nas napradę kosztuje. \n" +
                "W celu ułatwienia użytkownikowi sprawdzenia interesującego go kursu, stworzyliśmy Aplikację Kursu Walut. \n" +
                "Program udostępnia następujące opcje: \n\n 2. Zapisanie pliku kursu walut na wskazanej lokalizacji \n" +
                "Umożliwia użytkownikowi zapisanie pliku na dowolnej lokalizacji na komputerze. \n\n" +
                "3. Wyświetlanie dostępnych  walut \n" +
                "Ta opcja pokazuje użytkownikowi, jakie waluty są dostępne w pliku oraz ich zakres czasowy. \n" +
                "Program umożliwia również wypisanie aktualnego kursu walut na dzień wskazany przez użytkownika \n\n" +
                "4 . Wyświetl ekstrema globalne \n" +
                "Pokazuje użytkownikowi, kiedy wskazana waluta przyjmowała najwyższy oraz najniższy kurs \n\n" +
                "5. Wyświetl ekstrema lokalne \n" +
                "Pokazuje użytkownikowi, kiedy wskazana waluta przyjmowała najwyższy oraz najniższy kurs we wskazanym przez niego zakresie czasowym\n\n" +
                "6. Kalkulator walut\n" +
                "Umożliwia użytkownikowi przeliczenie kursu walut w określonej walucie i po określonym kursie.");
    }

    public void readCurrent(){
        CurrentCurrency currentCurrency=new CurrentCurrency();
        currentCurrency.currentCash();
    }
}
