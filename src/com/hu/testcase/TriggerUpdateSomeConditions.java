package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TriggerUpdateSomeConditions {
	 WebDriver driver;


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
		String timeStr=methods.timeDate();
		methods.navigation(driver, "Triggers", By.linkText("Create a Trigger"));

		
	    new Select(driver.findElement(By.id("Triggers_is_once"))).selectByVisibleText("Yes");
	    Thread.sleep(1000);
	}

}
