	package com.dockerExpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dockerExpress.base.TestBase;

public class TradeBookPage extends TestBase{
	
	@FindBy(xpath = "//li[@data-id='tb' and @data-title = '/tradebook/index']")
	WebElement tradeBookWindow;
	
	public TradeBookPage(){
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validateTradeBookWindow(){
		return isdisplayed(tradeBookWindow);
	}

	public String tradeStatus(){
		return text(getDriver().findElement(By.xpath("//table[@id='tb_myGrid']//tbody//tr[contains(@id,'|0')]//td[@aria-describedby = 'tb_myGrid_reportyp']")),3);
	}
}
