
public class Main {
    public static void main(String[] args) {

     // currentCurrency.currentDate();
       MenuProject menu=new MenuProject();
       menu.menuPanel();


        AppProperties appProperties = PropertiesLoader.loadProperties();
//        System.out.println(appProperties.getVersion());;
//        System.out.println(appProperties.getSourceFilePath());

        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.getListOfCurrencies().toString();


    }
}
