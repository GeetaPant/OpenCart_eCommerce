package qa.opencart.test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.opencart.base.BaseTest;
import qa.opencart.constant.AppConstants;
import qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		regpage = login.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		 Random random = new Random();
		String email= "automation"+random.nextInt(1000)+"@gmail.com";
		return email;
	}
	

	@DataProvider
	public Object[][] getRegTestData() {
	Object regData[][]=	ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	return regData;
	}
	@Test(dataProvider = "getRegTestData")
	public void userRegTest(String fn, String ln, String telephone, String pass, String subscribe ) {
		Assert.assertTrue(regpage.registerUser(fn, ln, getRandomEmail(), telephone, pass, subscribe));
	}
}
