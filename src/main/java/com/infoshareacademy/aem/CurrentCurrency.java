package com.infoshareacademy.aem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CurrentCurrency {

    CurrentVariable currentVariable = new CurrentVariable();

    private List<Currency> readFile() {
        for (Currency currency : CurrencyRepository.getCurrencies()) {
            currentVariable.dateCurrency.add(currency);
        }
        return currentVariable.dateCurrency;
    }

    public void currentCash() {
        readFile();
        currentDate();
        checkDate();


        System.out.println("    ****************************************");
        System.out.println("    *            EXCHANGE RATE             *");
        System.out.println("    ****************************************");
        System.out.println("        currency              amount         ");
        System.out.println("                                            ");

        for (Currency currency : currentVariable.dateCurrency) {

            if (currency.getDate().toString().equals(currentVariable.dateCurrent)) {
                currentVariable.listCurrency.add(currency);


            }

        }

        //sort currency rate
        currentVariable.listCurrency.sort(new SortCurrency());

        for (Currency current : currentVariable.listCurrency) {
            System.out.println("        " + current.getName() + "                  " + current.getClose());
        }

//check date that she show again
        if (currentVariable.listCurrency.isEmpty()) {
            System.out.println("The file does not contain currency rate for the selected day");
        }

        MenuProject menuProject = new MenuProject();
        menuProject.menuPanel();
    }

    private void currentDate() {

        sortCurrency();
        System.out.println("    ****************************************");
        System.out.println("    *            RANGE CURRENCY            *");
        System.out.println("    ****************************************");
        System.out.println("     currency    min date      max date      ");
        System.out.println("                                            ");
        for (ContenerDateCurrency sort : sortCurrency()) {
            System.out.println("     " + sort.current + "        " + sort.dateMin + "   " + sort.dateMax);
        }
    }

    //check which day of week write user, if day is in weekend choose currency rate from Friday
    private String checkDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the date in the format: yyyy-mm-dd:");
        boolean exception = true;
        while (exception) {
            try {
                currentVariable.localTime1 = LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE);
                exception = false;
            } catch (Exception e) {
                System.out.println("Incorrect date format");
                System.out.println("Write the date again in the format: yyyy-mm-dd: ");
            }
        }
        switch (currentVariable.localTime1.getDayOfWeek()) {
            case SUNDAY:
                currentVariable.dateCurrent = currentVariable.localTime1.minusDays(2).format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;
            case SATURDAY:
                currentVariable.dateCurrent = currentVariable.localTime1.minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;
            default:
                currentVariable.dateCurrent = currentVariable.localTime1.format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;


        }

        return currentVariable.dateCurrent;
    }

    //date range for each currency
    private List<ContenerDateCurrency> sortCurrency() {
        Set<String> uniqueCurrent = new TreeSet<>();
        for (Currency cur : currentVariable.dateCurrency) {
            uniqueCurrent.add(cur.getName());
        }

        List<ContenerDateCurrency> contenerDateCurrencies = new ArrayList<>();
        for (String current : uniqueCurrent) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate dateMin = LocalDate.parse("40000101", dateTimeFormatter);
            LocalDate dateMax = LocalDate.parse("19990101", dateTimeFormatter);


            for (int i = 0; i < currentVariable.dateCurrency.size(); i++) {

                if (current.equalsIgnoreCase(currentVariable.dateCurrency.get(i).getName()) && currentVariable.dateCurrency.get(i).getDate().isBefore(dateMin)) {
                    dateMin = currentVariable.dateCurrency.get(i).getDate();
                }
                if (current.equalsIgnoreCase(currentVariable.dateCurrency.get(i).getName()) && currentVariable.dateCurrency.get(i).getDate().isAfter(dateMax)) {
                    dateMax = currentVariable.dateCurrency.get(i).getDate();
                }

            }

            contenerDateCurrencies.add(new ContenerDateCurrency(current, dateMin, dateMax));
        }
        return contenerDateCurrencies;

    }


}
