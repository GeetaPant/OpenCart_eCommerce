package qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.opencart.base.BaseTest;

public class ProdInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void prodPageSetup() {
		accpage = login.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProdImageData() {
		return new Object[][] {
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung", "Samsung SyncMaster 941BW",1},
		};
	}
	@Test(dataProvider ="getProdImageData")
	public void prodImagesCount(String searchkey, String prodname, int imgcount) {
	searchpage =accpage.performSearch(searchkey);
	prodinfopage= searchpage.selectProduct(prodname);
	int imgCount = prodinfopage.getProdImageCount();
	Assert.assertEquals(imgCount, imgcount);
	}

	@Test
	public void productInfoTest() {
		searchpage= accpage.performSearch("Macbook");
		prodinfopage = searchpage.selectProduct("MacBook Pro");
		Map<String, String> actProdInfoMap = prodinfopage.getProdInfo();
		
		softassert.assertEquals(actProdInfoMap.get("Brand"), "Apple");
		softassert.assertEquals(actProdInfoMap.get("Product Code"), "Product 18");
		softassert.assertEquals(actProdInfoMap.get("productprice"), "$2,000.00");
		softassert.assertAll();		
	}
	@Test(priority= 1)
	public void addToCartTest() {
		searchpage= accpage.performSearch("Macbook");
		prodinfopage = searchpage.selectProduct("MacBook Air");
		prodinfopage.enterProdQuantity(1);
		String actCartMsg = prodinfopage.addProdToCart();
		//softassert.assertTrue(actCartMsg.contains("Success"));
		//softassert.assertTrue(actCartMsg.contains("MacBook Air"));
		softassert.assertEquals(actCartMsg,"Success: You have added MacBook Air to your shopping cart!");
		softassert.assertAll();
		
	}
}
