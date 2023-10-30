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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.hu.testcase.LoginTest;
public class TagUpdateTest {
	
	public String tagU;
	 WebDriver driver;
	public boolean flag=true;
	private static Logger logger = Logger.getLogger(TagUpdateTest.class);

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

		methods.navigation(driver, "Tags", By.linkText("Create a Tag"));
		
		tagU = "Tag_" + methods.timeDate();
		driver.findElement(By.name("Tags[name]")).sendKeys(tagU);
		driver.findElement(By.name("Tags[name]")).sendKeys(Keys.ENTER);
		while(true){
			Thread.sleep(500);
			if(methods.isElementPresent(driver, By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[4]")))
				if(driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText().contains(tagU))
					break;
			System.out.println("tag筛选中。。。 (update tag script)");
		}
		
			driver.findElement(By.className("update")).click();
			while(true){
				if(methods.isElementPresent(driver, By.name("yt0")))
					break;
				System.out.println("open update tag page ...");
			}
			tagU="New"+tagU;
	
			driver.findElement(By.id("Tags_name")).clear();
		   driver.findElement(By.id("Tags_name")).sendKeys(tagU);	
			//confirm
			driver.findElement(By.name("yt0")).click();
			 while(true){
				 if(methods.isElementPresent(driver, By.linkText("Create a Tag")))
					 break;
			 }
			AssertJUnit.assertEquals(flag,driver.findElement(By.tagName("body")).getText().contains(tagU));
			System.out.println("Update "+tagU+" successfully!");	
	}
}
