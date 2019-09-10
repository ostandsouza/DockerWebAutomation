package com.dockerExpress.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dockerExpress.utils.WebEventListener;

public class AbstractSelenium {
	
	//protected static WebDriver driver;
	/* ---------------------------------------------	
			enable this if running webdriver
	------------------------------------------------*/
	//protected static RemoteWebDriver driver;
	protected static ThreadLocal<RemoteWebDriver> drivers = new ThreadLocal<>();
	protected static EventFiringWebDriver e_driver;
	protected static WebEventListener eventListener;
	
	
//	public void Base(WebDriver driver){
//		this.driver = driver;
//	}
	
	/* ---------------------------------------------	
	enable this if running threadlocal webdriver
------------------------------------------------*/
    protected static RemoteWebDriver getDriver(){
        RemoteWebDriver driver = null;

        try {
            driver = drivers.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }
//	
	public WebDriver Base(){
		return getDriver();
	}
	
	public static void visit(String url){
		getDriver().get(url);
	}
	
	public String title(){
		return getDriver().getTitle();
	}
	
	public WebElement find(WebElement locator){
		return locator;
	}
	
	public void click(WebElement locator, int timeout){
		try{
			new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.elementToBeClickable(locator));
			locator.click();
		}
		catch(Exception e){
			//new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator));
			locator.click();
		}
	}
	
	public String text(WebElement locator, int timeout){
		try{
			new WebDriverWait(getDriver(), timeout).until(ExpectedConditions.visibilityOf(locator));
			return locator.getText();
		}
		catch(Exception e){
			//new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locator));
			return locator.getText();
		}
	}
	
	public void type(String inputText, WebElement locator, int timeout){
		//new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(locator));
		locator.sendKeys(inputText);
	}
	
	public void submit(WebElement locator){
		locator.submit();
	}
	
	public Boolean isdisplayed(WebElement locator){
		try{
			return locator.isDisplayed();
		}catch(org.openqa.selenium.NoSuchElementException execption){
			System.out.println(locator+ "element was not found");
			return false;
		}
	}
	
	public Boolean isdisplayed(WebElement locator, int maxWaitTime){
		try{
			waitFor(ExpectedConditions.visibilityOf(locator), maxWaitTime);
		}
		catch(org.openqa.selenium.TimeoutException exception){
			return false;
		}
		return true;
	}
	
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout){
		timeout = timeout != null ? timeout	: 5;
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		wait.until(condition);
	}
	
	public void moveCursor(WebElement locator){
		Actions action = new Actions(getDriver());
		action.moveToElement(locator).build().perform();
	}

}
