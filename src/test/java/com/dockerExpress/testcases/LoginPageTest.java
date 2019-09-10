package com.dockerExpress.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dockerExpress.base.TestBase;
import com.dockerExpress.galen.GalenFramework;
import com.dockerExpress.pages.HomePage;
import com.dockerExpress.pages.LoginPage;


public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	GalenFramework galen;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws InterruptedException {
		log = Logger.getLogger(LoginPageTest.class);
		log.info("Executing login test");
//		initialization();
		loginPage = new LoginPage();
		galen = new GalenFramework();
	}
	
	@Test(priority = 1)
	public void loginPageTitleTest(){
		Assert.assertEquals("OMNESYS NEST WEB", loginPage.validateLoginPageTitle(),"Login page title not mtched");
	}
	
	@Test(priority = 2)
	public void loginPageFrgtPwd(){
		Assert.assertEquals(true, loginPage.validateForgotPwd(),"Login page has no forgot pwd option");
	}
	
	@Test(priority = 3)
	public void loginTest(){
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\login.gspec", "desktop");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("2FA"));
		Assert.assertEquals(true, homePage.validateName().contains(prop.getProperty("username")),"After login username is not displayed in trading page");
	}
	
//	@AfterSuite
//	public void tearDown(){
//		driver.quit();
//	}
}
