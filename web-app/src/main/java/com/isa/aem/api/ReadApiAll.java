package com.isa.aem.api;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ReadApiAll {
    URL url;

    {
        try {
            url = new URL("http://api.nbp.pl/api/exchangerates/tables/A/2018-07-11?format=json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    {
        try {
            InputStream is = url.openStream();
            JsonReader rdr = Json.createReader(is);
            JsonArray dane=rdr.readArray();

            for (JsonObject result : dane.getValuesAs(JsonObject.class)) {
                System.out.println(result.getString("no", ""));
                System.out.println(result.getString("effectiveDate", ""));
                JsonArray rates=result.getJsonArray("rates");
                for (JsonObject result1 : rates.getValuesAs(JsonObject.class)) {
                    System.out.println(result1.getString("currency", ""));
                    System.out.println(result1.getString("code", ""));
                    System.out.println(result1.getJsonNumber("mid"));
                }
            }
            is.close();
            rdr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
