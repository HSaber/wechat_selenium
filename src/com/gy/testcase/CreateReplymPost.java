package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class CreateReplymPost extends TestBase{
	private static Logger logger = Logger.getLogger(CreateReplymPost.class);
	@Test(groups="createReplyMPost",dependsOnGroups="send48Post")
	  public void createReplyMPost() throws Exception {
		//进入create页面
	
		oldEditorPage();
	    driver.findElement(By.id("create_reply_post")).click();//跳到reply编辑页面
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("i.fa.fa-plus")).click();//增加一个item
	   //第一个item
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("jQuery('div.appmsg-mark').show();");
	    //Thread.sleep(4000);
	    boolean status3 = isElementPresent(By.xpath("//div[@id='J_sortable']/div[1]/div[2]/a[1]"));
	    while(status3==false){
	    	status3 = isElementPresent(By.xpath("//div[@id='J_sortable']/div[1]/div[2]/a[1]"));
	    	System.out.println("多图文创建浮动框未显示完成，请等待！！");
	    }
	   // Actions action=new Actions(driver);
	    WebElement edit1=driver.findElement(By.xpath("//div[@id='J_sortable']/div[1]/div[2]/a[1]"));
	    //action.moveToElement(driver.findElement(By.xpath("//div[@id='J_sortable']/div[1]"))).perform();
	    edit1.click();
	    driver.findElement(By.name("msgName")).sendKeys("reply'post多图文“测试” selenium");
	    driver.findElement(By.name("msgTitle")).sendKeys("reply'title1“测试” selenium");
	    
	    driver.findElement(By.id("msgContentUrl")).clear();
	    driver.findElement(By.id("msgContentUrl")).sendKeys("http://app.jingsocial.com/artview/index/v/3/rid/11505/wid/42");
	    //上传封面图片
	    driver.findElement(By.id("addPic_avatar")).sendKeys(file+"/com/material/0.jpg");
	    boolean status = isElementPresent(By.cssSelector(".jcrop-tracker"));
	    while(status==false){
	    	status = isElementPresent(By.cssSelector(".jcrop-tracker"));
	    	System.out.println("封面图片上传未完成，请等待！！");
	    }
	    //Thread.sleep(3000);
	    driver.findElement(By.linkText("Cancel")).click();
	    Thread.sleep(4000);
	    //第二个item
	    WebElement edit2=driver.findElement(By.xpath("//div[@id='J_sortable']/div[2]/div[2]/div/a[1]"));
	    //action.moveToElement(driver.findElement(By.xpath("//div[@id='J_sortable']/div[2]"))).perform();
	    edit2.click();
	    driver.findElement(By.name("msgTitle")).sendKeys("reply'title2“测试” ");
	    
	    driver.findElement(By.id("msgContentUrl")).clear();
	    driver.findElement(By.id("msgContentUrl")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA4MzI2NTMzOA==&mid=2652212192&idx=1&sn=c484b75bebcee9c9f6f51cc2042ce18e&chksm=841839b5b36fb0a3b6225eadd4285c51af4beca105275a53b2451e8d5b894563129d0582491e&scene=21#wechat_redirect");
	    //上传封面图片
	  //long t1=System.currentTimeMillis();  
	    driver.findElement(By.id("addPic_avatar")).sendKeys(file+"/com/material/6.jpg");
        //Thread.sleep(3000);
        boolean status1 = isElementPresent(By.cssSelector(".jcrop-tracker"));
	    while(status1==false){
	    	status1 = isElementPresent(By.cssSelector(".jcrop-tracker"));
	    	System.out.println("封面图片上传未完成，请等待！！");
	    }
	    driver.findElement(By.linkText("Cancel")).click();
	    Thread.sleep(3000);
	   // long t2=System.currentTimeMillis();  
        //System.out.println(t2-t1);  	
	    driver.findElement(By.id("J_addNews")).click();
	    //Thread.sleep(15000);
	    boolean status2 = isElementPresent(By.xpath("//a[@id='create-more-posts']/button"));
	    while(status2==false){
	    	status2 = isElementPresent(By.xpath("//a[@id='create-more-posts']/button"));
	    	System.out.println("reply multi post创建保存未完成，请等待！！");
	    }
        try {
	      AssertJUnit.assertEquals("Manage Posts", driver.findElement(By.cssSelector("h1.page_title")).getText());
	    } catch (Error e) {
	      
	    	logger.error("reply多图文创建失败");
	      Assert.fail("reply多图文创建失败");
	    }
	  }
}
