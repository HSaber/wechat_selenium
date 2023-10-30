package com.gy.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class SegmentationAnyFollowTag extends TestBase{
	 @Test(groups="segmentationAnyFollowTag")
	  public void segmentationAnyFollowTag() throws Exception {
	    
	    
	    String name[] = {"rainbow"};
	    for(int i=0;i<name.length;i++)
	    {
		driver.findElement(By.linkText("Follower Management")).click();
	    driver.findElement(By.linkText("Followers")).click();
	    driver.findElement(By.name("WechatCustomer[nickname]")).clear();
	    driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(name[i]);
	    driver.findElement(By.name("WechatCustomer[city]")).sendKeys(Keys.ENTER);
	    Thread.sleep(4000);
	    driver.findElement(By.cssSelector("img.follower_img")).click();
	   
	    driver.findElement(By.linkText("add")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@type='text']")).click();
	    driver.findElement(By.xpath("//input[@type='text']")).clear();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("segmentationAnyFollowTag");
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys(Keys.ENTER);
	    driver.findElement(By.id("addEav")).click();
	    driver.findElement(By.cssSelector("button.savehandle.btn")).click();
	    }
	   
	    driver.findElement(By.linkText("Follower Management")).click();
	    driver.findElement(By.linkText("Segmentation")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
	    new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("Profile");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/select[2]"))).selectByVisibleText("Tag");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/select[3]"))).selectByVisibleText("Name");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/select[4]"))).selectByVisibleText("segmentationAnyFollowTag");
	    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("greater than");
	    driver.findElement(By.cssSelector("input.values.amount")).clear();
	    driver.findElement(By.cssSelector("input.values.amount")).sendKeys("0");
	    driver.findElement(By.linkText("Preview")).click();
	    Thread.sleep(5000);
	    try {
	      AssertJUnit.assertEquals("rainbow", driver.findElement(By.linkText("rainbow")).getText());
	    } catch (Error e) {
	      Assert.fail("rainbow没有打上测试组tag，请检查！！");
	    }
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("segmentationAnyFollowTag");
	    driver.findElement(By.name("yt1")).click();
	    try {
	      AssertJUnit.assertEquals("segmentationAnyFollowTag", driver.findElement(By.cssSelector("tr.odd > td")).getText());
	    } catch (Error e) {
	      System.out.println("segmentationAnyFollowTag可能没创建成功");
	     Assert.fail("segmentationAnyFollowTag可能没创建成功");
	    }
	  }
}
