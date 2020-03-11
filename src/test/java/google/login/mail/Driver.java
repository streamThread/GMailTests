package google.login.mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static DesiredCapabilities capabilities;

    private Driver() {
    }

    private static DesiredCapabilities createCapabilities() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        DesiredCapabilities tmpCapabilities = new DesiredCapabilities();
        tmpCapabilities.setBrowserName("chrome");
        tmpCapabilities.setVersion("80.0");
        tmpCapabilities.setCapability("videoName", String.format("%s_mail_page_tests", time));
        tmpCapabilities.setCapability("enableVNC", true);
        tmpCapabilities.setCapability("enableVideo", true);
        return tmpCapabilities;
    }

    public static synchronized WebDriver createDriver() {
        if (capabilities == null) {
            capabilities = createCapabilities();
        }
        RemoteWebDriver tmpDriver = null;
        try {
            tmpDriver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
            tmpDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            tmpDriver.manage().window().maximize();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return tmpDriver;
    }
}
