package qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import qa.opencart.factory.OptionsManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optMgr;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
public WebDriver initDriver(Properties prop) {
	optMgr = new OptionsManager(prop);
	highlight = prop.getProperty("highlight").trim();
	
	String browserName = prop.getProperty("browser").trim().toLowerCase();		
	System.out.println("The browser is : "+ browserName);
	
	if(browserName.equalsIgnoreCase("chrome")) {
		tlDriver.set(new ChromeDriver(optMgr.getChromeOptions()));
	}
	else if(browserName.equals("Firefox")) {
		tlDriver.set(new FirefoxDriver(optMgr.getFirefoxOptions()));
		}
	else if(browserName.equals("Edge")) {
		tlDriver.set(new EdgeDriver(optMgr.getEdgeOptions()));
		}
	else { System.out.println("Please pass the right browser.......");
	}
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	getDriver().get(prop.getProperty("url"));
	return getDriver();
}

public Properties initProp() { 
	prop = new Properties();
	try {
		FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\config\\Config.properties");
		prop.load(ip);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop;
}
public synchronized static WebDriver getDriver() {
	return tlDriver.get();
}
public  static String getScreenshot() {
	File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	String path =System.getProperty("user.dir")+"\\screenshot\\"+System.currentTimeMillis()+".png";
	File destination  = new File(path);
	try {
		FileHandler.copy(srcFile, destination);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return path;
}
}

