package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;

public class AddMultiPostSummary extends TestBase{
	private static Logger logger = Logger.getLogger(AddMultiPostSummary.class);
	//@Test
	@Test(groups="addMultiPostSummary",dependsOnGroups="newEditorMultiPost")
	  public void addMultiPostSummary() throws Exception {
	   
		navigation("Manage Posts");
	    try{
	    	driver.findElement(By.xpath("//div[@id='js_search_title']/span/input")).clear();
	    	
	    }catch(org.openqa.selenium.InvalidElementStateException e){
	    	driver.findElement(By.id("post_filter_search")).click();
	    }
	    
	    driver.findElement(By.xpath("//div[@id='js_search_title']/span/input")).clear();
	    driver.findElement(By.xpath("//div[@id='js_search_title']/span/input")).sendKeys("new editor多图文'selenium");
	    driver.findElement(By.cssSelector("i.icon16_common.search_gray")).click();
	    Thread.sleep(8000);
	    driver.findElement(By.xpath(".//*[@id='main']/div/div/div/div[2]/ul/li[1]/a/span")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.id("Summary")).clear();
	    driver.findElement(By.id("Summary")).sendKeys("items 1:\nnew post editor update summary: add summary in normal multi post\nthe post created in old editor");
	    
	    driver.findElement(By.xpath("//div[@id='postSetting']/div[3]/figure")).click();
	    driver.findElement(By.id("Summary")).clear();
	    driver.findElement(By.id("Summary")).sendKeys("items 3:\nnew post editor update summary: add summary in normal multi post\nthe post created in old editor");
	   
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement target = driver.findElement(By.id("J_save"));
		js.executeScript("arguments[0].scrollIntoView();", target);
	    driver.findElement(By.id("J_save")).click();
	    Thread.sleep(5000);
	   
	    try {
	      AssertJUnit.assertEquals("items 1:\nnew post editor update summary: add summary in normal multi post\nthe post created in old editor", driver.findElement(By.id("Summary")).getAttribute("value"));
	    } catch (Error e) {
	      
	    	logger.error("items 1 summmary没有添加成功");
	      Assert.fail("items 1 summmary没有添加成功");
	    }
	   
	    driver.findElement(By.xpath("//div[@id='postSetting']/div[3]/figure")).click();
	    try {
	      AssertJUnit.assertEquals("items 3:\nnew post editor update summary: add summary in normal multi post\nthe post created in old editor", driver.findElement(By.id("Summary")).getAttribute("value"));
	    } catch (Error e) {
	      
	    	logger.error("items 3 summmary没有添加成功");
	      Assert.fail("items 3 summmary没有添加成功");
	    }
	  }
}
