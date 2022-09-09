package resources;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectUtils {

	WebDriver driver;
	
	public PageObjectUtils(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public void presenceOfElementWait(By element, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}
	
	public void elementToClickableWait(By element, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void textMatchesWait(By element, int timeInSeconds, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.textToBe(element, text));
	}

	public Object executeJavaScript(String script, WebElement locator){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return js.executeScript(script, locator);
	}

}
