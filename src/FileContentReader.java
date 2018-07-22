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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj sciezke do pliku z danymi");
        setFilePath(scanner.nextLine());
        Path path = Paths.get(filePath);

        // lista przechowujaca kolejne linie jako String
        ArrayList<String> allLinesAsString = new ArrayList<>();
        try {
            allLinesAsString = (ArrayList) Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Plik nie istnieje");
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
}
