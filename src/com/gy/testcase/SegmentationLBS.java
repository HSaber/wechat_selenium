package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SegmentationLBS extends TestBase{
	private static Logger logger = Logger.getLogger(SegmentationLBS.class);
	 @Test
	  public void segmentationLBS() throws Exception {
	    
	    driver.findElement(By.linkText("Follower Management")).click();
	    driver.findElement(By.linkText("Segmentation")).click();
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("seleniumLBS");
	    driver.findElement(By.id("dynamicRules_note")).clear();
	    driver.findElement(By.id("dynamicRules_note")).sendKeys("shanghai&North_China");
	    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
	    new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("LBS");
	    new Select(driver.findElement(By.cssSelector("select.tempCountryField"))).selectByVisibleText("China");
	    //driver.findElement(By.cssSelector("option[value=\"中国\"]")).click();
	    driver.findElement(By.xpath("//div[@id='1']/label/input")).click();
	    driver.findElement(By.xpath("//div[@id='sel_district']/div[2]/div[3]/div[1]/div[2]/div[1]/input")).click();
	   //Thread.sleep(3000);
	    try {
	      AssertJUnit.assertEquals("Shanghai", driver.findElement(By.xpath(".//*[@id='sel_district']/div[2]/div[3]/div[2]/div/label[1]")).getText());
	    } catch (Error e) {
	     
	    	logger.error("勾选上海失败");
	      Assert.fail("勾选上海失败");
	    }
	    try {
	      AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='sel_district']/div[2]/div[3]/div[2]/div/label[5]")).getText().contains("Hebei"));
	    } catch (Error e) {
	     
	    	logger.error("勾选North_China失败");
	      Assert.fail("勾选North_China失败");
	    }
	    driver.findElement(By.cssSelector("input.but80.Set")).click();
	   // Thread.sleep(2000);
	    try {
	      AssertJUnit.assertEquals("Shanghai++Beijing+Tianjin+Shanxi+Hebei+Inner Mongolia", driver.findElement(By.id("district_cn")).getAttribute("value"));
	    } catch (Error e) {
	      
	    	logger.error("segmentation city选择结果未显示在文本框");
	      Assert.fail("segmentation city选择结果未显示在文本框");
	    }
	    driver.findElement(By.linkText("Preview")).click();
	    Thread.sleep(3000);
	    try {
	      AssertJUnit.assertTrue(driver.findElement(By.cssSelector("div.summary")).getText().contains("Displaying"));
	    } catch (Error e) {
	     
	    	logger.error("LBS segmentation preview失败");
	      Assert.fail("LBS segmentation preview失败");
	    }
	    driver.findElement(By.name("yt1")).click();
	    try {
	      AssertJUnit.assertEquals("seleniumLBS", driver.findElement(By.cssSelector("tr.odd > td")).getText());
	    } catch (Error e) {
	    
	    	logger.error("LBS segmentation创建失败");
	      Assert.fail("LBS segmentation创建失败");
	    }
	    
	 }
}
