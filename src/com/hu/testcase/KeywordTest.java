package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class KeywordTest {
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
	public void test() throws InterruptedException {
		String timeStr=methods.timeDate();
		 Actions builder=new Actions(driver);
		 builder.moveToElement(driver.findElement(By.linkText("Messaging"))).click().perform();
		 Thread.sleep(1000);
		 builder.moveToElement(driver.findElement(By.linkText("Auto Responders"))).click().perform();
		 Thread.sleep(1000);
		try{
			driver.findElement(By.linkText("Keyword Auto-Reply")).click();
			 Thread.sleep(5000);
			
			 System.out.println("Open trigger successfully!");
		}catch(Exception e){
			 System.out.println("Fail to open trigger!");
		}
		try{
		  driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div[1]/a[2]/button")).click();
		  Thread.sleep(5000);
		
		  System.out.println("Open create trigger successfully!");
		}catch(Exception e){
			 System.out.println("Fail to open create trigger!");
		}
				
		   driver.findElement(By.xpath(".//*[@id='key-word-form']/div[5]/div[1]/input")).clear();
		   driver.findElement(By.xpath(".//*[@id='key-word-form']/div[5]/div[1]/input")).sendKeys("Test");
		   Thread.sleep(2000);
		   driver.findElement(By.className("add_new_rule")).click();
		   driver.findElement(By.xpath(".//*[@id='key-word-form']/div[6]/div[2]/button")).click();
		   Thread.sleep(10000);
		   
	}

}
