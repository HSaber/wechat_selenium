package com.hu.testcase;


import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class QrcodeImage {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(QrcodeImage.class);
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
			 while(true){
					if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div[1]/a/button")))
					break;
				 }
			    driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div[1]/a/button")).click();
			   	System.out.println("Open Create qrcode page successfully!");
			   }catch(Exception e){
				   Assert.fail("Fail to open create qrcode page!");
				   logger.error("Fail to open create qrcode page!");
			   }
				
				String titleC=methods.timeDate();
				long begin = System.currentTimeMillis();   
				 while(true){
					if(methods.isElementPresent(driver,By.id("Qrcode_scene_name")))
					break;
				 }
				 long end = System.currentTimeMillis();   
		         long result = (end - begin)/1000;
		         System.out.println("页面打开耗时:" +result+ " 秒");
				 driver.findElement(By.id("Qrcode_scene_name")).sendKeys("QrcodeImage_"+titleC);
	
				//tags
				StyleMethods.AddTag(driver, "QrcodeImage_"+titleC);

				//text 
				driver.findElement(By.linkText("Text")).click();
				driver.findElement(By.linkText("Image")).click();
				String parentPath = getClass().getResource("../../").getFile().toString();
				String parentPath1 = parentPath + "/material/Qrcode.JPG";
				File f1 = new File(parentPath1);
				driver.findElement(By.xpath(".//*[@id='pic_upload']/input")).sendKeys(f1.toString());

				String ImgSrc=(String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[4].getAttribute(\"src\");");
				System.out.println(ImgSrc);
				while(true){
					Thread.sleep(500);
					ImgSrc=(String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"img\")[4].getAttribute(\"src\");");
					System.out.println(this.getClass().getSimpleName()+" 图片上传中... 请稍后...");
					if(!ImgSrc.isEmpty())
						break;
				}
			    driver.findElement(By.name("yt0")).click();
			    while(true){
					if(methods.isElementPresent(driver,By.className("detail-view")))
					break;
				 }
			    AssertJUnit.assertEquals("QrcodeImage_"+titleC,driver.findElement(By.className("page_title")).getText().toString());
			    System.out.println("Create QrcodeImage_"+titleC+" successfully!");
			    
				//截图取证
				String title=methods.FileDate();
		        methods.snapshot((TakesScreenshot)driver, title+"QrcodeImage.jpg");
		
	}

}
