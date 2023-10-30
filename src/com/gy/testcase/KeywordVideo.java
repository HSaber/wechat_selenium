package com.gy.testcase;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

import static org.testng.Assert.*;

import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class KeywordVideo extends TestBase{
	private static Logger logger = Logger.getLogger(KeywordVideo.class);
	@Test(groups="keywordVideo")
	  public void keywordVideo() throws Exception {
		//login();
		navigation("Keyword Auto-Reply");
	    
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    driver.findElement(By.xpath("(//input[@id='KeyWord_replyType'])[4]")).click();
	    driver.findElement(By.name("yt0")).click();
	    AssertJUnit.assertEquals("Video must be uploaded!", closeAlertAndGetItsText());
	    
	    driver.findElement(By.id("KeyWord_category")).clear();
	    driver.findElement(By.id("KeyWord_category")).sendKeys("seleniumkeywordvideo");
	    driver.findElement(By.id("KeyWord_name")).clear();
	    driver.findElement(By.id("KeyWord_name")).sendKeys("keywordvideoselenium");
	    driver.findElement(By.id("KeyWord_internal_name")).clear();
	    driver.findElement(By.id("KeyWord_internal_name")).sendKeys("keywordvideoselenium");
	    driver.findElement(By.cssSelector("input.keyword")).clear();
	    driver.findElement(By.cssSelector("input.keyword")).sendKeys("seleniumvideo");
	    new Select(driver.findElement(By.cssSelector("select.matchingType.half"))).selectByVisibleText("Full Matching");
	    
	    String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    driver.findElement(By.xpath("(//input[@type='text'])[7]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("seleniumvideo" + time);
	    Thread.sleep(1000);
	    new Select(driver.findElement(By.cssSelector("select.matchingType.half"))).selectByVisibleText("Full Matching");
	    new Select(driver.findElement(By.cssSelector("select.matchingType.half"))).selectByVisibleText("Full Matching");
	    
	    driver.findElement(By.xpath("(//input[@id='KeyWord_replyType'])[4]")).click();
	    driver.findElement(By.xpath("//div[@id='video_upload']/input")).clear();
	    driver.findElement(By.xpath("//div[@id='video_upload']/input")).sendKeys(file+"/com/material/test_video.mp4");
	    
	    String src = null;
	    src= driver.findElement(By.xpath("//div[@id='video_upload']/video")).getAttribute("src");
	    System.out.println("src的值是："+ src);
	    while(src==null||src.equals("")){
		   src= driver.findElement(By.xpath("//div[@id='video_upload']/video")).getAttribute("src");
		   System.out.println("video未上传完成,继续等待中！");
	   }
	   
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.name("KeyWord[keyWord]")).clear();
	    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys("seleniumvideo");
	    new Select(driver.findElement(By.name("KeyWord[status]"))).selectByVisibleText("Active");
	    String search = "";
	    boolean b = true;
	  
	    while(true){
	    	b = true;
	    	try{
	    		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[4]")).getText();
	    	}catch(org.openqa.selenium.StaleElementReferenceException e){
	   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
	   		 	b=false;
	    	}
	    	
	    	if(search.equals("seleniumvideo")&&b)
	    			break;
	    }
	    
	    try {
		      AssertJUnit.assertEquals("seleniumvideo", search);
		    } catch (Error e) {
		    	logger.error("创建keywordvideo，list页面keyword显示错误，或者创建失败，请检查！！！");
		    	Assert.fail("创建keywordvideo，list页面keyword显示错误，或者创建失败，请检查！！！");
		    }
	    
	    while(true){
	    	b = true;
	    	try{
	    		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[2]")).getText();
	    	}catch(org.openqa.selenium.StaleElementReferenceException e){
	   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
	   		 	b=false;
	    	}
	    	
	    	if(b)
	    			break;
	    }
	    try {
	      AssertJUnit.assertEquals("seleniumkeywordvideo", search);
	    } catch (Error e) {
	    	logger.error("创建keywordvideo，list页面category显示错误，或者创建失败，请检查！！！");
	    	Assert.fail("创建keywordvideo，list页面category显示错误，或者创建失败，请检查！！！");
	    }
	    
	    while(true){
	    	b = true;
	    	try{
	    		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[3]")).getText();
	    	}catch(org.openqa.selenium.StaleElementReferenceException e){
	   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
	   		 	b=false;
	    	}
	    	
	    	if(b)
	    			break;
	    }
	    try {
	      AssertJUnit.assertEquals("keywordvideoselenium", search);
	    } catch (Error e) {
	    	logger.error("创建keywordvideo，list页面name显示错误，或者创建失败，请检查！！！");
	    	Assert.fail("创建keywordvideo，list页面name显示错误，或者创建失败，请检查！！！");
	    }
	   
	    
	    while(true){
	    	b = true;
	    	try{
	    		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[5]")).getText();
	    	}catch(org.openqa.selenium.StaleElementReferenceException e){
	   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
	   		 	b=false;
	    	}
	    	
	    	if(b)
	    			break;
	    }
	    try {
	      AssertJUnit.assertEquals("Video", search);
	    } catch (Error e) {
	    	logger.error("创建keywordvideo，list页面type显示错误，或者创建失败，请检查！！！");
	    	Assert.fail("创建keywordvideo，list页面type显示错误，或者创建失败，请检查！！！");
	    }
	    
	    while(true){
	    	 b=true;
        	try{
        		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[6]/video")).getAttribute("src");
        	}catch(org.openqa.selenium.StaleElementReferenceException ex){
        		b=false;
        	}
        	if(b)
        		break;
        }
	    
	   try {
	    	 assertNotEquals(null, search);
	    } catch (Error e) {
	    	logger.error("创建keywordvideo，list页面不显示图片，或者创建失败，请检查！！！");
	    	Assert.fail("创建keywordvideo，list页面不显示图片，或者创建失败，请检查！！！");
	    }
	   String category = null;
	    while(true){
	    	 b = true;
	    	try{
	    		 category = driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[7]/a")).getText();
	    	}catch(org.openqa.selenium.StaleElementReferenceException e){
	    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
	    		 b=false;
	    	}
	    	if(category.equals("0")&&b)
	    		break;
	    }
	    try {
	      AssertJUnit.assertEquals("0", category);
	    } catch (Error e) {
	    	logger.error("创建keywordvideo，list页面触发次数显示不对，或者创建失败，请检查！！！");
	    	Assert.fail("创建keywordvideo，list页面触发次数显示不对，或者创建失败，请检查！！！");
	    }
	    
	    
	    while(true){
	    	b = true;
	    	try{
	    		search=driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[8]/img")).getAttribute("src");
	    	}catch(org.openqa.selenium.StaleElementReferenceException e){
	   		 	System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
	   		 	b=false;
	    	}
	    	
	    	if(b)
	    			break;
	    }
	    try {
	    	AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png", search);
	    } catch (Error e) {
	    	logger.error("创建keywordvideo，list页面status显示不对，或者创建失败，请检查！！！");
	    	Assert.fail("创建keywordvideo，list页面status显示不对，或者创建失败，请检查！！！");
	    }
	    
	    while(true){
	    	boolean a=true;
        	try{
        		driver.findElement(By.xpath(".//*[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[9]/a")).click();
        	}catch(org.openqa.selenium.StaleElementReferenceException ex){
        		a=false;
        	 }
        	if(a)
        		break;
        }
	    
	    Thread.sleep(2000);
	    try {
	      AssertJUnit.assertEquals("seleniumvideo" + time, driver.findElement(By.cssSelector("li.tagItem")).getText());
	    } catch (Error e) {
	    	logger.error("keyword tag没加上，请检查！！");
	    	Assert.fail("keyword tag没加上，请检查！！");
	    }
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    try {
	      AssertJUnit.assertEquals("Keyword Auto-Responders", driver.findElement(By.cssSelector("h1.page_title")).getText());
	    } catch (Error e) {
	    	logger.error("keyword update失败！！！");
	    	Assert.fail("keyword update失败！！！");
	    }
	  }
}
