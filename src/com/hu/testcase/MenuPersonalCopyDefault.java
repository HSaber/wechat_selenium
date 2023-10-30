package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MenuPersonalCopyDefault {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(MenuPersonalCopyDefault.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="MenuSetting",groups="DefaultMenuCopy")
	public void test() throws InterruptedException,Exception {
		methods.navigation(driver, "Menus", By.className("btn"));
		
		String menuNum=driver.findElement(By.className("summary")).getText();
		String[] NumArr=methods.Getnum(menuNum);
		int MenuSum=Integer.parseInt(NumArr[2]);
		int MenuSumNew=MenuSum+1;
		//下一行 元素不存在的说
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr[1]/td[8]/a[2]")).click();
		while(true){
			if(methods.isElementPresent(driver,By.className("summary")))
				break;
		}
		//获取ul下li的个数
	
		if(MenuSumNew>10)
		{
		Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").getElementsByTagName(\"li\").length;");
		driver.findElement(By.xpath(".//*[@id='yw0']/li["+str+"]/a")).click();

		while(true){
		String classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-3)+"].className;");
		if(classStr.contains("selected"))
				break;
		}	
		int lastMenu=MenuSumNew%10;
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+lastMenu+"]/td[3]")).getText().contains("Default Copy");
		System.out.println("Copy default menu successfully!");
		}
		else{
		int lastMenu=MenuSumNew%10;
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+lastMenu+"]/td[3]")).getText().contains("Default Copy");
		System.out.println("Copy default menu successfully!");
		}
	}

}
