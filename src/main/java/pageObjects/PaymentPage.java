package pageObjects;

import org.checkerframework.checker.calledmethods.qual.EnsuresCalledMethodsIf;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.PageObjectUtils;

import java.util.List;

public class PaymentPage extends PageObjectUtils{
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    OrderPlaced op = new OrderPlaced(driver);
    // Wait Locators

    By placeOrderButtonWait = By.cssSelector("a.btnn.action__submit.ng-star-inserted");

    // Locators

    @FindBy(xpath = "//div[2]/div[2]/input")
    private WebElement cvvField;

    @FindBy(xpath = "//form/div/div[3]/div/input")
    private WebElement nameOnCard;

    @FindBy(css = "[placeholder = 'Select Country']")
    private WebElement findCountryDropdown;

    @FindBy(css = "a.btnn.action__submit.ng-star-inserted")
    private WebElement placeOrderButton;

    @FindBy(css = "button.ta-item.list-group-item.ng-star-inserted")
    private List<WebElement> selectCountryNameFromDropdown;

    // Actions

    public void placeOrder(String cvv, String cardHolderName, String countryName)
            throws InterruptedException {
        cvvField.sendKeys(cvv);
        nameOnCard.sendKeys(cardHolderName);
        findCountryDropdown.sendKeys(countryName);
        Thread.sleep(2000);
        for (WebElement country: selectCountryNameFromDropdown){
            if (country.getText().trim().equalsIgnoreCase(countryName)){
                country.click();
                break;
            }
        }
            executeJavaScript("arguments[0].click();", placeOrderButton);
            presenceOfElementWait(op.orderSuccessfulTextWait, 30);

    }


}
