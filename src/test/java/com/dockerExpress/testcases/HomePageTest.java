package com.dockerExpress.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dockerExpress.base.TestBase;
import com.dockerExpress.galen.GalenFramework;
import com.dockerExpress.pages.HomePage;
import com.dockerExpress.pages.LimitsPage;
import com.dockerExpress.pages.LoginPage;
import com.dockerExpress.pages.OrderBookPage;
import com.dockerExpress.pages.PlaceOrderPage;
import com.dockerExpress.pages.PositionBookPage;
import com.dockerExpress.pages.TradeBookPage;

public class HomePageTest extends TestBase{
	HomePage homePage;
	LoginPage loginPage;
	PlaceOrderPage placeOrderPage;
	OrderBookPage orderBookPage;
	TradeBookPage tradeBookPage;
	PositionBookPage positionBookPage;
	LimitsPage limitsPage;
	GalenFramework galen;
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws InterruptedException {
		log = Logger.getLogger(LoginPageTest.class);
		log.info("Executing homepage test");
//		initialization();
		homePage = new HomePage();
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
	
	@Test(priority = 4)
	public void isSocketConn(){
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\homepage.gspec", "desktop");
		Assert.assertEquals(true, homePage.validateSocketConn(),"Post login socket is not connected");
	}
	
	@Test(priority = 5)
	public void isLogoDisplayed(){
		Assert.assertEquals(true, homePage.validateLogo(),"Logo is not seen post login");
	}
	
	@Test(priority = 6)
	public void mwStatus(){
		Assert.assertEquals(true, homePage.isMwLoaded(),"MW data is not available");
	}
	
	@Test(priority = 7)
	public void invokeOrderBook(){
		orderBookPage = homePage.goToOrderBook();
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\orderbook.gspec", "desktop");
		Assert.assertEquals(true, orderBookPage.validateOrderBookWindow(),"Can not find order book");
	}
	
	@Test(priority = 8)
	public void isBuyOrderInoked(){
		placeOrderPage = homePage.invokeOrder("Buy");
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\buy.gspec", "desktop");
		Assert.assertEquals(true, placeOrderPage.validatePlaceOrderWindow(),"Not able to invoke buy order entry");
	}
	
	@Test(priority = 9)
	public void placeBuyOrderSuccess(){
		orderBookPage = placeOrderPage.placeOrder();
		Assert.assertEquals("open", orderBookPage.orderStatus(placeOrderPage.nestOrderNo),"Order is not open");
	}
	
	@Test(priority = 10)
	public void isSellOrderInoked() throws InterruptedException{
		Thread.sleep(5000);
		placeOrderPage = homePage.invokeOrder("Sell");
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\sell.gspec", "desktop");
		Assert.assertEquals(true, placeOrderPage.validatePlaceOrderWindow(),"Not able to invoke sell order entry");
	}
	
	@Test(priority = 11)
	public void placeSellOrderSuccess(){
		orderBookPage = placeOrderPage.placeOrder();
		Assert.assertEquals("complete", orderBookPage.orderStatus(placeOrderPage.nestOrderNo),"Order is not complete");
	}
	
	@Test(priority = 12)
	public void invokeTradeBook(){
		tradeBookPage = homePage.goToTradeBook();
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\tradebook.gspec", "desktop");
		Assert.assertEquals(true, tradeBookPage.validateTradeBookWindow(),"Can not find trade book");
	}
	
	@Test(priority = 13)
	public void validateTradeStatus(){
		Assert.assertEquals("fill", tradeBookPage.tradeStatus(),"cannot find trade status");
	}
	
	@Test(priority = 14)
	public void invokePositionBook(){
		positionBookPage = homePage.goToPositionBook();
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\positionbook.gspec", "desktop");
		Assert.assertEquals(true, positionBookPage.validatePositionBookWindow(),"Can not find position book");
	}
	
	@Test(priority = 15)
	public void validatePosition(){
		Assert.assertEquals("0", positionBookPage.positionStatus(),"Net qty was not zero");
	}
	
	@Test(priority = 16)
	public void invokeLimits(){
		limitsPage = homePage.goToLimits();
		Assert.assertEquals(true, limitsPage.validateLimitskWindow(),"Can not find Limits book");
	}
	
	@Test(priority = 17)
	public void validateAllLimits(){
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\limits.gspec", "desktop");
		Assert.assertEquals(true, limitsPage.allLimits().contains("CASH"),"Net qty was not zero");
	}
	
	@Test(priority = 18)
	public void validateComLimits(){
		Assert.assertEquals(true, limitsPage.comLimits().contains("COM"),"Net qty was not zero");
	}
	
	@Test(priority = 19)
	public void verifyLogout(){
		loginPage = homePage.logOff();
		Assert.assertEquals(true, loginPage.validateLoginForm(),"User logged out successfully");
	}
	
	@Test(priority = 20)
	public void loginPageTitleTest_tab(){
		String width = prop.getProperty("tab_width");
		String height = prop.getProperty("tab_height");
		loginPage.resizeBrowser(Integer.parseInt(width), Integer.parseInt(height));
		Assert.assertEquals("OMNESYS NEST WEB", loginPage.validateLoginPageTitle(),"Login page title not mtched");
	}
	
	@Test(priority = 21)
	public void loginPageFrgtPwd_tab(){
		Assert.assertEquals(true, loginPage.validateForgotPwd(),"Login page has no forgot pwd option");
	}
	
	@Test(priority = 22)
	public void loginTest_tab(){
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\login.gspec", "tablet");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("2FA"));
		Assert.assertEquals(true, homePage.validateSocketConn(),"Post login socket is not connected");
	}
	
	@Test(priority = 23)
	public void isSocketConn_tab(){
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\homepage.gspec", "tablet");
		Assert.assertEquals(true, homePage.validateSocketConn(),"Post login socket is not connected");
	}
	
	@Test(priority = 24)
	public void isLogoDisplayed_tab(){
		Assert.assertEquals(true, homePage.validateLogo(),"Logo is not seen post login");
	}
	
	@Test(priority = 25)
	public void mwStatus_tab(){
		Assert.assertEquals(true, homePage.isMwLoaded(),"MW data is not available");
	}
	
	@Test(priority = 26)
	public void invokeOrderBook_tab(){
		orderBookPage = homePage.goToOrderBook_menu();
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\orderbook.gspec", "tablet");
		Assert.assertEquals(true, orderBookPage.validateOrderBookWindow(),"Can not find order book");
	}
	
	@Test(priority = 27)
	public void isBuyOrderInoked_tab(){
		placeOrderPage = homePage.invokeOrder_search("Buy");
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\buy.gspec", "tablet");
		Assert.assertEquals(true, placeOrderPage.validatePlaceOrderWindow(),"Not able to invoke buy order entry");
	}
	
	@Test(priority = 28)
	public void placeBuyOrderSuccess_tab(){
		orderBookPage = placeOrderPage.placeOrder();
		Assert.assertEquals("open", orderBookPage.orderStatus(placeOrderPage.nestOrderNo),"Order is not open");
	}
	
	@Test(priority = 29)
	public void isSellOrderInoked_tab() throws InterruptedException{
		Thread.sleep(5000);
		placeOrderPage = homePage.invokeOrder_search("Sell");
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\sell.gspec", "tablet");
		Assert.assertEquals(true, placeOrderPage.validatePlaceOrderWindow(),"Not able to invoke sell order entry");
	}
	
	@Test(priority = 30)
	public void placeSellOrderSuccess_tab(){
		orderBookPage = placeOrderPage.placeOrder();
		Assert.assertEquals("complete", orderBookPage.orderStatus(placeOrderPage.nestOrderNo),"Order is not complete");
	}
	
	@Test(priority = 31)
	public void invokeTradeBook_tab(){
		tradeBookPage = homePage.goToTradeBook_menu();
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\tradebook.gspec", "tablet");
		Assert.assertEquals(true, tradeBookPage.validateTradeBookWindow(),"Can not find trade book");
	}
	
	@Test(priority = 32)
	public void validateTradeStatus_tab(){
		Assert.assertEquals("fill", tradeBookPage.tradeStatus(),"cannot find trade status");
	}
	
	@Test(priority = 33)
	public void invokePositionBook_tab(){
		positionBookPage = homePage.goToPositionBook_menu();
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\positionbook.gspec", "tablet");
		Assert.assertEquals(true, positionBookPage.validatePositionBookWindow(),"Can not find position book");
	}
	
	@Test(priority = 34)
	public void validatePosition_tab(){
		Assert.assertEquals("0", positionBookPage.positionStatus(),"Net qty was not zero");
	}
	
	@Test(priority = 35)
	public void invokeLimits_tab(){
		limitsPage = homePage.goToLimits_menu();
		Assert.assertEquals(true, limitsPage.validateLimitskWindow(),"Can not find Limits book");
	}
	
	@Test(priority = 36)
	public void validateAllLimits_tab(){
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\limits.gspec", "tablet");
		Assert.assertEquals(true, limitsPage.allLimits().contains("CASH"),"Net qty was not zero");
	}
	
	@Test(priority = 37)
	public void validateComLimits_tab(){
		Assert.assertEquals(true, limitsPage.comLimits().contains("COM"),"Net qty was not zero");
	}
	
	@Test(priority = 38)
	public void verifyLogout_tab(){
		loginPage = homePage.logOff();
		Assert.assertEquals(true, loginPage.validateLoginForm(),"User logged out successfully");
	}
	
	@Test(priority = 39)
	public void loginPageTitleTest_mob(){
		String width = prop.getProperty("mob_width");
		String height = prop.getProperty("mob_hieght");
		loginPage.resizeBrowser(Integer.parseInt(width), Integer.parseInt(height));
		Assert.assertEquals("OMNESYS NEST WEB", loginPage.validateLoginPageTitle(),"Login page title not mtched");
	}
	
	@Test(priority = 40)
	public void loginPageFrgtPwd_mob(){
		Assert.assertEquals(true, loginPage.validateForgotPwd(),"Login page has no forgot pwd option");
	}
	
	@Test(priority = 41)
	public void loginTest_mob(){
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\login.gspec", "mobile");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("2FA"));
		Assert.assertEquals(true, homePage.validateSocketConn(),"Post login socket is not connected");
	}
	
	@Test(priority = 42)
	public void isSocketConn_mob(){
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\homepage.gspec", "mobile");
		Assert.assertEquals(true, homePage.validateSocketConn(),"Post login socket is not connected");
	}
	
	@Test(priority = 43)
	public void isLogoDisplayed_mob(){
		Assert.assertEquals(true, homePage.validateLogo(),"Logo is not seen post login");
	}
	
	@Test(priority = 44)
	public void mwStatus_mob(){
		Assert.assertEquals(true, homePage.isMwLoaded(),"MW data is not available");
	}
	
	@Test(priority = 45)
	public void invokeOrderBook_mob(){
		orderBookPage = homePage.goToOrderBook_menu();
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\orderbook.gspec", "mobile");
		Assert.assertEquals(true, orderBookPage.validateOrderBookWindow(),"Can not find order book");
	}
	
	@Test(priority = 46)
	public void isBuyOrderInoked_mob(){
		placeOrderPage = homePage.invokeOrder_search("Buy");
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\buy.gspec", "mobile");
		Assert.assertEquals(true, placeOrderPage.validatePlaceOrderWindow(),"Not able to invoke buy order entry");
	}
	
	@Test(priority =47)
	public void placeBuyOrderSuccess_mob(){
		orderBookPage = placeOrderPage.placeOrder();
		Assert.assertEquals("open", orderBookPage.orderStatus(placeOrderPage.nestOrderNo),"Order is not open");
	}
	
	@Test(priority = 48)
	public void isSellOrderInoked_mob() throws InterruptedException{
		Thread.sleep(5000);
		placeOrderPage = homePage.invokeOrder_search("Sell");
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\sell.gspec", "mobile");
		Assert.assertEquals(true, placeOrderPage.validatePlaceOrderWindow(),"Not able to invoke sell order entry");
	}
	
	@Test(priority = 49)
	public void placeSellOrderSuccess_mob(){
		orderBookPage = placeOrderPage.placeOrder();
		Assert.assertEquals("complete", orderBookPage.orderStatus(placeOrderPage.nestOrderNo),"Order is not complete");
	}
	
	@Test(priority = 50)
	public void invokeTradeBook_mob(){
		tradeBookPage = homePage.goToTradeBook_menu();
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\tradebook.gspec", "mobile");
		Assert.assertEquals(true, tradeBookPage.validateTradeBookWindow(),"Can not find trade book");
	}
	
	@Test(priority = 51)
	public void validateTradeStatus_mob(){
		Assert.assertEquals("fill", tradeBookPage.tradeStatus(),"cannot find trade status");
	}
	
	@Test(priority = 52)
	public void invokePositionBook_mob(){
		positionBookPage = homePage.goToPositionBook_menu();
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\positionbook.gspec", "mobile");
		Assert.assertEquals(true, positionBookPage.validatePositionBookWindow(),"Can not find position book");
	}
	
	@Test(priority = 53)
	public void validatePosition_mob(){
		Assert.assertEquals("0", positionBookPage.positionStatus(),"Net qty was not zero");
	}
	
	@Test(priority = 54)
	public void invokeLimits_mob(){
		limitsPage = homePage.goToLimits_menu();
		Assert.assertEquals(true, limitsPage.validateLimitskWindow(),"Can not find Limits book");
	}
	
	@Test(priority = 55)
	public void validateAllLimits_mob(){
		galen.loginPageLayoutTest(System.getProperty("user.dir")+"\\src\\main\\java\\com\\dockerExpress\\specs\\limits.gspec", "mobile");
		Assert.assertEquals(true, limitsPage.allLimits().contains("CASH"),"Net qty was not zero");
	}
	
	@Test(priority = 56)
	public void validateComLimits_mob(){
		Assert.assertEquals(true, limitsPage.comLimits().contains("COM"),"Net qty was not zero");
	}
	
	@Test(priority = 57)
	public void verifyLogout_mob(){
		loginPage = homePage.logOff();
		Assert.assertEquals(true, loginPage.validateLoginForm(),"User logged out successfully");
	}
}