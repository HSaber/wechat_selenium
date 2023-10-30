package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.hu.testcase.LoginTest;
public class TagCreateTest {
	
	public String tagT;
	 WebDriver driver;
	public boolean flag=true;
	private static Logger logger = Logger.getLogger(TagCreateTest.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(groups="tag")
	public void test() throws InterruptedException,Exception {
		
		methods.navigation(driver, "Tags", By.name("Tags[name]"));
			try{
				driver.findElement(By.linkText("Create a Tag")).click();
				while(true){
					 if(methods.isElementPresent(driver, By.id("Tags_name")))
						 break;
				 }
				System.out.println("Open create tag page successfully!");
			}catch(Exception e){
				System.out.println("Fail to open create tag page!");
				logger.error("Fail to open create tag page!");
			}
			
			
		    tagT=methods.timeDate();
		    tagT="Tag_"+tagT;
			driver.findElement(By.id("Tags_name")).sendKeys(tagT);
			//confirm
			driver.findElement(By.name("yt0")).click();
			Thread.sleep(1000);
			if(driver.findElement(By.tagName("body")).getText().contains("Tag already exists!"))
				System.out.println(tagT+" already exists!");
			else
			{    while(true){
				Thread.sleep(500);
				 if(methods.isElementPresent(driver, By.linkText("Create a Tag")))
					 break;
			 }
				AssertJUnit.assertEquals(flag,driver.findElement(By.tagName("body")).getText().contains(tagT));
				System.out.println("Create "+tagT+" successfully!");
			}
			
		
	}
}
