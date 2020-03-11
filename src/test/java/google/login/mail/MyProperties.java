package google.login.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyProperties {

    private static Properties properties;

    private MyProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties = properties;
    }

    public static synchronized Properties getInstance(){
        if (properties == null){
            new MyProperties();
        }
        return properties;
    }
}
