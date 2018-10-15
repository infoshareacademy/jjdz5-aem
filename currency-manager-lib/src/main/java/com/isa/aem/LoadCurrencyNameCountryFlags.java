package com.isa.aem;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadCurrencyNameCountryFlags {

    private String filePath;
    private Map<String, CurrencyNameCountryFlags> currenciesWithFlags = new HashMap<>();
    public Map<String, CurrencyNameCountryFlags> currencyInformation = new HashMap<>();

    public LoadCurrencyNameCountryFlags() {
        readFile();
    }

    public void readFile() {
        AppProperties appProperties = PropertiesLoader.loadProperties();
        this.filePath = appProperties.getSourceFilePath("sourceFlags");
        Path path = Paths.get(filePath).toAbsolutePath();

        List<String> allLinesAsString = new ArrayList<>();

        try {
            allLinesAsString = Files.readAllLines(path);

        } catch (IOException e) {
            e.printStackTrace();

        }

        currencyInformation = importCurrencyNameCountryFlags(allLinesAsString);
        CurrencyNameCountryFlags.setCurrencies(currencyInformation);

    }

    public Map<String, CurrencyNameCountryFlags> importCurrencyNameCountryFlags(List<String> read) {
        for (String oneLine : read) {

            String[] line = oneLine.split(",");
            String currencyName = line[0];
            CurrencyNameCountryFlags currencyFlags = new CurrencyNameCountryFlags(
                    line[0],
                    line[1],
                    line[2],
                    line[3]

            );
            currenciesWithFlags.put(currencyName, currencyFlags);
        }

        return currenciesWithFlags;
    }
}