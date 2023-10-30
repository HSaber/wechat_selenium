package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MenuPerCleanOldVersion {

	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(MenuPerCleanOldVersion.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}
	
	@Test(groups="MenuClean")//dependsOnGroups="BackUpSegmentation",
	public void test() throws InterruptedException,Exception {
		methods.navigation(driver, "Menus", By.className("btn"));
		
		int lastMenu;
		
		String menuNum=driver.findElement(By.className("summary")).getText();
		String[] NumArr=methods.Getnum(menuNum);
		int MenuSum=Integer.parseInt(NumArr[2]);
		System.out.println(NumArr[0]+"  "+NumArr[1]+"  "+NumArr[2]);
	    int lastMenu1=Integer.parseInt(NumArr[1]);
	    
		if(MenuSum>10)
		{
			Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").getElementsByTagName(\"li\").length;");
			driver.findElement(By.xpath(".//*[@id='yw0']/li["+str+"]/a")).click();
			Long pageLeng=str-4;
			 System.out.println(str+" "+pageLeng);
			while(true){
				Thread.sleep(500);
				String classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-3)+"].className;");
				if(classStr.contains("selected"))
						break;
				}	
			
	   for(int j=1;j<=pageLeng;j++){		
		   String menuNum1=driver.findElement(By.className("summary")).getText();
			String[] NumArr1=methods.Getnum(menuNum1);
			lastMenu1=Integer.parseInt(NumArr1[1]);
			if(lastMenu1>10){
				lastMenu1=lastMenu1%10;
				if(lastMenu1==0)
					lastMenu1=10;
			}
		for(int i=lastMenu1;i>0;i--){	
			System.out.println(lastMenu1);
			String menuStr=driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+i+"]/td[3]")).getText();
			System.out.println(menuStr);
			if(menuStr.contains("rainbow+rainbowgy") || menuStr.contains("H.+Candy") || menuStr.contains("Default Copy")){
				//此行报错 报不存在
				
				driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+i+"]/td[8]/a[3]")).click();
				while(true){
					if(methods.isAlertPresent(driver))
					{
						methods.closeAlertAndGetItsText(driver);
					break;
					}
				}
	
		    Thread.sleep(3000);
			}		
		}
		menuNum1=driver.findElement(By.className("summary")).getText();
		NumArr1=methods.Getnum(menuNum1);
		int menuSum1=Integer.parseInt(NumArr1[2]);
		if(menuSum1>10)
		{
		   String classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-(j+3))+"].className;");
		   System.out.println("str"+(str-(j+3)));
		   System.out.println(classStr);
			if(classStr.contains("selected")||classStr.contains("previous"))
				return;
			else{
		driver.findElement(By.xpath(".//*[@id='yw0']/li[2]/a")).click();
		while(true){
			Thread.sleep(500);
			classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-(j+3))+"].className;");
			if(classStr.contains("selected"))
					break;
			}
		str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").getElementsByTagName(\"li\").length;");
		pageLeng=str-4;
		 System.out.println(str+"  "+pageLeng);
		}
		}
		else
		{
			break;
		}
			
			}
	}else{
		for(int i=MenuSum;i>0;i--){
			String menuStr=driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+i+"]/td[3]")).getText();
			if(menuStr.contains("rainbow+rainbowgy") || menuStr.contains("H.+Candy") || menuStr.contains("Default Copy")){
				driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+i+"]/td[8]/a[3]")).click();
				while(true){
					if(methods.isAlertPresent(driver))
					{
						methods.closeAlertAndGetItsText(driver);
					break;
					}
				}
				while(true){
					Thread.sleep(500);
					if(!methods.isElementPresent(driver, By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+i+"]/td[3]")))
							break;
				}
			}
			
		}
		
	}
	
	}

}
