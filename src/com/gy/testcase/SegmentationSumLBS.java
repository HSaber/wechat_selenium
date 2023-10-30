package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SegmentationSumLBS extends TestBase{
	private static Logger logger = Logger.getLogger(SegmentationSumLBS.class);
	 @Test
	  public void segmentationSumLBS() throws Exception {
	    
	    driver.findElement(By.linkText("Follower Management")).click();
	    driver.findElement(By.linkText("Segmentation")).click();
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
	    new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("LBS");
	    new Select(driver.findElement(By.cssSelector("select.tempCountryField"))).selectByVisibleText("Empty");
	    driver.findElement(By.linkText("Preview")).click();
	    Thread.sleep(3000);
	    String empty = driver.findElement(By.cssSelector("div.summary")).getText();
	    //empty.substring(1 ,3 );
	    int a = catchNum("of "," results.",empty);
	    
	   
	    new Select(driver.findElement(By.cssSelector("select.tempCountryField"))).selectByVisibleText("Aboard");
	    driver.findElement(By.linkText("Preview")).click();
	    Thread.sleep(3000);
	    String abroad = driver.findElement(By.cssSelector("div.summary")).getText();
	    int b = catchNum("of "," results.",abroad);
	   
	    new Select(driver.findElement(By.cssSelector("select.tempCountryField"))).selectByVisibleText("China");
	    driver.findElement(By.cssSelector("option[value=\"中国\"]")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.cssSelector("input.but80.Set")).click();
	    driver.findElement(By.linkText("Preview")).click();
	    Thread.sleep(3000);
	    String china = driver.findElement(By.cssSelector("div.summary")).getText();
	    int c = catchNum("of "," results.",china);
	    
	    int sum = a+b+c;
	    System.out.println("LBSsegmentation总人数是："+sum);
	    
	    driver.findElement(By.linkText("Follower Management")).click();
	    driver.findElement(By.linkText("Followers")).click();
	    Thread.sleep(3000);
	    String follower = driver.findElement(By.cssSelector("div.summary")).getText();
	    int d = catchNum("of "," results.",follower);
	    System.out.println("总follower人数是："+d);
	    
	    try{
	    	
	    	AssertJUnit.assertEquals(sum,d);
	    }catch(Error e){
	    	
	    	logger.error("LBS Segmentation 3 种类型人数的和与总关注人数不匹配");
	    	Assert.fail("LBS Segmentation 3 种类型人数的和与总关注人数不匹配");
	    }
	    
	 }
	 
}
