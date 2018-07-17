import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileContentReader {

    //lista przechowujaca liste obiektow Currency
    private ArrayList<Currency> listOfCurrencies = new ArrayList<>();
    private final CurrencyRepository currencyRepository = new CurrencyRepository();

    private String filePath = "C:\\Users\\Adam\\Documents\\GitHub\\jjdz5-aem\\ndohlcv.txt";



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
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Podaj sciezke do pliku z danymi");    <----- tu nic nie ruszałem bo ma byc itka inaczej
//        setFilePath(scanner.nextLine());
        Path path = Paths.get(filePath);

        // lista przechowujaca kolejne linie jako String
        ArrayList<String> allLinesAsString = new ArrayList<>();
        try {
            allLinesAsString = (ArrayList) Files.readAllLines(path);
            convertIntoObject(allLinesAsString);
        } catch (IOException e) {
            System.out.println("Plik nie istnieje");
        }
        // przypisanie do listy currencies gotowych obiektow (sparsowane dane) jako efekt wywolania metody convertIntoObject()
        //listOfCurrencies = convertIntoObject(allLinesAsString);  <--- wywołuje bezposrednio merode tam wyzej
            return listOfCurrencies;
    }

    // metoda konwertujaca kolejne linie stringow do obiektow (parsowanie oraz formatowanie danych do wlasciwych typow)
    private void convertIntoObject(ArrayList<String> read) {   //<------ misialem zmienic zeby nic nie zwracało
        //ArrayList<Currency> currencies = new ArrayList<>();   <-- to jest w klasie CurrencyRepository
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
            currencyRepository.addCurrency(currency); // <--- tu wywołuje metode z kalsy CurrencyRepository i dodaje do listy
        }
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
