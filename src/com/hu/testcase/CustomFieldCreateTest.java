package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CustomFieldCreateTest {
	   WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  public String FieldTitle,CategoryTitle;
	  private static Logger logger = Logger.getLogger(CustomFieldCreateTest.class);

		@BeforeClass
		public  void setUpBeforeClass() throws Exception {
			driver= new ChromeDriver();
			methods.account_login(driver);
		}

		@AfterClass
		public  void tearDownAfterClass() throws Exception {
				driver.close();
		}

	@Test(groups="CustomField", dependsOnGroups="CustomFieldCategory")
	public void test() throws InterruptedException,Exception {		
		FieldTitle=methods.timeDate();
		CategoryTitle=methods.timeDate1();
		methods.navigation(driver, "Custom Field", By.linkText("Create a field"));	
	
		//custom data field 数组
	    String[] CustomDFArr={"Name","Telephone","Company","Gender","Hometown","Shopping Rate",
	    		              "消费","Salary","用餐地点","\"lunchtime\""+FieldTitle,FieldTitle+" 的天气","Names","Phone Number",
	    		              "FriendsAccount"+FieldTitle,"Comment"+FieldTitle};
	    
	    for(int i=0;i<CustomDFArr.length;i++){
	    	
	    	driver.findElement(By.linkText("Create a field")).click();
	    	while(true)
			 {
				if(methods.isElementPresent(driver,By.id("FollowerEavAttribute_label")))
					break;
			 }
	    	if(i<=10)
	    		new Select(driver.findElement(By.id("FollowerEavAttribute_category_id"))).selectByVisibleText("Survey"+CategoryTitle);
	    	else
	    		new Select(driver.findElement(By.id("FollowerEavAttribute_category_id"))).selectByVisibleText("FormEvent"+CategoryTitle);
	    	
	        driver.findElement(By.id("FollowerEavAttribute_label")).clear();
		    driver.findElement(By.id("FollowerEavAttribute_label")).sendKeys(CustomDFArr[i]);
		    driver.findElement(By.name("yt0")).click();
		    Thread.sleep(1000);
		    if(driver.findElement(By.tagName("body")).getText().contains("Same Field already exist, please check it first"))
		    {
		    	System.out.println(CustomDFArr[i]+" field already exist");
		    	driver.findElement(By.name("yt1")).click();
		    }
		    else if(driver.findElement(By.tagName("body")).getText().contains(CustomDFArr[i]))
		    	System.out.println("Create "+CustomDFArr[i]+" field successfully!");
		    else
		    {
		    	System.out.println("fail to create "+CustomDFArr[i]+" field");	
		       logger.error("fail to create "+CustomDFArr[i]+" field");
		    }
		    while(true)
			 {
				if(methods.isElementPresent(driver,By.cssSelector("a.btn > button")))
					break;
			 }
	    }

	      
	}

}
