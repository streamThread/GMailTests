package google.login;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestPasswordPage {
    public static RemoteWebDriver driver = null;
    public final String url = "https://www.google.com/gmail/about/";
    public String googleLogin = "vladovaanelya339@gmail.com";
    public String googlePass = "Nq2Tjw5jhowe";

    @Before
    public void setUp() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("videoName", String.format("%s password page tests", time));
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(url);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Password input simple test")
    public void testSendPassword() {
        new GMailPrevPage(driver).clickSignInButton();
        new LoginPage(driver).sendLogIn(googleLogin);
        new PasswordPage(driver).sendPassword(googlePass);
        Assert.assertEquals("Аккаунт Google: Анеля Владова  \n(vladovaanelya339@gmail.com)",
                driver.findElementByXPath("//div[2]/div/a[@aria-label]").getAttribute("aria-label"));
    }

    @Test
    @DisplayName("Wrong password input test")
    public void testSendWrongPassword() {
        new GMailPrevPage(driver).clickSignInButton();
        new LoginPage(driver).sendLogIn(googleLogin);
        new PasswordPage(driver).sendPassword("test");
        Assert.assertEquals("Неверный пароль. Повторите попытку или нажмите на ссылку \"Забыли пароль?\", чтобы сбросить его.",
                driver.findElementByXPath("//div[2]/div[2]/span[@jsslot]").getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
