package google.login.mail;

import google.login.GMailPrevPage;
import google.login.LoginPage;
import google.login.MainGooglePage;
import google.login.PasswordPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class TestMailPage {

    WebDriver driver = Driver.getInstance();
    Properties properties = MyProperties.getInstance();

    @Before
    public void setUp() {
        driver.get(properties.getProperty("urlStart"));
        System.out.println("Clicking Mail buttton");
        new MainGooglePage(driver).clickMailButton();
        System.out.println("Clicking SignIn Button");
        new GMailPrevPage(driver).clickSignInButton();
        System.out.println("Sending Login");
        new LoginPage(driver).sendLogIn(properties.getProperty("googleLogin"));
        System.out.println("Sending password");
        new PasswordPage(driver).sendPassword(properties.getProperty("googlePass"));
    }

    @Test
    @DisplayName("Send letter test")
    public void testLetterWithLettersCount() {
        Assert.assertTrue(new MailPage(driver)
                .sendLetterWithLettersCount(properties.getProperty("e-mail"),
                        "Тестовое задание", "Афлятунов"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
