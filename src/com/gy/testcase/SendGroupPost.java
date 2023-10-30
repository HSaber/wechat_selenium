package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;//下拉框方法调用

public class SendGroupPost extends TestBase{
	
	@Test
	  public void sendpost() throws Exception {
		
		navigation("Followers");
		int a = catchNum("of "," results",driver.findElement(By.cssSelector("div.summary")).getText());
		
	    
	    //直接跳到schedule post页面选择下拉框中第一个post以48小时发送
		navigation("Schedule a Post");
	    new Select(driver.findElement(By.name("sendType"))).selectByVisibleText("All Followers");
	    new Select(driver.findElement(By.id("res_id"))).selectByVisibleText("multi'post多图文“测试” selenium");
	    try {
	        AssertJUnit.assertEquals("标题title'1“测试” selenium", driver.findElement(By.linkText("标题title'1“测试” selenium")).getText());
	      } catch (Error e) {
	       
	        System.out.println("all followers send group post选择post后post preview校验失败");
	        Assert.fail("all followers send group post选择post后post preview校验失败");
	      }
	      try {
	        AssertJUnit.assertEquals("标题title'4“测试”", driver.findElement(By.linkText("标题title'4“测试”")).getText());
	      } catch (Error e) {
	        
	        System.out.println("all followers send group post选择post后post preview校验失败");
	        Assert.fail("all followers send group post选择post后post preview校验失败");
	      }
	    driver.findElement(By.id("group_message_send")).click();
	    Thread.sleep(2000);
	    try {
	        AssertJUnit.assertEquals("Users Total:" + a, driver.findElement(By.xpath("//div[6]/p")).getText());
	      } catch (Error e) {
	       
	        System.out.println("all follower群发总人数错误，和follower list显示的总人数不符");
	        Assert.fail("all follower群发总人数错误，和follower list显示的总人数不符");
	      }
	      try {
	        AssertJUnit.assertEquals("Materials: multi'post多图文“测试” selenium", driver.findElement(By.xpath("//div[6]/p[2]")).getText());
	      } catch (Error e) {
	     
	        System.out.println("all follower群发post名称错误");
	       Assert.fail("all follower群发post名称错误");
	      }
	    driver.findElement(By.cssSelector("button.group_message_submit")).click();
	    Thread.sleep(3000);
	    try {
	      AssertJUnit.assertEquals("Post Sending Queue", driver.findElement(By.cssSelector("h1.page_title")).getText());
	    } catch (Error e) {
	      
	      System.out.println("all followers send group post发送失败");
	      Assert.fail("all followers send group post发送失败");
	    }
	  }
}
