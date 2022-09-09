package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.PageObjectUtils;

public class CartPage extends PageObjectUtils {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    PaymentPage payment = new PaymentPage(driver);

    // Wait Locators

    By buyNowButtonWait = By.cssSelector("div.cartSection.removeWrap button.btn.btn-primary");

    // Locators

    @FindBy(css = "div.cartSection.removeWrap button.btn.btn-primary")
    private WebElement buyNowButton;

    // Actions

    public void clickOnBuyNowButton(){
        buyNowButton.click();
        elementToClickableWait(payment.placeOrderButtonWait, 30);
    }
}
