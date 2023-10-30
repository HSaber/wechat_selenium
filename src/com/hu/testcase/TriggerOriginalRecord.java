package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TriggerOriginalRecord  {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(TriggerOriginalRecord.class);
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}
	@Parameters({"TriggerTitle","TriggerNum"})
	@Test
	public void test(String TriggerTitle,int TriggerNum) throws InterruptedException,Exception {
		String timeStr=methods.timeDate();
		methods.navigation(driver, "Triggers", By.linkText("Create a Trigger"));			
		driver.findElement(By.name("Triggers[title]")).clear();
		driver.findElement(By.name("Triggers[title]")).sendKeys(TriggerTitle);
		new Select(driver.findElement(By.name("Triggers[status]"))).selectByValue("1");
		driver.findElement(By.name("Triggers[title]")).sendKeys(Keys.ENTER);
		Thread.sleep(500);
		int flag=0;
		while(true){
			if(driver.findElement(By.id("triggers-grid")).getAttribute("class").equals("grid-view"))
				break;
			Thread.sleep(500);
		}
	 while(true){
		 Thread.sleep(500);
		 if(methods.isElementPresent(driver, By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")))
		 {
			 if(driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")).getText().equals(TriggerTitle))
				 flag=1;
			 break;			
		 }
		 else
			 if(methods.isElementPresent(driver, By.className("empty")))
			 {
				 System.out.println(TriggerTitle+"不存在.");
		 break;
			 }
		 
	 }
	 if(flag==1){
	 
	 methods.TriggerCountArr[TriggerNum]=Integer.parseInt(driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr[1]/td[8]")).getText());
	 		
			System.out.println(TriggerTitle+" 之前触发次数："+methods.TriggerCountArr[TriggerNum]+" .");
	 }
		
}

}
