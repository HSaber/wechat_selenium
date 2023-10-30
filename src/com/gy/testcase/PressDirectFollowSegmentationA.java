package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PressDirectFollowSegmentationA extends TestBase{
	@Test
	  public void pressDirectFollowSegmentationA() throws Exception {
	    
	    driver.findElement(By.linkText("Follower Management")).click();
	    driver.findElement(By.linkText("Segmentation")).click();
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("AB Testing");
	    driver.findElement(By.cssSelector("input.values.list")).clear();
	    
	    driver.findElement(By.cssSelector("input.values.list")).sendKeys("1,2,3,4");
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("direct follow 压力测试A组");
	    driver.findElement(By.id("dynamicRules_note")).clear();
	    driver.findElement(By.id("dynamicRules_note")).sendKeys("direct follow 压力测试A组");
	   
	    driver.findElement(By.name("yt1")).click();
	    try {
	      AssertJUnit.assertEquals("direct follow 压力测试A组", driver.findElement(By.cssSelector("tr.odd > td")).getText());
	    } catch (Error e) {
	     
	      System.out.println("Segmentation title创建失败");
	      Assert.fail("Segmentation title创建失败");
	    }
	    
	    
	  }
}
