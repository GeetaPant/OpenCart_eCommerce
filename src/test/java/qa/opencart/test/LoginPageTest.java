package qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import qa.opencart.base.BaseTest;
import qa.opencart.constant.AppConstants;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginPageTitleTest() {
		String accTitle = login.checkLoginPageTitle();
		Assert.assertEquals(accTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	@Test 
	public void loginPageURLTest() {
		String accURL = login.checkLoginPageURL();
		Assert.assertTrue(accURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	@Test
	public void loginPageForgetLinkExistTest() {
		Assert.assertTrue(login.loginPageForgetPasswordLink());
	}
	@Test
	public void loginPageRightMenuLinkTest() { 
		List<String> accLinks = login.loginPageRightMenuLink();
		System.out.println(accLinks.size());
		Assert.assertEquals(accLinks.size(), AppConstants.LOGIN_PAGE_RIGHT_MENU_COUNT);
	}
	@Test(priority = 1)
	public void doLoginTest() {
		accpage = login.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accpage.checkLogoutLink());
		}

}
