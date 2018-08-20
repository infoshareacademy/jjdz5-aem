package com.isa.aem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Calculator {
    Currency cc=new Currency();
    CurrentVariable currentVariable1 = new CurrentVariable();
    CurrentVariable currentVariable2 = new CurrentVariable();
    List<Currency> currentVariable = new ArrayList<Currency>();
    Set<String> singleCurrent = new TreeSet<>();
    Boolean check = true;
    Boolean check2 = true;
    String currenc;
    String currenc2;
    Double number1;
    MenuProject menuProject = new MenuProject();

    public Calculator() {

        readFile();
        checkCurrency();
        checkCurrencyOut();
        checkNumber();
        checkDate();
        checkIfDate();


//check  if a date exist
        do {

            if (currenc.equalsIgnoreCase("PLN") && currentVariable2.listCurrency.size() > 0) {
                System.out.println(currentVariable2.toString() + " " + number1 + " " + currenc.trim().toUpperCase() +
                        " = " + number1 / currentVariable2.listCurrency.get(0).getClose() + " " + currentVariable2.listCurrency.get(0).getName() + "\n");
                menuProject.menuPanel();
                break;

            } else if (currenc2.equalsIgnoreCase("PLN") && currentVariable1.listCurrency.size() > 0) {
                System.out.println(currentVariable1.toString() + " " + number1 + " " + currenc.trim().toUpperCase() +
                        " = " + number1 * currentVariable1.listCurrency.get(0).getClose() + " " + currenc2 + "\n");
                menuProject.menuPanel();
                break;
            } else if (currentVariable1.listCurrency.isEmpty() || currentVariable2.listCurrency.isEmpty()) {
                System.out.println("The file does not contain currency for the selected day");
                System.out.println("If you want to go back to the Menu, type 1");
                System.out.println("If you want to stay, press Enter");


                Scanner scanner4 = new Scanner(System.in);
                if (scanner4.nextInt()==1) {
                    MenuProject menuProject = new MenuProject();
                    menuProject.menuPanel();
                    break;
                } else {
                    checkDate();
                    checkIfDate();
                }
            } else {
                //currency conversion

                System.out.println(currentVariable1.toString() + " " + number1 + " " + currentVariable1.listCurrency.get(0).getName() +
                        " = " + calc(currentVariable1.listCurrency.get(0).getClose(), currentVariable2.listCurrency.get(0).getClose(), number1) + " " + currentVariable2.listCurrency.get(0).getName() + "\n");


                menuProject.menuPanel();
                break;
            }


        } while (true);

    }


    //add currency to ArrayList
    public List<Currency> readFile() {

        for (Currency currency : CurrencyRepository.getCurrencies()) {
            currentVariable.add(currency);
            currentVariable1.dateCurrency.add(currency);
        }
        return currentVariable;
    }
//print out single currency

    public Set<String> singleCurrency(List<Currency> list) {
        singleCurrent.add("PLN");
        for (Currency currency : list) {
            singleCurrent.add(currency.getName());
        }
        return singleCurrent;
    }

    //check if currency exist (currency convert from)
    public String checkCurrency() {

        do {
            System.out.println("Choose currency to convert from: ");
            System.out.println(singleCurrency(currentVariable));
            Scanner scanner = new Scanner(System.in);
            currenc = scanner.next().trim();
            //check whether the currency exist
            for (String cur : singleCurrent) {
                if (cur.equalsIgnoreCase(currenc)) {
                    check = false;
                    break;
                }

            }
            if (check) {
                System.out.println("The file does not contain the selected currency");
            }
        } while (check == true);

        return currenc;
    }

    //check if currency exist - currency convert to
    public String checkCurrencyOut() {

        do {
            System.out.println("Choose currency to convert to: ");
            System.out.println(singleCurrency(currentVariable));
            Scanner scanner = new Scanner(System.in);
            currenc2 = scanner.next().trim();
            //check if currency exist
            for (String cur : singleCurrent) {
                if (cur.equalsIgnoreCase(currenc2)) {

                    check2 = false;
                    break;
                }

            }
            if (check2) {
                System.out.println("The file does not contain the selected currency");
            }
        } while (check2 == true);

        return currenc2;
    }

    //check whether the typed value is a number
    public Double checkNumber() {

        do {
            System.out.println("Type the amount you want to convert " +  currenc);

            Scanner scanner = new Scanner(System.in);
            String number = scanner.next().trim().replace(",", ".");


            //check whether the typed amount is a number
            if (number.matches("[0-9 .]+")) {
                number1 = Double.parseDouble(number);
                break;

            } else {
                System.out.println("The value you entered is not a number");
            }
        } while (true);

        return number1;
    }

    private String checkDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the date of exchange rate you want to use. Please use the following format: yyyy-mm-dd: ");
        boolean exception = true;
        while (exception) {
            try {
                currentVariable1.localTime1 = LocalDate.parse(scanner.next(), DateTimeFormatter.ISO_LOCAL_DATE);
                exception = false;
            } catch (Exception e) {
                System.out.println("Incorrect date format");
                System.out.println("Type the date again in the following format: yyyy-mm-dd: ");
            }
        }
        switch (currentVariable1.localTime1.getDayOfWeek()) {
            case SUNDAY:
                currentVariable1.dateCurrent = currentVariable1.localTime1.minusDays(2).format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;
            case SATURDAY:
                currentVariable1.dateCurrent = currentVariable1.localTime1.minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;
            default:
                currentVariable1.dateCurrent = currentVariable1.localTime1.format(DateTimeFormatter.ISO_LOCAL_DATE);
                break;


        }

        return currentVariable1.dateCurrent;
    }

    //currency conversion
    private double calc(double kurs1, double kurs2, double value) {

        Double score = (value * kurs1) / kurs2;
        return score;

    }

    public void checkIfDate() {

        for (Currency currency : currentVariable1.dateCurrency) {
//from currency

            if (currency.getDate().toString().equals(currentVariable1.dateCurrent) && (currency.getName().equalsIgnoreCase(currenc))) {
                currentVariable1.listCurrency.add(currency);

            }
//to currency
            if (currency.getDate().toString().equals(currentVariable1.dateCurrent) && (currency.getName().equalsIgnoreCase(currenc2))) {
                currentVariable2.listCurrency.add(currency);

            }

        }
    }


}



