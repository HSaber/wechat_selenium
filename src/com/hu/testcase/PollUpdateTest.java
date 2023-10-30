package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class PollUpdateTest {

	boolean acceptNextAlert = true;
	 WebDriver driver;
	public boolean flag = false;
	public static String Osname;
	 private static Logger logger = Logger.getLogger(PollUpdateTest.class);

		@BeforeClass
		public  void setUpBeforeClass() throws Exception {
			driver= new ChromeDriver();
			methods.account_login(driver);
		}

		@AfterClass
		public  void tearDownAfterClass() throws Exception {
				driver.close();
		}

	@Test(dependsOnGroups="poll")
	public void test() throws InterruptedException,Exception {

		String PollTitle = methods.timeDate();
		methods.navigation(driver, "Poll",By.cssSelector("a.btn > button"));
		driver.findElement(By.name("Poll[poll_title]")).sendKeys("中学生上网情况问卷调查" +PollTitle);
		driver.findElement(By.name("Poll[poll_title]")).sendKeys(Keys.ENTER);
		while(true){
	         if(methods.isElementPresent(driver, By.xpath(".//*[@id='poll-grid']/div[1]/table/tbody/tr[1]/td[1]")))
	        	 if(driver.findElement(By.xpath(".//*[@id='poll-grid']/div[1]/table/tbody/tr[1]/td[1]")).getText().equals("中学生上网情况问卷调查" +PollTitle))
	        		 break;
	         System.out.println("Poll 筛选中....");
		}
		
		try{
			driver.findElement(By.className("update")).click();
			while(true){
				if(methods.isElementPresent(driver, By.id("Poll_poll_title")))
					break;
				 System.out.println("update poll页面打开ing.....");
			}
			System.out.println("Open update poll page successfully!");	
		}catch(Exception e){
			System.out.println("Fail to open update poll page!");
			logger.error("Fail to open update poll page!");		
		}
		String EndDate=methods.funcday(2,0);
        EndDate=EndDate.substring(0, 10);
		driver.findElement(By.id("Poll_end_date")).clear();
		driver.findElement(By.id("Poll_end_date")).sendKeys(EndDate);
		driver.findElement(By.id("PollQuestion_question_title")).clear();
		driver.findElement(By.id("PollQuestion_question_title")).sendKeys("你目前所读的年级？----update");

		driver.findElement(By.name("yt0")).click();
		while(true){
			if(methods.isElementPresent(driver,By.id("poll-grid")))
				break;
		}
		flag = driver.findElement(By.tagName("body")).getText().contains("中学生上网情况问卷调查" + PollTitle);
		if (flag) {
			System.out.println("Update 中学生上网情况问卷调查" + PollTitle + " successfully!");
		} else {
			System.out.println("Fail to update 中学生上网情况问卷调查" + PollTitle);
			logger.error("Fail to update 中学生上网情况问卷调查" + PollTitle);		
		}
	}

}