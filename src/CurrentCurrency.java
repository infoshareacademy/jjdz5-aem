

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CurrentCurrency {

    LocalDate localTime1;
    String dateCurrent;
    List<Currency>  listCurrency=new ArrayList<>();
    List<Currency>  dateCurrency=new ArrayList<>();


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

public List<Currency> readFile(){
    FileContentReader fileContentReader = new FileContentReader();
    fileContentReader.readFile();
    for (Currency currency : fileContentReader.getListOfCurrencies()) {
        dateCurrency.add(currency);
    }
      return   dateCurrency;
}

    public void currentCash() {

        checkDate();

        System.out.println("    ****************************************");
        System.out.println("    *        CURRENCY RATES                *");
        System.out.println("    ****************************************");
        System.out.println("        currency            value           ");
        System.out.println("                                            ");

     for (Currency currency : dateCurrency) {

         if (currency.getDate().toString().equals(dateCurrent)) {
             listCurrency.add(currency);
             System.out.println("        " + currency.getName() + "                  " + currency.getClose());

         }

     }

     if (listCurrency.isEmpty()){
         System.out.println("Plik nie posiada kursu ze wskazanego dnia");
     }


 }

    public void currentDate() {


        System.out.println("    ****************************************");
        System.out.println("    *        CURRENCY DATE                 *");
        System.out.println("    ****************************************");
        System.out.println("     currency    date min     date max      ");
        System.out.println("                                            ");
        for( ContenerDateCurrency sort : sortCurrency()) {
            System.out.println("     " + sort.current + "        " + sort.dateMin + "   " + sort.dateMax);
        }
    }
//sprawdzenie czy wskazana daata wypada w sobote lub niedziele, jeżeli tak to wybieramy kurs z piątku
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

 //dodanie zakresu czasowego do walut znajdujących się w pliku
        public List<ContenerDateCurrency> sortCurrency(){
        Set<String> uniqueCurrent=new HashSet<>();
        for(Currency cur: dateCurrency){
            uniqueCurrent.add(cur.getName());
        }
            List<ContenerDateCurrency>contenerDateCurrencies=new ArrayList<>();
        for (String current:uniqueCurrent){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate dateMin=LocalDate.parse("40000101",dateTimeFormatter);
            LocalDate dateMax=LocalDate.parse("19990101",dateTimeFormatter);


           for(int i=0; i<dateCurrency.size(); i++){

               if(current.equalsIgnoreCase(dateCurrency.get(i).getName()) && dateCurrency.get(i).getDate().isBefore(dateMin)){
                   dateMin=dateCurrency.get(i).getDate();
               }
               if(current.equalsIgnoreCase(dateCurrency.get(i).getName()) && dateCurrency.get(i).getDate().isAfter(dateMax)){
                   dateMax=dateCurrency.get(i).getDate();
               }

           }

            contenerDateCurrencies.add(new ContenerDateCurrency(current, dateMin,dateMax));
        }
            return contenerDateCurrencies;

        }

}
