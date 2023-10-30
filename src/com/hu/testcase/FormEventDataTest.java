package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FormEventDataTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(FormEventDataTest.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
		
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Test(dependsOnGroups="FormeventParti")
	public void test() throws InterruptedException,Exception {
		String dateStr=methods.timeDate();
		methods.navigation(driver, "Form Events",By.className("btn") );
		
		driver.findElement(By.name("FormEvent[inter_name]")).sendKeys("FormEventUp_"+dateStr);
		driver.findElement(By.name("FormEvent[inter_name]")).sendKeys(Keys.ENTER);
		while(true){
			Thread.sleep(500);
			if(methods.isElementPresent(driver,By.xpath(".//*[@id='form-event-grid']/div[1]/table/tbody/tr[1]/td[1]")))
				if(driver.findElement(By.xpath(".//*[@id='form-event-grid']/div[1]/table/tbody/tr[1]/td[1]")).getText().equals("FormEventUp_"+dateStr))
				break;
			System.out.println("FormEvent 筛选中...");
		}
		if(driver.findElement(By.xpath(".//*[@id='form-event-grid']/div[1]/table/tbody/tr[1]/td[5]/a")).getText().equals("0"))
	    Assert.fail("FormEvent 参加失败。");
		else{	
		driver.findElement(By.xpath(".//*[@id='form-event-grid']/div[1]/table/tbody/tr[1]/td[5]/a")).click();
		while(true){
			if(methods.isElementPresent(driver, By.id("form-event-member-grid")))
				break;
			Thread.sleep(500);
		}
		Assert.assertTrue(driver.findElement(By.id("form-event-member-grid")).getText().contains("H.'"));
		Assert.assertTrue(driver.findElement(By.id("form-event-member-grid")).getText().contains(dateStr));
		System.out.println("Formevent 数据录入正常!");		
		}
	}

}
