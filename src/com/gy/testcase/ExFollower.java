package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;

public class ExFollower extends TestBase{
	private static Logger logger = Logger.getLogger(ExFollower.class);
	 @Test
	  public void exFollower() throws Exception {
	    
	    driver.findElement(By.linkText("Follower Management")).click();
	    driver.findElement(By.linkText("Ex-Followers")).click();
	    
	    if(isElementPresent(By.cssSelector("a.view > img[alt=\"图片已加载！\"]"))){
		    
	    	driver.findElement(By.cssSelector("a.view > img[alt=\"图片已加载！\"]")).click();
		    driver.findElement(By.xpath("//div[@id='topTab']/div[4]/dl/dd/div/div/div[2]/div")).click();
		    driver.findElement(By.id("message")).click();
		    WebElement editor = driver.findElement(By.cssSelector("div.edit_area.js_editorArea.reply_html"));
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("$('div.js_editorArea').text('{{nickname}}');", editor);
		    driver.findElement(By.cssSelector("button.send_msg")).click();
		    Thread.sleep(1000);
		    try {
		      AssertJUnit.assertEquals("Message sent failed! Please try again!", driver.findElement(By.xpath("//div[5]/p")).getText());
		    } catch (Error e) {
		     
		    	logger.error("给取消关注的用户发message，没有提示发送失败");
		      Assert.fail("给取消关注的用户发message，没有提示发送失败");
		    }
		    driver.findElement(By.cssSelector("button.cancel")).click();
		    
	    }else{
		    
	    	try{	
		    	AssertJUnit.assertEquals("No results found.", driver.findElement(By.cssSelector("span.empty")).getText());
		    	System.out.println("本账号没有取消关注的人");
	    	}catch(Error e){
	    		
	    		logger.error("没显示No results found.页面样式有问题");
	    		Assert.fail("没显示No results found.页面样式有问题");
	    	}
		    }
	  }

}
