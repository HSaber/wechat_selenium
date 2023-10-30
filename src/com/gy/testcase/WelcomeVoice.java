package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import static org.testng.Assert.*;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class WelcomeVoice extends TestBase{
	private static Logger logger = Logger.getLogger(WelcomeVoice.class);
	@Test(groups="welcomeVoice",dependsOnGroups="welcomeVideoInteractive",alwaysRun=true)
	  public void welcomeVoice() throws Exception {
		 
		navigation("Welcome Message");
		    
		    driver.findElement(By.cssSelector("a.btn > button")).click();
		    driver.findElement(By.xpath("(//input[@id='ytFirstAttention_typ'])[5]")).click();
		    driver.findElement(By.name("yt0")).click();
		    AssertJUnit.assertEquals("Voice must be uploaded!", closeAlertAndGetItsText());
		    driver.findElement(By.xpath("//div[@id='voice_upload']/input")).clear();
		    driver.findElement(By.xpath("//div[@id='voice_upload']/input")).sendKeys(file+"/com/material/welcome.mp3");
		    //Thread.sleep(2000);
		    String src = null;
			src= driver.findElement(By.xpath("//div[@id='voice_upload']/audio")).getAttribute("src");
			System.out.println("src的值是："+ src);
			while(src==null||src.equals("")||src.equals(baseUrl+"Manager/firstAttention/create/category/welcome_message")){
				 src= driver.findElement(By.xpath("//div[@id='voice_upload']/audio")).getAttribute("src");
				 System.out.println("voice未上传完成,继续等待中！");
			}
		    new Select(driver.findElement(By.id("FirstAttention_status"))).selectByVisibleText("Active");
		    driver.findElement(By.name("yt0")).click();
		    try {
		      AssertJUnit.assertEquals("Voice", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div/table/tbody/tr/td[2]")).getText());
		    } catch (Error e) {
		    	logger.error("Welcome Voice创建后显示类型错误，或者是创建不成功");
		    	Assert.fail("Welcome Voice创建后显示类型错误，或者是创建不成功");
		    }
		    try {
		      assertNotEquals(null, driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[7]/audio")).getAttribute("src"));
		    } catch (Error e) {
		    	logger.error("Welcome Voice创建后list页面没显示Voice，或者创建失败，请检查！");
		    	Assert.fail("Welcome Voice创建后list页面没显示Voice，或者创建失败，请检查！");
		    }
		    try {
		    	logger.error(driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getText());
		    	AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src"));
		    } catch (Error e) {
		    	logger.error("Welcome Voice创建后list页面状态显示不对，或者创建失败，请检查！");
		    	Assert.fail("Welcome Voice创建后list页面状态显示不对，或者创建失败，请检查！");
		    }
		    try {
		      AssertJUnit.assertEquals(baseUrl+"images/switch-button_off.png", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[2]/td[8]/img")).getAttribute("src"));
		    } catch (Error e) {
		    	logger.error("Welcome Voice只允许一个状态为active，请检查！！！");
		    	Assert.fail("Welcome Voice只允许一个状态为active，请检查！！！");
		    }
		    
	 }
}
