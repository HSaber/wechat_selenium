package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SegmentationCFDTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SegmentationCFDTest.class);

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
		}
		//custom data field --Name
	    driver.findElement(By.id("dynamicRules_name")).clear();
	    driver.findElement(By.id("dynamicRules_name")).sendKeys("CustomFieldName");
	    driver.findElement(By.id("dynamicRules_note")).clear();
	    driver.findElement(By.id("dynamicRules_note")).sendKeys("HaveValue");
		new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Follower");
		new Select(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/div/select"))).selectByVisibleText("Profile");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[2]"))).selectByVisibleText("Custom Field");
	    new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[4]"))).selectByVisibleText("Name");
	    new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("Have Value");
	    driver.findElement(By.linkText("Preview")).click();
	
	    
	    //由于custom data去掉了empty 暂时不跑这个脚本
	    System.out.println("-----------------------Segmentation custom profile -----------------------------------");
	    while(true)
		 {
			if(methods.isElementPresent(driver,By.className("summary")))
				break;
		 }
		String result1=driver.findElement(By.className("summary")).getText();
		String[] result1Arr=new String[1];
		result1Arr=methods.Getnum(result1);
		System.out.println("Follower+Profile+Custom Field+Name+Have Value = "+result1Arr[2]);
/*		new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("Empty");
		driver.findElement(By.linkText("Preview")).click();*/
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.className("summary")))
				break;
		 }
		String result2=driver.findElement(By.className("summary")).getText();
		String[] result2Arr=new String[1];
		result2Arr=methods.Getnum(result2);
		System.out.println("Follower+Profile+Custom Field+Name+Empty = "+result2Arr[2]);
		int sum=Integer.parseInt(result1Arr[2])+Integer.parseInt(result2Arr[2]);
		System.out.println("Have Value+Empty = " +sum); 
		try{
			driver.get(methods.baseUrl+"Manager/wechatCustomer/admin/category/followers");	
			while(true){
				if(methods.isElementPresent(driver,By.className("follower_img")))
					break;
			}
			System.out.println("Open follower list page successfully!");
			}
		catch (Exception e){
			System.out.println("fail to open follower list page!");
			}
		 String followers=driver.findElement(By.className("summary")).getText();
		 String[] followersArr=new String[1];
		 followersArr=methods.Getnum(followers);
		 int followersnum=Integer.parseInt(followersArr[2]);
		 System.out.println("Followerlist count = " +followersnum);
		 AssertJUnit.assertEquals(sum,followersnum);
		 System.out.println("custom data name value and empty is right!");
		 
		 
		 //Menu title
		  driver.get(methods.baseUrl + "/Manager/dynamicRules/admin/category/dynamic_rules");
		  while(true){
				if(methods.isElementPresent(driver,By.className("btn")))
					break;
			}
		  driver.findElement(By.className("btn")).click();
		  while(true)
			 {
				if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
					break;
			 }
		  new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Menu");
		  new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[2]"))).selectByVisibleText("Menu Title");
		  new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("equals");
		  new Select(driver.findElement(By.cssSelector("select.values"))).selectByVisibleText("Formevent"+timeStr); 
		  driver.findElement(By.linkText("Preview")).click();
		  while(true)
			 {
				if(methods.isElementPresent(driver,By.className("summary")))
					break;
			 }
		  result1=driver.findElement(By.className("summary")).getText();
		  result1Arr=methods.Getnum(result1);
		  System.out.println("Menu+MenuTitle+equals+Formevent"+timeStr+" = "+result1Arr[2]);
		  new Select(driver.findElement(By.cssSelector("select.operator"))).selectByVisibleText("does not equal");
		  new Select(driver.findElement(By.cssSelector("select.values"))).selectByVisibleText("Formevent"+timeStr);
		  driver.findElement(By.linkText("Preview")).click();
		  while(true)
			 {
				if(methods.isElementPresent(driver,By.className("summary")))
					break;
			 }
		  result2=driver.findElement(By.className("summary")).getText();
		  result2Arr=methods.Getnum(result2);
		  System.out.println("Menu+MenuTitle+do not equal+Formevent"+methods.timeDate()+" = "+result2Arr[2]);
		  sum=Integer.parseInt(result1Arr[2])+Integer.parseInt(result2Arr[2]);
			System.out.println("Have Value+Empty = " +sum); 
			try{
				driver.get(methods.baseUrl+"Manager/wechatCustomer/admin/category/followers");	
				while(true){
					if(methods.isElementPresent(driver,By.className("follower_img")))
						break;
				}
				System.out.println("Open follower list page successfully!");
				}
			catch (Exception e){
				System.out.println("fail to open follower list page!");
				}
			 followers=driver.findElement(By.className("summary")).getText();
			 followersArr=methods.Getnum(followers);
			 followersnum=Integer.parseInt(followersArr[2]);
			 System.out.println("Followerlist count = " +followersnum);
			 AssertJUnit.assertEquals(sum,followersnum);
			 System.out.println("custom data name value and empty is right!");
			
			 
		//custom data html5  
		driver.get(methods.baseUrl + "/Manager/dynamicRules/admin/category/dynamic_rules");
		 while(true){
				if(methods.isElementPresent(driver,By.className("btn")))
					break;
			}
		driver.findElement(By.className("btn")).click();
		while(true)
		 {
			if(methods.isElementPresent(driver,By.id("dynamicRules_name")))
				break;
		 }
		new Select(driver.findElement(By.cssSelector("select.rule_types"))).selectByVisibleText("Custom Data");
		new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[2]"))).selectByVisibleText("Html5_"+timeStr);
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[4]")))
				break;
		 }
		new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[4]"))).selectByVisibleText("Action Name");
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[5]")))
				break;
		 }
		new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[5]"))).selectByVisibleText(methods.CData[0]);
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[6]")))
				break;
		 }
		new Select(driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/div[2]/div/div/div[2]/div/select[6]"))).selectByVisibleText("No Properties");
		 while(true)
		 {
			if(methods.isElementPresent(driver,By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/select[10]/div/option[2]")))
				break;
		 }
		String option=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/div[2]/div[1]/div/div[2]/select[10]/div/option[2]")).getText();
		new Select(driver.findElement(By.cssSelector("select.values"))).selectByVisibleText(option);
	    driver.findElement(By.linkText("Preview")).click();
	    while(true)
		 {
			if(methods.isElementPresent(driver,By.className("summary")))
				break;
		 }
		  result1=driver.findElement(By.className("summary")).getText();
		  result1Arr=methods.Getnum(result1);
		  System.out.println("CustomData+ActionName = "+result1Arr[2]);	    
	//	assertTrue(driver.findElement(By.tagName("body")).getText().contains("H.'"));	 
			 
		  

		 
		 }
	    
}
