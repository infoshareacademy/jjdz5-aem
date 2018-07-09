

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CurrentCurrency {

    LocalDate localTime1;
    String dateCurrent;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentCurrency that = (CurrentCurrency) o;
        return Objects.equals(localTime1, that.localTime1) &&
                Objects.equals(dateCurrent, that.dateCurrent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(localTime1, dateCurrent);
    }


    public void currentCash() {
     FileContentReader fileContentReader = new FileContentReader();
     fileContentReader.readFile();
     Map<String,Double> map=new HashMap<>();
        checkDate();

        System.out.println("    ****************************************");
        System.out.println("    *        CURRENCY RATES                *");
        System.out.println("    ****************************************");
        System.out.println("        currency            value           ");
        System.out.println("                                            ");

     for (Currency currency : fileContentReader.getListOfCurrencies()) {

         if (currency.getDate().toString().equals(dateCurrent)) {
             map.put(currency.getName(), currency.getClose());
             System.out.println("        " + currency.getName() + "                  " + currency.getClose());
         }

     }

     if (map.isEmpty()){
         System.out.println("Plik nie posiada kursu ze wskazanego dnia");
     }


 }

 private String checkDate() {
     Scanner scanner = new Scanner(System.in);
     System.out.println("Podaj datę w formacie yyyy-mm-dd:");
     boolean exception=true;
    while (exception) {
        try {
            localTime1 = LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE);
            exception=false;
        } catch (Exception e) {
            System.out.println("Nieprawidłowy format daty");
            System.out.println("Wpisz ponownie poprawny format daty w formacie yyyy-mm-dd: ");
        }
    }
        switch (localTime1.getDayOfWeek()){
            case SUNDAY: dateCurrent=localTime1.minusDays(2).format(DateTimeFormatter.ISO_LOCAL_DATE);
            break;
            case SATURDAY:dateCurrent=localTime1.minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
            break;
            default: dateCurrent=localTime1.format(DateTimeFormatter.ISO_LOCAL_DATE);
            break;


        }

        return dateCurrent;
 }

}
