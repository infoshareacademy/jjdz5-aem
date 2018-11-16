package com.isa.aem.data_loaders;

import com.isa.aem.AppProperties;
import com.isa.aem.Currency;
import com.isa.aem.CurrencyRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileContentReader {
    CurrencyRepository currencyRepository = new CurrencyRepository();
    private String filePath;

    private List<Currency> listOfCurrencies = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();
    private static String NAME_FILE = "sourceFilePath";

    public void readFile() {
        AppProperties appProperties = PropertiesLoader.loadProperties();
        this.filePath = appProperties.getSourceFilePath(NAME_FILE);
        Path path = Paths.get(filePath).toAbsolutePath();

        List<String> allLinesAsString = new ArrayList<>();
        try {
            allLinesAsString = Files.readAllLines(path);
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

        listOfCurrencies = convertIntoObject(allLinesAsString);
        CurrencyRepository.setCurrencies(listOfCurrencies);
    }

    public void addPLNToListCurrency() {
        List<LocalDate> everySingleDateOfFile = currencyRepository.getEverySingleDateFromFile();
        for (LocalDate date : everySingleDateOfFile) {
            Currency currencyPLN = new Currency(
                    "PLN", date, 1.0, 1.0, 1.0, 1.0, 1);
            currencyRepository.add(currencyPLN);
        }
    }

    private List<Currency> convertIntoObject(List<String> read) {
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

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

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
