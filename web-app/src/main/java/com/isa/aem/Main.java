package com.isa.aem;

import com.isa.aem.calc.AlgorithmCurrencyConversion;
import com.isa.aem.calc.DateService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        CurrencyRepository cc=new CurrencyRepository();
        AlgorithmCurrencyConversion aa=new AlgorithmCurrencyConversion();
        FileContentReader fileContentReader = new FileContentReader();
        fileContentReader.readFile();
        fileContentReader.addPLNToListCurrency();
        DateService dataService = new DateService();
        String data="2018-01-01";

        LocalDate date1 = dataService.dataParse(   data.replace("-",""));
        System.out.println(date1);

    }


}
