
import java.util.Properties;

public class AppProperties {

    private Properties properties;

    public AppProperties(Properties properties) {
        this.properties = properties;
    }

    public String getVersion() {
        return properties.getProperty("version");
    }

    public String getSourceFilePath(){
        return properties.getProperty("sourceFilePath");
    }
}
