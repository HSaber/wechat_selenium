package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class WelcomePost extends TestBase{
	private static Logger logger = Logger.getLogger(WelcomePost.class);
	 @Test(groups="welcomePost",dependsOnGroups="welcomeMessageDefaultInteractive",alwaysRun=true)
	  public void welcomePost() throws Exception {
	    
		 navigation("Welcome Message");
	    
	      driver.findElement(By.cssSelector("a.btn > button")).click();
	      driver.findElement(By.name("yt0")).click();
	      try{
	      AssertJUnit.assertEquals("Material was not selected!", closeAlertAndGetItsText());
	      }catch(Error e){
	    	 
	    	  logger.error("welcome message post 类型必填项校验失败");
	    	  Assert.fail("welcome message post 类型必填项校验失败");
	      }
	      new Select(driver.findElement(By.id("FirstAttention_resource_id"))).selectByVisibleText("multi'post多图文“测试” selenium");
	      try {
	        AssertJUnit.assertEquals("标题title'1“测试” selenium", driver.findElement(By.linkText("标题title'1“测试” selenium")).getText());
	      } catch (Error e) {
	     
	    	  logger.error("welcome message post preview没显示出来");
	        Assert.fail("welcome message post preview没显示出来");
	      }
	      try {
	        AssertJUnit.assertEquals("标题title'4“测试”", driver.findElement(By.linkText("标题title'4“测试”")).getText());
	      } catch (Error e) {
	       
	    	  logger.error("welcome message post preview没显示全");
	        Assert.fail("welcome message post preview没显示全");
	      }
	      new Select(driver.findElement(By.id("FirstAttention_status"))).selectByVisibleText("Active");
	      driver.findElement(By.name("yt0")).click();
	      try {
	        AssertJUnit.assertEquals("Materials", driver.findElement(By.xpath("//div[@id='first-attention-grid']/div/table/tbody/tr/td[2]")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("welcome message post 创建后类型不是mterial,或者没创建成功");
	        Assert.fail("welcome message post 创建后类型不是mterial,或者没创建成功");
	      }
	      try {
	        AssertJUnit.assertEquals("multi'post多图文“测试” selenium", driver.findElement(By.linkText("multi'post多图文“测试” selenium")).getText());
	      } catch (Error e) {
	      
	    	  logger.error("welcome message post 创建后没显示对应post的internal name,或者没创建成功");
	       Assert.fail("welcome message post 创建后没显示对应post的internal name,或者没创建成功");
	      }
	    
	    try {
	    	String a = driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src");
	    	System.out.println(a);
	        AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", a);
	      } catch (Error e) {
	       
	    	  logger.error("新创建的welcome post状态未开启，应该是active状态");
	       Assert.fail("新创建的welcome post状态未开启，应该是active状态");
	      }
	    
	    try {
	    	String b = driver.findElement(By.xpath("//div[@id='first-attention-grid']/div[1]/table/tbody/tr[2]/td[8]/img")).getAttribute("src");
	    	System.out.println(b);
	        AssertJUnit.assertEquals(baseUrl+"images/switch-button_off.png", b);
	      } catch (Error e) {
	       
	    	  logger.error("状态错误，创建新的active的welcome post后，旧的message应该自动变为inactive");
	        Assert.fail("状态错误，创建新的active的welcome post后，旧的message应该自动变为inactive");
	      }
	  }

}
