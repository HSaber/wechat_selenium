package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;

public class SendPreviewText extends TestBase{
	private static Logger logger = Logger.getLogger(SendPreviewText.class);
	@Test
	  public void sendPreviewText() throws Exception {
		  
	    navigation("Schedule a Post");
	    driver.findElement(By.cssSelector("a.btnZi > button")).click();
	    Thread.sleep(3000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("jQuery('input').css('visibility','visible');");
	    driver.findElement(By.cssSelector("div.preview-wechat-id.pullDown > #preview-method")).click();
	    driver.findElement(By.id("preview-wechat-id")).clear();
	    driver.findElement(By.id("preview-wechat-id")).sendKeys("Rainbowgy121");
	    driver.findElement(By.xpath("(//input[@id='msg_type'])[2]")).click();
	    WebElement editor = driver.findElement(By.xpath("//div[@id='res_wb']/div[2]/div[2]/div[1]/div"));
	    String time = currentTime();
		
	    js.executeScript("$('div.editArea>div').text('send a text preview,selenium"+ time +"');", editor);
	    editor.sendKeys(Keys.ENTER);
	    driver.findElement(By.xpath("//div[@id='res_wb']/div[2]/div[2]/div[2]/div[1]/a")).click();
	    driver.findElement(By.cssSelector("div.eItem")).click();
	    driver.findElement(By.id("group_message_send")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("button.group_message_submit.btn")).click();
	    Thread.sleep(5000);
	    navigation("Sent Posts List");
	    
	    try {
	      AssertJUnit.assertEquals("text", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	      
	    	logger.error("send preview text发送失败或者table中发送内容 type显示错误");
	     Assert.fail("send preview text发送失败或者table中发送内容 type显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("/微笑send a text preview,selenium" + time, driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[3]")).getText());
	    } catch (Error e) {
	     
	    	logger.error("send preview text发送失败或者table中text content显示错误");
	     Assert.fail("send preview text发送失败或者table中text content显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("Preview", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[4]")).getText());
	    } catch (Error e) {
	    
	    	logger.error("send preview text发送失败或者table中send type显示错误");
	      Assert.fail("send preview text发送失败或者table中send type显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("1", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[5]")).getText());
	    } catch (Error e) {
	     
	    	logger.error("send preview text发送失败或者table中发送人数显示错误");
	      Assert.fail("send preview text发送失败或者table中发送人数显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("Sent", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[6]")).getText());
	    } catch (Error e) {
	     
	    	logger.error("send preview text发送失败");
	     Assert.fail("send preview text发送失败");
	    }
	  }

}
