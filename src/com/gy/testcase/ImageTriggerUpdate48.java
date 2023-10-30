package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ImageTriggerUpdate48 extends TestBase{
	private static Logger logger = Logger.getLogger(ImageTriggerUpdate48.class);
	//@Test
	@Test(groups="imageTriggerUpdate48",dependsOnGroups="imageTriggerMIPInteractive")
	  public void imageTriggerUpdate48() throws Exception {
		
		navigation("Triggers");
	    Thread.sleep(2000);
	    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category.equals("no conditional image trigger")){
	    	System.out.println("第一行就是no conditional image trigger，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("no conditional image trigger");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category.equals("no conditional image trigger")&&b)
		    		break;
		    }
	    }
	    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("Triggers_category")).clear();
	    driver.findElement(By.id("Triggers_category")).sendKeys("48 hour image trigger");
	    new Select(driver.findElement(By.id("segmentation"))).selectByVisibleText("selenium48hour");
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    String category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category1.equals("48 hour image trigger")){
	    	System.out.println("第一行就是48 hour image trigger，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("48 hour image trigger");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		  
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category1.equals("48 hour image trigger")&&b)
		    		break;
		    }
	    }
	    
	  
        
	    try {
	        AssertJUnit.assertEquals("48 hour image trigger", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
	      } catch (Error e) {
	       
	    	  logger.error("image trigger更新category名称后table中category显示错误，更新失败！！！");
	        Assert.fail("image trigger更新category名称后table中category显示错误，更新失败！！！");
	      }
	    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
	    Thread.sleep(1000);
	    String condition = driver.findElement(By.cssSelector("#segmentation > option[selected=\"selected\"]")).getText();
	    try{
	    		AssertJUnit.assertEquals("selenium48hour",condition);
	    }catch(Error e){
	    	logger.error("该image trigger更改condition未成功！！！");
	    	Assert.fail("该image trigger更改condition未成功！！！");
	    }
	    
	   
	}
}
