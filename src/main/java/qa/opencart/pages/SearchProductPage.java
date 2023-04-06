package qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qa.opencart.constant.AppConstants;
import qa.opencart.utils.ElementUtil;

public class SearchProductPage {
 private WebDriver driver;
 private ElementUtil eleutil;
 private By searchProdResult = By.cssSelector("div#content div.product-layout");
 public SearchProductPage(WebDriver driver) {
	 this.driver = driver;
	 eleutil = new ElementUtil(driver);
 }
 
 public int getSearchCount() {
  int prodCount =  eleutil.waitForElementsVisible(searchProdResult, AppConstants.DEFAULT_MEDIUM_IMEOUT).size();
  System.out.println("Product Image Count: "+prodCount);
  return prodCount;
 }
 
 public ProdInfoPage selectProduct(String productname) {
	By prodLink = By.linkText(productname);
	 eleutil.waitForElementVisible(prodLink, AppConstants.DEFAULT_MEDIUM_IMEOUT).click();
	 return new ProdInfoPage(driver);
	 
 }
}
