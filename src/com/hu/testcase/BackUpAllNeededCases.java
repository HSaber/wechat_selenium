package com.hu.testcase;


import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
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


public class BackUpAllNeededCases {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(BackUpAllNeededCases.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(groups="BackUpSegmentation")
	public void test() throws InterruptedException,Exception {
			   
	     String timeStr=methods.timeDate();
		 Actions builder=new Actions(driver);
		 long begin,end,result;
		 methods.navigation(driver, "Segmentation", By.className("btn"));
		
		try{
			driver.findElement(By.className("btn")).click();
			while(true)
			 {
				if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
					break;
			 }
			AssertJUnit.assertEquals("Create a Segment",driver.findElement(By.className("page_title")).getText());
			System.out.println("Open create segmentation page successfully!");
		}catch(Exception e){
			 System.out.println("Fail to open create segmentation page!");
			 logger.error("Fail to open create segmentation page!");
		}
		
//		-------personal menu segmentation----------------------------------------------------------------------------------------
		 driver.findElement(By.id("dynamicRules_name")).clear();
		 driver.findElement(By.id("dynamicRules_name")).sendKeys("GY2+PersonalMenu");
		 new Select(driver.findElement(By.className("rule_types"))).selectByVisibleText("Follower");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"))).selectByVisibleText("contains");
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).clear();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys("rainbow");
		 driver.findElement(By.linkText("Preview")).click();
		  begin = System.currentTimeMillis();  
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.className("summary")))
				break;
		 }
		 end=System.currentTimeMillis();
		 result=(end-begin)/1000;
		 System.out.println("GY2+PersonalMenu Preview耗时"+result+"秒!");
		 Thread.sleep(1000);
		 driver.findElement(By.name("yt1")).click();
		 while(true){
			 if(methods.isElementPresent(driver,By.linkText("Create a segmentation")))
				 break;
		 }
		 System.out.println("GY2+PersonalMenu 创建成功!");
//---------------------------------------------------------------------------------------------------------
		 driver.findElement(By.className("btn")).click();
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
				break;
		 }
		 driver.findElement(By.id("dynamicRules_name")).clear();
		 driver.findElement(By.id("dynamicRules_name")).sendKeys("HC2+PersonalMenu");
		 new Select(driver.findElement(By.className("rule_types"))).selectByVisibleText("Follower");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"))).selectByVisibleText("contains");
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).clear();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys("'");
		 driver.findElement(By.className("add_a_rule")).click();
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Message");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"))).selectByVisibleText("keywords");
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).clear();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).sendKeys("HCTestMenu");
		 
		 driver.findElement(By.linkText("Preview")).click();
		 begin = System.currentTimeMillis();  
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.className("summary")))
				break;
		 }
		  end=System.currentTimeMillis();
		  result=(end-begin)/1000;
		 System.out.println("HC2+PersonalMenu Preview耗时"+result+"秒!");
		 Thread.sleep(1000);
		 driver.findElement(By.name("yt1")).click();
		 while(true){
			 if(methods.isElementPresent(driver,By.linkText("Create a segmentation")))
				 break;
		 }
		 System.out.println("HC2+PersonalMenu 创建成功!");
//----------------------------------------------------------------------------------------------------------------------------------------
		 driver.findElement(By.className("btn")).click();
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
				break;
		 }
		 driver.findElement(By.id("dynamicRules_name")).clear();
		 driver.findElement(By.id("dynamicRules_name")).sendKeys("Just+rainbow");
		 new Select(driver.findElement(By.className("rule_types"))).selectByVisibleText("Follower");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"))).selectByVisibleText("does not contain");
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).clear();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys("gy");
		 
		 driver.findElement(By.className("add_a_rule")).click();
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Follower");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"))).selectByVisibleText("Profile");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"))).selectByVisibleText("contains");
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).clear();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).sendKeys("rainbow");
		 
		 driver.findElement(By.linkText("Preview")).click();
		begin = System.currentTimeMillis();  
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.className("summary")))
				break;
		 }
		 end=System.currentTimeMillis();
		 result=(end-begin)/1000;
		 System.out.println("Just+rainbow Preview耗时"+result+"秒!");
		 Thread.sleep(1000);
		 driver.findElement(By.name("yt1")).click();
		 while(true){
			 if(methods.isElementPresent(driver,By.linkText("Create a segmentation")))
				 break;
		 }
		 System.out.println("Just+rainbow 创建成功!");
//---------personal menu segmentation-----------------------------------------------------------------------------------------------------------
		 driver.findElement(By.className("btn")).click();
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
				break;
		 }
		 driver.findElement(By.id("dynamicRules_name")).clear();
		 driver.findElement(By.id("dynamicRules_name")).sendKeys("No Testers");
		 String[] testersArr={"H.'","Candy","rainbow","T_T"};
		 for (int i=1;i<5;i++)
		 {	 
		if(i==1)
		{
		 new Select(driver.findElement(By.className("rule_types"))).selectByVisibleText("Follower");
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
		 while(true){
			 if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]")))
				 break;
		 }
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
		 while(true){
			 if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]")))
				 break;
		 }
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select[3]"))).selectByVisibleText("does not contain");
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).clear();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys(testersArr[i-1]);
		 }
		else{	                      
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+(i-1)+"]/div[4]/div[1]")).click();
		 while(true){
			 if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[1]/select")))
				 break;
		 }
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[1]/select[2]"))).selectByVisibleText("Follower");
		 while(true){
			 if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[2]/div/select")))
				 break;
		 }
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[2]/div/select"))).selectByVisibleText("Profile");
		 while(true){
			 if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[2]/div/select[2]")))
				 break;
		 }
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
		 while(true){
			 if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[2]/div/select[3]")))
				 break;
		 }
		 new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[2]/div/select[3]"))).selectByVisibleText("does not contain");
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[2]/div/input")).clear();
		 driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+i+"]/div[2]/div/input")).sendKeys(testersArr[i-1]);
		 while(true){
			 if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div["+(i-1)+"]/div[4]/div[1]")))
				 break;
		 }
	    	}
		 }
		 
		 driver.findElement(By.linkText("Preview")).click();
		 begin = System.currentTimeMillis();  
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.className("summary")))
				break;
		 }
		 end=System.currentTimeMillis();
		 result=(end-begin)/1000;
		 System.out.println("No Testers Preview耗时"+result+"秒!");
		 Thread.sleep(1000);
		 driver.findElement(By.name("yt1")).click();
		 while(true){
			 if(methods.isElementPresent(driver,By.linkText("Create a segmentation")))
				 break;
		 }
		 System.out.println("No Testers 创建成功!");
		 
	}

}
