package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CouponStatusTest {
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

	@Test(dependsOnGroups="couponUpdate",groups="couponStatus")
	public void test() throws InterruptedException,Exception {
		//CouponCashCreateTest
		String titleE = methods.timeDate();
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
		 Thread.sleep(1000);
		try {
			driver.findElement(By.linkText("Coupon Cards")).click();
			Thread.sleep(5000);
			System.out.println("open coupon list page successfully!");
			logger.log(Level.INFO, "open coupon list page successfully!");
		} catch (Exception e) {
			System.out.println("fail to open coupon page!");
			logger.log(Level.INFO,"fail to open coupon page!");
		}
		
			if(driver.findElement(By.tagName("body")).getText().contains("Coupon"+titleE))
			{
				System.out.println("Coupon"+titleE+" exist");
				logger.log(Level.INFO, "Coupon"+titleE+" exist");
			}
			else
			{
				System.out.println("Fail to find Coupon"+titleE);
				logger.log(Level.INFO,"Fail to find Coupon"+titleE);
				Assert.fail("Fail to find Coupon"+titleE);
			}
		
      
       driver.findElement(By.xpath(".//*[@id='cards-grid']/div[1]/table/thead/tr[2]/td[1]/input")).sendKeys("Coupon"+titleE);
       //模拟键盘enter键 
	    try {
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	        AssertJUnit.assertEquals("Pending",driver.findElement(By.xpath(".//*[@id='cards-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText());
	        System.out.println("Coupon"+titleE+" Status is "+driver.findElement(By.xpath(".//*[@id='cards-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText());
	        logger.log(Level.INFO,"Coupon"+titleE+" Status is "+driver.findElement(By.xpath(".//*[@id='cards-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText());
	  
	}

}
