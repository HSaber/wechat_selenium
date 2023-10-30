package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class UpdateLBSTriggerToNoOnce extends TestBase{
	private static Logger logger = Logger.getLogger(UpdateLBSTriggerToNoOnce.class);
	//@Test
	@Test(groups="updateLBSTriggerToNoOnce",dependsOnGroups="lBSTriggerOnceInteractive")
	  public void updateLBSTriggerToNoOnce() throws Exception {
		
		navigation("Triggers");
	    Thread.sleep(2000);
	    
	    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category.equals("lbstriggerOnce")){
	    	System.out.println("第一行就是lbstriggerOnce，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("lbstriggerOnce");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category.equals("lbstriggerOnce")&&b)
		    		break;
		    }
	    }
	    
	   
	    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("Triggers_category")).clear();
	    driver.findElement(By.id("Triggers_category")).sendKeys("lbstriggerOnceToNoonce");
	    new Select(driver.findElement(By.id("Triggers_is_once"))).selectByVisibleText("No");
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    
	   String category2 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category2.equals("lbstriggerOnceToNoonce")){
	    	System.out.println("第一行就是lbstriggerOnceToNoonce，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("lbstriggerOnceToNoonce");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category2 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category2.equals("lbstriggerOnceToNoonce")&&b)
		    		break;
		    }
	    }
	    
	    try {
	        AssertJUnit.assertEquals("lbstriggerOnceToNoonce", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("trigger搜索查询没加载好！！或者category没更新成功，请检查！！！！");
	        Assert.fail("trigger搜索查询没加载好！！或者category没更新成功，请检查！！！！");
	      }
	   
	    
	    try {
	        AssertJUnit.assertEquals("No", driver.findElement(By.xpath("//div[@id='triggers-grid']/div/table/tbody/tr/td[9]")).getText());
	        System.out.println("LBS trigger成功从once更改成no once!");
	      } catch (Error e) {
	    	  logger.error("LBS trigger从once更改成no once失败! 或者搜索未完成，请检查！！！");
	        Assert.fail("LBS trigger从once更改成no once失败! 或者搜索未完成，请检查！！！");
	        
	      }
	    
	}
}
