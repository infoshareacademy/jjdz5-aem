import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertiesLoader {

    public static AppProperties loadProperties(){
        try(InputStream resourceAsStream = PropertiesLoader.class. getResourceAsStream("app.properties")){
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            return new AppProperties(properties);

        }catch (IOException e) {
            throw new UncheckedIOException(e);
        }catch (NullPointerException e){
            System.out.println("Plik properties nie moze byc znaleziony");
            System.out.println("Parametr getResourceAsStream(name) nie jest spojny z nazwa pliku properties lub plik properties nie znajduje sie w Resources \n");
        }
        return null;
    }
}
