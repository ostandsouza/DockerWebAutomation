package com.dockerExpress.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dockerExpress.base.TestBase;

public class LimitsPage extends TestBase{
	
	@FindBy(xpath = "//li[@data-id='lm' and @data-title = '/limits/index']")
	WebElement limitsWindow;
	
	@FindBy(id = "CASHFO")
	WebElement cash;
	
	@FindBy(id = "COM")
	WebElement com;
	
	@FindBy(xpath = "//table[@id = 'lm_wid']//tbody//tr//th")
	WebElement limitsType;
	
	public LimitsPage(){
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validateLimitskWindow(){
		return isdisplayed(limitsWindow,5);
	}

	public String allLimits(){
		click(cash,3);
		return text(limitsType,3);
	}
	
	public String comLimits(){
		click(com,3);
		return text(limitsType,3);
	}
}
