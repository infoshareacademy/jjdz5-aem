import src.CurrencyRepository;
import src.ContenerDateCurrency;

import java.time.LocalDate;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.lang.Math;
import java.util.Currency;
import src.CurrencyRepository;
import java.util.*;
import static java.lang.Integer.parseInt;

public class LocalExtremum
{
    public localExtremum() {
        CurrencyRepository.getCurrencies();


        GlobalExtreme.SortCurrency sortCurrency = new GlobalExtreme.SortCurrency();
         CurrencyRepository currencyRepository = new CurrencyRepository();
        List<Currency> singleCurrency = new ArrayList<>();




        class SortCurrency implements Comparator<Currency> {
            @Override
            public int compare(Currency first, Currency second) {
                return first.getClose().compareTo(second.getClose());

            }

            private void sortSingleCurrency( List<Currency> singleCurrency)
            {
                Collections.sort(singleCurrency,sortCurrency);
            }
        }
    public void limitData()
        {
            System.out.println("Podaj datę od której chcesz zacząć w formacie yyyy-mm-dd:");

            Scanner scanner = new Scanner(System.in);

            String rtDate = scanner.next();

            System.out.println("Podaj datę na której chcesz skończyć w formacie yyyy-mm-dd:");

            Scanner scannr = new Scanner(System.in);

            String dDate = scannr.next();

            //Integer startDate = parseInt(rtDate);
            //Integer endDate = parseInt(dDate);

           boolean before = Currency.getDate().before(rtDate);
           boolean after = Currency.getDate().after(dDate);
if (before == false & after == false)
{
    for (Currency sort : CurrencyRepository.getCurrencies()) {



        System.out.println("     " + sort.getAvailableCurrencies() + "        " + rtDate + "   " + dDate + " " +Math.Min(Currency.GetValue)+ " " + Math.Max(Currency.getValue));

    }




}




        }


    }




}