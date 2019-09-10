package com.dockerExpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dockerExpress.base.TestBase;
import com.dockerExpress.pages.PlaceOrderPage;

public class OrderBookPage extends TestBase{
	
	PlaceOrderPage placeOrderPage;
	
	@FindBy(xpath = "//li[@data-id='ob' and @data-title = '/orderbook/index']")
	WebElement orderBookWindow;
	
	public OrderBookPage(){
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validateOrderBookWindow(){
		return isdisplayed(orderBookWindow);
	}
	
	public String orderStatus(String nestord){
		return text(getDriver().findElement(By.xpath("//table[@id='ob_myGrid']//tbody//tr[contains(@id,'"+nestord+"')]//td[@aria-describedby = 'ob_myGrid_status']")),3);
	}
}
