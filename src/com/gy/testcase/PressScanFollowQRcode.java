package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PressScanFollowQRcode extends TestBase{
	 @Test
	  public void pressScanFollowQRcode() throws Exception {
	    
	    driver.findElement(By.linkText("Engagement")).click();
	    driver.findElement(By.linkText("QR Codes")).click();
	    
	   
	    driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div[1]/a/button")).click();
	    
	    driver.findElement(By.id("Qrcode_scene_name")).clear();
	    driver.findElement(By.id("Qrcode_scene_name")).sendKeys("扫码关注压力测试");
	    
		String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    //driver.findElement(By.xpath("(//input[@type='text'])[9]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("扫码关注压测扫码tag"+ time);
	    Thread.sleep(1000);
	    
	    new Select(driver.findElement(By.id("Qrcode_reply_post_id"))).selectByVisibleText("multi'post多图文“测试” selenium");
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    
	    try {
	      AssertJUnit.assertEquals("扫码关注压力测试", driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/h1")).getText());
	    } catch (Error e) {
	    	Assert.fail("扫码关注压力测试QR Code创建失败！！category名称错误，请检查！！");
	      
	    }
	    
	  }
}
