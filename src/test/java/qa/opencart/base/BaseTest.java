package qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import qa.opencart.factory.DriverFactory;
import qa.opencart.pages.AccountPage;
import qa.opencart.pages.LoginPage;
import qa.opencart.pages.ProdInfoPage;
import qa.opencart.pages.RegisterPage;
import qa.opencart.pages.SearchProductPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage login;
	protected AccountPage accpage;
	protected SearchProductPage searchpage;
	protected ProdInfoPage prodinfopage;
	protected RegisterPage regpage;
	
	protected SoftAssert softassert;
	
	@BeforeTest
	public void doSetup() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);	
		login = new LoginPage(driver);
		 softassert = new SoftAssert();
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
		
	
}
