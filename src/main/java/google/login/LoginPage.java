package google.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class LoginPage {

    private RemoteWebDriver driver;
    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    private WebElement inputField;
    @FindBy(xpath = "//*[@class=\"RveJvd snByac\"]")
    private WebElement nextButton;

    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    private LoginPage inputLogin(String userLogin) {
        inputField.sendKeys(userLogin);
        return this;
    }

    private PasswordPage clickNextButton() {
        nextButton.click();
        return new PasswordPage(driver);
    }

    public PasswordPage sendLogIn(String userLogIn) {
        inputLogin(userLogIn);
        clickNextButton();
        return new PasswordPage(driver);
    }

}
