package google.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class MainGooglePage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@class=\"gb_g\"] [@data-pid=\"23\"]")
    private WebElement mailButton;

    public MainGooglePage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public GMailPrevPage clickMailButton() {
        mailButton.click();
        return new GMailPrevPage(driver);
    }
}
