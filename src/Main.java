import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        MenuProject menu = new MenuProject();

        AppProperties appProperties = PropertiesLoader.loadProperties();
//        System.out.println(appProperties.getVersion());;
//        System.out.println(appProperties.getSourceFilePath());

        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.getListOfCurrencies().toString();


    }
}
