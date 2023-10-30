package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FollowersListTest {
	 WebDriver driver;
	public boolean flag=true;
	private static Logger logger = Logger.getLogger(FollowersListTest.class);

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
		 methods.navigation(driver, "Followers", By.className("follower_img"));
			
			try{
		    	String followerAmount=driver.findElement(By.xpath(".//*[@id='wechat-customer-grid']/div[3]")).getText();
		    	System.out.println("The amount of followers is "+followerAmount);
			}
			catch (Exception e){
				System.out.println("fail to get followers amount!");
				logger.error("fail to get followers amount!");
			}
			driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys("H.'");
			driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(Keys.ENTER);
			while(true){
				if(!methods.isElementPresent(driver,By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[2]/td[1]")))
					break;
			}
		

	}
	public boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
	

}
