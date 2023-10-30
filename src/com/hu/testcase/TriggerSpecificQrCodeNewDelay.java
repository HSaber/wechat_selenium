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

public class TriggerSpecificQrCodeNewDelay{
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(TriggerSpecificQrCodeNewDelay.class);

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
		    driver.findElement(By.id("Triggers_category")).sendKeys("Specific QrCode Trigger");
		    driver.findElement(By.id("Triggers_title")).clear();
		    String TriggerTitle="SpecQrNew";
		    driver.findElement(By.id("Triggers_title")).sendKeys(TriggerTitle);
		    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
		    new Select(driver.findElement(By.id("Triggers_label_event_type"))).selectByVisibleText("Scan QR Code");
		    driver.findElement(By.id("one_qrcode_scan")).click();
		    new Select(driver.findElement(By.id("event_qrcode_follow"))).selectByVisibleText("QrcodePost");
		    driver.findElement(By.id("Triggers_qrcode_type_0")).click();
		    
            //actions
		    new Select(driver.findElement(By.id("Triggers_action"))).selectByVisibleText("Send a Post");
		    new Select(driver.findElement(By.id("action_post"))).selectByIndex(3);
/*		    driver.findElement(By.id("delay_radio_1")).click();
		    new Select(driver.findElement(By.id("delay_schedule_seconds"))).selectByVisibleText("1 Second");
		    Thread.sleep(1000); */  
		    
		    driver.findElement(By.id("add_action")).click();
		    new Select(driver.findElement(By.name("Triggers[extra_action][2]"))).selectByVisibleText("Send a Image");
			String parentPath = getClass().getResource("../../").getFile().toString();
			String parentPath1 = parentPath + "/material/33.JPG";
			File f1 = new File(parentPath1);
			driver.findElement(By.xpath(".//*[@id='actions']/div[2]/div[1]/div[6]/div/input")).sendKeys(f1.toString());
			while(true){
				Thread.sleep(500);
				if(driver.findElement(By.xpath(".//*[@id='actions']/div[2]/div[1]/div[6]/div/img")).getAttribute("src").contains("/upload/images/"))
					break;
				System.out.println(this.getClass().getSimpleName()+" Image 上传中");
			}
		    new Select(driver.findElement(By.cssSelector("span.delay_schedule > #delay_schedule_seconds"))).selectByVisibleText("1 Second");		    
	
		    driver.findElement(By.id("add_action")).click();
		    new Select(driver.findElement(By.name("Triggers[extra_action][3]"))).selectByVisibleText("Send a Message");
		    driver.findElement(By.xpath("(//textarea[@id='action_message'])[3]")).clear();
		    driver.findElement(By.xpath("(//textarea[@id='action_message'])[3]")).sendKeys("{{nickname}} 新关注的用户\n 通过Scan Specific(QrcodePost) QrCode 触发"+TriggerTitle+".\n<a href=\"https://www.zhihu.com/question/31970604\">《死镜——B/B》</a>\n三秒前有一篇近期3的post\n-----延时3s"+timeStr);
		    new Select(driver.findElement(By.xpath("(//select[@id='delay_schedule_seconds'])[3]"))).selectByVisibleText("3 Seconds");

		    driver.findElement(By.id("tags")).click();
		    driver.findElement(By.cssSelector("li.tagInput")).click();
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("Specific QrCode Trigger");
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);

		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("SpecQrNewTri"+timeStr);
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);

		    driver.findElement(By.name("yt0")).click(); 
		    while(true){
				 if(methods.isElementPresent(driver, By.name("Triggers[title]")))
					 break;
			 }
		    driver.findElement(By.name("Triggers[title]")).sendKeys(TriggerTitle);
		    driver.findElement(By.name("Triggers[title]")).sendKeys(Keys.ENTER);
			while(true){
				if(driver.findElement(By.id("triggers-grid")).getAttribute("class").equals("grid-view"))
					break;
				Thread.sleep(500);
			}
		    while(true){
				 Thread.sleep(500);
				 if(methods.isElementPresent(driver, By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")))
					 if(driver.findElement(By.xpath(".//*[@id='triggers-grid']/div[1]/table/tbody/tr/td[3]")).getText().equals(TriggerTitle))
					 break;

			 }System.out.println("Create "+TriggerTitle+" Successfully!");
	}

}
