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
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FollowerProfileMessageTest {
	 WebDriver driver;
	public String tagT;
	public boolean flag=true;
	public String FieldTitle;
	public String path;
	private static Logger logger = Logger.getLogger(FollowerProfileMessageTest.class);
	
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
		 String fileStr=methods.FileDate();
		 methods.navigation(driver, "Followers", By.className("follower_img"));
		 // 同样的导航问题应该是
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys("H.'");
		driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(Keys.ENTER);
		while(true){
			if(!methods.isElementPresent(driver,By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[2]/td[1]")))
				break;
		}
		try{
			driver.findElement(By.className("follower_img")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("analytics")))
					break;
			}
			System.out.println("Open follower profile page successfully!");
		}
		catch (Exception e){
			System.out.println("fail to open follower profile page!");
			logger.error("fail to open follower profile page!");
		}
		
		//messaging
		//driver.findElement(By.xpath("(//a[contains(text(),'Messaging')])[2]")).click();
		driver.findElement(By.id("message")).click();
		while(true){
			Thread.sleep(500);
			if(methods.isElementPresent(driver, By.className("bottom_white_block")))
				break;
		}
		
		//post-------
		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[1]/div/ul/li[1]/a")).click();
		while(true){
			if(methods.isElementPresent(driver,By.id("resId")))
				break;
		}
		driver.findElement(By.id("resId")).click();
		new Select(driver.findElement(By.id("resId"))).selectByIndex(1);
		String title=new Select(driver.findElement(By.id("resId"))).getFirstSelectedOption().getText();
		System.out.println(title);
		while(true){
          	if(methods.isElementPresent(driver, By.className("picture_news")))
		     break;
          	System.out.println("Post加载中...");
            }
		
		driver.findElement(By.className("send_msg")).click();
		while(true){
			Thread.sleep(500);
		if(driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/ul/li[1]")).getText().contains(title))
		{
			System.out.println(driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/ul/li[1]")).getText());
			System.out.println("Send post successfully");
			break;
		}
		System.out.println(driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/ul/li[1]")).getText());
		System.out.println("Message列表加载中...");
		}

				
		
		//text--------	
		driver.findElement(By.xpath(".//*[@id='quickReplyBox']/div[1]/div/div[2]")).sendKeys("test send message to {{nickname}}"+"\n");
		while(true){
			if(methods.isElementPresent(driver,By.xpath(".//*[@id='quickReplyBox']/div[1]/div/div[3]/a")))
				break;
		}
		driver.findElement(By.xpath(".//*[@id='quickReplyBox']/div[1]/div/div[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='quickReplyBox']/div[1]/div/div[3]/div/ul/li[1]/i")).click();
		driver.findElement(By.xpath(".//*[@id='quickReplyBox']/div[1]/div/div[2]")).sendKeys("\n"+"{{time}}好"+"\n");

		driver.findElement(By.className("send_msg")).click();
		while(true){
			Thread.sleep(500);
		if(driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/ul/li[1]")).getText().contains("test send message to H.'"))
		{
			System.out.println("Send message successfully");
			break;
		}
		System.out.println("Message列表加载中...");
		}

		
		//image--------
		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[1]/div/ul/li[3]/a")).click();
		while(true){
			if(methods.isElementPresent(driver, By.id("pic_Box")))
				break;
		}
		String parentPath = getClass().getResource("../../").getFile().toString();
		parentPath = parentPath + "/material/27.JPG";	
		File f1 = new File(parentPath);
		driver.findElement(By.name("files[]")).sendKeys(f1.toString());
		while(true){
			String result=(String)((JavascriptExecutor)driver).executeScript("return typeof($(\"#pic_Box\").attr(\"src\"))");
			if(!result.equals("undefined"))
			if(driver.findElement(By.id("pic_Box")).getAttribute("src").contains("/upload/images/"))
				break;
			System.out.println("图片加载中。。。");
		}
		driver.findElement(By.className("send_msg")).click();
		while(true){
			Thread.sleep(500);
		if(driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/ul/li[1]")).getText().contains("image:"))
		{
			System.out.println("Send Imag successfully");
			break;
		}
		System.out.println("Message列表加载中...");
		}
		
		//image2--------Coupon.jpg
/*		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[1]/div/ul/li[3]/a")).click();
		Thread.sleep(1000);
		parentPath = getClass().getResource("../../").getFile().toString();
		parentPath = parentPath + "/screenshots/"+fileStr+"Coupon.jpg";	
		f1 = new File(parentPath);
		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[3]/input[1]")).sendKeys(f1.toString());
		Thread.sleep(5000);
		driver.findElement(By.className("send_msg")).click();
		Thread.sleep(4000);
		assertTrue(driver.findElement(By.xpath("//.//*[@id='topTab']/div[3]/div/div/ul/li[1]/div[2]/div/div")).getText().contains("image:"));
		System.out.println("Send image successfully");
		logger.log(Level.INFO, "Send image successfully");*/
		
/*		
		//image3--------html5Create.jpg
		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[1]/div/ul/li[3]/a")).click();
		Thread.sleep(1000);
		parentPath = getClass().getResource("../../").getFile().toString();
		parentPath = parentPath + "/screenshots/"+fileStr+"QrcodeImage.jpg";	
		f1 = new File(parentPath);
		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[3]/input[1]")).sendKeys(f1.toString());
		Thread.sleep(5000);
		driver.findElement(By.className("send_msg")).click();
		Thread.sleep(4000);
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//.//*[@id='topTab']/div[3]/div/div/ul/li[1]/div[2]/div/div")).getText().contains("image:"));
		System.out.println("Send image successfully");
		logger.log(Level.INFO, "Send image successfully");
		
		
		//image4---CDReport.jpg-----
		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[1]/div/ul/li[3]/a")).click();
		Thread.sleep(1000);
		parentPath = getClass().getResource("../../").getFile().toString();
		parentPath = parentPath + "/screenshots/"+fileStr+"CDReport.jpg";	
		f1 = new File(parentPath);
		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[3]/input[1]")).sendKeys(f1.toString());
		Thread.sleep(5000);
		driver.findElement(By.className("send_msg")).click();
		Thread.sleep(4000);
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//.//*[@id='topTab']/div[3]/div/div/ul/li[1]/div[2]/div/div")).getText().contains("image:"));
		System.out.println("Send image successfully");
		logger.log(Level.INFO, "Send image successfully");
		
		
		
		//image5---QrcodeText.jpg-----
		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[1]/div/ul/li[3]/a")).click();
		Thread.sleep(1000);
		parentPath = getClass().getResource("../../").getFile().toString();
		parentPath = parentPath + "/screenshots/"+fileStr+"QrCodeText.jpg";	
		f1 = new File(parentPath);
		driver.findElement(By.xpath(".//*[@id='topTab']/div[3]/div/div/div[3]/input[1]")).sendKeys(f1.toString());
		Thread.sleep(5000);
		driver.findElement(By.className("send_msg")).click();
		Thread.sleep(4000);
		AssertJUnit.assertTrue(driver.findElement(By.xpath("//.//*[@id='topTab']/div[3]/div/div/ul/li[1]/div[2]/div/div")).getText().contains("image:"));
		System.out.println("Send image successfully");
		logger.log(Level.INFO, "Send image successfully");*/
	}

}
