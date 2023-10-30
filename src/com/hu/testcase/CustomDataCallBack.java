package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.interactive.testcase.HttpClient;

public class CustomDataCallBack {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(CustomDataCallBack.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}
	@Test(dependsOnGroups="Html5Action")
	public void test() throws InterruptedException,Exception {
		String timeStr=methods.timeDate();
		int p=methods.getAccountOrder(methods.baseUrl)[0];
		int q=methods.getAccountOrder(methods.baseUrl)[1];
		System.out.println(p+" "+q+" "+HttpClient.openidArr[p][q]);
		methods.navigation(driver, "Custom Data Call Back", By.name("module_name"));
		driver.findElement(By.name("module_name")).clear();
		driver.findElement(By.name("module_name")).sendKeys("Html5_"+timeStr);
		driver.findElement(By.name("yt0")).click();
		while(true)
		 {
			if(methods.isElementPresent(driver,By.id("tracking-grid")))
				break;
		 }
		AssertJUnit.assertTrue(driver.findElement(By.id("tracking-grid")).getText().contains("Html5_"+timeStr));
		AssertJUnit.assertTrue(driver.findElement(By.id("tracking-grid")).getText().contains(HttpClient.openidArr[p][q]));
		System.out.println("Html5_"+timeStr+"数据正常！");		
	}

}
