package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;


public class SendPreview extends TestBase{
	private static Logger logger = Logger.getLogger(SendPreview.class);
	//在schedule post页面发送preview
	//@Test
	@Test(groups="schedulePagePreview",dependsOnGroups="newEditorSinglePost")
	  public void schedulePagePreview() throws Exception {
		  
		  navigation("Schedule a Post");
	    driver.findElement(By.cssSelector("a.btnZi > button")).click();
	    Thread.sleep(3000);
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("jQuery('input').css('visibility','visible');");
	    driver.findElement(By.cssSelector("div.preview-wechat-id.pullDown > #preview-method")).click();
	    driver.findElement(By.id("preview-wechat-id")).clear();
	    driver.findElement(By.id("preview-wechat-id")).sendKeys("Rainbowgy121");
	    //new Select(driver.findElement(By.id("res_id"))).selectByVisibleText("single'post单图文“测试” selenium");
	    new Select(driver.findElement(By.id("res_id"))).selectByVisibleText("new editor单图文'selenium");
	    Thread.sleep(2000);
	   
	    try {
	      //AssertJUnit.assertEquals("单图文'title“测试” selenium", driver.findElement(By.linkText("单图文'title“测试” selenium")).getText());
	      AssertJUnit.assertEquals("new'editor selenium单图文", driver.findElement(By.linkText("new'editor selenium单图文")).getText());
	    } catch (Error e) {
	      
	    	logger.error("send preview页面选择post后post preview校验失败");
	     Assert.fail("send preview页面选择post后post preview校验失败");
	    }
	    driver.findElement(By.id("group_message_send")).click();
	    //Thread.sleep(3000);
	    boolean status = isElementPresent(By.cssSelector("button.group_message_submit.btn"));
	    while(status==false){
	    	status = isElementPresent(By.cssSelector("button.group_message_submit.btn"));
	    	System.out.println("send preview弹框未弹出，请等待！！");
	    }
	    driver.findElement(By.cssSelector("button.group_message_submit.btn")).click();
	    Thread.sleep(5000);
	    try {
	        AssertJUnit.assertEquals("Post Sending Queue", driver.findElement(By.cssSelector("h1.page_title")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("send preview发送后没有跳到send post list页面");
	       Assert.fail("send preview发送后没有跳到send post list页面");
	      }
	    Thread.sleep(5000);
	    navigation("Sent Posts List");
	    Thread.sleep(3000);
	    try {
	     // AssertJUnit.assertEquals("single'post单图文“测试” selenium", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[3]/a")).getText());
	      AssertJUnit.assertEquals("new editor单图文'selenium", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[3]/a")).getText());

	    } catch (Error e) {
	      
	    	logger.error("send preview没有发送成功或者send post list中post name显示错误");
	      Assert.fail("send preview没有发送成功或者send post list中post name显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("Preview", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[4]")).getText());
	    } catch (Error e) {
	     
	    	logger.error("send preview没有发送成功或者send post list中send type显示错误");
	     Assert.fail("send preview没有发送成功或者send post list中send type显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("1", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[5]")).getText());
	    } catch (Error e) {
	    
	    	logger.error("send preview没有发送成功或者send post list中send 人数显示错误");
	      Assert.fail("send preview没有发送成功或者send post list中send 人数显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("Sent", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[6]")).getText());
	    } catch (Error e) {
	      
	    	logger.error("send preview没有发送成功或者send post list中send status显示错误");
	      Assert.fail("send preview没有发送成功或者send post list中send status显示错误");
	    }
	    
	  }
}
