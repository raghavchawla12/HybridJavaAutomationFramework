package pageObjects;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.PageObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends PageObjectUtils {

	WebDriver driver;

	public MainPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	CartPage cp = new CartPage(driver);

	// Wait Locators
	By homeButtonWait = By.cssSelector("li:nth-child(1) button.btn.btn-custom");
	By productAddedToCartWait = By.cssSelector("[aria-label = 'Product Added To Cart']");

	// Locators

	@FindBy(css = "li:nth-child(3) button.btn.btn-custom")
	private WebElement ordersButton;

	@FindBy(xpath = "//div/div[@class = 'card'][1]/div/h5/b")
	private List<WebElement> allProductsName;

	@FindBy(xpath = "//div/div/div/button[2]")
	private List<WebElement> addtoCartButton;

	@FindBy(css = "li:nth-child(4) button.btn.btn-custom")
	private WebElement cartButton;

	// Actions

	public String getOrdersButtonText(){
		return ordersButton.getText().trim();
	}

	public void clickOnOrdersButton(){
		ordersButton.click();
	}

	public List<String> storeAllProductsNameInAList(){
		List<String> uiProductsNameList = new ArrayList<String>();
		for(WebElement productName : allProductsName){
			String text = productName.getText();
			uiProductsNameList.add(text);
		}
		return uiProductsNameList;
	}

	public void addTestProductToCart(String productNameAPI) {
		for (int i = 0; i < allProductsName.size(); i++){
			if (allProductsName.get(i).getText().trim().equalsIgnoreCase(productNameAPI)){
				WebElement locatorToClick = addtoCartButton.get(i);
				executeJavaScript("arguments[0].click();", locatorToClick);
				break;
			}
		}
			presenceOfElementWait(productAddedToCartWait, 5);
		}

	public void clickOnCartButton(){
		cartButton.click();
		elementToClickableWait(cp.buyNowButtonWait, 30);
	}
}