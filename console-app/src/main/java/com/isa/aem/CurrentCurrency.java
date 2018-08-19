package com.isa.aem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CurrentCurrency {

    CurrentVariable currentVariable = new CurrentVariable();

    private List<com.isa.aem.Currency> readFile() {
        for (com.isa.aem.Currency currency : CurrencyRepository.getCurrencies()) {
            currentVariable.dateCurrency.add(currency);
        }
        return currentVariable.dateCurrency;
    }

    public void currentCash() {
        readFile();
        currentDate();
        checkDate();


        System.out.println("    ****************************************");
        System.out.println("    *           KURSY WALUT                *");
        System.out.println("    ****************************************");
        System.out.println("        waluta              wartość         ");
        System.out.println("                                            ");

        for (com.isa.aem.Currency currency : currentVariable.dateCurrency) {

            if (currency.getDate().toString().equals(currentVariable.dateCurrent)) {
                currentVariable.listCurrency.add(currency);


            }

        }

        //sortowanie listy walut z datą wskazaną przez użytkownika
        currentVariable.listCurrency.sort(new SortCurrency());

        for (com.isa.aem.Currency current : currentVariable.listCurrency) {
            System.out.println("        " + current.getName() + "                  " + current.getClose());
        }

//sprawdzenie czy data wystąpiła
        if (currentVariable.listCurrency.isEmpty()) {
            System.out.println("Plik nie posiada kursu ze wskazanego dnia");
        }

        MenuProject menuProject = new MenuProject();
        menuProject.menuPanel();
    }

    private void currentDate() {

        sortCurrency();
        System.out.println("    ****************************************");
        System.out.println("    *            ZAKRES WALUT              *");
        System.out.println("    ****************************************");
        System.out.println("     waluta    data min     data max      ");
        System.out.println("                                            ");
        for (ContenerDateCurrency sort : sortCurrency()) {
            System.out.println("     " + sort.current + "        " + sort.dateMin + "   " + sort.dateMax);
        }
    }

    //sprawdzenie czy wskazana daata wypada w sobote lub niedziele, jeżeli tak to wybieramy kurs z piątku
    private String checkDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj datę w formacie yyyy-mm-dd:");
        boolean exception = true;
        while (exception) {
            try {
                currentVariable.localTime1 = LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE);
                exception = false;
            } catch (Exception e) {
                System.out.println("Nieprawidłowy format daty");
                System.out.println("Wpisz ponownie poprawny format daty w formacie yyyy-mm-dd: ");
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

    //dodanie zakresu czasowego do walut znajdujących się w pliku
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
