import java.util.ArrayList;

import java.util.List;

import java.util.*;

public class Calculator {//List<Currency> CurrentVariable.listCurrency = new ArrayList<>();

    public Calculator(){

        score();

    }    public void score() {

        System.out.println("Podaj walute");

        Scanner scanner = new Scanner(System.in);

        String waluta=scanner.next();

        System.out.println("podaj kwote");
        Scanner scannerValue = new Scanner(System.in);

        Double value=scannerValue.nextDouble();
       String val =Double.toString(value);
        boolean v = val.matches(".");
        if(v == true)
            throw new ArithmeticException("Liczba nie może zawierać  .  a jedynie  , ");

        FileContentReader fileContentReader = new FileContentReader();
        CurrencyRepository.getCurrencies();
        for (Currency currency : fileContentReader.getListOfCurrencies())
        {
            if(currency.getName().equalsIgnoreCase(waluta)){

            Double source=  currency.getClose()*value;

            System.out.println("Po przeliczeniu  wynosi " + source + " waluty " + currency.getName());

        }

        }
    }

    }