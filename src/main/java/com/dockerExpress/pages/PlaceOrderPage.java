package com.dockerExpress.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dockerExpress.base.TestBase;

public class PlaceOrderPage extends TestBase{
	
	OrderBookPage orderBookPage;
	public String nestOrderNo;

	@FindBy(xpath = "//div[@class='modal-content']//div[@class ='quoteDiv']")
	WebElement placeOrderWindow;
	
	@FindBy(id = "plc_Sub")
	WebElement submitOrder;
	
	@FindBy(id = "ptorder_submit")
	WebElement confirmOrder;
	
//	@FindBy(id = "norder")
//	WebElement Alert;
	
	@FindBy(css = "#popup")
	WebElement Alert;
	
//	@FindBy(id = "closeIdOK")
//	WebElement nestOrderPrompt;
	
	@FindBy(css = "#modal_ok")
	WebElement nestOrderPrompt;
	
	public PlaceOrderPage(){
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validatePlaceOrderWindow(){
		return isdisplayed(placeOrderWindow);
	}
	
	public OrderBookPage placeOrder(){
		click(submitOrder,3);
		click(confirmOrder,3);
		nestOrderNo = text(Alert,3);
		int n = nestOrderNo.indexOf(":");
		nestOrderNo = nestOrderNo.substring(n+1, nestOrderNo.length());
		click(nestOrderPrompt,3);
		return new OrderBookPage();
	}
}
