import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        MenuProject menu = new MenuProject();

        AppProperties appProperties = new AppProperties(PropertiesLoader.loadProperties());
        System.out.println(appProperties.getVersion());;
        System.out.println(appProperties.getSourceFilePath());



    }
}
