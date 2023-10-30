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

public class MenuAllDeleteTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(MenuAllDeleteTest.class);
	public boolean flag=true;

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
		methods.navigation(driver, "Menu", By.id("remove"));
	
		driver.findElement(By.id("remove")).click();
		driver.findElement(By.xpath("//button[@onclick='jQuery.unblockUI();removeMenu();']")).click();
		long begin = System.currentTimeMillis();  
		long end,result;
		while(true){
			  end = System.currentTimeMillis();   
	          result = (end - begin)/1000;
			if(!methods.isElementPresent(driver,By.className("qp_lodediv")) || (result>=20))
			break;
		 }
		if(result>=20){
			System.out.println("Fail to delete all menu successfully!");
			logger.error("Fail to delete all menu successfully!");
		}
         
		else {
		AssertJUnit.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Unpublished"));
		 System.out.println("Delete all menu successfully! 并且耗时"+result+"秒!");
		}
		
	}

}
