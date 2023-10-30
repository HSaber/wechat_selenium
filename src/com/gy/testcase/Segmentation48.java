package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class Segmentation48 extends TestBase{
	private static Logger logger = Logger.getLogger(Segmentation48.class);
	 @Test(groups="segmentation48")
	  public void segmentation48() throws Exception {
		
		 navigation("Segmentation");
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    driver.findElement(By.name("yt1")).click();
	    Thread.sleep(5000);
	   try{
		   AssertJUnit.assertEquals("error!", closeAlertAndGetItsText());
	    }catch(Error e){
	    	 
	    	 logger.error("segmentation48创建必选校验失败");
		      Assert.fail("segmentation48创建必选校验失败");
	    }
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("selenium48hour");
	    driver.findElement(By.name("yt1")).click();
	    driver.findElement(By.xpath(".//*[@id='dynamic-rules-grid']/div[1]/table/tbody/tr[1]/td[5]/a[1]")).click();
	    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("48 Hours");
	    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("Have Value");
	    //driver.findElement(By.linkText("Preview")).click();
	    driver.findElement(By.name("yt1")).click();
	    try {
	      AssertJUnit.assertEquals("selenium48hour", driver.findElement(By.xpath("//div[@id='dynamic-rules-grid']/div[1]/table/tbody/tr[1]/td[1]")).getText());
	    } catch (Error e) {
	     
	    	 logger.error("segmentation48创建后，table中name显示错误");
	     Assert.fail("segmentation48创建后，table中name显示错误");
	    }
		
	    //segmentation中的人数与48 hour list的人数作比较
	    navigation("The 48 Hour List");
	    Thread.sleep(2000);
	    String display = driver.findElement(By.cssSelector("div.summary")).getText();
	    System.out.println("48 hour list显示的人数是："+ display);
	    int num1 = catchNum("of "," result",display);
	    System.out.println("48 hour list显示的人数是：" + num1);
	    
	    navigation("Segmentation");
	    driver.findElement(By.name("DynamicRules[name]")).sendKeys("selenium48hour");
	    driver.findElement(By.name("DynamicRules[note]")).sendKeys(Keys.ENTER);
	    Thread.sleep(3000);
	    driver.findElement(By.xpath(".//*[@id='dynamic-rules-grid']/div[1]/table/tbody/tr[1]/td[5]/a[1]")).click();
	    Thread.sleep(3000);
	   /* boolean status = isElementPresent(By.cssSelector("div.summary"));
		while(!status){
				status = isElementPresent(By.cssSelector("div.summary"));
				System.out.println("preview没加载完成,继续等待中！");
			}*/
		String display2=driver.findElement(By.cssSelector("div.summary")).getText();
		while(true){
			display2 = driver.findElement(By.cssSelector("div.summary")).getText();
			System.out.println("开始"+display2+"结束");
			if(display2.length()>0)
				break;
		}
		
		int num2 = catchNum("of "," result",display2);
		System.out.println("48 hour segmentation显示的人数是：" + num2);
	    try {
		      AssertJUnit.assertEquals(num1, num2);
		    } catch (Error e) {
		      //verificationErrors.append(e.toString());
		    	 logger.error("segmentation中的人数与48 hour list的人数不同");
		      Assert.fail("segmentation中的人数与48 hour list的人数不同");
		    }
	  }

}
