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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FollowerProfileCustomTest {
	 WebDriver driver;
	public String tagT;
	public boolean flag=true;
	public String FieldTitle;
	public String path;
	private static Logger logger = Logger.getLogger(FollowerProfileCustomTest.class);
	
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="CustomField")
	public void test() throws InterruptedException,Exception {
		FieldTitle=methods.timeDate();
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
		}
		catch (Exception e){
			System.out.println("fail to open follower profile page!");
			logger.error("fail to open follower profile page!");
		}
		
		
		// Add custom field
		driver.findElement(By.xpath(".//*[@id='addEav']")).click();
		//FieldTitle
		while(true){
			if(methods.isElementPresent(driver,By.className("newSelect")))
				break;
		}
		driver.findElement(By.className("newSelect")).click();
		String selectID=driver.findElement(By.className("newSelect")).getAttribute("id");
		Select selected = new Select(driver.findElement(By.className("newSelect")));
		selected.selectByVisibleText(FieldTitle+" 的天气");
		path=".//*[@id='input"+methods.Getnum(selectID)[0]+"']";
		 System.out.println(selectID+"  "+methods.Getnum(selectID)[0]);
		
		 driver.findElement(By.xpath(path)).sendKeys("阳光明媚");	
		 driver.findElement(By.xpath(".//*[@id='profileSave']")).click();
		 Thread.sleep(1000);
		 if(driver.findElement(By.cssSelector(".leftBox.nopad.profileBg")).getText().contains("View"))
		 {
			 driver.findElement(By.cssSelector(".allbtn.llb")).click();
			 AssertJUnit.assertTrue(driver.findElement(By.xpath(".//*[@id='topTab']/div[2]")).getText().contains(FieldTitle+" 的天气"));
			 System.out.println("Add custom field data successfully!");	
		 }
		 //2016-04-19 18:41 的天气:
		 else{
		 AssertJUnit.assertTrue(driver.findElement(By.xpath(".//*[@id='topTab']/div[2]")).getText().contains(FieldTitle+" 的天气"));
	     System.out.println("Add custom field data successfully!");
		 }
		//Delete custom field
	     
	}

}
