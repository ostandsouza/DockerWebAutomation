package com.dockerExpress.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.dockerExpress.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(id = "user")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "2fa")
	WebElement twoFactor;
	
	@FindBy(id = "submitbutton")
	WebElement submitbtn;
	
	@FindBy(xpath = "//a[@id='frgtpswdbtn']")
	WebElement forgotPwd;
	
	@FindBy(id = "formId")
	WebElement formId;
	
	//By forgotPwd = By.xpath("//a[@id='frgtpswdbtn']");
	
	//Initializing the page Objects
	public LoginPage(){
		PageFactory.initElements(getDriver(), this);
	}
	
	//Actions
	public void resizeBrowser(int width, int height){
		getDriver().manage().window().setSize(new Dimension(width, height));

//		Map<String, Object> deviceMetrics = new HashMap<>();
//		deviceMetrics.put("width", 768);
//		deviceMetrics.put("height", 1024);
//		deviceMetrics.put("pixelRatio", 2);
//
//
//		Map<String, Object> mobileEmulation = new HashMap<>();
//		mobileEmulation.put("deviceMetrics", deviceMetrics);
//		//mobileEmulation.put("userAgent", "Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
//		Map<String, Object> chromeOptions = new HashMap<>();
//		chromeOptions.put("mobileEmulation", mobileEmulation);
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
	}
	
	
	public String validateLoginPageTitle(){
		return title();
	}
	
	public boolean validateLoginForm(){
		return isdisplayed(formId);
	}
	
	public boolean validateForgotPwd(){
		return isdisplayed(forgotPwd);
		//return forgotPwd.isDisplayed();
	}
	
	public HomePage login (String user, String pwd, String secondFactor){
	    String userName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("userID");
	    System.out.println(userName);
		//GalenFramework galen = new GalenFramework();
		//galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\login.gspec");
		type(userName,username,3);
		type(pwd,password,1);
		type(secondFactor,twoFactor,1);
		submit(submitbtn);	
		return new HomePage();
	}

}
