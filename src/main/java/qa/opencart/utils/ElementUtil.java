package qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.opencart.factory.DriverFactory;

public class ElementUtil {
private WebDriver driver;
private JavaScriptUtil jsutil ;

	public ElementUtil(WebDriver driver){
		this.driver = driver;
		jsutil = new JavaScriptUtil(driver);
 }
	public WebElement getElement(By locator){
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsutil.flash(element);
		}
		return element;
  }
	public WebElement getElement(By locator, int timeOut){
		return waitForElementVisible(locator, timeOut);
	}
	public List<WebElement> getElements(By locator){
		return driver.findElements(locator);
}
	public void doSendKeys(By locator, String value){
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
}
	public  void doActionsSendKey(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();	
}
	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
}
	public void doClick(By locator){
		getElement(locator).click();
}
	public String getElementText(By locator) {
		return	getElement(locator).getText();
}
	public boolean getElementDispalyed(By locator){
		return getElement(locator).isDisplayed();
}
	public String getElementAttribute(By locator, String atrval){
		return getElement(locator).getAttribute(atrval);
}
	public int getCount(By locator){
		int elecount =getElements(locator).size();
		System.out.println("total element count..."+ elecount);
		return elecount;	 
}
	public List<String> getElementsTextList(By locator){	
		List<String> textList = new ArrayList<String>();
		List<WebElement>eleList =getElements(locator);
		for(WebElement e: eleList){
			String text= e.getText();
			textList.add(text);
	}
		System.out.println(textList);
		return textList;
	}
	
	//----------------------------Select class util----------------------
	public void doSearchByIndex(By locator, int index){
		Select select = new Select(getElement(locator));
		select.deselectByIndex(index);	
	}
	public void doSearchByValue(By locator,String value){
		Select select = new Select(getElement(locator));
		select.selectByValue(value);	
	}
	public void doSearchByVisibleText(By locator,String value){
		Select select = new Select(getElement(locator));
		select.selectByValue(value);	
	}

	public List<WebElement> getDropDownList(By locator){
		Select select = new Select(getElement(locator));
		return select.getOptions();
	}

	public List<String> getDropDownTextList(By locator){
		getElements(locator);
		List<WebElement> options = getDropDownList(locator);
		List<String> store = new ArrayList<String>();
		for(WebElement e: options)
	{
			String text = e.getText();
			store.add(text);
	}
		return store;
	}
//--------------------------Wait  util-------------------------
	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public Alert waitForAlertPresence(int timeout) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	return wait.until(ExpectedConditions.alertIsPresent());
	
	}
	public String getAlertText(int timeout) {
	return waitForAlertPresence(timeout).getText();
	
	}
	public void acceptAlert(int timeout) {
	waitForAlertPresence(timeout).accept();
	
	}
	public void dismissAlert(int timeout) {
	waitForAlertPresence(timeout).dismiss();
	
		}
	public void alertSendKeys(int timeout, String message) {
	waitForAlertPresence(timeout).sendKeys(message);
	
		}
	public String waitForTitleContainsAndFetch(int timeout, String title) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	wait.until(ExpectedConditions.titleContains(title));
	 return driver.getTitle();
	 
		}
	public String waitForTitleIsAndFetch(int timeout, String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleIs(title));
		 return driver.getTitle();
		 
		}
	public String waitForURLContainsAndFetch(int timeout, String URL) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(URL));
		 return driver.getCurrentUrl();
		}
	
	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	
	}
	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	
	}
	public void waitForFrameAndSwitchByIDOrName(int timeOut, String IDorName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDorName));
	}
	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public WebElement waitForElementToBeClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void doClickWithActionsAndWait(By locator, int timeOut) {
		WebElement ele= waitForElementToBeClickable(locator, timeOut);
		Actions act = new Actions(driver);
		act.click(ele).build().perform();
	}
}