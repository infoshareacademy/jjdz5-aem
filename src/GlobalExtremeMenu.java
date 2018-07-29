import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlobalExtremeMenu {

    private final ConsolePrinter consolePrinter = new ConsolePrinter();
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final IgnoreCase ignoreCase = new IgnoreCase();
    private ListAvailableCurrency listAvailableCurrency = new ListAvailableCurrency();
    private FeatureReader featureReader = new FeatureReader();
    private GlobalExtreme globalExtreme = new GlobalExtreme();
    //private  MenuProject menuProject = new MenuProject();
    MenuInformation menuInformation = new MenuInformation();


    private final String currencies = "currencies";
    private final String extreme = "extreme";


    public void run() {
        printOptjons();
        menu();
    }



    private void printOptjons() {

        consolePrinter.printLn("\nWybierz jedną z dostępnych opcji: ");
        consolePrinter.printLn("Jeżeli chcesz wypisać dostępne waluty wpisz w konsoli: \"waluta\"");
        consolePrinter.printLn("Jeżeli chcesz znaleźć ekstremyum globalne wpisz w konsoli: \"ekstremum\"");
        consolePrinter.printLn("Jeżeli chcesz wyjść do głównego menu wpisz w konsoli: \"menu\"");
        consolePrinter.print("Wprowadź opcję: ");
    }



    private void menu() {
        do {
            String chooseCommand = consoleReader.getString();
            String s = ignoreCase.lowerSize(chooseCommand);

            if (s.equals("waluta") || s.equals("ekstremum")) {
                switch (s) {
                    case "waluta":
                        listAvailableCurrency.run();
                        break;
                    case "ekstremum":
                        globalExtreme.run();
                        break;
                }
            }
            else if (s.equals("menu")) {
                menuInformation.readMenu();
                break;
            }
            else  {
                System.out.println("Niepoprawna komenda, spróbuj ponownie.\n");

            }
            printOptjons();
        } while (true);


    }

}



