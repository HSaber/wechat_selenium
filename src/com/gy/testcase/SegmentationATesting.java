package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SegmentationATesting extends TestBase{
	private static Logger logger = Logger.getLogger(SegmentationATesting.class);
	 @Test
	  public void segmentationATesting() throws Exception {
	    
	    driver.findElement(By.linkText("Follower Management")).click();
	    driver.findElement(By.linkText("Segmentation")).click();
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("AB Testing");
	    driver.findElement(By.cssSelector("input.values.list")).clear();
	    //输入偶数id
	    driver.findElement(By.cssSelector("input.values.list")).sendKeys("0,2,4,6,8");
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("selenium_A_Group(50%)");
	    driver.findElement(By.id("dynamicRules_note")).clear();
	    driver.findElement(By.id("dynamicRules_note")).sendKeys("selenium_A_Group(50%)");
	    //加个action再删掉
	    driver.findElement(By.cssSelector("span.add_a_rule")).click();
	    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div[2]/div[3]/div/img")).click();
	    //点击preview，加校验
	    driver.findElement(By.linkText("Preview")).click();
	    Thread.sleep(3000);
	    try {
		      AssertJUnit.assertTrue(driver.findElement(By.cssSelector("div.summary")).getText().contains("Displaying"));
		    } catch (Error e) {
		     
		    	logger.error("segmentaion preview失败");
		     Assert.fail("segmentaion preview失败");
		    }
	    driver.findElement(By.name("yt1")).click();
	    try {
	      AssertJUnit.assertEquals("selenium_A_Group(50%)", driver.findElement(By.cssSelector("tr.odd > td")).getText());
	    } catch (Error e) {
	     
	    	logger.error("Segmentation title创建失败");
	      Assert.fail("Segmentation title创建失败");
	    }
	    try {
	      AssertJUnit.assertEquals("selenium_A_Group(50%)", driver.findElement(By.xpath("//div[@id='dynamic-rules-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	      
	    	logger.error("Segmentation note创建失败");
	      Assert.fail("Segmentation note创建失败");
	    }
	    driver.findElement(By.cssSelector("img[alt=\"Update\"]")).click();
	    driver.findElement(By.id("dynamicRules_note")).clear();
	    driver.findElement(By.id("dynamicRules_note")).sendKeys("selenium_A_Group(50%)(even)");
	    driver.findElement(By.name("yt1")).click();
	    try {
	      AssertJUnit.assertEquals("selenium_A_Group(50%)(even)", driver.findElement(By.xpath("//div[@id='dynamic-rules-grid']/div/table/tbody/tr/td[2]")).getText());
	    } catch (Error e) {
	      
	    	logger.error("Segmentation note更新失败");
	      Assert.fail("Segmentation note更新失败");
	    }
	    
	  }
}

