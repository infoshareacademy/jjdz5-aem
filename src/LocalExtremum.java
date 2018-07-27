import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.lang.Math;
import static java.lang.Integer.parseInt;

public class LocalExtremum
{
    public localExtremum()
    {
        local();
    }


    private SortCurrency sortCurrency = new SortCurrency();

    private List<Currency> singleCurrency = new ArrayList<>();
    public void local() {


        System.out.println("Podaj walute");

        Scanner scanner = new Scanner(System.in);

        String waluta = scanner.next();
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        for (Currency currency : fileContentReader.getListOfCurrencies()) {
            if (currency.getName().equalsIgnoreCase(waluta)) {
                singleCurrency.add(currency);
            }
        }
        for (Currency currency : singleCurrency) {
            System.out.println(currency);
        }
        sortSingleCurrency(singleCurrency);
    }

        private void sortSingleCurrency(List<Currency> list) {
            Collections.sort(list,sortCurrency);
        }

    class SortCurrency implements Comparator<Currency> {
        @Override
        public int compare(Currency first, Currency second) {
            return first.getClose().compareTo(second.getClose());

            }
    }
    public limitData()
    {
        System.out.println("Podaj datę od której chcesz zacząć w formacie yyyy-mm-dd:");

        Scanner scanner = new Scanner(System.in);

        String rtDate = scanner.next();

        System.out.println("Podaj datę na której chcesz skończyć w formacie yyyy-mm-dd:");

        Scanner scannr = new Scanner(System.in);

        String dDate = scannr.next();

        Integer startDate = parseInt(rtDate);
        Integer endDate = parseInt(dDate);

        for( ContenerDateCurrency sort : sortCurrency()) {
            System.out.println("     " + sort.current + "        " + sort.dateMin + "   " + sort.dateMax + " " + Math.Min(singleCurrency) +" " + Math.Max(singleCurrency));
        }

    }








}


