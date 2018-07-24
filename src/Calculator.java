import java.util.ArrayList;
import java.util.List;
import java.util.*;
public class Calculator {


//List<Currency> CurrentVariable.listCurrency = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    Integer value=Integer.parseInt(scanner.nextLine());

    Currency expectedCurrency = new Currency();


    public Integer getExpectedCurrencyVolume() {
        return expectedCurrency.getVolume();
    }
    public String getExpectedCurrencyName(){return  expectedCurrency.getName();}
public Calculator(){
    Integer yourMultiplier = getExpectedCurrencyVolume()*value;
    System.out.println("Po przeliczeniu ilość Waluty wynosi "+yourMultiplier+" "+getExpectedCurrencyName());}


}
