package qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import qa.opencart.constant.AppConstants;
import qa.opencart.utils.ElementUtil;

public class ProdInfoPage {
	 private WebDriver driver;
	 private ElementUtil eleutil;
	 private By prodHeader = By.tagName("h1");
	 private By prodImage  = By.cssSelector("ul.thumbnails img");
	 private By prodMetaData = By.xpath("(//div[@id= 'content']//ul[@class ='list-unstyled'])[1]/li");
	 private By ProdMetaPrice = By.xpath("(//div[@id= 'content']//ul[@class ='list-unstyled'])[2]/li");	 
	 private By cartSuccessMsg = By.cssSelector("div.alert.alert-success");
	 
	private Map<String, String> prodInfoMap;
	 
	private By quantity = By.id("input-quantity");
	private By addToCart = By.id("button-cart");
	 
public ProdInfoPage(WebDriver driver) {
	this.driver = driver;
	eleutil = new ElementUtil(driver);
	
}
public String getProductHeader()
{
	String header = eleutil.getElementText(prodHeader);
	System.out.println("Product Header is: "+ header);
	return header;	
	}
public int getProdImageCount() {
	int imgcount = eleutil.waitForElementsVisible(prodImage, AppConstants.DEFAULT_MEDIUM_IMEOUT).size();
	System.out.println(imgcount);
	return imgcount;
}
public Map<String, String> getProdInfo() {
//	prodInfoMap = new HashMap<String, String>();// unordered list
	prodInfoMap = new TreeMap<String, String>(); // Sorted list
	
	
	prodInfoMap.put("Product Header", getProductHeader());
	getMetaData();
	getMetaPrice();
	
	System.out.println("Product Info: "+ prodInfoMap);
	return prodInfoMap;
}
	private void getMetaData() {
	List<WebElement> metaList = driver.findElements(prodMetaData);
	for(WebElement e: metaList) {
	String meta =e.getText();
	String metaInfo[] = meta.split(":");
	String key  =metaInfo[0].trim();
	String value = metaInfo[1].trim();
	prodInfoMap.put(key, value);
	    }
	}
	private void getMetaPrice() {
	List<WebElement> metaPrice = driver.findElements(ProdMetaPrice);
	String price = metaPrice.get(0).getText().trim();
	String exTax = metaPrice.get(1).getText().trim();
	String exTaxVal = exTax.split(":")[1].trim();
	prodInfoMap.put("productprice", price);
	prodInfoMap.put("Ex Tax", exTaxVal);
	}
	
	public void enterProdQuantity(int qty) {
		System.out.println("Product Quantity: "+ qty);
		eleutil.doSendKeys(quantity, String.valueOf(qty));
	}
	public String addProdToCart() {
		eleutil.doClick(addToCart);
		String successmsg = eleutil.waitForElementVisible(cartSuccessMsg, AppConstants.DEFAULT_MEDIUM_IMEOUT).getText();
		StringBuilder sb = new StringBuilder(successmsg);
		String success= sb.substring(0, successmsg.length()-1).replace("\n","");
		System.out.println("Cart Success Message" + success);
		return success;
		
	}
}

