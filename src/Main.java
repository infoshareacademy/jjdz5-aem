public class Main {
    public static void main(String[] args) {
        FileContentReader fileContentReader = new FileContentReader();
        CurrencyRepository currencyRepository = new CurrencyRepository();
        Currency currency = new Currency();
        fileContentReader.readFile();
        System.out.println(currencyRepository.find());
        currencyRepository.findName();

    }
}
