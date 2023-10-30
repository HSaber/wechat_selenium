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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FollowerProfileCleanTags {
	 WebDriver driver;
	public String FieldTitle;
	public String path;
	private static Logger logger = Logger.getLogger(FollowerProfileCleanTags.class);
	
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
		
		//添加一个tag就要更改一下数组(methods.TagNumArr)的长度 
		 String timeStr=methods.timeDate();
		 methods.navigation(driver, "Followers", By.className("follower_img"));
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(methods.nicknameArr[1]);
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
			System.out.println("Open Candy follower profile page successfully!");
		}
		catch (Exception e){
			System.out.println("fail to open Candy follower profile page!");
			logger.error("fail to open Candy follower profile page!");
		}
		int divOrder;
		if(methods.isElementPresent(driver, By.id("ecoTab")))
				divOrder=4;
		else 
			divOrder=4;
		//CheckTag
		driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dt/a")).click();
		Thread.sleep(3000);
		//获取ul下li的个数
		int j=1;
		Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"tag_handler\").getElementsByTagName(\"li\").length;")-1;	
		System.out.println(str);
			for(int i=1;i<=str;i++){
				String tag=driver.findElement(By.xpath(".//*[@id='tag_handler']/li["+i+"]")).getText();
				
				if(tag.isEmpty()){
					break;
				}else if(methods.hasDigit(tag)){
					System.out.println(tag);
					driver.findElement(By.xpath(".//*[@id='tag_handler']/li["+i+"]")).click();
					i=i-1;
					str=str-1;

					System.out.println("删掉了"+j+" 删的是"+tag+" 还剩"+str+"个tags");
					j=j+1;
				}
			}
			driver.findElement(By.className("tagBg")).click();
			driver.findElement(By.cssSelector(".savehandle.btn")).click();
			while(!methods.isElementPresent(driver,By.cssSelector("#tag_handler")))
			{
				break;
			}
		driver.navigate().back();
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(methods.nicknameArr[0]);
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
			System.out.println("Open H.' follower profile page successfully!");
		} catch (Exception e) {
			System.out.println("fail to H.' open follower profile page!");
			logger.error("fail to H.' open follower profile page!");
		}
		

		//Delete Tag
		driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dt/a")).click();
		Thread.sleep(3000);
		//获取ul下li的个数
		str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"tag_handler\").getElementsByTagName(\"li\").length;")-1;	
		for(int i=1;i<=str;i++){
			String tag=driver.findElement(By.xpath(".//*[@id='tag_handler']/li["+i+"]")).getText();
			if(tag.isEmpty()){
				break;
			}else if(methods.hasDigit(tag)){
				driver.findElement(By.xpath(".//*[@id='tag_handler']/li["+i+"]")).click();
				i=i-1;
				str=str-1;
			}
		}
		driver.findElement(By.className("tagBg")).click();
		driver.findElement(By.cssSelector(".savehandle.btn")).click();
		while(!methods.isElementPresent(driver,By.cssSelector("#tag_handler")))
		{
			break;
		}
		for (int m = 0; m < methods.TagNumArr.length; m++) {      
            for (int n = 0; n < methods.TagNumArr[m].length; n++) {   
            //循环遍历数组中的每个元素  
            	if(!(methods.TagNumArr[m][n] == null || methods.TagNumArr[m][n].length() <= 0))
            	{
            	if(m<(methods.TagNumArr.length/2))
            	{
            		if(n==0)
                  System.out.print("Candy Old Tags "+methods.TagNumArr[m][n]); 
            		else
            	  System.out.print("="+methods.TagNumArr[m][n]); 
            	}
            	else
            	{
            		if(n==0)
                  System.out.print("H.' Old Tags "+methods.TagNumArr[m][n]); 
                  	else
                  System.out.print("="+methods.TagNumArr[m][n]); 
            	}
            	}

                //将数组中的元素输出  
            }  
            System.out.println("   ");   
	}
	}

}
