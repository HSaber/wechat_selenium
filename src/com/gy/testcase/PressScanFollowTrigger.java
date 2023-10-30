package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PressScanFollowTrigger extends TestBase{
	 @Test
	  public void pressScanFollowTrigger() throws Exception {
	    
	    driver.findElement(By.linkText("Messaging")).click();
	    driver.findElement(By.linkText("Trigger Actions")).click();
	    
	   
	    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/a[2]/button")).click();
	    
	    driver.findElement(By.id("Triggers_category")).clear();
	    driver.findElement(By.id("Triggers_category")).sendKeys("扫码关注压力测试");
	    driver.findElement(By.id("Triggers_title")).clear();
	    driver.findElement(By.id("Triggers_title")).sendKeys("扫码关注压力测试");
	    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
	    new Select(driver.findElement(By.id("Triggers_label_event_type"))).selectByVisibleText("Scan QR Code");
	    driver.findElement(By.id("one_qrcode_scan")).click();
	    
	    new Select(driver.findElement(By.id("event_qrcode_follow"))).selectByVisibleText("扫码关注压力测试");
	    
	    driver.findElement(By.id("Triggers_qrcode_type_0")).click();
	    
	    new Select(driver.findElement(By.id("Triggers_action"))).selectByVisibleText("Send a Message");
	    driver.findElement(By.xpath("(//textarea[@id='action_message'])[1]")).clear();
	    driver.findElement(By.xpath("(//textarea[@id='action_message'])[1]")).sendKeys("{{nickname}},扫码关注trigger压力测试");
		
	    String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    //driver.findElement(By.xpath("(//input[@type='text'])[9]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("扫码关注压力测试trigger"+ time);
	    Thread.sleep(1000);
	    
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    
	    try {
	      AssertJUnit.assertEquals("扫码关注压力测试", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	    	Assert.fail("扫码关注trigger压力测试创建失败！！category名称错误，请检查！！");
	      
	    }
	    
	  }
}
