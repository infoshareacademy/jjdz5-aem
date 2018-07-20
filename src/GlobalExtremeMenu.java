import java.util.Arrays;
import java.util.List;

public class GlobalExtremeMenu {

    private final ConsolePrinter consolePrinter = new ConsolePrinter();
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final IgnoreCase ignoreCase = new IgnoreCase();
    private ListAvailableCurrency listAvailableCurrency = new ListAvailableCurrency();
    private FeatureReader featureReader = new FeatureReader();
    private GlobalExtreme globalExtreme = new GlobalExtreme();

    private final String currencies = "currencies";
    private final String extreme = "extreme";


    public void run() {
        printOptjons();
        menu();
    }



    private void printOptjons() {
        consolePrinter.printLn("Choose what do you want to do");
        consolePrinter.printLn("If you want print available currencies enter: \"currencies\"");
        consolePrinter.printLn("If you want find extreme enter: \"extreme\"");
    }

    private void menu() {

        String chooseCommand = consoleReader.getString("Choose command");
        String s = ignoreCase.lowerSize(chooseCommand);
//        featureReader.predictedLiteray(s);

        switch (s) {
            case "currency":
                listAvailableCurrency.run();
                break;
            case "extreme":
                globalExtreme.run();
                break;


        }
    }

}
