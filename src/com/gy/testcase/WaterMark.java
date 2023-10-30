package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.apache.log4j.Logger;
import org.junit.*;

	

public class WaterMark extends TestBase{
	private static Logger logger = Logger.getLogger(WaterMark.class);
	@Test(groups="waterMark")
	public void waterMark() throws Exception{
	
		
		String time=currentTime();
		navigation("Account Profile");
	    new Select(driver.findElement(By.id("Customer_is_watermark"))).selectByVisibleText("Enable");
	    new Select(driver.findElement(By.id("Customer_watermark_location"))).selectByVisibleText("Right Bottom");
	    driver.findElement(By.id("Customer_watermark_note")).clear();
	    driver.findElement(By.id("Customer_watermark_note")).sendKeys("融锋"+time);
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    navigation("Account Profile");
	    Thread.sleep(2000);
	    
	   
	    try {
	        AssertJUnit.assertEquals("融锋"+time, driver.findElement(By.id("Customer_watermark_note")).getAttribute("value"));
	      } catch (Error e) {
	        
	    	  logger.error("water mark名称保存失败");
	        Assert.fail("water mark名称保存失败");
	      }
	}
}
