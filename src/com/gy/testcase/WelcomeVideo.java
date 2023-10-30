package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import static org.testng.Assert.*;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class WelcomeVideo extends TestBase{
	private static Logger logger = Logger.getLogger(WelcomeVideo.class);
	@Test(dependsOnGroups="welcomeImageInteractive",groups="welcomeVideo",alwaysRun=true)
	  public void welcomeVideo() throws Exception {
		 
		navigation("Welcome Message");
		    
		    driver.findElement(By.cssSelector("a.btn > button")).click();
		    driver.findElement(By.xpath("(//input[@id='ytFirstAttention_typ'])[4]")).click();
		    driver.findElement(By.name("yt0")).click();
		    AssertJUnit.assertEquals("Video must be uploaded!", closeAlertAndGetItsText());
		    driver.findElement(By.xpath("//div[@id='video_upload']/input")).clear();
		    driver.findElement(By.xpath("//div[@id='video_upload']/input")).sendKeys(file+"/com/material/test_video.mp4");
		   // Thread.sleep(10000);
		    String src = null;
			src= driver.findElement(By.xpath("//div[@id='video_upload']/video")).getAttribute("src");
			System.out.println("src的值是："+ src);
			while(src==null||src.equals("")||src.equals(baseUrl+"Manager/firstAttention/create/category/welcome_message")){
				 src= driver.findElement(By.xpath("//div[@id='video_upload']/video")).getAttribute("src");
				 System.out.println("video未上传完成,继续等待中！");
			}
		    new Select(driver.findElement(By.id("FirstAttention_status"))).selectByVisibleText("Active");
		    driver.findElement(By.name("yt0")).click();
		    try {
		      AssertJUnit.assertEquals("Video", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div/table/tbody/tr/td[2]")).getText());
		    } catch (Error e) {
		    	 logger.error("Welcome Video创建后显示类型错误，或者是创建不成功");
		    	Assert.fail("Welcome Video创建后显示类型错误，或者是创建不成功");
		    }
		    try {
		      assertNotEquals(null, driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[6]/video")).getAttribute("src"));
		    } catch (Error e) {
		    	 logger.error("Welcome video创建后list页面没显示video，或者创建失败，请检查！");
		    	Assert.fail("Welcome video创建后list页面没显示video，或者创建失败，请检查！");
		    }
		    try {
		         System.out.println(driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getText());
		    	AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src"));
		    } catch (Error e) {
		    	 logger.error("Welcome video创建后list页面状态显示不对，或者创建失败，请检查！");
		    	Assert.fail("Welcome video创建后list页面状态显示不对，或者创建失败，请检查！");
		    }
		    try {
		      AssertJUnit.assertEquals(baseUrl+"images/switch-button_off.png", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[2]/td[8]/img")).getAttribute("src"));
		    } catch (Error e) {
		    	 logger.error("Welcome video只允许一个状态为active，请检查！！！");
		    	Assert.fail("Welcome video只允许一个状态为active，请检查！！！");
		    }
		    
	 }
}
