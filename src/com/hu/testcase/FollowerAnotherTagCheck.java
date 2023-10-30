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

public class FollowerAnotherTagCheck {
	WebDriver driver;
	public String FieldTitle;
	public String path;
	private static Logger logger = Logger.getLogger(FollowerAnotherTagCheck.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="MenuClick")
	public void test() throws InterruptedException,Exception {
				
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
			System.out.println("Open follower profile page successfully!");
		}
		catch (Exception e){
			System.out.println("fail to open follower profile page!");
			logger.error("fail to open follower profile page!");
		}
		int divOrder;
		if(methods.isElementPresent(driver, By.id("ecoTab")))
				divOrder=4;
		else 
			divOrder=4;
		
		//CheckTag
		if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("View"))
		{
			driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dd/div[1]/a")).click();
		
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("DynamicMenu"+timeStr))
			{
		        methods.CheckTagAndTagNum(driver,"DynamicMenu", timeStr, "1");
			}
			else
			{
				System.out.println("DynamicMenu"+timeStr+" tag 添加失败!");
				logger.error("DynamicMenu"+timeStr+" tag 添加失败！");
			}
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("Html5_"+timeStr))
			{
		        methods.CheckTagAndTagNum(driver,"Html5_", timeStr, "2");
			}
			else
			{
				System.out.println("Html5_"+timeStr+" tag 添加失败！");
				logger.error("Html5_"+timeStr+" tag 添加失败！");
			}
			
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("SinglePost Menu"+timeStr))
			{
		        methods.CheckTagAndTagNum(driver,"SinglePost Menu", timeStr, "2");
			}
			else
			{
				System.out.println("SinglePost Menu"+timeStr+" tag 添加失败！");
				logger.error("SinglePost Menu"+timeStr+" tag 添加失败！");
			}
			//中学生上网情况问卷调查
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("中学生上网情况问卷调查"+timeStr))
			{
		        methods.CheckTagAndTagNum(driver,"中学生上网情况问卷调查", timeStr, "1");
			}
			else
			{
				System.out.println("中学生上网情况问卷调查"+timeStr+" tag 添加失败！");
				logger.error("中学生上网情况问卷调查"+timeStr+" tag 添加失败！");
			}
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("AnyActConTri"+timeStr))
			{
		        methods.CheckTagAndTagNum(driver,"AnyActConTri", timeStr, "1");
			}
			else
			{
				System.out.println("AnyActConTri"+timeStr+" tag 添加失败！");
				logger.error("AnyActConTri"+timeStr+" tag 添加失败！");
			}
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("MultiPost Menu"))
			{
		        methods.CheckTagAndTagNum(driver,"MultiPost Menu", "", "1");
			}
			else
			{
				System.out.println("MultiPost Menu tag 添加失败！");
				logger.error("MultiPost Menu tag 添加失败！");
			}

			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("URL LINK Menu"))
			{
		        methods.CheckTagAndTagNum(driver,"URL LINK Menu", "", "9");
			}
			else
			{
				System.out.println("URL LINK Menu 添加失败！");
				logger.error("URL LINK Menu 添加失败！");
			}
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("Dynamic Menu"))
			{
		        methods.CheckTagAndTagNum(driver,"Dynamic Menu", "", "2");
			}
			else
			{
				System.out.println("Dynamic Menu tag 添加失败！");
				logger.error("Dynamic Menu tag 添加失败！");
			}
			

		}
		
		else{
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("FormEvent_"+timeStr))
			{
				 methods.CheckTagAndTagNum(driver,"FormEvent_", timeStr, "2");	 	
			}
			else
			{
				System.out.println("FormEvent_"+timeStr+" tag 添加失败！");
				logger.error("FormEvent_"+timeStr+" tag 添加失败！");
			}
			
			//"Survey"+title
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("Survey"+timeStr))
			{		 
				 methods.CheckTagAndTagNum(driver,"Survey", timeStr, "1");	 
			}
			else
			{
				System.out.println("Survey"+timeStr+" tag 添加失败！");
				logger.error("Survey"+timeStr+" tag 添加失败！");
			}
			
		
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("客服"+timeStr))
			{ 
				 methods.CheckTagAndTagNum(driver,"客服", timeStr, "1");
			}
			else
			{
				System.out.println("客服"+timeStr+" tag 添加失败！");
				logger.error("客服"+timeStr+" tag 添加失败！");
			}
			
	
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("Message"+timeStr))
			{
		        methods.CheckTagAndTagNum(driver,"Message", timeStr, "1");
			}
			else
			{
				System.out.println("Message"+timeStr+" tag 添加失败！");
				logger.error("Message"+timeStr+" tag 添加失败！");
			}
			
			
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("DynamicMenu"+timeStr))
			{
		        methods.CheckTagAndTagNum(driver,"DynamicMenu", timeStr, "1");
			}
			else
			{
				System.out.println("DynamicMenu"+timeStr+" tag 添加失败！");
				logger.error("DynamicMenu"+timeStr+" tag 添加失败！");
			}
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("Html5_"+timeStr))
			{
		        methods.CheckTagAndTagNum(driver,"Html5_", timeStr, "2");
			}
			else
			{
				System.out.println("Html5_"+timeStr+" tag 添加失败！");
				logger.error("Html5_"+timeStr+" tag 添加失败！");
			}
			
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("SinglePost Menu"+timeStr))
			{
		        methods.CheckTagAndTagNum(driver,"SinglePost Menu", timeStr, "2");
			}
			else
			{
				System.out.println("SinglePost Menu"+timeStr+" tag 添加失败！");
				logger.error("SinglePost Menu"+timeStr+" tag 添加失败！");
			}
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("MultiPost Menu"))
			{
		        methods.CheckTagAndTagNum(driver,"MultiPost Menu", "", "1");
			}
			else
			{
				System.out.println("MultiPost Menu tag 添加失败！");
				logger.error("MultiPost Menu tag 添加失败！");
			}

			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("URL LINK Menu"))
			{
		        methods.CheckTagAndTagNum(driver,"URL LINK Menu", "", "1");
			}
			else
			{
				System.out.println("URL LINK Menu 添加失败！");
				logger.error("URL LINK Menu 添加失败！");
			}
			
			if(driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains("Dynamic Menu"))
			{
		        methods.CheckTagAndTagNum(driver,"Dynamic Menu", "", "2");
			}
			else
			{
				System.out.println("Dynamic Menu tag 添加失败！");
				logger.error("Dynamic Menu tag 添加失败！");
			}
		}

	
	}

}
