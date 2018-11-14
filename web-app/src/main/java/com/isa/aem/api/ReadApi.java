package com.isa.aem.api;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadApi {
    URL url;

    {
        try {
            url = new URL("http://api.nbp.pl/api/exchangerates/rates/A/EUR/today?format=json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    {
        try (
                InputStream is = url.openStream();
                JsonReader rdr = Json.createReader(is)) {

            JsonObject obj = rdr.readObject();

            System.out.println(obj.getJsonString("table"));
            System.out.println(obj.getJsonString("currency"));
            System.out.println(obj.getJsonString("code"));

            JsonArray results = obj.getJsonArray("rates");
            System.out.println(results);
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                System.out.println(result.getString("no", ""));
                System.out.println(result.getString("effectiveDate", ""));
                System.out.println(result.getJsonNumber("mid"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
