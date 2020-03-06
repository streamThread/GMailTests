package google.login.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.PageFactory.initElements;

public class MailPage {

    private final String secondName = "Aflyatunov";
    private RemoteWebDriver driver;

    @FindBy(xpath = "//div[@role=\"tabpanel\"]/div[3]//tbody")
    private WebElement lettersTable;
    @FindBy(xpath = "//*[@class=\"T-I J-J5-Ji T-I-KE L3\"]")
    private WebElement writeLetterButton;
    @FindBy(xpath = "//div[@role=\"region\"]//form/div[2]")
    private WebElement recepientEmailInputField;
    @FindBy(xpath = "//textarea[@name=\"to\"]")
    private WebElement recepientInputField;
    @FindBy(xpath = "//input[@name=\"subjectbox\"]")
    private WebElement messageSubjectField;
    @FindBy(xpath = "//div[@role=\"textbox\"]")
    private WebElement messageBodyFieild;
    @FindBy(xpath = "//tr/td/div/div[2]/div[@role=\"button\"][1]")
    private WebElement sendMessageButton;
    @FindBy(xpath = "//div[@style]//div[@aria-live=\"assertive\"]//div[2]")
    private WebElement sendingCompleteText;

    public MailPage(RemoteWebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public void sendLetterWithLettersCount(String emeil, String subject) {
        int lettersCount = lettersTable.findElements(By.cssSelector("tr")).size();
        writeLetterButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(messageBodyFieild));
        messageSubjectField.sendKeys(String.format("%s %s", subject, secondName));
        messageBodyFieild.sendKeys("Общее количество писем в ящике:" + lettersCount);
        recepientEmailInputField.click();
        recepientInputField.sendKeys(emeil);
        sendMessageButton.click();
    }
}
