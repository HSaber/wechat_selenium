package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DirectFollowConditionTrigger extends TestBase{
	private static Logger logger = Logger.getLogger(DirectFollowConditionTrigger.class);
	//@Test(groups="directFollowConditionTrigger")
	@Test(groups="directFollowConditionTrigger",dependsOnGroups="directFollowTriggerInteractive")
	  public void directFollowConditionTrigger() throws Exception {
		
		navigation("Triggers");
	    Thread.sleep(2000);
	    String category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category.equals("direct follow no condition")){
	    	System.out.println("第一行就是direct follow no condition，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("direct follow no condition");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}catch(Exception e){
		    		System.out.println("搜索中，抛出异常Exception e");
		    		 b=false;
		    	}
		    	
		    	if(category.equals("direct follow no condition")&&b)
		    		break;
		    }
	    }
	    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.id("Triggers_category")).clear();
	    driver.findElement(By.id("Triggers_category")).sendKeys("direct follow condition");
	    new Select(driver.findElement(By.id("segmentation"))).selectByVisibleText("测试组tag");
	    driver.findElement(By.name("yt0")).click();
	    Thread.sleep(2000);
	    String category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
	    if(category1.equals("direct follow condition")){
	    	System.out.println("第一行就是direct follow condition，不需要搜素查询");
	    }else{
	    	driver.findElement(By.name("Triggers[category]")).clear();
		    driver.findElement(By.name("Triggers[category]")).sendKeys("direct follow condition");
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    while(true){
		    	boolean b = true;
		    	try{
		    		 category1 = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText();
		    	}catch(org.openqa.selenium.StaleElementReferenceException e){
		    		 System.out.println("搜索中，捕捉异常org.openqa.selenium.StaleElementReferenceException");
		    		 b=false;
		    	}
		    	if(category1.equals("direct follow condition")&&b)
		    		break;
		    }
	    }
	    
	   
       
	    try {
	        AssertJUnit.assertEquals("direct follow condition", driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText());
	      } catch (Error e) {
	        
	    	  logger.error("direct follow condition更新category名称后table中category显示错误，更新失败！！！");
	        Assert.fail("direct follow condition更新category名称后table中category显示错误，更新失败！！！");
	      }
	    driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[11]/a")).click();
	    Thread.sleep(1000);
	    String condition = driver.findElement(By.cssSelector("#segmentation > option[selected=\"selected\"]")).getText();
	    try{
	    		AssertJUnit.assertEquals("测试组tag",condition);
	    }catch(Error e){
	    	logger.error("该triggerdirect follow condition更改condition未成功！！！");
	    	Assert.fail("该triggerdirect follow condition更改condition未成功！！！");
	    }
	    
	    
	   
	}
}
