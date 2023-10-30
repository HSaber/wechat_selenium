package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PressKeywordText extends TestBase{
	@Test
	   public void pressKeywordText() throws Exception {
		
	    driver.findElement(By.linkText("Messaging")).click();
	    driver.findElement(By.linkText("Auto Responders")).click();
	    driver.findElement(By.linkText("Keyword Auto-Reply")).click();
	    //System.out.println(driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[6]")).getText());
	    //======================创建keyword======================
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    driver.findElement(By.id("KeyWord_category")).clear();
	    driver.findElement(By.id("KeyWord_category")).sendKeys("keyword压力测试--message");
	    driver.findElement(By.id("KeyWord_name")).click();
	    driver.findElement(By.id("KeyWord_name")).clear();
	    driver.findElement(By.id("KeyWord_name")).sendKeys("keyword压力测试--message");
	    driver.findElement(By.id("KeyWord_internal_name")).clear();
	    driver.findElement(By.id("KeyWord_internal_name")).sendKeys("keyword压力测试--message");
	    
	    driver.findElement(By.xpath("//form[@id='key-word-form']/div[5]/div/input")).clear();
	    driver.findElement(By.xpath("//form[@id='key-word-form']/div[5]/div/input")).sendKeys("keywordpressmessage");
	    //new Select(driver.findElement(By.xpath("//form[@id='key-word-form']/div[5]/select"))).selectByVisibleText("Full Matching");
	    new Select(driver.findElement(By.cssSelector("select.matchingType.half"))).selectByVisibleText("Full Matching");
	   
	    String time=currentTime();
	    driver.findElement(By.id("tags")).click();
	    driver.findElement(By.xpath("(//input[@type='text'])[7]")).click();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("keywordpressmessage" + time);
	    Thread.sleep(1000);
	    new Select(driver.findElement(By.cssSelector("select.matchingType.half"))).selectByVisibleText("Full Matching");
	    
	    driver.findElement(By.xpath("(//input[@id='KeyWord_replyType'])[2]")).click();
	    WebElement editor = driver.findElement(By.xpath("//div[@id='text']/div"));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("$('div.js_editorArea').text('由Keyword压力测试触发：{{nickname}}"+ time +"');", editor);
	    editor.sendKeys(Keys.ENTER);
	    
	    driver.findElement(By.linkText("Facial Expression")).click();
	    driver.findElement(By.cssSelector("i.js_emotion_i")).click();
	    driver.findElement(By.name("yt0")).click();
	    try {
	      AssertJUnit.assertEquals("keyword压力测试--message", driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
	    } catch (Error e) {
	     
	      System.out.println("keywordText创建后table中category显示错误");
	      Assert.fail("keywordText创建后table中category显示错误");
	    }
	}
}
