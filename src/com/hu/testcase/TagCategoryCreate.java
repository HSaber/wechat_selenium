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

public class TagCategoryCreate {
	  WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  public String CategoryTitle;
	  private static Logger logger = Logger.getLogger(TagCategoryCreate.class);

		@BeforeClass
		public  void setUpBeforeClass() throws Exception {
			driver= new ChromeDriver();
			methods.account_login(driver);
		}

		@AfterClass
		public  void tearDownAfterClass() throws Exception {
				driver.close();
		}

	@Test(groups="TagCategory")
	public void test() throws InterruptedException,Exception {				
		CategoryTitle=methods.timeDate1();
		Actions builder=new Actions(driver);
		builder.moveToElement(driver.findElement(By.linkText("Setup"))).click().perform();
		builder.moveToElement(driver.findElement(By.linkText("Tags"))).click().perform();
		try{
			driver.findElement(By.linkText("Category")).click();
			while(true)
			 {
				if(methods.isElementPresent(driver,By.linkText("Create a Category")))
					break;
			 }
	        System.out.println("Open tag category successfully!");
		}
		catch(Exception e){
			System.out.println("fail to open tag category page!");
			 logger.error("fail to open tag category page!");
		}
	
	
		//custom data field 数组
	    String[] CategoryArr={"Trigger"+CategoryTitle,"Menu"+CategoryTitle,"HCMenu"+CategoryTitle,"GYMenu"+CategoryTitle,"PerDefMenu"+CategoryTitle,"Qrcode"+CategoryTitle};
	    
	    for(int i=0;i<CategoryArr.length;i++){
	    	
	    	driver.findElement(By.linkText("Create a Category")).click();
	    	while(true)
			 {
				if(methods.isElementPresent(driver,By.id("TagCategory_category_name")))
					break;
			 }
	        driver.findElement(By.id("TagCategory_category_name")).clear();
		    driver.findElement(By.id("TagCategory_category_name")).sendKeys(CategoryArr[i]);
		    driver.findElement(By.name("yt0")).click();
		    Thread.sleep(1000);
		    if(driver.findElement(By.tagName("body")).getText().contains("category name has exists"))
		    {
		    	System.out.println(CategoryArr[i]+" already exist");
		    	driver.findElement(By.name("yt1")).click();
		    }
		    else if(driver.findElement(By.tagName("body")).getText().contains(CategoryArr[i]))
		    	System.out.println("Create "+CategoryArr[i]+" successfully!");
		    else
		    	System.out.println("fail to create "+CategoryArr[i]+"!");	    	  
		    while(true)
			 {
				if(methods.isElementPresent(driver,By.cssSelector("a.btn > button")))
					break;
			 }
	    }

	      
	}

}
