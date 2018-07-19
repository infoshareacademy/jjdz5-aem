import java.util.ArrayList;
import java.util.List;

public class GlobalExtreme {

    private FileContentReader fileContentReader = new FileContentReader();
    private GlobalExtremeMenu globalExtremeMenu = new GlobalExtremeMenu();
    private Currency currency;

    public void run() {
        findCurrency();
    }

    private void findCurrency() {
        List<Currency> singleCurrency = new ArrayList<>();
        for (Currency c:fileContentReader.getCurrencies()) {
            if (c.getName().equals(globalExtremeMenu.getChooseCommand())){
                singleCurrency.add(c);
            }
        }
        for (Currency c: singleCurrency) {
            System.out.println(c);
        }
    }

}
