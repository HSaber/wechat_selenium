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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SurveyUpdateTest {
	boolean acceptNextAlert = true;
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SurveyUpdateTest.class);
    private StringBuffer verificationErrors = new StringBuffer();
    boolean flag=true;
    
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="Survey",groups="UpdateSurvey")
	public void test() throws InterruptedException,Exception {
		String SurveyTitle = methods.timeDate();
		SurveyTitle="个人信息调查问卷"+SurveyTitle;
		methods.navigation(driver, "Surveys", By.cssSelector("a.btn > button"));
		driver.findElement(By.name("Survey[survey_name]")).sendKeys(SurveyTitle);
		driver.findElement(By.name("Survey[survey_name]")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		try{
			driver.findElement(By.className("update")).click();
		    Thread.sleep(5000);		    
		    System.out.println("Open update survey page successfully!");
		}catch(Exception e){
			System.out.println("Fail to open update survey page!");
			logger.error("Fail to open update survey page!");
		}

    driver.findElement(By.id("Survey_completion_message")).clear();
    driver.findElement(By.id("Survey_completion_message")).sendKeys("感谢您参加我们的调研！Very thank you!!!");
    driver.findElement(By.name("yt0")).click();
    Thread.sleep(5000);
   
    boolean status=driver.findElement(By.xpath(".//*[@id='yw0']/tbody/tr[4]/td")).getText().contains("感谢您参加我们的调研！Very thank you!!!");
    AssertJUnit.assertEquals(flag,status);
    System.out.println("Update "+SurveyTitle+" successfully!");   
	}

}
