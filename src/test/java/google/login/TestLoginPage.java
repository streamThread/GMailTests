package google.login;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestLoginPage {
    public static RemoteWebDriver driver = null;
    public final String url = "https://accounts.google.com/";
    public String googleLogin = "vladovaanelya339@gmail.com";

    @Before
    public void setUp() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("videoName", String.format("%s_login_page_tests", time));
        capabilities.setCapability("name", LocalDate.now().toString());
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(url);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Login input simple test")
    public void testSendLogin() {
        new LoginPage(driver).sendLogIn(googleLogin);
        String login = new WebDriverWait(driver, 10).until(ExpectedConditions
                .visibilityOf(driver.findElementByXPath("//div[@id=\"profileIdentifier\"]"))).getText();
        Assert.assertEquals(googleLogin, login);
    }

    @Test
    @DisplayName("Wrong login input test")
    public void testSendWrongLogin() {
        new LoginPage(driver).sendLogIn("test");
        Assert.assertEquals("Не удалось найти аккаунт Google",
                driver.findElementByXPath("//span[@jsslot]//div[2]//div[2]//div").getText());
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
