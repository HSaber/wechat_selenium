package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class FollowerListReply extends TestBase{
	private static Logger logger = Logger.getLogger(FollowerListReply.class);
	 @Test
	  public void followerListReply() throws Exception {
	     
	    driver.findElement(By.linkText("Follower Management")).click();
	    driver.findElement(By.linkText("Followers")).click();
	    driver.findElement(By.name("WechatCustomer[nickname]")).clear();
	    driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys("rainbow");
	    Thread.sleep(3000);
	    driver.findElement(By.linkText("rainbow")).click();
	    Thread.sleep(3000);
	   
	    driver.findElement(By.id("message")).click();
	  
	    driver.findElement(By.linkText("Post")).click();
	    //driver.findElement(By.id("sendMsgType")).click();
	    new Select(driver.findElement(By.id("resId"))).selectByVisibleText("reply'post多图文“测试” selenium");
	    Thread.sleep(2000);
	    try {
	      AssertJUnit.assertEquals("reply'title1“测试” selenium", driver.findElement(By.cssSelector("p.msg-des > a.i-title")).getText());
	    } catch (Error e) {
	      
	    	logger.error("follower message reply选择post后post preivew没有正常显示");
	      Assert.fail("follower message reply选择post后post preivew没有正常显示");
	    }
	    driver.findElement(By.cssSelector("button.send_msg")).click();
	    Thread.sleep(6000);
	    try {
	        AssertJUnit.assertEquals("reply'post多图文“测试” selenium", driver.findElement(By.xpath("//li/div[2]/div/a")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("follower message reply选择post发送成功后，下面的message log没有正常显示");
	       Assert.fail("follower message reply选择post发送成功后，下面的message log没有正常显示");
	      }
	    
	    driver.findElement(By.linkText("Image")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.name("files[]")).sendKeys(file+"/com/material/23.jpg");
	    //Thread.sleep(2000);
	    String src = null;
		src= driver.findElement(By.xpath("//img[@id='pic_Box']")).getAttribute("src");
		System.out.println("src的值是："+ src);
		while(src==null||src.equals("")){
			 src= driver.findElement(By.xpath("//img[@id='pic_Box']")).getAttribute("src");
			 System.out.println("image未上传完成,继续等待中！");
		}
	    driver.findElement(By.cssSelector("button.send_msg")).click();
	    Thread.sleep(6000);
	   
	    try {
	        AssertJUnit.assertEquals("image:", driver.findElement(By.xpath("//li/div[2]/div/div")).getText());
	      } catch (Error e) {
	       
	    	  logger.error("follower message reply选择image发送成功后，下面的message log没有正常显示");
	        Assert.fail("follower message reply选择image发送成功后，下面的message log没有正常显示");
	      }
	    
	    driver.findElement(By.linkText("Text")).click();
	    Thread.sleep(2000);
	    try {
	    	System.out.println(driver.findElement(By.cssSelector("p.editor_tip.js_140")).getText());
	    	AssertJUnit.assertEquals("0 / 600", driver.findElement(By.cssSelector("p.editor_tip.js_140")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("follower profile reply message可输入字数没显示或者显示错误");
	        Assert.fail("follower profile reply message可输入字数没显示或者显示错误");
	      }
	    WebElement editor = driver.findElement(By.xpath("//div[@id='quickReplyBox']/div[1]/div/div[2]"));
	    editor.sendKeys("{{nickname}}");
	    editor.sendKeys(Keys.ENTER);
	    editor.sendKeys("from follower reply");
	    driver.findElement(By.linkText("Facial Expression")).click();
	    driver.findElement(By.cssSelector("i.js_emotion_i")).click();
	    try {
	    	System.out.println(driver.findElement(By.cssSelector("p.editor_tip.js_140")).getText());
	    	AssertJUnit.assertEquals("34 / 600", driver.findElement(By.cssSelector("p.editor_tip.js_140")).getText());
	      } catch (Error e) {
	      
	    	  logger.error("follower profile reply message可输入字数没有实时更新");
	        Assert.fail("follower profile reply message可输入字数没有实时更新");
	      }
	    driver.findElement(By.cssSelector("button.send_msg")).click();
	    Thread.sleep(6000);
	    try {
	        AssertJUnit.assertEquals("rainbow\nfrom follower reply", driver.findElement(By.xpath("//li/div[2]/div")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("follower message reply选择Text发送成功后，下面的message log没有正常显示");
	        Assert.fail("follower message reply选择Text发送成功后，下面的message log没有正常显示");
	      }
	    try {
	        System.out.println(driver.findElement(By.xpath("//li/div[2]/div/img")).getAttribute("alt"));
	    	AssertJUnit.assertEquals("mo-微笑", driver.findElement(By.xpath("//li/div[2]/div/img")).getAttribute("alt"));
	      } catch (Error e) {
	       
	    	  logger.error("follower message reply选择text发送成功后，text里面的表情没有正常显示");
	        Assert.fail("follower message reply选择text发送成功后，text里面的表情没有正常显示");
	      }
	  }
	
}

