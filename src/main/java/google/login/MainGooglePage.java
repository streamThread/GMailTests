package google.login;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class MainGooglePage {

    private RemoteWebDriver driver;

    @FindBy(xpath = "//a[@class=\"gb_g\"] [@data-pid=\"23\"]")
    private WebElement mailButton;

    public MainGooglePage(RemoteWebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public GMailPrevPage clickMailButton() {
        mailButton.click();
        return new GMailPrevPage(driver);
    }
}
