package com.infoshareacademy.aem;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppProperties {

    private Properties properties;

    public AppProperties(Properties properties) {
        this.properties = properties;
    }

    public String getVersion() {
        return properties.getProperty("version");
    }

    public String getSourceFilePath() {
        Pattern pattern = Pattern.compile("^.*\\.(txt|csv)$");
        Matcher matcher = pattern.matcher(properties.getProperty("sourceFilePath"));
        if (!matcher.matches()) {
            System.out.println("Plik z danymi ma nieobsługiwane rozszerzenie! \n" +
                    "Wybierz plik z rozszerzeniem .txt lub .csv");
            try {
                System.out.println("Nacisnij Enter aby wyjść z programu");
                System.in.read();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
        return properties.getProperty("sourceFilePath");
    }
}
