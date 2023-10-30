package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SegmentationChina extends TestBase{
	private static Logger logger = Logger.getLogger(SegmentationChina.class);
	//@Test
	@Test(groups="segmentationChina",dependsOnGroups="messageTriggerPMInteractive")
	  public void segmentationChina() throws Exception {
		
		 navigation("Segmentation");
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("seleniumchina");
	    driver.findElement(By.id("dynamicRules_note")).clear();
	    driver.findElement(By.id("dynamicRules_note")).sendKeys("seleniumchina");
	    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
	    new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("Profile");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[2]"))).selectByVisibleText("Wechat Country");
	    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("equals");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[4]"))).selectByVisibleText("中国");
	    driver.findElement(By.linkText("Preview")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.name("yt1")).click();
	    try {
	      AssertJUnit.assertEquals("seleniumchina", driver.findElement(By.xpath("//div[@id='dynamic-rules-grid']/div/table/tbody/tr/td[1]")).getText());
	    } catch (Error e) {
	    	logger.error("seleniumchina segmentation name显示错误，或者该segmentation创建失败！！");
	    	Assert.fail("seleniumchina segmentation name显示错误，或者该segmentation创建失败！！");
	     
	    }
	    try {
	      AssertJUnit.assertEquals("seleniumchina", driver.findElement(By.xpath("//div[@id='dynamic-rules-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	    	logger.error("seleniumchina segmentation note显示错误，或者该segmentation创建失败！！");
	    	Assert.fail("seleniumchina segmentation note显示错误，或者该segmentation创建失败！！");
	    }
	 }
}
