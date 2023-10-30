package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PressKeywordImage extends TestBase{
	@Test
	  public void pressKeywordImage() throws Exception {
		
	    driver.findElement(By.linkText("Messaging")).click();
	    driver.findElement(By.linkText("Auto Responders")).click();
	    driver.findElement(By.linkText("Keyword Auto-Reply")).click();
	    
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    
	    driver.findElement(By.id("KeyWord_category")).clear();
	    driver.findElement(By.id("KeyWord_category")).sendKeys("keyword压力测试--图片");
	    driver.findElement(By.id("KeyWord_name")).clear();
	    driver.findElement(By.id("KeyWord_name")).sendKeys("keyword压力测试--image");
	    driver.findElement(By.id("KeyWord_internal_name")).clear();
	    driver.findElement(By.id("KeyWord_internal_name")).sendKeys("keyword压力测试--image");
	    driver.findElement(By.cssSelector("input.keyword")).clear();
	    driver.findElement(By.cssSelector("input.keyword")).sendKeys("keywordpressimage");
	   
	    String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    driver.findElement(By.xpath("(//input[@type='text'])[7]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("keywordpressimage" + time);
	    Thread.sleep(1000);
	    new Select(driver.findElement(By.cssSelector("select.matchingType.half"))).selectByVisibleText("Full Matching");
	    
	    driver.findElement(By.xpath("(//input[@id='KeyWord_replyType'])[3]")).click();
	    driver.findElement(By.name("files[]")).clear();
	    driver.findElement(By.name("files[]")).sendKeys(file+"/com/material/9.jpg");
	    String src = null;
		src= driver.findElement(By.xpath("//div[@id='pic_upload']/img")).getAttribute("src");
		System.out.println("src的值是："+ src);
		while(src==null||src.equals("")){
			 src= driver.findElement(By.xpath("//div[@id='pic_upload']/img")).getAttribute("src");
			 System.out.println("image未上传完成,继续等待中！");
		}
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    try {
	      AssertJUnit.assertEquals("keyword压力测试--图片", driver.findElement(By.xpath("//div[@id='key-word-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	    	System.out.println("创建keywordimage，list页面category显示错误，或者创建失败，请检查！！！");
	    	Assert.fail("创建keywordimage，list页面category显示错误，或者创建失败，请检查！！！");
	    }
	   
	  }

}
