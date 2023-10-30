package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class CloseOldKeyword extends TestBase{
	private static Logger logger = Logger.getLogger(CloseOldKeyword.class);
	 @Test(groups={"closeOldKeyword"})
	  public void closeOldKeyword() throws Exception {
	
	    String[] title = {"seleniumtext","seleniumpost","seleniumvoice","seleniumvideo","seleniumimage"};
	    for(int j=0;j<title.length;j++){
	    	navigation("Keyword Auto-Reply");
		    Thread.sleep(2000);
		    driver.findElement(By.name("KeyWord[keyWord]")).clear();
		    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys(title[j]);
		    System.out.println(title[j]);
		   // driver.findElement(By.name("KeyWord[keyWord]")).sendKeys(Keys.ENTER);
		    new Select(driver.findElement(By.name("KeyWord[status]"))).selectByVisibleText("Active");
		    Thread.sleep(10000);
		    
		   
		    if(isElementPresent(By.cssSelector("div.summary"))){
		    	
		    	String str = driver.findElement(By.cssSelector("div.summary")).getText();
			    int num = catchNum("1-"," of",str);
			   
			    System.out.println("准备关闭"+num+"个跟"+ title[j] +"有关的keyword");
			   
			    try {
		    		  
		    		  AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText().contains(title[j]));
		    	      String status = driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src");
		   		      System.out.println(status);
		   		      AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png",status);
		   		      
		   			  driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).click();
		   			  Thread.sleep(15000);
		   			  System.out.println("关掉了一个keyword");
		    	 	
		    	    } catch (Error e) {
		    	    	logger.error("keyword搜索结果不对，或者搜索未完成");
		    	    	 Assert.fail("keyword搜索结果不对，或者搜索未完成");
		    	    	 
		    	    }
				    
			    for(int i=num;i>1;i--){
				    	driver.findElement(By.name("KeyWord[keyWord]")).clear();
					    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys(title[j]);
					    new Select(driver.findElement(By.name("KeyWord[status]"))).selectByVisibleText("Active");
					    Thread.sleep(8000);
				    	try {
				    		  
				    		  AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText().contains(title[j]));
				    	      String status = driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src");
				   		      System.out.println(status);
				   		      AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png",status);
				   		      
				   			  driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).click();
				   			  Thread.sleep(15000);
				   			  System.out.println("关掉了一个keyword");
				    	 	
				    	    } catch (Error e) {
				    	    	logger.error("keyword搜索结果不对，或者搜索未完成");
				    	    	 Assert.fail("keyword搜索结果不对，或者搜索未完成");
				    	    	 
				    	    }
				    	 
				    }
			    
			    
		    }else{
		    	 try {
		    	     System.out.println("没有找到和"+ title[j] +"相关的keyword"); 
		    		 AssertJUnit.assertEquals("No results found.", driver.findElement(By.cssSelector("span.empty")).getText());
		    	    } catch (Error e) {
		    	    	logger.error("没有找到和"+ title[j] +"相关的keyword,也没有显示No results found."); 
		    	    	Assert.fail("没有找到和"+ title[j] +"相关的keyword,也没有显示No results found."); 
		    	    }
		    }
		    
		    navigation("Keyword Auto-Reply");
		    Thread.sleep(2000);
		    driver.findElement(By.name("KeyWord[keyWord]")).clear();
		    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys(title[j]);
	   	    new Select(driver.findElement(By.name("KeyWord[status]"))).selectByVisibleText("Active");
	   	    Thread.sleep(3000);
	   	    
	   	    try{
	   	    	AssertJUnit.assertTrue(isElementPresent(By.cssSelector("span.empty")));
	   	    	try {
	  	   		  AssertJUnit.assertEquals("No results found.", driver.findElement(By.cssSelector("span.empty")).getText());
	  	   		  System.out.println("keyword关闭完成");
	  	   		} catch (Error e) {
	  	   		logger.error("keyword关闭完成了，但是显示的提示不是No results found.");
	  	   		  
	  	   		} 
	   	    }catch (Error e) {
		   		  
	   	    	logger.error("keyword没关完，关闭失败");
		   		  Assert.fail("keyword没关完，关闭失败");
	   	    }
		    
	    }
	 
	 }
	
}
