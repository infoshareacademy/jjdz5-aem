import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadProperties(){
        try(InputStream resourceAsStream = PropertiesLoader.class.getResourceAsStream("app.properties")){
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            return properties;

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
