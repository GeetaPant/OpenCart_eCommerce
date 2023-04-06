package qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.opencart.pages.RegisterPage;

import qa.opencart.constant.AppConstants;
import qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	ElementUtil eleutil;
	
	private WebDriver driver;
	private By email = By.name("email");
	private By password= By.name("password");
	private By forgetPassword = By.linkText("Forgotten Password");
	private By btnLogin = By.xpath("//input[@type = 'submit']");
	
	private By rightMenuLinks = By.xpath("//aside[@id= 'column-right']//a");
	private By registerLink = By.linkText("Register");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	public String checkLoginPageTitle() {
		String title = eleutil.waitForTitleIsAndFetch(AppConstants.DEFAULT_MEDIUM_IMEOUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println(title);
		return title;
	}
	public String checkLoginPageURL() {
		String currentUrl= eleutil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_IMEOUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println(currentUrl);
		return currentUrl;
	}
	
	public boolean  loginPageForgetPasswordLink() {
		return eleutil.getElementDispalyed(forgetPassword);
	}
	public List<String> loginPageRightMenuLink() {
	 return eleutil.getElementsTextList(rightMenuLinks);
	}
	
	public AccountPage doLogin(String un, String pwd) {
		eleutil.waitForElementVisible(email, AppConstants.DEFAULT_SHORT_IMEOUT);
		eleutil.getElement(email).sendKeys(un);
		eleutil.getElement(password).sendKeys(pwd);
		eleutil.doClick(btnLogin);
		return new AccountPage(driver);
	}
	
	
	public RegisterPage navigateToRegisterPage()
	{
		eleutil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	

}
