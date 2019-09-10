package com.dockerExpress.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.dockerExpress.utils.TestUtil;
import com.dockerExpress.utils.WebEventListener;

public class TestBase extends AbstractSelenium {

	protected static Properties prop;
	protected static Logger log;
	
	public TestBase(){
		try{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\config\\config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@BeforeTest
    @Parameters({"browser","version","userID"})
	public static void initialization(String browser, String version, String UserID) throws Exception{
		//String browserName = prop.getProperty("browser");
    	String value = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
	    System.out.println(value);
		String ScreenResolution = prop.getProperty("ScreenResolution");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if(browser.equalsIgnoreCase("chrome")){
			/* ---------------------------------------------	
			  			enable this for webdriver
			------------------------------------------------*/
//			ChromeOptions options = new ChromeOptions();
//		 	Map<String, Object> prefs = new HashMap<String, Object>();
//	        Map<String, Object> profile = new HashMap<String, Object>();
//	        Map<String, Object> contentSettings = new HashMap<String, Object>();
//	        contentSettings.put("notifications", 2);
//	        profile.put("managed_default_content_settings", contentSettings);
//	        prefs.put("profile", profile);
//	        options.setExperimentalOption("prefs", prefs);
//	        options.addArguments("--disable-plugins");
//	        options.addArguments("--start-maximized");
//			System.setProperty("webdriver.chrome.driver", "C://drivers//chromedriver.exe");
//			driver = new ChromeDriver();
			
			/* ---------------------------------------------	
  					enable this for remotedriver
			------------------------------------------------*/
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			//final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability("enableVNC", true);
			//capabilities.setCapability("screenResolution", "1920x1080x24");
			capabilities.setCapability("screenResolution", ScreenResolution);
			//System.out.println(ScreenResolution);
			capabilities.setBrowserName(browser);
			capabilities.setVersion(version);
			capabilities.setCapability("enableVideo", false);
//			try {
//				//driver.set(new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities));
//				driver = new RemoteWebDriver(
//				    new URL("http://192.168.99.100:4444/wd/hub"), 
//				    capabilities
//				);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
		}
		if(browser.equalsIgnoreCase("firefox")){
//			System.setProperty("webdriver.gecko.driver", "C://drivers//geckodriver.exe");
//			DesiredCapabilities capabilities=DesiredCapabilities.firefox();
//			capabilities.setCapability("marionette",true);
//			FirefoxOptions options = new FirefoxOptions();
//			options.setLegacy(true);
//			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
//			driver = new FirefoxDriver();
//			System.setProperty("webdriver.gecko.driver", "path");
//			driver = new FirefoxDriver();
			//final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			//final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
			capabilities.setCapability("enableVNC", true);
			//capabilities.setCapability("screenResolution", "1920x1080x24");
			capabilities.setCapability("screenResolution", ScreenResolution);
			capabilities.setBrowserName(browser);
			capabilities.setVersion(version);
			capabilities.setCapability("enableVideo", false);
//			try {
//				driver = new RemoteWebDriver(
//				    new URL("http://192.168.99.100:4444/wd/hub"), 
//				    capabilities
//				);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
		}
		if(browser.equalsIgnoreCase("opera")){
//			System.setProperty("webdriver.gecko.driver", "path");
//			driver = new FirefoxDriver();
			//final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			//final DesiredCapabilities capabilities = DesiredCapabilities.opera();
			capabilities = DesiredCapabilities.opera();
			capabilities.setCapability("enableVNC", true);
			//capabilities.setCapability("screenResolution", "1920x1080x24");
			capabilities.setCapability("screenResolution", ScreenResolution);
			//capabilities.setCapability("opera.binary","/usr/bin/opera");
			capabilities.setBrowserName(browser);
			capabilities.setVersion(version);
//			try {
//				driver = new RemoteWebDriver(
//				    new URL("http://192.168.99.100:4444/wd/hub"), 
//				    capabilities
//				);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
		}
		if(browser.equalsIgnoreCase("mob")){
			Map<String, Object> deviceMetrics = new HashMap<>();
			deviceMetrics.put("width", 600);
			deviceMetrics.put("height", 800);
			deviceMetrics.put("pixelRatio", 2);
	
	
			Map<String, Object> mobileEmulation = new HashMap<>();
			mobileEmulation.put("deviceMetrics", deviceMetrics);
			//mobileEmulation.put("userAgent", "Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
			Map<String, Object> chromeOptions = new HashMap<>();
			chromeOptions.put("mobileEmulation", mobileEmulation);
			capabilities = DesiredCapabilities.chrome();
			//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			capabilities.setCapability("enableVNC", true);
			//capabilities.setCapability("screenResolution", "1920x1080x24");
			capabilities.setCapability("screenResolution", ScreenResolution);
			capabilities.setBrowserName("chrome");
			capabilities.setVersion("72.0");
//			try {
//				driver = new RemoteWebDriver(
//				    new URL("http://192.168.99.100:4444/wd/hub"), 
//				    capabilities
//				);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
		
		}
		if(browser.equalsIgnoreCase("internet explorer")){
			capabilities = DesiredCapabilities.internetExplorer();
			//capabilities.setCapability("enableVNC", true);
			capabilities.setCapability("screenResolution", ScreenResolution);
		}
		if(browser.equalsIgnoreCase("android")){
			capabilities = DesiredCapabilities.android();
			capabilities.setCapability("browserName", browser);
			capabilities.setCapability("version", version);
			capabilities.setCapability("appPackage", "com.android.chrome");
			capabilities.setCapability("appActivity", "com.android.chrome.Main");
			capabilities.setCapability("enableVNC", true);
			//capabilities.setCapability("videoName", "android.mp4");
			capabilities.setCapability("screenResolution", ScreenResolution);
		}
		
		try {
			drivers.set(new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities));
//			driver = new RemoteWebDriver(
//			    new URL("http://192.168.99.100:4444/wd/hub"), 
//			    capabilities
//			);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		//registering listener class
		e_driver = new EventFiringWebDriver(getDriver());
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		/* ---------------------------------------------	
			enable this if running webdriver
		------------------------------------------------*/
		//driver = e_driver;   
		
		drivers.get().manage().window().maximize();
		//driver.manage().deleteAllCookies();
		drivers.get().manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		visit(prop.getProperty("url"));
	}

	
	public String getChromePath(){
		String path = prop.getProperty("path");
		return path;
	}
	
	@DataProvider(name="Set_Environment", parallel=true)
    public Object[][] getData(){
    
    Object[][] Browser_Property = new Object[][]{
    		
 
    {"OSTAN2-OTPUAT", "chrome", "70.0"},
    {"OSTAN3-OTPUAT", "firefox", "65.0"}
    };
    return Browser_Property;
    
    }

	@AfterTest
	public void tearDown(){
		getDriver().quit();
		GetParamters sendResult = new GetParamters();
		String testng = sendResult.SendFile("\\test-output\\Extent.html");
		String galen = sendResult.SendFile("\\galen-results\\chrome_72.0\\report.html");
		String res = sendResult.sendVal(testng , galen);
	}
		
}
