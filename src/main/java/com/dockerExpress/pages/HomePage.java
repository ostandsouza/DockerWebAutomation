package com.dockerExpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dockerExpress.base.TestBase;
import com.dockerExpress.pages.PlaceOrderPage;

public class HomePage extends TestBase{
	
	LoginPage loginPage;
	PlaceOrderPage placeOrderPage;
	OrderBookPage orderBookPage;
	TradeBookPage tradeBookPage;
	PositionBookPage positionBookPage;
	LimitsPage limitsPage;
	
	@FindBy(xpath = "//label[@id = 'flash_text' and text() = 'Connected']")
	WebElement flash;
	
	//label[@id = 'welcome_user']//span[contains(text(), ''+letter+'')]
	@FindBy(id = "welcome_user")
	WebElement dashboardName;
	
	@FindBy(xpath = "//img[@alt='logo']")
	WebElement logo;

	@FindBy(xpath = "//table[@id = 'mw_myGrid']")
	WebElement nullMW;
	
	@FindBy(id = "globalSearch")
	WebElement search;
	
	@FindBy(xpath = "//input[@id= 'searchBox']")
	WebElement input;
	
	@FindBy(css = ".active .search_mw_buy")
	WebElement Buy;
	
	@FindBy(css = ".active .search_mw_sell")
	WebElement Sell;
	
	@FindBy(css = "nav#cssmenu > div.button")
	WebElement menu;
	
	@FindBy(xpath = "//a[text()='Trade']")
	WebElement trade;
	
	@FindBy(id = "home")
	WebElement home;
	
	@FindBy(css = ".a#mw")
	WebElement mwName;
	
	@FindBy(css = ".a#ob")
	WebElement obName;
	
	@FindBy(css = ".a#tb")
	WebElement tbName;
	
	@FindBy(css = ".a#pb")
	WebElement pbName;
	
	@FindBy(css = ".a#lm")
	WebElement lmName;
	
	@FindBy(id = "logout")
	WebElement logout;
	
	public HomePage(){
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validateSocketConn(){
		return isdisplayed(flash);
	}
	
	public String validateName(){
		return text(dashboardName,3);
	}
	
	public boolean validateLogo(){
		return isdisplayed(logo);
	}
	
	public boolean isMwLoaded(){
		return isdisplayed(nullMW);
	}
	
	public PlaceOrderPage invokeOrder(String transType){
		click(search,3);
		type("TCS-EQ",input,3);
		if(transType.equalsIgnoreCase("Buy"))
			click(Buy,10);
		else
			//driver.findElement(By.xpath("//li[@class='active']//a//button[@class='search_mw_sell']")).click();
			click(Sell,10);
		return new PlaceOrderPage();
	}
	
	public PlaceOrderPage invokeOrder_search(String transType){
		click(getDriver().findElement(By.cssSelector("#globalSearch  > i.fa")),3);
		type("TCS-EQ",input,3);
		if(transType.equalsIgnoreCase("Buy"))
			click(Buy,10);
		else
			//driver.findElement(By.xpath("//li[@class='active']//a//button[@class='search_mw_sell']")).click();
			click(Sell,10);
		return new PlaceOrderPage();
	}
	
	public OrderBookPage goToOrderBook(){
		moveCursor(trade);
		click(mwName,3);
		click(obName,3);
		moveCursor(home);
		return new OrderBookPage();
	}
	
	public TradeBookPage goToTradeBook(){
		moveCursor(trade);
		click(mwName,3);
		click(tbName,3);
		moveCursor(home);
		return new TradeBookPage();
	}
	
	public PositionBookPage goToPositionBook(){
		moveCursor(trade);
		click(mwName,3);
		click(pbName,3);
		moveCursor(home);
		return new PositionBookPage();
	}
	
	public LimitsPage goToLimits(){
		moveCursor(trade);
		click(mwName,3);
		click(lmName,3);
		moveCursor(home);
		return new LimitsPage();
	}
	
	public OrderBookPage goToOrderBook_menu(){
		click(menu,3);
		click(trade,3);
		click(mwName,3);
		click(menu,3);
		click(trade,3);
		click(obName,3);
		moveCursor(home);
		return new OrderBookPage();
	}
	
	public TradeBookPage goToTradeBook_menu(){
		click(menu,3);
		click(trade,3);
		click(mwName,3);
		click(menu,3);
		click(trade,3);
		click(tbName,3);
		moveCursor(home);
		return new TradeBookPage();
	}
	
	public PositionBookPage goToPositionBook_menu(){
		click(menu,3);
		click(trade,3);
		click(mwName,3);
		click(menu,3);
		click(trade,3);
		click(pbName,3);
		moveCursor(home);
		return new PositionBookPage();
	}
	
	public LimitsPage goToLimits_menu(){
		click(menu,3);
		click(trade,3);
		click(mwName,3);
		click(menu,3);
		click(trade,3);
		click(lmName,3);
		moveCursor(home);
		return new LimitsPage();
	}
	
	public LoginPage logOff(){
		click(logout,3);
		return new LoginPage();
	}

}
