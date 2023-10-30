package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TriggerClose {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(TriggerClose.class);
	 
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}
	@Test
	public void test() throws InterruptedException,Exception {
		 String timeStr=methods.timeDate();
		 methods.navigation(driver, "Triggers", By.linkText("Create a Trigger"));
		String[] TriggerArr={"AnyActCon","AnyActOnce","AnyAct-ConOnce","AnyMenuCon","AnyMenuOnce",
				"AnyQrExCon","AnyQrExNew","AnyQrNewCon","SpeMenuCon",
				"SpeMenuOnce","SpecQrExOnce","SpecQrExNewCon","SpecQrNew"};
		for(int i=0;i<TriggerArr.length;i++){
			driver.findElement(By.name("Triggers[title]")).clear();
			driver.findElement(By.name("Triggers[title]")).sendKeys(TriggerArr[i]);
			new Select(driver.findElement(By.name("Triggers[status]"))).selectByValue("1");
			driver.findElement(By.name("Triggers[title]")).sendKeys(Keys.ENTER);
			Thread.sleep(500);			
			while(true){
				if(driver.findElement(By.id("triggers-grid")).getAttribute("class").equals("grid-view"))
					break;
				Thread.sleep(500);
			}
			 while(true){
				 Thread.sleep(500);				 
				 if(methods.isElementPresent(driver, By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]"))){
					 Thread.sleep(500);
					 if(driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")).getText().equals(TriggerArr[i]))
					 {
					 String summary=driver.findElement(By.className("summary")).getText();
						String[] results=methods.Getnum(summary);
						int num = Integer.parseInt(results[2]);
						if(num>1){
							for(;num>1;num--)
							{
							driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr["+num+"]/td[10]/img")).click();
							Thread.sleep(500);
							while(true){
								 Thread.sleep(500);
								 String option= (String)((JavascriptExecutor)driver).executeScript("return document.getElementsByTagName(\"select\")[1].value;");
							     System.out.println(option);
								 if(!option.equals("1"))
									 break;
							 }
							driver.findElement(By.name("Triggers[title]")).clear();
							driver.findElement(By.name("Triggers[title]")).sendKeys(TriggerArr[i]);
							new Select(driver.findElement(By.name("Triggers[status]"))).selectByValue("1");
							driver.findElement(By.name("Triggers[title]")).sendKeys(Keys.ENTER);
							while(true){
								if(driver.findElement(By.id("triggers-grid")).getAttribute("class").equals("grid-view"))
									break;
								Thread.sleep(500);
							}
							while(true){
								 Thread.sleep(500);
								 if(methods.isElementPresent(driver, By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")))
								 if(driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")).getText().equals(TriggerArr[i]))
								 	if(!methods.isElementPresent(driver, By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr["+num+"]/td[3]")))
									 break;			
								 Thread.sleep(1000);
								 driver.findElement(By.name("Triggers[title]")).sendKeys(Keys.ENTER);
									System.out.println("Trigger inactive中。。。。");
							 }
							Thread.sleep(500);
					    	}							
						}
					else
					 break;					 
				 }
				 } else if(driver.findElement(By.className("mobile_table")).getText().contains("No results found."))
					 break;
				 System.out.println("Trigger 筛选中.....");
			 }
			 Thread.sleep(500);
		}			
	}

}
