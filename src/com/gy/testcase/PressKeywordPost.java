package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PressKeywordPost extends TestBase{
	  @Test
	   public void pressKeywordPost() throws Exception {
		
	    driver.findElement(By.linkText("Messaging")).click();
	    driver.findElement(By.linkText("Auto Responders")).click();
	    driver.findElement(By.linkText("Keyword Auto-Reply")).click();
	    //======================创建keyword======================
	    
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	   
	 //输入keyword
	    driver.findElement(By.cssSelector("input.keyword")).click();
	    driver.findElement(By.cssSelector("input.keyword")).clear();
	    driver.findElement(By.cssSelector("input.keyword")).sendKeys("keywordpresspost");
	    new Select(driver.findElement(By.cssSelector("select.matchingType.half"))).selectByVisibleText("Full Matching");
	   
	   //输入name 
	    driver.findElement(By.id("KeyWord_category")).clear();
	    driver.findElement(By.id("KeyWord_category")).sendKeys("keyword压力测试--post");
	    driver.findElement(By.id("KeyWord_name")).clear();
	    driver.findElement(By.id("KeyWord_name")).sendKeys("keyword压力测试--post");
	    driver.findElement(By.id("KeyWord_internal_name")).clear();
	    driver.findElement(By.id("KeyWord_internal_name")).sendKeys("keyword压力测试--post");
	   //添加tag
	    String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    driver.findElement(By.xpath("//ul[@id='tag_handler']/li/input")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("keywordpresspost"+time);
	    driver.findElement(By.id("tags")).click();
	    driver.findElement(By.id("tags")).click();
	    Thread.sleep(1000);
	    new Select(driver.findElement(By.id("KeyWord_resId"))).selectByVisibleText("multi'post多图文“测试” selenium");
	    Thread.sleep(2000);
	    driver.findElement(By.name("yt0")).click();
	    //验证是否创建成功 
	    try {
	      AssertJUnit.assertEquals("keyword压力测试--post", driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText());
	    } catch (Error e) {
	     
	      System.out.println("keyword name table中显示不正确");
	      Assert.fail("keyword name table中显示不正确");
	    }
	   
	   }
}
