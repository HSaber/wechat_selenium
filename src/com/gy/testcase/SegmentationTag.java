package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SegmentationTag extends TestBase{
	private static Logger logger = Logger.getLogger(SegmentationTag.class);
	 @Test(groups="segmentationTag")
	  public void segmentationTag() throws Exception {
	    
	    
	    String name[] = {"rainbow","rainbowgy"};
	    for(int i=0;i<name.length;i++)
	    {
	    	navigation("Followers");
	    driver.findElement(By.name("WechatCustomer[nickname]")).clear();
	    driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(name[i]);
	    driver.findElement(By.name("WechatCustomer[action_count]")).sendKeys(Keys.ENTER);
	    Thread.sleep(4000);
	    driver.findElement(By.linkText(name[i])).click();
	    //driver.findElement(By.cssSelector("img.follower_img")).click();
	   
	    driver.findElement(By.linkText("add")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@type='text']")).click();
	    driver.findElement(By.xpath("//input[@type='text']")).clear();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("测试组tag3");
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.ENTER);
	    driver.findElement(By.id("addEav")).click();
	    driver.findElement(By.cssSelector("button.savehandle.btn")).click();
	    }
	   
	    navigation("Segmentation");
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
	    new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("Profile");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[2]"))).selectByVisibleText("Tag");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[3]"))).selectByVisibleText("Name");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[4]"))).selectByVisibleText("测试组tag3");
	    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("greater than");
	    driver.findElement(By.cssSelector("input.values.amount")).clear();
	    driver.findElement(By.cssSelector("input.values.amount")).sendKeys("0");
	    driver.findElement(By.linkText("Preview")).click();
	    Thread.sleep(5000);
	    try {
	      AssertJUnit.assertEquals("rainbow", driver.findElement(By.linkText("rainbow")).getText());
	    } catch (Error e) {
	    	logger.error("rainbow没有打上测试组tag，请检查！！");
	      Assert.fail("rainbow没有打上测试组tag，请检查！！");
	    }
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("测试组tag");
	    driver.findElement(By.name("yt1")).click();
	    try {
	      AssertJUnit.assertEquals("测试组tag", driver.findElement(By.cssSelector("tr.odd > td")).getText());
	    } catch (Error e) {
	    	logger.error("测试组tag segmentation没创建成功，请检查！");
	      Assert.fail("测试组tag segmentation没创建成功，请检查！");
	    }
	  }
}
