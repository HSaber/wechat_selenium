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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class QrCodeCouponTest {
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
	@Test(dependsOnGroups="couponStatus",groups="couponQrcode")
	public void test() throws InterruptedException,Exception {
		//CouponCashCreateTest,QrCodeTextTest
		methods.navigation(driver, "QR Codes", By.className("btn"));
		
		try {
			WebElement create = driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div[1]/a/button"));
			create.click();
			Thread.sleep(3000);
			System.out.println("Open Create qrcode page successfully!");
			logger.log(Level.INFO, "Open Create qrcode page successfully!");
		} catch (Exception e) {
			Assert.fail("Fail to open create qrcode page!");
			logger.log(Level.INFO, "Fail to open create qrcode page!");
		}

		String titleC = methods.timeDate();
		driver.findElement(By.id("Qrcode_scene_name")).sendKeys("QrcodeCoupon_" + titleC);
		// tags
		driver.findElement(By.id("tags")).click();
		driver.findElement(By.cssSelector("li.tagInput")).click();
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("QrcodeCoupon_"+titleC);	

		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		/*
		 * //Link your code //Thread.sleep(1000);
		 * driver.findElement(By.id("event")).click(); //Thread.sleep(2000);
		 * driver.findElement(By.id("Qrcode_event_id")).click(); new
		 * Select(driver
		 * .findElement(By.id("Qrcode_event_id"))).selectByVisibleText
		 * ("Test event"); //Thread.sleep(1000);
		 * driver.findElement(By.xpath(".//*[@id='qrcode-form']/div[5]/div[1]/label"
		 * )).click();
		 */

		// coupon card
		driver.findElement(By.className("text")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("coupon_card")).click();
		Thread.sleep(1000);
	//	driver.findElement(By.id("Qrcode_reply_coupon_card_id")).click();
		new Select(driver.findElement(By.id("Qrcode_reply_coupon_card_id"))).selectByVisibleText("CashCoupon" + titleC);
		// confirm----------
		driver.findElement(By.xpath(".//*[@id='qrcode-form']/div[6]/input[1]")).click();
		Thread.sleep(5000);
		AssertJUnit.assertTrue(driver.findElement(By.className("page_title")).getText().toString().contains(titleC));
		System.out.println("Create Coupon" + titleC + " successfully!");
		logger.log(Level.INFO, "Create Coupon" + titleC + " successfully!");

		//截图取证
		String title=methods.FileDate();
        methods.snapshot((TakesScreenshot)driver, title+"Coupon.jpg");

	}

}
