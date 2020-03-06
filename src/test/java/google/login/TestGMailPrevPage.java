package google.login;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestGMailPrevPage {

    public static RemoteWebDriver driver = null;
    public final String url = "https://www.google.com/gmail/about/";

    @Before
    public void setUp() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("videoName", String.format("%s prev page tests", time));
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(url);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Click to \"Login\" button test")
    public void testLogIn() {
        new GMailPrevPage(driver).clickSignInButton();
        Assert.assertTrue(driver.findElements(By.xpath("//*[@id=\"identifierId\"]")).size() > 0);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

