package com.dockerExpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dockerExpress.base.TestBase;

public class PositionBookPage extends TestBase{
	
	@FindBy(xpath = "//li[@data-id='pb' and @data-title = '/positionbook/index']")
	WebElement positionBookWindow;
	
	public PositionBookPage(){
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validatePositionBookWindow(){
		return isdisplayed(positionBookWindow);
	}
	
	public String positionStatus(){
		return text(getDriver().findElement(By.xpath("//table[@id='pb_myGrid']//tbody//tr[contains(@id,'CNC')]//td[@aria-describedby = 'pb_myGrid_netqty']")),3);
	}
}
