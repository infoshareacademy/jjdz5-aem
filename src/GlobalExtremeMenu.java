import java.util.Arrays;
import java.util.List;

public class GlobalExtremeMenu {

    private final ConsolePrinter consolePrinter = new ConsolePrinter();
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final IgnoreCase ignoreCase = new IgnoreCase();
    private ListAvailableCurrency listAvailableCurrency = new ListAvailableCurrency();
    private FeatureReader featureReader = new FeatureReader();

    private final String currencies = "currencies";
    private final String extreme = "extreme";
    private String chooseCommand;

    public void run() {
        printOptjons();
        menu();
    }

    public String getChooseCommand() {
        return chooseCommand;
    }

    private void printOptjons() {
        consolePrinter.printLn("Choose what do you want to do");
        consolePrinter.printLn("If you want print available currencies enter: \"currencies\"");
        consolePrinter.printLn("If you want find extreme enter: \"extreme\"");
    }

    public void menu() {
        this.chooseCommand = consoleReader.getString("Choose command");
        ignoreCase.lowerSize(chooseCommand);
        featureReader.predictedLiteray(chooseCommand);
        switch (chooseCommand) {
            case "currencies":
                listAvailableCurrency.run();
                break;
            case "extreme":

                break;


        }
    }

}
