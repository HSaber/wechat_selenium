package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class UpdatePMINoCondition extends TestBase{
	private static Logger logger = Logger.getLogger(UpdatePMINoCondition.class);
	//@Test
	@Test(groups="updatePMINoCondition",dependsOnGroups="chinaPMITriggerInteractive")
	  public void updatePMINoCondition() throws Exception {
		
		navigation("Triggers");
	    Thread.sleep(2000);
	    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category.equals("seleniumchina&multi2")){
	    	System.out.println("第一行就是seleniumchina&multi2，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("seleniumchina&multi2");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category.equals("seleniumchina&multi2")&&b)
		    		break;
		    }
	    }
	    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("Triggers_category")).clear();
	    driver.findElement(By.id("Triggers_category")).sendKeys("seleniumNoCondition&multi2");
	    new Select(driver.findElement(By.id("segmentation"))).selectByVisibleText("Please select...");
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    String category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category1.equals("seleniumNoCondition&multi2")){
	    	System.out.println("第一行就是seleniumNoCondition&multi2，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("seleniumNoCondition&multi2");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category1.equals("seleniumNoCondition&multi2")&&b)
		    		break;
		    }
	    }
	    
	   
        
        
	    try {
	        AssertJUnit.assertEquals("seleniumNoCondition&multi2", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("triggerPMI更新category名称后table中category显示错误，更新失败！！！");
	        Assert.fail("triggerPMI更新category名称后table中category显示错误，更新失败！！！");
	      }
	    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
	    Thread.sleep(1000);
	    if(isElementPresent(By.cssSelector("#segmentation > option[selected=\"selected\"]"))){
	    	logger.error("该triggerPMI取消condition未成功！！！");
	    	Assert.fail("该triggerPMI取消condition未成功！！！");
	    }
	    
	  
	}
}
