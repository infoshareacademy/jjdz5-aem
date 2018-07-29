import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesLoader {

    public static AppProperties loadProperties() {
        try(InputStream resourceAsStream = PropertiesLoader.class. getResourceAsStream("app.properties")){
            if (resourceAsStream == null) {
                System.out.println("Plik ustawień aplikacji nie może byc znaleziony! \n" +
                        "Parametr metody getResourceAsStream(name) nie jest spójny z nazwą pliku properties " +
                        "lub plik properties nie znajduję się w Resources \n" +
                        "Wyjdź z programu, popraw i uruchom ponownie \n");
                try {
                    System.out.println("Nacisnij Enter aby wyjść z programu");
                    System.in.read();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            } else {
                Properties properties = new Properties();
                properties.load(resourceAsStream);

                return new AppProperties(properties);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return null;
    }
}
