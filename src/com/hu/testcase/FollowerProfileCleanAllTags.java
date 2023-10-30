package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FollowerProfileCleanAllTags {
	 WebDriver driver;
	public String FieldTitle;
	public String path;
	private static Logger logger = Logger.getLogger(FollowerProfileCleanAllTags.class);
	
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
		
		//添加一个tag就要更改一下数组(methods.TagNumArr)的长度 
		 String timeStr=methods.timeDate();
		 methods.navigation(driver, "Followers", By.className("follower_img"));

		//String[] NicknameArr={"H.'","๑Candy๑3'"};
		 String[] NicknameArr={"Unfollower"};
		for(int m=0;m<NicknameArr.length;m++){
			Thread.sleep(500);
			driver.findElement(By.name("WechatCustomer[nickname]")).clear();
			driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(NicknameArr[m]);
			driver.findElement(By.name("WechatCustomer[nickname]")).sendKeys(Keys.ENTER);
			
			while(true){
				Thread.sleep(500);
				if(methods.isElementPresent(driver,By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[1]/td[1]")))
					if(driver.findElement(By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[1]/td[1]")).getText().contains(NicknameArr[m]))
					break;
			}
			if(driver.findElement(By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText().contains("N/A"))
				System.out.println("N/A");
			else
			{
			try{
				driver.findElement(By.xpath(".//*[@id='wechat-customer-grid']/div[1]/table/tbody/tr[1]/td[1]/a[2]")).click();
				while(true){
					Thread.sleep(500);
					if(methods.isElementPresent(driver,By.id("analytics")))
						break;
				}
				System.out.println("Open "+NicknameArr[m]+" follower profile page successfully!");
			}
			catch (Exception e){
				System.out.println("fail to open "+NicknameArr[m]+" follower profile page!");
				logger.error("fail to open "+NicknameArr[m]+" follower profile page!");
			}
	
			int divOrder;
			if(methods.isElementPresent(driver, By.id("ecoTab")))
					divOrder=4;
			else 
				divOrder=4;
			//CheckTag
			driver.findElement(By.xpath(".//*[@id='topTab']/div["+divOrder+"]/dl/dt/a")).click();
			Thread.sleep(3000);
			//获取ul下li的个数
			int j=1;
			Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"tag_handler\").getElementsByTagName(\"li\").length;")-1;	
			System.out.println(str);
				for(int i=1;i<=str;i++){
					String tag=driver.findElement(By.xpath(".//*[@id='tag_handler']/li["+i+"]")).getText();
					
					if(tag.isEmpty()){
						break;
					}else {
						System.out.println(tag);
						driver.findElement(By.xpath(".//*[@id='tag_handler']/li["+i+"]")).click();
						i=i-1;
						str=str-1;

						System.out.println("删掉了"+j+" 删的是"+tag+" 还剩"+str+"个tags");
						j=j+1;
					}
				}
				Thread.sleep(500);
				driver.findElement(By.className("tagBg")).click();
				Thread.sleep(500);
				driver.findElement(By.cssSelector(".savehandle.btn")).click();
				Thread.sleep(500);
				driver.findElement(By.cssSelector(".savehandle.btn")).click();
				Thread.sleep(500);
				driver.findElement(By.cssSelector(".savehandle.btn")).click();
				while(true){
					if(methods.isElementPresent(driver, By.className("tagList")))
						if(driver.findElement(By.className("tagList")).getText().contains("N/A"))
						break;
				}
		
			driver.navigate().back();
			}
		}
	}

}
