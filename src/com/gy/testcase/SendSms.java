package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SendSms extends TestBase{
 
	@Test
		public void testUntitled() throws Exception {
		    
		    driver.findElement(By.linkText("Messaging")).click();
		    driver.findElement(By.linkText("SMS")).click();
		    driver.findElement(By.linkText("Send SMS")).click();
		    driver.findElement(By.id("users_messages")).click();
		    new Select(driver.findElement(By.id("users"))).selectByVisibleText("rainbow");
		    driver.findElement(By.xpath("//input[@id='post-now']")).click();
		    
		    WebElement editor = driver.findElement(By.xpath("//div[@id='res_wb']/div[2]/div"));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("$('div.editArea > div').text('rainbowsms');", editor);
		  //  ((JavascriptExecutor)driver).executeScript("$(\"#welcome\").attr(\"style\",'display:inline')");
		  //  driver.findElement(By.xpath(".//*[@id='welcome']")).sendKeys("welcome");
		
		    driver.findElement(By.id("sms_message_submit")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.cssSelector("button.group_message_submit")).click();
		    Thread.sleep(3000);
		    try {
		      AssertJUnit.assertEquals("Success sent 1 customers", driver.findElement(By.xpath("//div[8]/p")).getText());
		    } catch (Error e) {
		    	Assert.fail();
		    }
		    driver.findElement(By.xpath("//div[8]/button")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.linkText("Messaging")).click();
		    driver.findElement(By.linkText("SMS")).click();
		    driver.findElement(By.linkText("SMS Log")).click();
		    try {
		      AssertJUnit.assertEquals("rainbow", driver.findElement(By.cssSelector("tr.odd > td")).getText());
		    } catch (Error e) {
		    	Assert.fail();
		    }
		    try {
		      AssertJUnit.assertEquals("13072123365", driver.findElement(By.xpath("//div[@id='sms-record-grid']/div/table/tbody/tr/td[2]")).getText());
		    } catch (Error e) {
		    	Assert.fail();
		    }
		    try {
		      AssertJUnit.assertEquals("SUCCESS", driver.findElement(By.xpath("//div[@id='sms-record-grid']/div/table/tbody/tr/td[3]")).getText());
		    } catch (Error e) {
		    	Assert.fail();
		    }
		    try {
		      AssertJUnit.assertEquals("您好！rainbowsms，官方咨询：(86) 21-61703137/18721820830官网:www.jingdigital.com 退订回复T", driver.findElement(By.xpath("//div[@id='sms-record-grid']/div/table/tbody/tr/td[4]")).getText());
		    } catch (Error e) {
		    	Assert.fail();
		    }
		    try {
		      AssertJUnit.assertEquals("Manager", driver.findElement(By.xpath("//div[@id='sms-record-grid']/div/table/tbody/tr/td[5]")).getText());
		    } catch (Error e) {
		     Assert.fail();
		    }
	  }
}


