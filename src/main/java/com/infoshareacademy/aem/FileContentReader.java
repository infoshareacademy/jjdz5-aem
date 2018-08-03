package com.infoshareacademy.aem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileContentReader {
    private String filePath;

    //lista przechowujaca liste obiektow Currency
    private ArrayList<Currency> listOfCurrencies = new ArrayList<>();
    private ArrayList<Currency> currencies = new ArrayList<>();

    //metoda wczytujaca plik i zwracajaca obiekty currencies
    public void readFile() {
        AppProperties appProperties = PropertiesLoader.loadProperties();
        this.filePath = appProperties.getSourceFilePath();
        Path path = Paths.get(filePath);

        // lista przechowujaca kolejne linie jako String
        ArrayList<String> allLinesAsString = new ArrayList<>();
        try {
            allLinesAsString = (ArrayList) Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Brak pliku! \n" +
                    "Upewnij się, że plik z danymi znajduję się w lokalizacji zdefiniowanej w " +
                    "app.properties \n" +
                    "Wyjdź z programu, popraw i uruchom ponownie \n");
            try {
                System.out.println("Nacisnij Enter aby wyjść z programu");
                System.in.read();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }

        // przypisanie do listy currencies gotowych obiektow (sparsowane dane)
        listOfCurrencies = convertIntoObject(allLinesAsString);
        CurrencyRepository.setCurrencies(listOfCurrencies);
    }

    // metoda konwertujaca kolejne linie stringow do obiektow (parsowanie oraz formatowanie danych do wlasciwych typow)
    private ArrayList<Currency> convertIntoObject(ArrayList<String> read) {
        for (String oneLine : read) {
            Pattern pattern = Pattern.compile("^\\w\\w\\w,\\d+,\\d\\.\\d+,\\d\\.\\d+,\\d\\.\\d+,\\d\\.\\d+,\\d$");
            Matcher matcher = pattern.matcher(oneLine);
            if (!matcher.matches()) {
                System.out.println("Plik z danymi ma niekompatybilną strukturę. " +
                        "Przykładowa prawidłowa struktura jednej linii wygląda następująco: \n" +
                        "AUD,19000626,2.7516,2.7516,2.7516,2.7516,0 \n" +
                        "Wyjdź z programu, popraw i spróbuj ponownie \n");
                try {
                    System.out.println("Nacisnij Enter aby wyjść z programu");
                    System.in.read();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            } else {
                String[] line = oneLine.split(",");

                // formater, ktory konwertuje zrodlowa date yyyyMMdd do formatu DateTime yyyy-MM-dd
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

                // parsowanie pol obiektow currency i wypelnenie nimi listy curriencies
                Currency currency = new Currency(
                        line[0],
                        LocalDate.parse(line[1], dateTimeFormatter),
                        Double.parseDouble(line[2]),
                        Double.parseDouble(line[3]),
                        Double.parseDouble(line[4]),
                        Double.parseDouble(line[5]),
                        Integer.parseInt(line[6])
                );
                currencies.add(currency);
            }
        }
        return currencies;
    }
}

