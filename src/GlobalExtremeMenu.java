public class GlobalExtremeMenu {

    private final ConsolePrinter consolePrinter = new ConsolePrinter();
    private final ConsoleReader consoleReader = new ConsoleReader();


    private void printOptjons() {
        consolePrinter.printLn("Choose what do you want to do");
        consolePrinter.printLn("Choose what do you want to do");
    }

    public void menu() {
        String chooseCommand = consoleReader.getString("Choose command");
        switch (chooseCommand) {

        }
    }

}
