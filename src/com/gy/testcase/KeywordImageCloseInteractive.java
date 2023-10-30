package com.gy.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.testng.Assert.*;

import org.apache.log4j.Logger;
import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class KeywordImageCloseInteractive extends TestBase{
	private static Logger logger = Logger.getLogger(KeywordImageCloseInteractive.class);
	 @Test(dependsOnGroups="keywordImageInteractive")
	  public void keywordImageCloseInteractive() throws Exception {
		 
		 navigation("Keyword Auto-Reply");
		 Thread.sleep(2000);
		 driver.findElement(By.name("KeyWord[keyWord]")).clear();
		 driver.findElement(By.name("KeyWord[keyWord]")).sendKeys("seleniumimage");
		 //driver.findElement(By.name("KeyWord[keyWord]")).sendKeys(Keys.ENTER);
		 new Select(driver.findElement(By.name("KeyWord[status]"))).selectByVisibleText("Active");
		 Thread.sleep(10000);
		 
		 if(isElementPresent(By.cssSelector("div.summary"))){
		    	
		    	String str = driver.findElement(By.cssSelector("div.summary")).getText();
			    int num = catchNum("1-"," of",str);
			   
			    System.out.println("准备关闭"+num+"个跟seleniumimage有关的keyword");
			    
			    try {
		    		  
		    		  AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText().contains("seleniumimage"));
		    	      String status = driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src");
		   		      System.out.println(status);
		   		      AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png",status);
		   		      
		   			  driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).click();
		   			  Thread.sleep(5000);
		   			  System.out.println("关掉了一个keyword");
		    	 	
		    	    } catch (Error e) {
		    	    	logger.error("keyword搜索结果不对，或者搜索未完成");
		    	    	 Assert.fail("keyword搜索结果不对，或者搜索未完成");
		    	    	 
		    	    }
			    
			    for(int i=num;i>1;i--){
			    	driver.findElement(By.name("KeyWord[keyWord]")).clear();
				    driver.findElement(By.name("KeyWord[keyWord]")).sendKeys("seleniumimage");
				    new Select(driver.findElement(By.name("KeyWord[status]"))).selectByVisibleText("Active");
				    Thread.sleep(5000);	 
			    	try {
				    		 
			    		  AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText().contains("seleniumimage"));
			    	      String status = driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).getAttribute("src");
			   		      System.out.println(status);
			   		      AssertJUnit.assertEquals(baseUrl+"images/switch-button_on.png",status);
			   		      
			   			  driver.findElement(By.xpath("//div[@id='key-word-grid']/div[1]/table/tbody/tr[1]/td[8]/img")).click();
			   			  Thread.sleep(5000);
			   			  System.out.println("关掉了一个keyword");
			    	
			    	} catch (Error e) {
			    		logger.error("keyword搜索结果不对，或者搜索未完成");
		    	    	 Assert.fail("keyword搜索结果不对，或者搜索未完成");
				    	 
				    }
			    
			    }   
		    
		 }else{
		    	 try {
		    	     System.out.println("没有找到和seleniumimage相关的keyword"); 
		    		 AssertJUnit.assertEquals("No results found.", driver.findElement(By.cssSelector("span.empty")).getText());
		    	    } catch (Error e) {
		    	    	logger.error("没有找到和seleniumimage相关的keyword,也没有显示No results found."); 
		    	    	Assert.fail("没有找到和seleniumimage相关的keyword,也没有显示No results found."); 
		    	    }
		    }
		 
		//接口部分
			//String[] info= {"appid","secret","mid","WechatID","openid"};
			String[] attr=info();
			String[] arr=getSignature(attr[2],attr[0],attr[1]);
			String[] msg={"seleniumimage"};
			for(int j=0;j<msg.length;j++){
				String keyword= "<xml><ToUserName><![CDATA["+attr[3]+"]]></ToUserName>"
								+ "<FromUserName><![CDATA["+attr[4]+"]]></FromUserName>"
								+"<CreateTime>"+arr[0]+"</CreateTime>"
								+ "<MsgType><![CDATA[text]]></MsgType>"
								+ "<Content><![CDATA["+msg[j]+"]]></Content>"
								+"<MsgId>6278109033050373709</MsgId></xml>";
				
				//String requesturl = attr[7]+attr[2]+"/signature/"+arr[2]+"/timestamp/"+arr[0]+"/nonce/"+arr[1];
				String requesturl=requestURL();
				String[] keyreply=  Post(requesturl,keyword);
				System.out.println(keyreply[0]);
				
				try{
					assertNull(keyreply[0]);	
					   				
			    }catch(Error e){
					
			    	logger.error("keyword已关闭，不能有返回信息！！！");
					Assert.fail("keyword已关闭，不能有返回信息！！！");
			    }
			}
	 }
}
