import sun.util.resources.cldr.uk.CurrencyNames_uk;

import java.util.*;

public class Calculator {
    List<Currency>currentVariable=new ArrayList<Currency>();
    Set<String> singleCurrent=new TreeSet<>();
    Boolean check=true;
    public Calculator(){

        readFile();
        checkCurrency();
        checkNumber();



    }
//wrzucenie walut do tablicy ArrayList
   public List<Currency> readFile(){

        for (Currency currency : CurrencyRepository.getCurrencies()) {
            currentVariable.add(currency);
        }
        return   currentVariable;
    }
//wypisanie pojedynczych walut

    public Set<String> singleCurrency(List<Currency> list){

        for (Currency currency : list) {
            singleCurrent.add(currency.getName());
        }
        return   singleCurrent;
    }

//sprawdzenie czy istnieje waluta
    public String checkCurrency() {
        String currenc;
        do {
            System.out.println("Wybierz dostępną walutę ");
            System.out.println(singleCurrency(currentVariable));
            Scanner scanner = new Scanner(System.in);
            currenc = scanner.next().trim();
            //sprawdzenie czy istnieje waluta
            for (String cur : singleCurrent) {
                if (cur.equalsIgnoreCase(currenc)) {
                    check = false;
                    break;
                }

            }
            if (check) {
                System.out.println("Brak waluty w zestawieniu.");
            }
        } while (check == true);

        return currenc;
    }

  //sprawdzenie czy wpisano prawidłową kwotę
  public Double checkNumber() {
      Double number1;
      do {
          System.out.println("Wpisz kwotę: ");

          Scanner scanner = new Scanner(System.in);
          String number = scanner.next().trim().replace(",",".");


          //sprawdzenie czy istnieje waluta
          if(number.matches("[0-9 .]+")) {
                number1=Double.parseDouble(number);
                  break;

          }else {
              System.out.println("Podana wartość nie jest cyfrą.");
          }
      } while (true);

      return number1;
  }


}

