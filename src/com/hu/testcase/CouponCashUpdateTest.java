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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CouponCashUpdateTest {
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

	@Test(dependsOnGroups="coupon",groups="couponUpdate")
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
		 Thread.sleep(1000);
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
		driver.findElement(By.cssSelector(".update>img")).click();
		Thread.sleep(5000);
		System.out.println("open CashCoupon"+ titleD+" successfully!");
		logger.log(Level.INFO, "open CashCoupon"+ titleD+" successfully!");
		}
		catch(Exception e){
			System.out.println("fail to open CashCoupon"+ titleD);
			logger.log(Level.INFO, "fail to open CashCoupon"+ titleD);
			
		}

	    
	    //加tag

/*	    driver.findElement(By.cssSelector("li.tagInput")).click();		   
	    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("CashCouponUpdate_"+titleD);
	   //模拟键盘enter键 
	    try {
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   */


		//submit--------
		driver.findElement(By.xpath(".//*[@id='js_submit']/button")).click();
		Thread.sleep(3000);
		String UpdateCoupon=driver.findElement(By.xpath(".//*[@id='cards-grid']/div[1]/table/tbody/tr[1]/td[1]")).getText();
		try{		
			AssertJUnit.assertEquals("CashCoupon"+titleD,UpdateCoupon);
			System.out.println("Update "+UpdateCoupon+" successfully!");
			logger.log(Level.INFO, "Update "+UpdateCoupon+" successfully!");
		}
		catch (Exception e){
			System.out.println("Fail to Update "+UpdateCoupon+"!");
			logger.log(Level.INFO, "Fail to Update "+UpdateCoupon+"!");
		}		
	    
	}

}
