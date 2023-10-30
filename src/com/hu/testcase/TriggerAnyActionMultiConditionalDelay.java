package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.interactive.testcase.HttpClient;

public class TriggerAnyActionMultiConditionalDelay {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(TriggerAnyActionMultiConditionalDelay.class);
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
		methods.navigation(driver, "Triggers", By.linkText("Create a Trigger"));
		try{
		  driver.findElement(By.linkText("Create a Trigger")).click();
		  while(true){
				 if(methods.isElementPresent(driver, By.id("Triggers_category")))
					 break;
			 }
		  AssertJUnit.assertEquals("Create a Trigger", driver.findElement(By.className("page_title")).getText());
		  System.out.println("Open create trigger successfully!");
		}catch(Exception e){
			 System.out.println("Fail to open create trigger!");
			 logger.error("Fail to open create trigger!");
		}
				
		    driver.findElement(By.id("Triggers_category")).clear();
		    driver.findElement(By.id("Triggers_category")).sendKeys("Any Action Trigger");
		    driver.findElement(By.id("Triggers_title")).clear();
		    String TriggerTitle="AnyActCon";

		    driver.findElement(By.id("Triggers_title")).sendKeys(TriggerTitle);
		    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
		    new Select(driver.findElement(By.id("Triggers_label_event_type"))).selectByVisibleText("Any of Following Actions");

		    new Select(driver.findElement(By.id("segmentation"))).selectByVisibleText("ActionToday+LBS(SH,JS)");
		    		    
		    //actions  Send a Post
		    new Select(driver.findElement(By.id("Triggers_action"))).selectByVisibleText("Send a Post");
		    while(true){
				 if(methods.isElementPresent(driver, By.id("action_post")))
					 break;
			 }
		    new Select(driver.findElement(By.id("action_post"))).selectByIndex(1);

/*		    driver.findElement(By.id("delay_radio_1")).click();
		    new Select(driver.findElement(By.id("delay_schedule_seconds"))).selectByVisibleText("1 Second");
		    Thread.sleep(1000);*/
		    
		    driver.findElement(By.id("add_action")).click();
		    new Select(driver.findElement(By.name("Triggers[extra_action][2]"))).selectByVisibleText("Send a Message");
		    driver.findElement(By.xpath("(//textarea[@id='action_message'])[2]")).clear();
		    driver.findElement(By.xpath("(//textarea[@id='action_message'])[2]")).sendKeys("{{nickname}} 任意操作.\n属于ActionToday+LBS(SH,JS)这个分组-----延时3s\n--------三秒前有一篇post 近期的第二篇\n--------- 1s后下面是接了一张图片\n"+TriggerTitle+timeStr);
		    new Select(driver.findElement(By.cssSelector("span.delay_schedule > #delay_schedule_seconds"))).selectByVisibleText("3 Seconds");
		    
		    driver.findElement(By.id("add_action")).click();
		    new Select(driver.findElement(By.name("Triggers[extra_action][3]"))).selectByVisibleText("Send a Image");
			String parentPath = getClass().getResource("../../").getFile().toString();
			String parentPath1 = parentPath + "/material/32.JPG";
			File f1 = new File(parentPath1);
			System.out.println();
			driver.findElement(By.xpath(".//*[@id='actions']/div[3]/div[1]/div[6]/div/input")).sendKeys(f1.toString());
			while(true){
				Thread.sleep(500);
				if(driver.findElement(By.xpath(".//*[@id='actions']/div[3]/div[1]/div[6]/div/img")).getAttribute("src").contains("/upload/images/"))
					break;
				System.out.println(this.getClass().getSimpleName()+" Image 上传中");
			}
			
		    new Select(driver.findElement(By.xpath("(//select[@id='delay_schedule_seconds'])[3]"))).selectByVisibleText("1 Second");
		    if(new Select(driver.findElement(By.id("Triggers_is_once"))).getFirstSelectedOption().getText().equals("Yes"))
		    	System.out.println("Any Aciton Trigger 触发状态自动切换正常。");
		    else
		    	logger.error("Any aciton trigger 状态没有自动切换到Yes.");
		    new Select(driver.findElement(By.id("Triggers_is_once"))).selectByVisibleText("No");

		    driver.findElement(By.id("tags")).click();
		    driver.findElement(By.cssSelector("li.tagInput")).click();
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("Any Action Trigger");
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		    
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("AnyActConTri"+timeStr);
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);

		    driver.findElement(By.name("yt0")).click(); 
		    while(true){
				 if(methods.isElementPresent(driver, By.name("Triggers[title]")))
					 break;
			 }
		    driver.findElement(By.name("Triggers[title]")).sendKeys(TriggerTitle);
		    driver.findElement(By.name("Triggers[title]")).sendKeys(Keys.ENTER);
			while(true){
				Thread.sleep(500);
				if(driver.findElement(By.id("triggers-grid")).getAttribute("class").equals("grid-view"))
					break;
			}
		    while(true){
				 if(methods.isElementPresent(driver, By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")))
					 if(driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")).getText().equals(TriggerTitle))
					 break;
				 Thread.sleep(500);
			 }
		    System.out.println("Create "+TriggerTitle+" Successfully!");
		 	}

}
