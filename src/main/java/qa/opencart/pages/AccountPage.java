package qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.opencart.constant.AppConstants;
import qa.opencart.utils.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By logoutLink = By.linkText("Logout");
	private By accountHeader = By.xpath("//div[@id = 'content']//h2");
	private By searchBox = By.xpath("//input[@name = 'search']");
	private By btnSearch = By.xpath("//button[@class= 'btn btn-default btn-lg']");
	
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);		
	}
	public String accPageTitle() {
		String title = eleutil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_MEDIUM_IMEOUT, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println(title);
		return title;
	}
	public boolean  doSearchExists() {
		return eleutil.getElementDispalyed(searchBox);
	}
	public boolean checkLogoutLink() {
		return eleutil.getElementDispalyed(logoutLink);
	}
	public List<String> getAccountHeaderList() {
	 return eleutil.getElementsTextList(accountHeader);
	}
	public SearchProductPage performSearch(String searchKey) {
		if(doSearchExists()) {
			eleutil.doSendKeys(searchBox, searchKey);
			eleutil.doClick(btnSearch);
			return new SearchProductPage(driver);
		}
		else {
			System.out.println(" Search is not present");
			return null;
			
		}
		
		
	}
}
