package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SegmentationBackup {
	 WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	 private static Logger logger = Logger.getLogger(SegmentationBackup.class);
	 
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
		//poll,formevent
		String timeStr=methods.timeDate();
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
	
		//ActionToday+LBS(SH,JS)
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("ActionToday+LBS(SH,JS)");
		new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Actions");
		methods.Loading(driver, By.cssSelector("select.newCondition"));
		new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("Date");
		methods.Loading(driver,By.cssSelector("select.operator"));
		new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("within");
		methods.Loading(driver, By.name("select"));
		new Select(driver.findElement(By.name("select"))).selectByVisibleText("Today");		
		
		driver.findElement(By.cssSelector("span.add_a_rule")).click();
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"));          
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Follower");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[1]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[1]"))).selectByVisibleText("LBS");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"));

		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("Last Location");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"))).selectByVisibleText("equals");
		methods.Loading(driver, By.cssSelector("select.tempCountryField"));
		new Select(driver.findElement(By.cssSelector("select.tempCountryField"))).selectByVisibleText("China");

		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[4]/div[1]")).click();
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[1]/select[2]"))).selectByVisibleText("Follower");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[1]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[1]"))).selectByVisibleText("Profile");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[2]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[3]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[3]"))).selectByVisibleText("contains");						
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/input")).sendKeys("Candy");
		
		Thread.sleep(500);
		driver.findElement(By.name("yt1")).click();
		    while(true)
		    {		    	
		    	if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
		    	{
		    	    System.out.println("Segmentation 创建成功!");
		    	     break;
		    	}
		    }
		
		//Pollparti+Pollresult
		driver.findElement(By.cssSelector("a.btn > button")).click();
		while(true)
		 {
			if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
				break;
		 }
		driver.findElement(By.id("dynamicRules_name")).clear();
		driver.findElement(By.id("dynamicRules_name")).sendKeys("Pollparti+Pollresult");
		new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Engagement");
		methods.Loading(driver, By.className("newCondition"));		
		new Select(driver.findElement(By.className("newCondition"))).selectByVisibleText("poll");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"))).selectByVisibleText("participated");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"))).selectByVisibleText("title");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[4]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[4]"))).selectByVisibleText("equals");
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[5]"))).selectByIndex(1);
		
		driver.findElement(By.cssSelector("span.add_a_rule")).click();
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"));		             
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Engagement");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"));		      
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"))).selectByVisibleText("poll");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("result");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"))).selectByIndex(1);
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[4]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[4]"))).selectByVisibleText("你的性别？");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[5]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[5]"))).selectByVisibleText("equals");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[6]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[6]"))).selectByVisibleText("女");
		Thread.sleep(500);
		driver.findElement(By.name("yt1")).click();
		    while(true)
		    {		    	
		    	if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
		    	{
		    	    System.out.println("Segmentation 创建成功!");
		    	     break;
		    	}
		    }
		
		//FollowerTagName+MenuClick  formevent
		driver.findElement(By.cssSelector("a.btn > button")).click();
		while(true)
		 {
			if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
				break;
		 }
		driver.findElement(By.id("dynamicRules_name")).clear();
		driver.findElement(By.id("dynamicRules_name")).sendKeys("FollowerTagName+MenuClick");
		new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"))).selectByVisibleText("Tag");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"))).selectByVisibleText("Name");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[4]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[4]"))).
		selectByValue(methods.optionStr(driver, ".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[4]", ""));
		methods.Loading(driver, By.cssSelector("select.operator"));
		new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("equals");
		methods.Loading(driver, By.cssSelector("input.values.amount"));
		driver.findElement(By.cssSelector("input.values.amount")).clear();
		driver.findElement(By.cssSelector("input.values.amount")).sendKeys("1");
		methods.Loading(driver, By.cssSelector("span.add_a_rule"));
		driver.findElement(By.cssSelector("span.add_a_rule")).click();
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Menu");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("Menu Title");
		methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"));
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"))).selectByVisibleText("equals");
		methods.Loading(driver, By.cssSelector("select.values"));
	    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[4]"))).selectByIndex(1);
		Thread.sleep(500);
		driver.findElement(By.name("yt1")).click();
		    while(true)
		    {		    	
		    	if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
		    	{
		    	    System.out.println("Segmentation 创建成功!");
		    	     break;
		    	}
		    }
		    
		//FollowProfileCustomFieldName+ActionDate
	    driver.findElement(By.cssSelector("a.btn > button")).click();
	    while(true)
		 {
			if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
				break;
		 }
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("FollowProfileCustomFieldName+ActionDate");
		    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
		    methods.Loading(driver, By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[2]"));
		    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[2]"))).selectByVisibleText("Custom Field");
		    methods.Loading(driver, By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[4]"));
		    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[4]"))).selectByVisibleText("Name");
		    methods.Loading(driver, By.cssSelector("select.operator"));
		    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("Have Value");
		    driver.findElement(By.cssSelector("span.add_a_rule")).click();
		    
		    methods.Loading(driver, By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div[2]/div/select[2]"));     
		    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div[2]/div/select[2]"))).selectByVisibleText("Actions");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"))).selectByVisibleText("Date");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("within");
		    methods.Loading(driver, By.name("select"));
		    new Select(driver.findElement(By.name("select"))).selectByVisibleText("Today");
			Thread.sleep(500);
			driver.findElement(By.name("yt1")).click();
			    while(true)
			    {		    	
			    	if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
			    	{
			    	    System.out.println("Segmentation 创建成功!");
			    	     break;
			    	}
			    }
		    
		//PostReadAmount+PostReadPostName    
		    driver.findElement(By.cssSelector("a.btn > button")).click();
		    while(true)
			 {
				if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
					break;
			 }
		    driver.findElement(By.id("dynamicRules_name")).clear();
		    driver.findElement(By.id("dynamicRules_name")).sendKeys("PostReadAmount+PostReadPostName");
		    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Post");
		    methods.Loading(driver, By.cssSelector("select.newCondition"));
		    new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("Views");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"))).selectByVisibleText("Amount");
		    methods.Loading(driver, By.cssSelector("select.operator"));
		    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("greater than");
		    methods.Loading(driver, By.cssSelector("input.values.amount"));
		    driver.findElement(By.cssSelector("input.values.amount")).clear();
		    driver.findElement(By.cssSelector("input.values.amount")).sendKeys("100");
		    
		    methods.Loading(driver, By.cssSelector("span.add_a_rule"));
		    driver.findElement(By.cssSelector("span.add_a_rule")).click();
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Post");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[1]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[1]"))).selectByVisibleText("Views");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("Post Name");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"))).selectByIndex(1);
		    methods.Loading(driver, By.cssSelector("select.values"));
		    new Select(driver.findElement(By.cssSelector("select.values"))).selectByVisibleText("标题title'1“测试” selenium");
		    
			Thread.sleep(500);
			driver.findElement(By.name("yt1")).click();
			    while(true)
			    {		    	
			    	if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
			    	{
			    	    System.out.println("Segmentation 创建成功!");
			    	     break;
			    	}
			    }
		    
		    
		//AB+Message
		    driver.findElement(By.cssSelector("a.btn > button")).click();
		    while(true)
			 {
				if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
					break;
			 }
		    driver.findElement(By.id("dynamicRules_name")).clear();
		    driver.findElement(By.id("dynamicRules_name")).sendKeys("AB+Message");
		    methods.Loading(driver, By.cssSelector("select.rule_types"));
		    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("AB Testing");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select"))).selectByVisibleText("lastid");
		    methods.Loading(driver, By.cssSelector("input.values.list"));
		    driver.findElement(By.cssSelector("input.values.list")).clear();
		    driver.findElement(By.cssSelector("input.values.list")).sendKeys("1,3,9");
		    methods.Loading(driver, By.cssSelector("span.add_a_rule"));
		    driver.findElement(By.cssSelector("span.add_a_rule")).click();
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Message");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("Full Matching");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input"));
		    driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).clear();
		    driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).sendKeys("test1");
		    
		    driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[4]/div[1]")).click();
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[1]/select[2]"))).selectByVisibleText("Follower");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[1]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[1]"))).selectByVisibleText("Profile");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[2]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
			methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[3]"));
			new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[3]"))).selectByVisibleText("contains");						
			driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/input")).sendKeys("H.'");
			
			Thread.sleep(500);
			driver.findElement(By.name("yt1")).click();
			    while(true)
			    {		    	
			    	if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
			    	{
			    	    System.out.println("Segmentation 创建成功!");
			    	     break;
			    	}
			    }
		    
		    //FollowerSource+FollowDate+ActionAmount
		    driver.findElement(By.cssSelector("a.btn > button")).click();
		    while(true)
			 {
				if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
					break;
			 }
		    driver.findElement(By.id("dynamicRules_name")).clear();
		    driver.findElement(By.id("dynamicRules_name")).sendKeys("FollowerSource+FollowDate+ActionAmount");
		    new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
		    methods.Loading(driver, By.cssSelector("select.newCondition"));
		    new Select(driver.findElement(By.cssSelector("select.newCondition"))).selectByVisibleText("Source Name");
		    methods.Loading(driver, By.cssSelector("select.operator"));
		    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("equals");
		    methods.Loading(driver, By.cssSelector("select.values"));
		    new Select(driver.findElement(By.cssSelector("select.values"))).selectByVisibleText("search");
		    driver.findElement(By.cssSelector("span.add_a_rule")).click();
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Follower");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"))).selectByVisibleText("Follows");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("Date");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"))).selectByVisibleText("within");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[4]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[4]"))).selectByVisibleText("Today");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/span"));

		    driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/span")).click();
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[1]/select[2]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[1]/select[2]"))).selectByVisibleText("Actions");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select"))).selectByVisibleText("Amount");
		    methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[2]"));
		    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[2]"))).selectByVisibleText("greater than");
		    methods.Loading(driver, By.cssSelector("input.values.amount"));
		    driver.findElement(By.cssSelector("input.values.amount")).clear();
		    driver.findElement(By.cssSelector("input.values.amount")).sendKeys("1000");

			Thread.sleep(500);
			driver.findElement(By.name("yt1")).click();
			    while(true)
			    {		    	
			    	if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
			    	{
			    	    System.out.println("Segmentation 创建成功!");
			    	     break;
			    	}
			    }
		   
			    //AB(1,2,3,4)
			    driver.findElement(By.cssSelector("a.btn > button")).click();
			    while(true)
				 {
					if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
						break;
				 }
			    driver.findElement(By.id("dynamicRules_name")).clear();
			    driver.findElement(By.id("dynamicRules_name")).sendKeys("AB(1,2,3,4)");
			    String lastid="1,2,3,4";
				new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("AB Testing");
				methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
				new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("lastid");
				driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys(lastid);

			    Thread.sleep(500);
				driver.findElement(By.name("yt1")).click();
				    while(true)
				    {		    	
				    	if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
				    	{
				    	    System.out.println("Segmentation 创建成功!");
				    	     break;
				    	}
				    }
				    
				 //   AB(5,6,7,8)
				    driver.findElement(By.cssSelector("a.btn > button")).click();
				    while(true)
					 {
						if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
							break;
					 }
				    driver.findElement(By.id("dynamicRules_name")).clear();
				    driver.findElement(By.id("dynamicRules_name")).sendKeys("AB(5,6,7,8)");
				    lastid="5,6,7,8";
					new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("AB Testing");
					methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"));
					new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("lastid");
					driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/input")).sendKeys(lastid);

				    Thread.sleep(500);
					driver.findElement(By.name("yt1")).click();
					while(true)
					  {		    	
					    if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
					    	{
					    	   System.out.println("Segmentation 创建成功!");
					    	    break;
					    	}
					  }
				//	HC2+PersonalMenu	
					driver.findElement(By.cssSelector("a.btn > button")).click();
					    while(true)
						 {
							if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
								break;
						 }
					    driver.findElement(By.id("dynamicRules_name")).clear();
					    driver.findElement(By.id("dynamicRules_name")).sendKeys("HC2+PersonalMenu");

						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Follower");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[1]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[1]"))).selectByVisibleText("Profile");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"))).selectByVisibleText("contains");						
						driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/input")).sendKeys("'");
						
						driver.findElement(By.cssSelector("span.add_a_rule")).click();
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"));						 
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Message");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"));
					    new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("Full Matching");
			            driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).clear();
						driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).sendKeys("HCTestMenu");
					    Thread.sleep(500);
						driver.findElement(By.name("yt1")).click();
						while(true)
						  {		    	
						    if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
						    	{
						    	   System.out.println("Segmentation 创建成功!");
						    	    break;
						    	}
						  }
				//GY2+PersonalMenu
						
						driver.findElement(By.cssSelector("a.btn > button")).click();
					    while(true)
						 {
							if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
								break;
						 }
					    driver.findElement(By.id("dynamicRules_name")).clear();
					    driver.findElement(By.id("dynamicRules_name")).sendKeys("GY2+PersonalMenu");

						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Follower");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[1]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[1]"))).selectByVisibleText("Profile");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"))).selectByVisibleText("contains");						
						driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/input")).sendKeys("rainbow");

					    Thread.sleep(500);
						driver.findElement(By.name("yt1")).click();
						while(true)
						  {		    	
						    if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
						    	{
						    	   System.out.println("Segmentation 创建成功!");
						    	    break;
						    	}
						  }  
						
			//Just+rainbow
						driver.findElement(By.cssSelector("a.btn > button")).click();
					    while(true)
						 {
							if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
								break;
						 }
					    driver.findElement(By.id("dynamicRules_name")).clear();
					    driver.findElement(By.id("dynamicRules_name")).sendKeys("Just+rainbow");

						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Follower");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[1]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[1]"))).selectByVisibleText("Profile");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"))).selectByVisibleText("contains");						
						driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/input")).sendKeys("gy");
						
						driver.findElement(By.cssSelector("span.add_a_rule")).click();
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Follower");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"))).selectByVisibleText("Profile");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"))).selectByVisibleText("contains");						
						driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).sendKeys("rainbow");
					    Thread.sleep(500);
						driver.findElement(By.name("yt1")).click();
						while(true)
						  {		    	
						    if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
						    	{
						    	   System.out.println("Segmentation 创建成功!");
						    	    break;
						    	}
						  }
			//No Testers
						driver.findElement(By.cssSelector("a.btn > button")).click();
					    while(true)
						 {
							if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
								break;
						 }
					    driver.findElement(By.id("dynamicRules_name")).clear();
					    driver.findElement(By.id("dynamicRules_name")).sendKeys("No Testers");

						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[1]/select"))).selectByVisibleText("Follower");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[1]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[1]"))).selectByVisibleText("Profile");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/select[3]"))).selectByVisibleText("contains");						
						driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[1]/div[2]/div/input")).sendKeys("T_T");
						
						driver.findElement(By.cssSelector("span.add_a_rule")).click();
						new Select(driver.findElement(By.className("addOr"))).selectByValue("2");
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[1]/select[2]"))).selectByVisibleText("Follower");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select"))).selectByVisibleText("Profile");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/select[3]"))).selectByVisibleText("contains");						
						driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[2]/div/input")).sendKeys("80");
						
						driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[2]/div[4]/div[1]")).click();
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[1]/select[1]"))).selectByValue("2");;
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[1]/select[2]"))).selectByVisibleText("Follower");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[1]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[1]"))).selectByVisibleText("Profile");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[2]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[2]"))).selectByVisibleText("Wechat Nickname");
						methods.Loading(driver, By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[3]"));
						new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/select[3]"))).selectByVisibleText("contains");						
						driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div[3]/div[2]/div/input")).sendKeys("Minglong");
						
					    Thread.sleep(500);
						driver.findElement(By.name("yt1")).click();
						while(true)
						  {		    	
						    if(methods.isElementPresent(driver, By.id("dynamic-rules-grid")))
						    	{
						    	   System.out.println("Segmentation 创建成功!");
						    	    break;
						    	}
						  }
						
						
	}

}
