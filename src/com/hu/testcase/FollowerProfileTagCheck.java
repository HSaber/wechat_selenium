package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class FollowerProfileTagCheck {
   WebDriver driver;
	public String FieldTitle;
	public String path;
	private static Logger logger = Logger.getLogger(FollowerProfileTagCheck.class);
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test
	public void test() throws InterruptedException,Exception {
		 String timeStr=methods.timeDate();
		 methods.navigation(driver, "Followers", By.className("follower_img"));
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys("H.'");
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(Keys.ENTER);
		while(true){
			if(!methods.isElementPresent(driver,By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[2]/td[1]")))
				break;
		}
		try{
			driver.findElement(By.className("follower_img")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("analytics")))
					break;
			}
			System.out.println("Open follower profile page successfully!");
			logger.error("Open follower profile page successfully!");
		}
		catch (Exception e){
			System.out.println("fail to open follower profile page!");
			logger.error("fail to open follower profile page!");
			Assert.fail("Not yet implemented");
		}
		//int tagLength=methods.TagNumArr.length/2;
		//CheckTag
		 String[] NewTagArr={"FormEvent_","Survey","Message","Html5_","SinglePost Menu",
				 "中学生上网情况问卷调查","客服Menu","PostUrl Menu","MultiPost Menu","NewTag_","DynamicMenu",
				 "AnyActConTri","AnyActOnceTri","AnyMenuConTri","AnyMenuOnceTri","SpecMenuOnceTri",
				 "SpeMenuConTri","AnyQrNewConTri","AnyQrExNewTri","AnyQrExMulConTri","SpecQrNewTri",
				 "SpecQrExNewTri","SpecQrExOnceTri","Image"};
		 
			int divOrder;
			if(methods.isElementPresent(driver, By.id("ecoTab")))
					divOrder=4;
			else 
				divOrder=4;
		if(methods.isElementPresent(driver,By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dd/div[1]")))
		{
			driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dd/div[1]/a")).click();
			//old tag check
			for (int m = methods.TagNumArr.length/2; m < methods.TagNumArr.length; m++) {      
	        if(!(methods.TagNumArr[m][0] == null || methods.TagNumArr[m][1].length() <= 0))
	        {
	          	System.out.println("H.' Old Tags "+methods.TagNumArr[m][0]+"="+methods.TagNumArr[m][1]); 
	        		if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(methods.TagNumArr[m][0]))
	        		{ 
	        			int num = Integer.parseInt(methods.TagNumArr[m][1])+1;
	        			methods.CheckTagAndTagNum(driver,methods.TagNumArr[m][0], "",Integer.toString(num));
	        		}
	        		else
	        		{
	        			System.out.println(methods.TagNumArr[m][0]+" 添加失败！");
	        			logger.error(methods.TagNumArr[m][0]+" 添加失败！");
	        		}
	        }
	           System.out.println("   ");   
			}
						 
			 for(int p=0;p<NewTagArr.length;p++){			 
				 if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(NewTagArr[p]+timeStr))
					{
						 methods.CheckTagAndTagNum(driver,NewTagArr[p], timeStr, "1");	 	
					}
					else
					{
						System.out.println("H.' New Tags "+NewTagArr[p]+timeStr+" tag 添加失败！");
						logger.error("H.' New Tags "+NewTagArr[p]+timeStr+" tag 添加失败！");
					}
				 System.out.println("   ");   
			 }
		}
		
		else{
			//old tag check
			for (int m = methods.TagNumArr.length/2; m < methods.TagNumArr.length; m++) {      
		        if(!(methods.TagNumArr[m][0] == null || methods.TagNumArr[m][1].length() <= 0))
		        {
		            	System.out.println("H.' Old Tags "+methods.TagNumArr[m][0]+"="+methods.TagNumArr[m][1]); 
		        		if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(methods.TagNumArr[m][0]))
		        		{ 
		        			int num = Integer.parseInt(methods.TagNumArr[m][1])+1;
		        			methods.CheckTagAndTagNum(driver,methods.TagNumArr[m][0], "",Integer.toString(num));
		        		}
		        		else
		        		{
		        			System.out.println(methods.TagNumArr[m][0]+" 添加失败！");
		        			logger.error(methods.TagNumArr[m][0]+" 添加失败！");
		        		}
		        }
		           System.out.println("   ");   
				}
							 
				 for(int p=0;p<NewTagArr.length;p++){			 
					 if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(NewTagArr[p]+timeStr))
						{
							 methods.CheckTagAndTagNum(driver,NewTagArr[p], timeStr, "1");	 	
						}
						else
						{
							System.out.println("H.' New Tags "+NewTagArr[p]+timeStr+" tag 添加失败！");
							logger.error("H.' New Tags "+NewTagArr[p]+timeStr+" tag 添加失败！");
						}
					 System.out.println("   ");   
				 }
		}
		
		//candy
		driver.navigate().back();
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys("๑Candy๑3'");
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(Keys.ENTER);
		while(true){
			if(!methods.isElementPresent(driver,By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[2]/td[1]")))
				break;
		}
		try {
			driver.findElement(By.className("follower_img")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("analytics")))
					break;
			}
			System.out.println("Open ๑Candy๑3 follower profile page successfully!");
		} catch (Exception e) {
			System.out.println("fail to ๑Candy๑3 open follower profile page!");
			logger.error("fail to ๑Candy๑3 open follower profile page!");
		}
		
		//CheckTag
		if(methods.isElementPresent(driver,By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dd/div[1]")))
		{
			driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dd/div[1]/a")).click();
			//old tag check
			for (int m = 0; m < methods.TagNumArr.length/2; m++) {      
	        if(!(methods.TagNumArr[m][0] == null || methods.TagNumArr[m][1].length() <= 0))
	        {
	            System.out.println("๑Candy๑3 Old Tags "+methods.TagNumArr[m][0]+"="+methods.TagNumArr[m][1]); 
	        		if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(methods.TagNumArr[m][0]))
	        		{ 
	        			int num = Integer.parseInt(methods.TagNumArr[m][1])+1;
	        			methods.CheckTagAndTagNum(driver,methods.TagNumArr[m][0], "",Integer.toString(num));
	        		}
	        		else
	        		{
	        			System.out.println("๑Candy๑3 Old Tags "+methods.TagNumArr[m][0]+" 添加失败！");
	        			logger.error("๑Candy๑3 Old Tags "+methods.TagNumArr[m][0]+" 添加失败！");
	        		}

	          }
	           System.out.println("   ");   
	        }
	            
					 
			 for(int p=0;p<NewTagArr.length;p++){			 
				 if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(NewTagArr[p]+timeStr))
					{
						 methods.CheckTagAndTagNum(driver,NewTagArr[p], timeStr, "1");	 	
					}
					else
					{
						System.out.println("๑Candy๑3 New Tags "+NewTagArr[p]+timeStr+" tag 添加失败！");
						logger.error("๑Candy๑3 New Tags "+NewTagArr[p]+timeStr+" tag 添加失败！");
					}
				 System.out.println("   ");   
			 }
		}
		
		else{
			//old tag check
			for (int m = 0; m < methods.TagNumArr.length/2; m++) {      
		        if(!(methods.TagNumArr[m][0] == null || methods.TagNumArr[m][1].length() <= 0))
		        {
		            System.out.println("๑Candy๑3 Old Tags "+methods.TagNumArr[m][0]+"="+methods.TagNumArr[m][1]); 
		        		if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(methods.TagNumArr[m][0]))
		        		{ 
		        			int num = Integer.parseInt(methods.TagNumArr[m][1])+1;
		        			methods.CheckTagAndTagNum(driver,methods.TagNumArr[m][0], "",Integer.toString(num));
		        		}
		        		else
		        		{
		        			System.out.println("๑Candy๑3 Old Tags "+methods.TagNumArr[m][0]+" 添加失败！");
		        			logger.error("๑Candy๑3 Old Tags "+methods.TagNumArr[m][0]+" 添加失败！");
		        		}

		          }
		           System.out.println("   ");   
		        }
		            
		
				 for(int p=0;p<NewTagArr.length;p++){			 
					 if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(NewTagArr[p]+timeStr))
						{
							 methods.CheckTagAndTagNum(driver,NewTagArr[p], timeStr, "1");	 	
						}
						else
						{
							System.out.println("๑Candy๑3 New Tags "+NewTagArr[p]+timeStr+" tag 添加失败！");
							logger.error("๑Candy๑3 New Tags "+NewTagArr[p]+timeStr+" tag 添加失败！");
						}
					 System.out.println("   ");   
				 }
		}
	}

}
