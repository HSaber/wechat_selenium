package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.*;

import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;

public class SendPreviewImage extends TestBase{
	private static Logger logger = Logger.getLogger(SendPreviewImage.class);
	@Test
	  public void sendPreviewImage() throws Exception {
		  
		navigation("Schedule a Post");
	    driver.findElement(By.cssSelector("a.btnZi > button")).click();
	    Thread.sleep(3000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("jQuery('input').css('visibility','visible');");
	    driver.findElement(By.cssSelector("div.preview-wechat-id.pullDown > #preview-method")).click();
	    driver.findElement(By.id("preview-wechat-id")).clear();
	    driver.findElement(By.id("preview-wechat-id")).sendKeys("Rainbowgy121");
	    driver.findElement(By.xpath("(//input[@id='msg_type'])[3]")).click();
	   
	    js.executeScript("jQuery('.imageArea input:file').css('display','block');");
	    driver.findElement(By.name("files[]")).clear();
	    System.out.println(file);
	    driver.findElement(By.name("files[]")).sendKeys(file+"/com/material/previewimage.gif");
	    //Thread.sleep(4000);
	    String src = null;
		src= driver.findElement(By.id("pic_Box")).getAttribute("src");
		System.out.println("src的值是："+ src);
		while(src==null||src.equals("")){
			 src= driver.findElement(By.id("pic_Box")).getAttribute("src");
			 System.out.println("image未上传完成,继续等待中！");
		}
	    driver.findElement(By.id("group_message_send")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("button.group_message_submit.btn")).click();
	    Thread.sleep(5000);
	    navigation("Sent Posts List");
	    
	    try {
	      AssertJUnit.assertEquals("Picture", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	     
	    	logger.error("send preview image发送失败或者table中发送内容 type显示错误");
	      Assert.fail("send preview image发送失败或者table中发送内容 type显示错误");
	    }
	    try {
	    	System.out.println(driver.findElement(By.xpath(".//*[@id='group-message-record-grid']/div[1]/table/tbody/tr[1]/td[3]/img")).getAttribute("src"));
	      assertNotEquals("",driver.findElement(By.xpath(".//*[@id='group-message-record-grid']/div[1]/table/tbody/tr[1]/td[3]/img")).getAttribute("src"));
	    } catch (Error e) {
	     
	    	logger.error("send preview image发送失败或者table中image不显示");
	      Assert.fail("send preview image发送失败或者table中image不显示");
	    }
	    try {
	      AssertJUnit.assertEquals("Preview", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[4]")).getText());
	    } catch (Error e) {
	      
	    	logger.error("send preview image发送失败或者table中send type显示错误");
	      Assert.fail("send preview image发送失败或者table中send type显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("1", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[5]")).getText());
	    } catch (Error e) {
	    
	    	logger.error("send preview image发送失败或者table中发送人数显示错误");
	      Assert.fail("send preview image发送失败或者table中发送人数显示错误");
	    }
	    try {
	      AssertJUnit.assertEquals("Sent", driver.findElement(By.xpath("//div[@id='group-message-record-grid']/div/table/tbody/tr/td[6]")).getText());
	    } catch (Error e) {
	     
	    	logger.error("send preview image发送失败");
	      Assert.fail("send preview image发送失败");
	    }
	  }

}
