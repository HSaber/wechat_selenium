package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import static org.testng.Assert.*;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class WelcomeImage extends TestBase{
	private static Logger logger = Logger.getLogger(WelcomeImage.class);
	 @Test(dependsOnGroups="welcomeMessageInteractive",groups="welcomeImage",alwaysRun=true)
	  public void welcomeImage() throws Exception {
		 
		 	navigation("Welcome Message");
		    
		    driver.findElement(By.cssSelector("a.btn > button")).click();
		    driver.findElement(By.xpath("(//input[@id='ytFirstAttention_typ'])[3]")).click();
		    driver.findElement(By.name("yt0")).click();
		    AssertJUnit.assertEquals("Picture must be uploaded!", closeAlertAndGetItsText());
		    driver.findElement(By.name("files[]")).clear();
		    driver.findElement(By.name("files[]")).sendKeys(file+"/com/material/7.jpg");
		    //Thread.sleep(2000);
		    String src = null;
			src= driver.findElement(By.xpath("//div[@id='pic_upload']/img")).getAttribute("src");
			System.out.println("src的值是："+ src);
			while(src==null||src.equals("")){
				 src= driver.findElement(By.xpath("//div[@id='pic_upload']/img")).getAttribute("src");
				 System.out.println("image未上传完成,继续等待中！");
			}
		    new Select(driver.findElement(By.id("FirstAttention_status"))).selectByVisibleText("Active");
		    driver.findElement(By.name("yt0")).click();
		    try {
		      AssertJUnit.assertEquals("Image", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div/table/tbody/tr/td[2]")).getText());
		    } catch (Error e) {
		    	 logger.error("Welcome image创建后显示类型错误，或者是创建不成功");
		    	Assert.fail("Welcome image创建后显示类型错误，或者是创建不成功");
		    }
		    try {
		      assertNotEquals(null, driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[5]/img")).getAttribute("src"));
		    } catch (Error e) {
		    	 logger.error("Welcome image创建后list页面没显示图片，或者创建失败，请检查！");
		    	Assert.fail("Welcome image创建后list页面没显示图片，或者创建失败，请检查！");
		    }
		    try {
		         System.out.println(driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getText());
		    	AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src"));
		    } catch (Error e) {
		    	 logger.error("Welcome image创建后list页面状态显示不对，或者创建失败，请检查！");
		    	Assert.fail("Welcome image创建后list页面状态显示不对，或者创建失败，请检查！");
		    }
		    try {
		      AssertJUnit.assertEquals(baseUrl+"images/switch-button_off.png", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[2]/td[8]/img")).getAttribute("src"));
		    } catch (Error e) {
		    	 logger.error("Welcome image只允许一个状态为active，请检查！！！");
		    	Assert.fail("Welcome image只允许一个状态为active，请检查！！！");
		    }
		    
	 }
}
