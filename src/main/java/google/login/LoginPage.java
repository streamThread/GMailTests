package google.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class LoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    private WebElement inputField;
    @FindBy(xpath = "//*[@class=\"RveJvd snByac\"]")
    private WebElement nextButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public PasswordPage sendLogIn(String userLogIn) {
        inputField.sendKeys(userLogIn);
        nextButton.click();
        return new PasswordPage(driver);
    }

}
