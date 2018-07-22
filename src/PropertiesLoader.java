import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertiesLoader {

    public static AppProperties loadProperties() {
        try(InputStream resourceAsStream = PropertiesLoader.class. getResourceAsStream("app.properties")){
            if(resourceAsStream == null) {
                System.out.println("Plik ustawien aplikacji nie moze byc znaleziony! \n" +
                        "Parametr metody getResourceAsStream(name) nie jest spojny z nazwa pliku properties " +
                        "lub plik properties nie znajduje sie w Resources \n" +
                        "Program zostanie zamkniety");
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
