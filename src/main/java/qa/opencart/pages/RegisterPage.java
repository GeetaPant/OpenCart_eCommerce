package qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.opencart.constant.AppConstants;
import qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	public boolean registerUser(String fname, String lname,
								String emailID, String phone, String pwd, String subscribe) {
		eleutil.waitForElementVisible(firstName, AppConstants.DEFAULT_SHORT_IMEOUT).sendKeys(fname);
		eleutil.doSendKeys(lastName, lname);
		eleutil.doSendKeys(email, emailID);
		eleutil.doSendKeys(telephone, phone);
		eleutil.doSendKeys(password, pwd);
		eleutil.doSendKeys(confirmpassword, pwd);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleutil.doClick(subscribeYes);
		}
		else {
			eleutil.doClick(subscribeNo);
		}
		eleutil.doActionsClick(agreeCheckBox);
		eleutil.doClick(continueButton);
		
		String successMsg =eleutil.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_MEDIUM_IMEOUT).getText();
		System.out.println("User Created: "+successMsg);
		
		if(successMsg.contains(AppConstants.USER_REG_SUCCESS_MSG)) {
			eleutil.doClick(logoutLink);
			eleutil.doClick(registerLink);
		return true;
		}
		else {
			return false;
		}
		
	
	}
}
