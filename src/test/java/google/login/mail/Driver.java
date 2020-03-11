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

    private static WebDriver driver;

    private Driver() {
        try {
            driver = createDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized WebDriver getInstance() {
        if (driver == null) {
            new Driver();
        }
        return driver;
    }

    private WebDriver createDriver() throws MalformedURLException {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("videoName", String.format("%s_mail_page_tests", time));
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        RemoteWebDriver tmpDriver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
        tmpDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        tmpDriver.manage().window().maximize();
        return tmpDriver;
    }
}
