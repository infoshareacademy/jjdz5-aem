package com.isa.aem.api;

import com.isa.aem.api.nbp.CurrencyRates;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

public class JsonSchemeReaderNbpApi {
    private static final String BASE_URL =
            "http://api.nbp.pl/api/exchangerates/tables/A/{startDate}/{endDate}";
    private static final String WEB_SLASH = "/";

    public List<CurrencyRates> loadJsonToListWithTwoDates(String startDate, String endDate) {
        Client client = ClientBuilder.newClient();
        try {
            return ClientBuilder.newClient().target(BASE_URL.replace("{startDate}/{endDate}", startDate + WEB_SLASH + endDate))
                    .request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .get().readEntity(new GenericType<ArrayList<CurrencyRates>>() {
                    });
        } finally {
            client.close();
        }
    }

    public List<CurrencyRates> loadActualJson() {
        Client client = ClientBuilder.newClient();
        try {
            return ClientBuilder.newClient().target(BASE_URL.replace("{startDate}/{endDate}", ""))
                    .request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .get().readEntity(new GenericType<ArrayList<CurrencyRates>>() {
                    });
        } finally {
            client.close();
        }
    }
}
