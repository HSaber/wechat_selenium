package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class FormEventUpdateTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(FormEventUpdateTest.class);
	public String FormTitle;

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="Formevent",groups="UpFormevent")
	public void test() throws InterruptedException,Exception {
		    methods.navigation(driver, "Form Events",By.className("btn") );
            String FormUpdate=methods.timeDate();
            driver.findElement(By.name("FormEvent[inter_name]")).clear();
			driver.findElement(By.name("FormEvent[inter_name]")).sendKeys("FormEvent_"+FormUpdate);
			driver.findElement(By.name("FormEvent[inter_name]")).sendKeys(Keys.ENTER);
			while(true){
				if(methods.isElementPresent(driver, By.className("update")))
					break;
			}
			try{
			driver.findElement(By.className("update")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("FormEvent_location")))
					break;
			}
			System.out.println("Open update formevent page successfully!");
			}catch(Exception e){
				System.out.println("Fail to open update formevent page!");
				logger.error("Fail to open update formevent page!");
			}
			driver.findElement(By.id("FormEvent_inter_name")).clear();
			driver.findElement(By.id("FormEvent_inter_name")).sendKeys("FormEventUp_"+FormUpdate);

			
			driver.findElement(By.id("FormEvent_location")).clear();
			driver.findElement(By.id("FormEvent_location")).sendKeys("上海，北京，广州");
			String titleC=methods.timeDate();

			//cke_FormEvent_content
			((JavascriptExecutor)driver).executeScript("CKEDITOR.instances['FormEvent_content'].setData('欢迎参加报名活动----update');");

		//	Thread.sleep(2000);
			 			
			//confirm-------------
			driver.findElement(By.name("yt0")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("promotion_url")))
					break;
			}
			AssertJUnit.assertEquals("Create Form Event Successful!",driver.findElement(By.className("page_title")).getText().toString());
		    System.out.println("Update FormEventUp_"+FormUpdate+" successfully!");	
	}

}
