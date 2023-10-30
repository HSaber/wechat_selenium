package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PressDirectFollowTriggerA extends TestBase{
	 @Test
	  public void pressDirectFollowTriggerA() throws Exception {
	    
	    driver.findElement(By.linkText("Messaging")).click();
	    driver.findElement(By.linkText("Trigger Actions")).click();
	    
	   
	    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/a[2]/button")).click();
	    
	    driver.findElement(By.id("Triggers_category")).clear();
	    driver.findElement(By.id("Triggers_category")).sendKeys("direct follow A组压力测试");
	    driver.findElement(By.id("Triggers_title")).clear();
	    driver.findElement(By.id("Triggers_title")).sendKeys("direct follow A组压力测试");
	    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
	    new Select(driver.findElement(By.id("Triggers_label_event_type"))).selectByVisibleText("Follow");
	    driver.findElement(By.id("direct_follow")).click();
	    
	    new Select(driver.findElement(By.id("segmentation"))).selectByVisibleText("direct follow 压力测试A组");
	    
	    new Select(driver.findElement(By.id("Triggers_action"))).selectByVisibleText("Send a Message");
	    driver.findElement(By.xpath("(//textarea[@id='action_message'])[1]")).clear();
	    driver.findElement(By.xpath("(//textarea[@id='action_message'])[1]")).sendKeys("{{nickname}},direct follow A组压力测试");
		
	    String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    //driver.findElement(By.xpath("(//input[@type='text'])[9]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("direct follow A组压力测试"+ time);
	    Thread.sleep(1000);
	    
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    
	    try {
	      AssertJUnit.assertEquals("direct follow A组压力测试", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	    	Assert.fail("sele direct follow创建失败！！category名称错误，请检查！！");
	      
	    }
	    
	  }
}
