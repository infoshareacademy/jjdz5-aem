import java.util.Arrays;
import java.util.List;

public class GlobalExtremeMenu {

    private final ConsolePrinter consolePrinter = new ConsolePrinter();
    private final ConsoleReader consoleReader = new ConsoleReader();
    private final IgnoreCase ignoreCase = new IgnoreCase();

    private final String currencies = "currencies";
    private final String extreme = "extreme";


    private void printOptjons() {
        consolePrinter.printLn("Choose what do you want to do");
        consolePrinter.printLn("If you want print available currencies enter: \"currencies\"");
        consolePrinter.printLn("If you want find extreme enter: \"extreme\"");
    }

    public void menu() {
        String chooseCommand = consoleReader.getString("Choose command");
        ignoreCase.ignoreSize(chooseCommand);
        ignoreCase.predictedLiteray(chooseCommand);
        switch (chooseCommand) {
            case "currencies":
                break;
            case "extreme":
                break;


        }
    }

}
