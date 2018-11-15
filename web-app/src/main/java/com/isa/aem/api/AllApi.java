package com.isa.aem.api;


import com.isa.aem.api.nbp.CurrencyDetails;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AllApi {

    ObjectMapper mapper = new ObjectMapper();
    URL url;
    CurrencyDetails currencyDetails=new CurrencyDetails();


    {
        try {
            url = new URL("http://api.nbp.pl/api/exchangerates/rates/A/EUR/today?format=json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    CurrencyDetails obj;

    {
        try {
            String jsonInString = "{\"name\":\"mkyong\",\"salary\":7500,\"skills\":[\"java\",\"python\"]}";
            obj = mapper.readValue(url, CurrencyDetails.class);
            System.out.println(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
