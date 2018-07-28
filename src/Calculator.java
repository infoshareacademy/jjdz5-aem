import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Calculator {
    CurrentVariable currentVariable1=new CurrentVariable();
    CurrentVariable currentVariable2=new CurrentVariable();
    List<Currency>currentVariable=new ArrayList<Currency>();
    Set<String> singleCurrent=new TreeSet<>();
    Boolean check=true;
    Boolean check2=true;
    String currenc;
    String currenc2;
    Double number1;
    MenuProject menuProject = new MenuProject();
    public Calculator(){

        readFile();
        checkCurrency();
        checkCurrencyOut();
        checkNumber();
        checkDate();
        checkIfDate();


//sprawdzenie czy data wystąpiła
        do {

            if (currenc.equalsIgnoreCase("PLN") && currentVariable2.listCurrency.size() > 0) {
                System.out.println("W dniu " + currentVariable2.listCurrency.get(0).getDate() + " " + number1 + " " + currenc.trim().toUpperCase() +
                        " = " +  number1/currentVariable2.listCurrency.get(0).getClose() + " " + currentVariable2.listCurrency.get(0).getName());
                menuProject.menuPanel();
                break;

            } else if (currenc2.equalsIgnoreCase("PLN") && currentVariable1.listCurrency.size() > 0) {
                System.out.println("W dniu " + currentVariable1.listCurrency.get(0).getDate() + " " + number1 + " " + currenc.trim().toUpperCase() +
                        " = " + number1 * currentVariable1.listCurrency.get(0).getClose() + " " + currenc2);
                menuProject.menuPanel();
                break;
            } else if (currentVariable1.listCurrency.isEmpty() || currentVariable2.listCurrency.isEmpty()) {
                System.out.println("Plik nie posiada kursu ze wskazanego dnia");
                System.out.println("Jeżli chcesz wyjść do MENU wpisz słowo menu");
                System.out.println("Jeżeli chcesz zostać wpisz dowolny ciąg znaków");


                Scanner scanner4=new Scanner(System.in);
                if(scanner4.next().equalsIgnoreCase("menu")){
                    MenuProject menuProject = new MenuProject();
                    menuProject.menuPanel();
                    break;
                }else {
                    checkDate();
                    checkIfDate();
                }
            } else {
                //obliczanie waluty

                System.out.println("W dniu " + currentVariable1.listCurrency.get(0).getDate() + " " + number1 + " " + currentVariable1.listCurrency.get(0).getName() +
                        " = " + calc(currentVariable1.listCurrency.get(0).getClose(), currentVariable2.listCurrency.get(0).getClose(), number1) + " " + currentVariable2.listCurrency.get(0).getName());


                menuProject.menuPanel();
                break;
            }



        }while(true);

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
        singleCurrent.add("PLN");
        for (Currency currency : list) {
            singleCurrent.add(currency.getName());
        }
        return   singleCurrent;
    }

//sprawdzenie czy istnieje waluta z której chcą wymieniać
    public String checkCurrency() {

        do {
            System.out.println("Wybierz waluę którą chcesz wymienić: ");
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

    //sprawdzenie czy istnieje waluta na którą chcą wymieniać
    public String checkCurrencyOut() {

        do {
            System.out.println("Wybierz dostępną walutę na którą chcesz wymienić ");
            System.out.println(singleCurrency(currentVariable));
            Scanner scanner = new Scanner(System.in);
            currenc2 = scanner.next().trim();
            //sprawdzenie czy istnieje waluta
            for (String cur : singleCurrent) {
                if (cur.equalsIgnoreCase(currenc2)) {

                    check2 = false;
                    break;
                }

            }
            if (check2) {
                System.out.println("Brak waluty w zestawieniu.");
            }
        } while (check2 == true);

        return currenc2;
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

    //przeliczanie walut
    private double calc(double kurs1, double kurs2, double value) {

    Double score=(value*kurs1)/kurs2;
        return  score ;

    }

    public void checkIfDate(){

        for (Currency currency : currentVariable1.dateCurrency) {
//z waluty

            if (currency.getDate().toString().equals(currentVariable1.dateCurrent) && (currency.getName().equalsIgnoreCase(currenc))){
                currentVariable1.listCurrency.add(currency);

            }
//na walute
            if (currency.getDate().toString().equals(currentVariable1.dateCurrent) && (currency.getName().equalsIgnoreCase(currenc2))){
                currentVariable2.listCurrency.add(currency);

            }

        }
    }



}



