package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class CloseOldTrigger extends TestBase{
	private static Logger logger = Logger.getLogger(CloseOldTrigger.class);
	 @Test(groups="closeOldTrigger")
	  public void closeOldTrigger() throws Exception {
	  
	    String[] title = {"MessageTriggerPMI","selbstrigger","sele direct follow","selenium any follow","ImageTriggerMIP"};
	    for(int j=0;j<title.length;j++){
	    	navigation("Triggers");
		    Thread.sleep(3000);
		    driver.findElement(By.name("Triggers[title]")).clear();
		    driver.findElement(By.name("Triggers[title]")).sendKeys(title[j]);
		    System.out.println(title[j]);
		    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
		    
		    Thread.sleep(8000);
		    
		    if(isElementPresent(By.cssSelector("div.summary"))){
			    String str = driver.findElement(By.cssSelector("div.summary")).getText();
			    int num = 0;
			    if(str.contains("results")){
			    	num = catchNum("of "," results",str);
			    }else{
			    	num = catchNum("of "," result",str);
			    }
			   
			    System.out.println("准备关闭"+num+"个跟" +title[j]+ "有关的trigger");
			    
			    try {
		    	      AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText().contains(title[j]));
		    	      String status = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[10]/img")).getAttribute("src");
		   		      System.out.println(status);
		   		      AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png",status);
		   		     
		   			   driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[10]/img")).click();
		   			   Thread.sleep(8000);
		   			   System.out.println("关掉了一个trigger");
		   		     
		    	     } catch (Error e) {
		    	     
		    	    	 logger.error("trigger搜索结果不对，或者搜索未完成");
			    	      Assert.fail("trigger搜索结果不对，或者搜索未完成");
		    	    }
			    
			    for(int i=num;i>1;i--){
			    	 driver.findElement(By.name("Triggers[title]")).clear();
					 driver.findElement(By.name("Triggers[title]")).sendKeys(title[j]);
					 new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
					 Thread.sleep(8000);
					 
			    	 try {
			    	      AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText().contains(title[j]));
			    	      String status = driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[10]/img")).getAttribute("src");
			   		      System.out.println(status);
			   		      AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png",status);
			   		     
			   			   driver.findElement(By.xpath("//div[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[10]/img")).click();
			   			   Thread.sleep(8000);
			   			   System.out.println("关掉了一个trigger");
			   		     
			    	     } catch (Error e) {
			    	     
			    	    	 logger.error("trigger搜索结果不对，或者搜索未完成");
				    	      Assert.fail("trigger搜索结果不对，或者搜索未完成");
			    	    }
			    }
		    
		    }else{
		    	 try {
		    	     System.out.println("没有找到和selenium相关的trigger"); 
		    		 AssertJUnit.assertEquals("No results found.", driver.findElement(By.cssSelector("span.empty")).getText());
		    	    } catch (Error e) {
		    	    	logger.error("没有找到和selenium相关的trigger,也没有显示No results found."); 
		    	    	Assert.fail("没有找到和selenium相关的trigger,也没有显示No results found."); 
		    	    }
		    }
		    
		    navigation("Triggers");
		    Thread.sleep(2000);
		    driver.findElement(By.name("Triggers[title]")).clear();
		    driver.findElement(By.name("Triggers[title]")).sendKeys(title[j]);
	   	    new Select(driver.findElement(By.name("Triggers[status]"))).selectByVisibleText("Active");
	   	    Thread.sleep(3000);
	   	    
	   	    try{
	   	    	AssertJUnit.assertTrue(isElementPresent(By.cssSelector("span.empty")));
	   	    	try {
	  	   		  AssertJUnit.assertEquals("No results found.", driver.findElement(By.cssSelector("span.empty")).getText());
	  	   		  System.out.println("trigger关闭完成");
	  	   		} catch (Error e) {
	  	   		logger.error("trigger关闭完成,但是显示的提示不是No results found.");
	  	   		  
	  	   		} 
	   	    }catch (Error e) {
		   		 
	   	    	logger.error("trigger没关完，关闭失败");
		   		Assert.fail("trigger没关完，关闭失败");
	   	    }
		    
	    }
	 }
}
