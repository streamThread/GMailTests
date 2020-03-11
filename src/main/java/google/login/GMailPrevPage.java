package google.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

import static org.openqa.selenium.support.PageFactory.initElements;

public class GMailPrevPage {

    private WebDriver driver;

    @FindBy(xpath = "//li[@class=\"h-c-header__nav-li g-mail-nav-links\"]/a[@ga-event-action=\"sign in\"]")
    private WebElement signInButton;

    public GMailPrevPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public LoginPage clickSignInButton() {
        Set<String> handles = driver.getWindowHandles();
        signInButton.click();
        Set<String> newHandles = driver.getWindowHandles();
        newHandles.removeAll(handles);
        driver.switchTo().window(newHandles.iterator().next());
        return new LoginPage(driver);
    }
}
