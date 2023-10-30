package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;


public class NewEditorMultiReplyPost extends TestBase{
	private static Logger logger = Logger.getLogger(NewEditorMultiReplyPost.class);
	//@Test
	 @Test(groups="newEditorMultiReplyPost",dependsOnGroups="send48Post")
	  public void newEditorMultiReplyPost() throws Exception {
		 
		 navigation("Manage Posts");
		 
		 driver.findElement(By.id("create-more-posts")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).click();
		 driver.findElement(By.xpath("//div[5]/div/div[2]/div[2]/div[1]/input")).sendKeys("new editor multi reply post'selenium测试");//new editor multi reply post'selenium测试
		 
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("jQuery('input').css('visibility','visible');");
		 driver.findElement(By.xpath("(//input[@id='create_reply_post'])[2]")).click();
		
		 driver.findElement(By.linkText("Submit")).click();
		 
		 driver.findElement(By.name("msgTitle")).sendKeys("new editor multi reply post'selenium测试--1");
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/16.jpg");
		 //Thread.sleep(3000);
		 boolean status = isElementPresent(By.cssSelector(".jcrop-tracker"));
		 while(status==false){
		    	status = isElementPresent(By.cssSelector(".jcrop-tracker"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 
		 
		 driver.findElement(By.linkText("Save")).click();
		 
		
		 String src = null;
		 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
		 System.out.println("src的值是："+ src);
		 while(src==null||src.equals("")){
				 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
				 System.out.println("封面图片未上传完成,继续等待中！");
		 }
		 
		 String time = currentTime();
		 WebElement target = driver.findElement(By.cssSelector(".tags_add"));
		 js.executeScript("arguments[0].scrollIntoView();", target);
		 driver.findElement(By.cssSelector(".tags_add")).click();
		 driver.findElement(By.cssSelector(".tagInputField.ui-autocomplete-input")).sendKeys("neweditor reply多图文-1"+ time);
		
		 driver.findElement(By.id("msgContentUrl")).sendKeys("http://app.jingsocial.com/artview/index/v/2/rid/11505/wid/42");
		 
		 
		 js.executeScript("jQuery('.postOperation').css({'opacity':'1','-webkit-transform':'translate3d(0,2px,0)'})");
		
		 Thread.sleep(2000);
		 //Actions action = new Actions(driver);
		 //action.moveToElement(driver.findElement(By.xpath("//div[@id='postSetting']/div[2]"))).perform();
		 
		 driver.findElement(By.xpath(".//*[@id='J_sortable']/section")).click();
		 driver.findElement(By.xpath(".//*[@id='postSetting']/div[2]")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.name("msgTitle")).sendKeys("new editor multi reply post'selenium测试--2");
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/17.gif");
		 //Thread.sleep(3000);
		 boolean status1 = isElementPresent(By.linkText("Save"));
		 while(status1==false){
		    	status1 = isElementPresent(By.linkText("Save"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 boolean status01 = isElementPresent(By.xpath(".//*[@id='J_uploadHtml']/div/div[1]/div[1]/div[5]"));
		 while(status01==false){
		    	status01 = isElementPresent(By.xpath(".//*[@id='J_uploadHtml']/div/div[1]/div[1]/div[5]"));
		    	System.out.println("裁剪区域未加载出来，请等待！！");
		 }
		 
		 driver.findElement(By.linkText("Save")).click();
		 
		 src = null;
		 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
		 System.out.println("src的值是："+ src);
		 while(src==null||src.equals("")){
				 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
				 System.out.println("封面图片未上传完成,继续等待中！");
		 }
		 
		 driver.findElement(By.id("msgContentUrl")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA4MzI2NTMzOA==&mid=2652212192&idx=1&sn=c484b75bebcee9c9f6f51cc2042ce18e&chksm=841839b5b36fb0a3b6225eadd4285c51af4beca105275a53b2451e8d5b894563129d0582491e&scene=21#wechat_redirect");
		 
		 driver.findElement(By.xpath(".//*[@id='postSetting']/section")).click();
		 
		 Thread.sleep(1000);
		 driver.findElement(By.name("msgTitle")).sendKeys("new editor multi reply post'selenium测试--3");
		 driver.findElement(By.id("uploadPic")).sendKeys(file+"/com/material/7.jpg");
		 //Thread.sleep(3000);
		 boolean status2 = isElementPresent(By.linkText("Cancel"));
		 while(status2==false){
		    	status2 = isElementPresent(By.linkText("Cancel"));
		    	System.out.println("封面图片弹框未弹出，请等待！！");
		 }
		 
		 
		 driver.findElement(By.linkText("Cancel")).click();
		 
		 src = null;
		 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
		 System.out.println("src的值是："+ src);
		 while(src==null||src.equals("")){
				 src= driver.findElement(By.cssSelector(".upload-prev-img")).getAttribute("src");
				 System.out.println("封面图片未上传完成,继续等待中！");
		 }
		 
		 WebElement target1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[7]"));
		 js.executeScript("arguments[0].scrollIntoView();", target1);
		 driver.findElement(By.xpath("(//input[@type='checkbox'])[7]")).click();
		 driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("neweditor reply多图文-3"+ time);
		 
		 driver.findElement(By.id("msgContentUrl")).sendKeys("http://app.jingsocial.com/redirect/index/?l=DBHSowfyEnT9HyZpH9hRndIzA7tCh2INBu6eVcbUA/c=");
		 driver.findElement(By.id("J_addNews")).click();
		 Thread.sleep(2000);
		 try {
		      AssertJUnit.assertEquals("Manage Posts", driver.findElement(By.cssSelector("h1.page_title")).getText());
		    } catch (Error e) {
		      
		    	logger.error("new editor reply多图文创建失败");
		      Assert.fail("new editor reply多图文创建失败");
		    }
	 }
}
