package qa.opencart.test;
 
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.opencart.base.BaseTest;
import qa.opencart.constant.AppConstants;

public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accpage = login.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@Test
	public void accPageTitleTest() {
		String accTitle = accpage.accPageTitle();
		Assert.assertEquals(accTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}
	@Test
	public void accPageLogoutLinkTest() {
		Assert.assertTrue(accpage.checkLogoutLink());
	}
	@Test
	public void accHeaderCountTest() {
		List<String> accHeaderList=accpage.getAccountHeaderList();
		int a = accHeaderList.size();
		Assert.assertEquals(a,AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	@Test
	public void accHeaderValTest() {
	List<String> accHeaderList=accpage.getAccountHeaderList();
	Assert.assertEquals(accHeaderList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	@DataProvider
	public Object[][] getProdData() {
		return new Object[][] {
			{"MacBook"},
			{"Samsung"},
			{"Apple"},
			{"iMac"}
		};
	}
	@Test(dataProvider = "getProdData")
	public void searchResultCount(String searchKey) {
		searchpage= accpage.performSearch(searchKey);
		Assert.assertTrue(searchpage.getSearchCount() >0);
	}
	@DataProvider
	public Object[][] getProdTestData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"MacBook", "MacBook"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			
		};
	}
	@Test(dataProvider = "getProdTestData")
	public void searchResult(String searchkey, String prodname) {
		searchpage= accpage.performSearch(searchkey);
		if(searchpage.getSearchCount() >0) {
			prodinfopage= searchpage.selectProduct(prodname);
			String prodHeader = prodinfopage.getProductHeader();
			Assert.assertEquals(prodHeader,prodname);
		}
	}

}
