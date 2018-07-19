import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileContentReader {
    private String filePath;

    //lista przechowujaca liste obiektow Currency
    private ArrayList<Currency> listOfCurrencies = new ArrayList<>();

    public FileContentReader() {
    }

    public ArrayList<Currency> getListOfCurrencies() {
        return listOfCurrencies;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    //metoda wczytujaca plik i zwracajaca obiekty currencies
    public ArrayList<Currency> readFile(){
        AppProperties appProperties = PropertiesLoader.loadProperties();
        setFilePath(appProperties.getSourceFilePath());
        Path path = Paths.get(filePath);

        // lista przechowujaca kolejne linie jako String
        ArrayList<String> allLinesAsString = new ArrayList<>();
        try {
            allLinesAsString = (ArrayList) Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Brak pliku! \n");
            System.out.println("Upewnij sie, ze plik z danymi znajduje sie w lokalizacji zdefiniowanej w app.properties i uruchom program ponownie");
            System.exit(0);
        }

        // przypisanie do listy currencies gotowych obiektow (sparsowane dane) jako efekt wywolania metody convertIntoObject()
        listOfCurrencies = convertIntoObject(allLinesAsString);
            return listOfCurrencies;
    }

    // metoda konwertujaca kolejne linie stringow do obiektow (parsowanie oraz formatowanie danych do wlasciwych typow)
    private ArrayList<Currency> convertIntoObject(ArrayList<String> read) {
        ArrayList<Currency> currencies = new ArrayList<>();
        for (String oneLine : read) {
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
        return currencies;
    }

    // wypisywanie obiektow currency
    public void printCurrencies(){

        if (listOfCurrencies.isEmpty()) {
            listOfCurrencies = readFile();
        }
        for (Currency currency : listOfCurrencies){
            System.out.println(currency.toString());
        }
    }

    // zapisywanie do pliku csv
    public void saveToCsv(){

        if (listOfCurrencies.isEmpty()) {
            listOfCurrencies = readFile();
        }

        Path savePath = Paths.get(filePath);

        System.out.println("Wprowasz sciezke do ktorej chcesz zapisac plik: ");
        Scanner scanner = new Scanner(System.in);
        String sPath = scanner.nextLine();

        if(sPath.length() > 0){
            savePath = Paths.get(sPath);
        }

        ArrayList<String> out = new ArrayList<>();
        for(Currency currency : listOfCurrencies){
            out.add(currency.toString());
        }
        try {
            Files.write(savePath, out);
        } catch (IOException ex) {
            System.out.println("Blad przy zapisywaniu pliku");
        }
    }
}
