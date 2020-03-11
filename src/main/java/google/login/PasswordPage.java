package google.login;

import google.login.mail.MailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class PasswordPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
    private WebElement inputField;
    @FindBy(xpath = "//*[@id=\"passwordNext\"]/span/span")
    private WebElement nextButton;

    public PasswordPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public MailPage sendPassword(String userPassword) {
        inputField.sendKeys(userPassword);
        nextButton.click();
        return new MailPage(driver);
    }
}
