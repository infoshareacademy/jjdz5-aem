package com.isa.aem.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;


public class ApiNbp {
    public ApiNbp() {
    }

    ObjectMapper mapper = new ObjectMapper();

    String url1="http://api.nbp.pl/api/exchangerates/tables/A/2018-07-11?format=json";
    Staff obj;

    {
        try {
            obj = mapper.readValue(new URL(url1), Staff.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
