package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class CreateReplysPost extends TestBase{
	private static Logger logger = Logger.getLogger(CreateReplysPost.class);
	@Test
	  public void createReplySPost() throws Exception {
		//进入create页面
		
		oldEditorPage();
		driver.findElement(By.id("create_reply_post")).click();//跳到reply编辑页面
	    Thread.sleep(2000);
	    Actions action=new Actions(driver);
	    WebElement edit1=driver.findElement(By.xpath("//div[@id='J_sortable']/div[1]/div[2]/a[1]"));
	    action.moveToElement(driver.findElement(By.xpath("//div[@id='J_sortable']/div[1]"))).perform();
	    edit1.click();
	    String timetitle = currentTime();
	    driver.findElement(By.name("msgName")).sendKeys("reply'post单图文“测试”selenium" + timetitle);
	    driver.findElement(By.name("msgTitle")).sendKeys("reply'title“测试” selenium" + timetitle);
	    driver.findElement(By.id("msgContentUrl")).clear();
	    driver.findElement(By.id("msgContentUrl")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA4MzI2NTMzOA==&mid=2652212192&idx=1&sn=c484b75bebcee9c9f6f51cc2042ce18e&chksm=841839b5b36fb0a3b6225eadd4285c51af4beca105275a53b2451e8d5b894563129d0582491e&scene=21#wechat_redirect");
	    //上传封面图片
	    driver.findElement(By.id("addPic_avatar")).sendKeys(file+"/com/material/0.jpg");
	    System.out.println(file+"/com/material/0.jpg");
	    //Thread.sleep(10000);
	    boolean status = isElementPresent(By.cssSelector(".jcrop-tracker"));
	    while(status==false){
	    	status = isElementPresent(By.cssSelector(".jcrop-tracker"));
	    	System.out.println("封面图片上传未完成，请等待！！");
	    }
	    
	    driver.findElement(By.linkText("Cancel")).click();
	    
	    Thread.sleep(3000);
	    
	    driver.findElement(By.id("J_addNews")).click();
	    //Thread.sleep(10000);
	    
	    boolean status1 = isElementPresent(By.xpath("//a[@id='create-more-posts']/button"));
	    while(status1==false){
	    	status1 = isElementPresent(By.xpath("//a[@id='create-more-posts']/button"));
	    	System.out.println("reply single post创建保存未完成，请等待！！");
	    }
	    
	    try {
	      AssertJUnit.assertEquals("Manage Posts", driver.findElement(By.cssSelector("h1.page_title")).getText());
	    } catch (Error e) {
	      
	    	logger.error("reply单图文创建失败");
	      Assert.fail("reply单图文创建失败");
	    }
	  }
}
