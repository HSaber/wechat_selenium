package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PressKeywordVoice extends TestBase{
	@Test
	  public void pressKeywordVoice() throws Exception {
		
	    driver.findElement(By.linkText("Messaging")).click();
	    driver.findElement(By.linkText("Auto Responders")).click();
	    driver.findElement(By.linkText("Keyword Auto-Reply")).click();
	    
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	   
	    driver.findElement(By.id("KeyWord_category")).clear();
	    driver.findElement(By.id("KeyWord_category")).sendKeys("keyword压力测试--voice");
	    driver.findElement(By.id("KeyWord_name")).clear();
	    driver.findElement(By.id("KeyWord_name")).sendKeys("keyword压力测试--voice");
	    driver.findElement(By.id("KeyWord_internal_name")).clear();
	    driver.findElement(By.id("KeyWord_internal_name")).sendKeys("keyword压力测试--voice");
	    driver.findElement(By.cssSelector("input.keyword")).clear();
	    driver.findElement(By.cssSelector("input.keyword")).sendKeys("keywordpressvoice");
	    
	    String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    driver.findElement(By.xpath("(//input[@type='text'])[7]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("keywordpressvoice"+ time);
	    Thread.sleep(1000);
	    new Select(driver.findElement(By.cssSelector("select.matchingType.half"))).selectByVisibleText("Full Matching");
	    
	    driver.findElement(By.xpath("(//input[@id='KeyWord_replyType'])[5]")).click();
	    //Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[@id='voice_upload']/input")).clear();
	    driver.findElement(By.xpath("//div[@id='voice_upload']/input")).sendKeys(file+"/com/material/welcome.mp3");
	    Thread.sleep(2000);
	    
	    String src = null;
		src= driver.findElement(By.xpath("//div[@id='voice_upload']/audio")).getAttribute("src");
		System.out.println("src的值是："+ src);
	    while(src==null||src.equals("")){
			   src= driver.findElement(By.xpath("//div[@id='voice_upload']/audio")).getAttribute("src");
			   System.out.println("voice未上传完成,继续等待中！");
	    }
			   driver.findElement(By.name("yt0")).click();
	    try {
	      AssertJUnit.assertEquals("keyword压力测试--voice", driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	    	System.out.println("创建keywordvoice，list页面category显示错误，或者创建失败，请检查！！！");
	    	Assert.fail("创建keywordvoice，list页面category显示错误，或者创建失败，请检查！！！");
	    }
	   
	  }
}
