

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CurrentCurrency {
    LocalDate localTime1 = LocalDate.now() ;
    String dateCurrent;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");


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
         System.out.println("Plik nie posiada bieżącego kursu");
     }


 }

 private String checkDate(){

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
