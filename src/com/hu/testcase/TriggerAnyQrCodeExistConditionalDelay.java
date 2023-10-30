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

public class TriggerAnyQrCodeExistConditionalDelay {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(TriggerAnyQrCodeExistConditionalDelay.class);

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
		methods methods=new methods();
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
			 logger.error("Open trigger successfully!");
		}
				
		    driver.findElement(By.id("Triggers_category")).clear();
		    driver.findElement(By.id("Triggers_category")).sendKeys("Any QrCode Trigger");
		    driver.findElement(By.id("Triggers_title")).clear();
		    String TriggerTitle="AnyQrExCon";
		    
		    driver.findElement(By.id("Triggers_title")).sendKeys(TriggerTitle);
		    new Select(driver.findElement(By.id("Triggers_status"))).selectByVisibleText("Active");
		    new Select(driver.findElement(By.id("Triggers_label_event_type"))).selectByVisibleText("Scan QR Code");
		    driver.findElement(By.id("any_qrcode_scan")).click();
		    driver.findElement(By.id("Triggers_qrcode_type_1")).click();
		    new Select(driver.findElement(By.id("segmentation"))).selectByVisibleText("ActionToday+LBS(SH,JS)");
		    new Select(driver.findElement(By.id("Triggers_action"))).selectByVisibleText("Send a Image");
			String parentPath = getClass().getResource("../../").getFile().toString();
			String parentPath1 = parentPath + "/material/33.JPG";
			File f1 = new File(parentPath1);
			driver.findElement(By.xpath(".//*[@id='actions']/div[1]/div[1]/div[6]/div/input")).sendKeys(f1.toString());
			Thread.sleep(3000);
			while(true){
				Thread.sleep(500);
				if(driver.findElement(By.xpath(".//*[@id='actions']/div[1]/div[1]/div[6]/div/img")).getAttribute("src").contains("/upload/images/"))
					break;
				System.out.println(this.getClass().getSimpleName()+" Image 上传中");
			}
/*		    driver.findElement(By.id("delay_radio_1")).click();
		    new Select(driver.findElement(By.id("delay_schedule_seconds"))).selectByVisibleText("1 Second");
		    Thread.sleep(1000);*/
		    
		    //actions
		    driver.findElement(By.id("add_action")).click();
		    new Select(driver.findElement(By.name("Triggers[extra_action][2]"))).selectByVisibleText("Send a Message");
		    driver.findElement(By.xpath("(//textarea[@id='action_message'])[2]")).clear();
		    driver.findElement(By.xpath("(//textarea[@id='action_message'])[2]")).sendKeys("{{nickname}} 已经关注的用户\n通过 Scan Any QrCode 触发"+TriggerTitle+".\nAnd 你属于ActionToday+LBS(SH,JS)分组\n间隔一秒之前有一张人物图片\n-----延时1s"+timeStr);
		    driver.findElement(By.id("delay_radio_1")).click();
		    new Select(driver.findElement(By.cssSelector("span.delay_schedule > #delay_schedule_seconds"))).selectByVisibleText("1 Second");
		    
		    driver.findElement(By.id("add_action")).click();
		    new Select(driver.findElement(By.name("Triggers[extra_action][3]"))).selectByVisibleText("Send a Message");
		    driver.findElement(By.xpath("(//textarea[@id='action_message'])[3]")).clear();
		    driver.findElement(By.xpath("(//textarea[@id='action_message'])[3]")).sendKeys("{{nickname}} 已经关注的用户\n通过  Scan Any QrCode 触发"+TriggerTitle+".\nAnd 你属于ActionToday+LBS(SH,JS)分组\n-----延时3s"+timeStr);
		    new Select(driver.findElement(By.xpath("(//select[@id='delay_schedule_seconds'])[3]"))).selectByVisibleText("3 Seconds");

		    driver.findElement(By.id("tags")).click();
		    driver.findElement(By.cssSelector("li.tagInput")).click();
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("Any QrCode Trigger");
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		    driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("AnyQrExMulConTri"+timeStr);
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
			 }
		    System.out.println("Create "+TriggerTitle+" Successfully!");
	}

}
