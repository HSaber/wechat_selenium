package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MenuPersonalPublish {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(MenuPersonalPublish.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}
	@Test(dependsOnGroups="DefaultMenuEdit",groups="MenuPublish")
	public void test() throws InterruptedException,Exception {
	
		int lastMenu;
		methods.navigation(driver, "Menus", By.className("btn"));

	
		String menuNum=driver.findElement(By.className("summary")).getText();
		String[] NumArr=methods.Getnum(menuNum);
		int MenuSum=Integer.parseInt(NumArr[2]);
	
		if(MenuSum>10&&MenuSum%10>2)
		{
			Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").getElementsByTagName(\"li\").length;");
			driver.findElement(By.xpath(".//*[@id='yw0']/li["+str+"]/a")).click();
			while(true){
				String classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-3)+"].className;");
				if(classStr.contains("selected"))
						break;
				}	
		lastMenu=MenuSum%10;
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+lastMenu+"]/td[2]/input")).click();
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+(lastMenu-1)+"]/td[2]/input")).click();
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+(lastMenu-2)+"]/td[2]/input")).click();
		
	}else if(MenuSum>10&&MenuSum%10<3)
	{
		Long str=(Long)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").getElementsByTagName(\"li\").length;");
		driver.findElement(By.xpath(".//*[@id='yw0']/li["+str+"]/a")).click();
		while(true){
			String classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-3)+"].className;");
			if(classStr.contains("selected"))
					break;
			}
		lastMenu=MenuSum%10;
		if(lastMenu==1){
			driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+lastMenu+"]/td[2]/input")).click();
			driver.findElement(By.xpath(".//*[@id='yw0']/li["+(str-3)+"]/a")).click();
			while(true){
				String classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-4)+"].className;");
				if(classStr.contains("selected"))
						break;
				}	
			driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr[10]/td[2]/input")).click();
			driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr[9]/td[2]/input")).click();
		}else{
			driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+(lastMenu-1)+"]/td[2]/input")).click();
			driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+lastMenu+"]/td[2]/input")).click();
			driver.findElement(By.xpath(".//*[@id='yw0']/li["+(str-3)+"]/a")).click();
			while(true){
				String classStr=(String)((JavascriptExecutor)driver).executeScript("return document.getElementById(\"yw0\").children["+(str-4)+"].className;");
				if(classStr.contains("selected"))
						break;
				}	
			driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr[10]/td[2]/input")).click();
		}
	}
	else{
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+MenuSum+"]/td[2]/input")).click();
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+(MenuSum-1)+"]/td[2]/input")).click();
		driver.findElement(By.xpath(".//*[@id='personal-menu-grid']/div[1]/table/tbody/tr["+(MenuSum-2)+"]/td[2]/input")).click();
	}
	
		driver.findElement(By.linkText("Publish Menu")).click();
		while(true){
			if(methods.isElementPresent(driver,By.xpath("html/body/div[4]/div[2]/div/button[1]")))
				break;
		}
		driver.findElement(By.xpath("html/body/div[4]/div[2]/div/button[1]")).click();
		while(true){
			if(methods.isElementPresent(driver,By.id("menu-publish-queue-grid")))
				break;
		}
		while(true)
		{
			if(driver.findElement(By.xpath(".//*[@id='publish-menu-record-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText().contains("rainbow+rainbowgy,H.+Candy,Default Copy")&&
				driver.findElement(By.xpath(".//*[@id='publish-menu-record-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText().contains("Sent"))
				break;
			driver.navigate().refresh();
			while(true){
				if(methods.isElementPresent(driver,By.id("menu-publish-queue-grid")))
					break;
			}
		}
		System.out.println("Menu publish 成功!");
	}
}
