package com.dockerExpress.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
	
import com.dockerExpress.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 2;
	
//	public boolean isDisplayed (By locator){
//		try{
//			return find(locator).isDisplayed();
//		}
//		catch(Exception e){
//			return false;
//		}
//	}
	
	public Boolean isDisplayed(By locator, int maxWaitTime){
		try{
			waitFor(ExpectedConditions.visibilityOfElementLocated(locator), maxWaitTime);
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
	
	public static void takeScreenshotAtEndOfTest() throws IOException{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		
		FileUtils.copyFile(srcFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

}
