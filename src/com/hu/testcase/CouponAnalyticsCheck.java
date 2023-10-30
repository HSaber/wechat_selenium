package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CouponAnalyticsCheck {
	 WebDriver driver;
	Logger logger = Logger.getLogger("log.text");

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="couponQrcode")
	public void test() throws InterruptedException,Exception {
	
		//CouponCashCreateTest
		 try {
             LoggerUtil.setLogingProperties(logger);           
         } catch (SecurityException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
		 Actions builder=new Actions(driver);
		 builder.moveToElement(driver.findElement(By.linkText("Engagement"))).click().perform();
		try {
			driver.findElement(By.linkText("Coupon Cards")).click();
			Thread.sleep(3000);
			System.out.println("open coupon list page successfully!--update");
			logger.log(Level.INFO, "open coupon list page successfully!--update");
		} catch (Exception e) {
			System.out.println("fail to open coupon page!--update");
			logger.log(Level.INFO, "fail to open coupon page!--update");
			Assert.fail("Not yet implemented");
		}
		String titleD = methods.timeDate();
		driver.findElement(By.name("Cards[internal_name]")).sendKeys("CashCoupon" + titleD);
		driver.findElement(By.name("Cards[internal_name]")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		try{
		driver.findElement(By.cssSelector(".analytics>img")).click();
		Thread.sleep(5000);
		System.out.println("open CashCoupon"+ titleD+"analytics successfully!");
		logger.log(Level.INFO, "open CashCoupon"+ titleD+"analytics successfully!");
		}
		catch(Exception e){
			System.out.println("fail to open CashCoupon"+ titleD+"analytics");
			logger.log(Level.INFO, "fail to open CashCoupon"+ titleD+"analytics");
			
		}

	    

		
		
		
	    
	}

}
