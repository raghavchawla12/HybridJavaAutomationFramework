package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.PageObjectUtils;

import java.util.List;

public class OrderPlaced extends PageObjectUtils {

    WebDriver driver;

    public OrderPlaced(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Wait Locators

    By orderSuccessfulTextWait = By.cssSelector("h1.hero-primary");

    // Locators

    @FindBy(css = "h1.hero-primary")
    private WebElement successfulOrderText;

    @FindBy(css = "label.ng-star-inserted")
    private WebElement productId;

    // Actions
    public String getSuccessfulOrderText(){
        return successfulOrderText.getText().trim();
    }

    public String getProductIdText(){
        return productId.getText().trim().split(" ")[1];
    }



}
