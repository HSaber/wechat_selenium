package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.util.Properties;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginTest {
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
		try{
			 Actions builder=new Actions(driver);
			 builder.moveToElement(driver.findElement(By.xpath(".//*[@id='dada']/div[2]/div/div/div[2]/ul[5]/li/a"))).click().perform();
			 Thread.sleep(1000);
			 builder.moveToElement(driver.findElement(By.xpath(".//*[@id='dada']/div[2]/div/div/div[2]/ul[5]/li/ul/li[2]/a"))).click().perform();
			 Thread.sleep(1000);
			try{
				driver.findElement(By.xpath(".//*[@id='dada']/div[2]/div/div/div[2]/ul[5]/li/ul/li[2]/ul/li[2]/a")).click();
				Thread.sleep(5000);
				System.out.println("Open formevent list page successfully!");

			}catch(Exception e){
				Assert.fail("Fail to Open formevent list page!");

			}
//		assertEquals("New Follows",driver.findElement(By.className("myTitle")).getText());
//		System.out.println("log in successfully!!");
		


		}
		catch(Exception e){
			System.out.println("Fail to login");
			Assert.fail("Not yet implemented");
		}
	}

}
