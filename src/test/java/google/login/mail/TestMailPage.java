package google.login.mail;

import google.login.GMailPrevPage;
import google.login.LoginPage;
import google.login.PasswordPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestMailPage {

    public final String NOT_AUTH_URL = "https://www.google.com/gmail/about/";
    public final String MESSAGE_SEND_LOCATOR = "//div[@style]//div[@aria-live=\"assertive\"]//div[2]//span[text()=\"Письмо отправлено.\"]";
    public RemoteWebDriver driver = null;
    public String googleLogin = "vladovaanelya339@gmail.com";
    public String googlePass = "Nq2Tjw5jhowe";
    WebDriverWait wait;

    @Before
    public void setUp() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm dd.MM.yyyy"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("videoName", String.format("%s mail page tests", time));
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        try {
            driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(NOT_AUTH_URL);
            new GMailPrevPage(driver).clickSignInButton();
            new LoginPage(driver).sendLogIn(googleLogin);
            new PasswordPage(driver).sendPassword(googlePass);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Send letter test")
    public void testLetterWithLettersCount() {
        new MailPage(driver).sendLetterWithLettersCount("oleg_in@bk.ru", "Тестовое задание");
        wait = new WebDriverWait(driver, 30);
        WebElement webEl = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(MESSAGE_SEND_LOCATOR)));
        Assert.assertNotNull(webEl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
