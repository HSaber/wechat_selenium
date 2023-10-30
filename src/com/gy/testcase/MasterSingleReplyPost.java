package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class MasterSingleReplyPost extends TestBase{
	private static Logger logger = Logger.getLogger(MasterSingleReplyPost.class);
	 @Test
	  public void masterSingleReplyPost() throws Exception {
		 
		 driver.get(baseUrl + "Master/dashboard/index/category/dashboard");
		 driver.findElement(By.linkText("Content")).click();
		  
		  driver.findElement(By.linkText("Manage Posts")).click();
		 
		 driver.findElement(By.id("create-more-posts")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[1]/input")).click();
		 driver.findElement(By.xpath("//div[4]/div/div[2]/div[2]/div[1]/input")).sendKeys("master single reply post'selenium测试");//master single reply post'selenium测试
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("jQuery('input').css('visibility','visible');");
		 driver.findElement(By.xpath("(//input[@id='create_reply_post'])[2]")).click();
		
		 driver.findElement(By.linkText("Submit")).click();
		 
		 driver.findElement(By.name("msgTitle")).sendKeys("master single reply post'selenium测试");
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/17.gif");
		 //Thread.sleep(3000);
		 boolean status = isElementPresent(By.linkText("Cancel"));
		 while(status==false){
		    	status = isElementPresent(By.linkText("Cancel"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 
		 
		 driver.findElement(By.linkText("Cancel")).click();
		 
		 String time = currentTime();
		
		
		 driver.findElement(By.id("msgContentUrl")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA5NDExNDAxMg==&mid=402503006&idx=1&sn=96f7cb154d807671b3dfac20b145d406");
		 
		 
		 js.executeScript("jQuery('.postOperation').css({'opacity':'1','-webkit-transform':'translate3d(0,2px,0)'})");
		
		 Thread.sleep(2000);
		 //Actions action = new Actions(driver);
		 //action.moveToElement(driver.findElement(By.xpath("//div[@id='postSetting']/div[2]"))).perform();
		 
		 WebElement delete2 = driver.findElement(By.xpath("//div[@id='postSetting']/div[2]/nav/*[name()='svg'][3]"));
		 Actions action=new Actions(driver);
		 action.click(delete2).perform();
		 
		 //Thread.sleep(2000);
		 boolean status1 = isElementPresent(By.linkText("Cancel"));
		 while(status1==false){
		    	status1 = isElementPresent(By.linkText("Cancel"));
		    	System.out.println("delete item弹框未弹出，请等待！！");
		 }
		 driver.findElement(By.linkText("Delete")).click();
		 
		 driver.findElement(By.id("Summary")).sendKeys("master reply 单图文summary：\n创建图文时添加\n注意检查换行===============");
		 
		 driver.findElement(By.id("J_addNews")).click();
		 Thread.sleep(2000);
		 try {
		      AssertJUnit.assertEquals("Manage Posts", driver.findElement(By.cssSelector("h1.page_title")).getText());
		    } catch (Error e) {
		      
		    	logger.error("master reply单图文创建失败");
		      Assert.fail("master reply单图文创建失败");
		    }
	 }
}
