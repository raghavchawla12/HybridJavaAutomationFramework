package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.PageObjectUtils;

public class LoginPage extends PageObjectUtils {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    MainPage mp = new MainPage(driver);

    // Wait Locators

    By loginButtonWait = By.cssSelector("input#login");

    // Locators

    @FindBy(css = "input#userEmail")
    private WebElement emailField;

    @FindBy(css = "input#userPassword")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    // Actions

    public void enterEmailInEmailIdField(String email){
        emailField.sendKeys(email);
    }

    public void enterPasswordInPasswordField(String password){
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton(){
        executeJavaScript("arguments[0].click();", loginButton);
        elementToClickableWait(mp.homeButtonWait, 30);
    }

    public void loginToApplication(String email, String password) {
        enterEmailInEmailIdField(email);
        enterPasswordInPasswordField(password);
        clickOnLoginButton();
    }




}

