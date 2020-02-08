import lombok.Getter;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


@Getter
public class BaseTest {
    private Properties properties;
    final static Logger logger = Logger.getLogger(BaseTest.class);

    public BaseTest() {
        readConfig();
    }

    private void readConfig() {
        properties = new Properties();
        try {
            FileInputStream ip = new FileInputStream("c:\\java\\qa3\\src\\main\\resources\\config.properties");
            properties.load(ip);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
