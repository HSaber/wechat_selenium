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

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class QrCodeTextTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(QrCodeTextTest.class);

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
		methods.navigation(driver, "QR Codes", By.className("btn"));
		 try{
			   driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div[1]/a/button")).click();
				System.out.println("Open Create qrcode page successfully!");
			   }catch(Exception e){
				   Assert.fail("Fail to open create qrcode page!");
				   logger.error("Fail to open create qrcode page!");
			   }
				
				String titleC=methods.timeDate();
				driver.findElement(By.id("Qrcode_scene_name")).sendKeys("QrcodeText_"+titleC);

				//tags
				driver.findElement(By.id("tags")).click();
				driver.findElement(By.cssSelector("li.tagInput")).click();
				driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
				driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("QrcodeText_"+titleC);	

				driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);
			/*	//Link your code 
				//Thread.sleep(1000);
                driver.findElement(By.id("event")).click();
				//Thread.sleep(2000);
				driver.findElement(By.id("Qrcode_event_id")).click();
				new Select(driver.findElement(By.id("Qrcode_event_id"))).selectByVisibleText("Test event");   
				//Thread.sleep(1000);
				driver.findElement(By.xpath(".//*[@id='qrcode-form']/div[5]/div[1]/label")).click();
				*/
				//text 
				driver.findElement(By.linkText("Text")).click();
				Thread.sleep(1000);
				//text 
				driver.findElement(By.id("Qrcode_auto_reply")).sendKeys("QrCode {{nickname}}test information "+"\n"+"{{time}}好 \n<a href=\"http://mp.weixin.qq.com/s?__biz=MzA5NDExNDAxMg==&mid=402503006&idx=1&sn=96f7cb154d807671b3dfac20b145d406\">Post Link to 蒂芙尼</a>");
			    driver.findElement(By.name("yt0")).click();
			    while(true){
					if(methods.isElementPresent(driver,By.className("detail-view")))
					break;
				 }
			    AssertJUnit.assertEquals("QrcodeText_"+titleC,driver.findElement(By.className("page_title")).getText().toString());
			    System.out.println("Create QrcodeText_"+titleC+" successfully!");
			    
				//截图取证
				String title=methods.FileDate();
		        methods.snapshot((TakesScreenshot)driver, title+"QrCodeText.jpg");
		
	}

}
