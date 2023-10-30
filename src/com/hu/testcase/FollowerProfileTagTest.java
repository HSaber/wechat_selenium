package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

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

public class FollowerProfileTagTest {
	 WebDriver driver;
	public String FieldTitle;
	public String path;
	private static Logger logger = Logger.getLogger(FollowerProfileTagTest.class);
	
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}
	@Test(dependsOnGroups="tag")
	public void test() throws InterruptedException,Exception {	
		 String timeStr=methods.timeDate();
		 String tagT="Tag_"+timeStr;
		 methods.navigation(driver, "Followers", By.className("follower_img"));
//查看一下是不是导航那边问题  导致下面的元素查不到     考虑一下关注用户没有tag的情况
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys("H.'");
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(Keys.ENTER);
		while(true){
			Thread.sleep(500);
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
		//add tag
		driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dt/a")).click();
		while(true){
			if(methods.isElementPresent(driver,By.id("tag_handler")))
				break;
		}
		driver.findElement(By.id("tag_handler")).click();	
		Thread.sleep(1000);
		driver.findElement(By.linkText(tagT)).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".savehandle.btn")).click();
		Thread.sleep(5000);//待解决
		
		if(methods.isElementPresent(driver,By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dd/div[1]")))
		{
			driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dd/div[1]/a")).click();
			boolean addtag=driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(tagT);
			AssertJUnit.assertTrue(addtag);
			System.out.println("add "+tagT+" successfully!");
		}
		else
		{
		boolean addtag=driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]")).getText().contains(tagT);
		AssertJUnit.assertTrue(addtag);
		System.out.println("add "+tagT+" successfully!");
		logger.error("add "+tagT+" successfully!");
		}
			
	}

}
