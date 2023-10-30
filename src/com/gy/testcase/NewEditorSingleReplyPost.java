package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class NewEditorSingleReplyPost extends TestBase{
	private static Logger logger = Logger.getLogger(NewEditorSingleReplyPost.class);
	 @Test
	  public void newEditorSingleReplyPost() throws Exception {
		 
		 navigation("Manage Posts");
		 
		 driver.findElement(By.id("create-more-posts")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).click();
		 driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).sendKeys("new editor single reply post'selenium测试");//new editor single reply post'selenium测试
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("jQuery('input').css('visibility','visible');");
		 driver.findElement(By.xpath("(//input[@id='create_reply_post'])[2]")).click();
		
		 driver.findElement(By.linkText("Submit")).click();
		 
		 driver.findElement(By.name("msgTitle")).sendKeys("new editor single reply post'selenium测试");
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/17.gif");
		 //Thread.sleep(3000);
		 boolean status = isElementPresent(By.linkText("Cancel"));
		 while(status==false){
		    	status = isElementPresent(By.linkText("Cancel"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 
		 
		 driver.findElement(By.linkText("Cancel")).click();
		 
		 String time = currentTime();
		 WebElement target = driver.findElement(By.cssSelector(".tags_add"));
		 js.executeScript("arguments[0].scrollIntoView();", target);
		 driver.findElement(By.cssSelector(".tags_add")).click();
 		 driver.findElement(By.cssSelector(".tagInputField.ui-autocomplete-input")).sendKeys("neweditor reply单图文"+ time);
		
		 driver.findElement(By.id("msgContentUrl")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA4MzI2NTMzOA==&mid=2652212192&idx=1&sn=c484b75bebcee9c9f6f51cc2042ce18e&chksm=841839b5b36fb0a3b6225eadd4285c51af4beca105275a53b2451e8d5b894563129d0582491e&scene=21#wechat_redirect");
		 
		 
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
		 
		 driver.findElement(By.id("Summary")).sendKeys("new editor reply 单图文summary：\n创建图文时添加\n注意检查换行===============");
		 
		 driver.findElement(By.id("J_addNews")).click();
		 Thread.sleep(2000);
		 try {
		      AssertJUnit.assertEquals("Manage Posts", driver.findElement(By.cssSelector("h1.page_title")).getText());
		    } catch (Error e) {
		      
		    	logger.error("new editor reply单图文创建失败");
		      Assert.fail("new editor reply单图文创建失败");
		    }
	 }
}
