import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertiesLoader {

    public static AppProperties loadProperties(){
        try(InputStream resourceAsStream = PropertiesLoader.class.getResourceAsStream("app.properties")){
            Properties properties = new Properties();
            properties.load(resourceAsStream);

            return new AppProperties(properties);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
