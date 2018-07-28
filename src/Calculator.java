import sun.util.resources.cldr.uk.CurrencyNames_uk;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Calculator {
    CurrentVariable currentVariable1=new CurrentVariable();
    List<Currency>currentVariable=new ArrayList<Currency>();
    Set<String> singleCurrent=new TreeSet<>();
    Boolean check=true;
    String currenc;
    Double number1;

    public Calculator(){

        readFile();
        checkCurrency();
        checkNumber();
        checkDate();
        for (Currency currency : currentVariable1.dateCurrency) {

            if (currency.getDate().toString().equals(currentVariable1.dateCurrent) && currency.getName().equalsIgnoreCase(currenc)){
                currentVariable1.listCurrency.add(currency);

            }

        }


//sprawdzenie czy data wystąpiła
        if (currentVariable1.listCurrency.isEmpty()){
            System.out.println("Plik nie posiada kursu ze wskazanego dnia");
        }else{
            for(Currency c:currentVariable1.listCurrency){
                Double score=c.getClose()*  number1;
                System.out.println("Wynik:" + score);
            }

        }


    }


//wrzucenie walut do tablicy ArrayList
   public List<Currency> readFile(){

        for (Currency currency : CurrencyRepository.getCurrencies()) {
            currentVariable.add(currency);
            currentVariable1.dateCurrency.add(currency);
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

    private String checkDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj datę w formacie yyyy-mm-dd:");
        boolean exception=true;
        while (exception) {
            try {
                currentVariable1.localTime1= LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE);
                exception=false;
            } catch (Exception e) {
                System.out.println("Nieprawidłowy format daty");
                System.out.println("Wpisz ponownie poprawny format daty w formacie yyyy-mm-dd: ");
            }
        }
        switch (currentVariable1.localTime1.getDayOfWeek()){
            case SUNDAY: currentVariable1.dateCurrent=currentVariable1.localTime1.minusDays(2).format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;
            case SATURDAY:currentVariable1.dateCurrent=currentVariable1.localTime1.minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;
            default: currentVariable1.dateCurrent=currentVariable1.localTime1.format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;


        }

        return currentVariable1.dateCurrent;
    }

}

