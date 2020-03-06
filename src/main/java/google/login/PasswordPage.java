package google.login;

import google.login.mail.MailPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class PasswordPage {

    private RemoteWebDriver driver;
    @FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
    private WebElement inputField;
    @FindBy(xpath = "//*[@id=\"passwordNext\"]/span/span")
    private WebElement nextButton;
    public PasswordPage(RemoteWebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public WebElement getInputField() {
        return inputField;
    }

    private PasswordPage inputPassword(String userPassword) {
        inputField.sendKeys(userPassword);
        return this;
    }

    private MailPage clickNextButton() {
        nextButton.click();
        return new MailPage(driver);
    }

    public MailPage sendPassword(String userPassword) {
        inputPassword(userPassword);
        clickNextButton();
        return new MailPage(driver);
    }

}
