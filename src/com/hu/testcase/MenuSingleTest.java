package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class MenuSingleTest {
	private static Logger logger = Logger.getLogger(MenuSingleTest.class);
	 WebDriver driver;
	public boolean flag=true;

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
		methods.navigation(driver, "Menu", By.id("maincd1"));
		String menuDate=methods.timeDate2();
		String title=methods.timeDate();

	    driver.findElement(By.id("maincd3")).click();
	    driver.findElement(By.id("cdmcinpo")).click();
	    driver.findElement(By.id("cdmcinpo")).clear();
	    driver.findElement(By.id("cdmcinpo")).sendKeys("MainMenu"+menuDate+methods.getStringRandom(2));
	    new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Show Sub Menu");
	    driver.findElement(By.id("xsejcdopt")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).click();
	    driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).clear();
	    driver.findElement(By.cssSelector("span.res_ejcd_1 > input[type=\"text\"]")).sendKeys("Formevent"+title);
	    driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).click();
	    driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).clear();
	    driver.findElement(By.cssSelector("span.res_ejcd_2 > input[type=\"text\"]")).sendKeys("Survey"+title);
	    driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).click();
	    driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).clear();
	    driver.findElement(By.cssSelector("span.res_ejcd_3 > input[type=\"text\"]")).sendKeys("Html5"+title);
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).click();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).clear();
		driver.findElement(By.cssSelector("span.res_ejcd_4 > input[type=\"text\"]")).sendKeys("URL" + title);
		
		driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td[1]/div/div/div[1]/div[4]")).click();
		new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
		driver.findElement(By.name("event_url")).click();
		driver.findElement(By.name("event_url")).clear();
		driver.findElement(By.name("event_url")).sendKeys("http://mp.weixin.qq.com/s?__biz=MzA5NDExNDAxMg==&mid=402503006&idx=1&sn=96f7cb154d807671b3dfac20b145d406");
		Thread.sleep(1000);
		
	    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td/div/div/div[3]/div[3]")).click();
	    new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
	    driver.findElement(By.name("event_url")).click();
	    driver.findElement(By.name("event_url")).clear();
	    driver.findElement(By.name("event_url")).sendKeys(methods.UrlLink[1]);
/*		// tag add
		driver.findElement(By.id("tags")).click();
		driver.findElement(By.cssSelector("li.tagInput")).click();
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("MenuHtml5"+title);
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
		*/
	    
	    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td/div/div/div[3]/div[2]")).click();
	    new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send a Survey");
	    while(true){
			if(methods.isElementPresent(driver,By.name("rad_tw")))
			break;
		 }
	    //"个人信息调查问卷"+SurveyTitle
	    new Select(driver.findElement(By.name("rad_tw"))).selectByVisibleText("个人信息调查问卷"+title+" (Active)");
		// tag add
/*		driver.findElement(By.id("tags")).click();
		driver.findElement(By.cssSelector("li.tagInput")).click();
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("MenuSurvey"+title);
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);*/
		

	    driver.findElement(By.xpath("//div[@id='page']/div[2]/div[3]/div/div/table/tbody/tr/td/div/div/div[3]/div")).click();
	    new Select(driver.findElement(By.id("answertype"))).selectByVisibleText("Send to a Link (URL)");
	    driver.findElement(By.name("event_url")).click();
	    driver.findElement(By.name("event_url")).clear();
	    driver.findElement(By.name("event_url")).sendKeys(methods.UrlLink[0]);
/*		// tag add
		driver.findElement(By.id("tags")).click();
		driver.findElement(By.cssSelector("li.tagInput")).click();
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).clear();
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys("Formevent"+title);
		driver.findElement(By.cssSelector("input.tagInputField.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);*/
		
	    
	    driver.findElement(By.id("sync")).click();
		while(true){			
		if(!methods.isElementPresent(driver,By.className("qp_lodediv")))
		break;
		 }
	    AssertJUnit.assertEquals(flag,driver.findElement(By.tagName("body")).getText().contains("MainMenu"+menuDate));
	    System.out.println("Create menu successfully!");
	}

}
