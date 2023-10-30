package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SurveyCreateTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SurveyCreateTest.class);
    private StringBuffer verificationErrors = new StringBuffer();
    boolean flag=true;
    
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="CustomField",groups="Survey")
	public void test() throws InterruptedException,Exception {
		String SurveyTitle = methods.timeDate();
		methods.navigation(driver, "Surveys", By.cssSelector("a.btn > button"));
		
		try{
			driver.findElement(By.cssSelector("a.btn > button")).click();
			while(true){
				if(methods.isElementPresent(driver,By.id("Survey_survey_name")))
				break;
			 }
		    System.out.println("Open create survey page successfully!");
		}catch(Exception e){
			System.out.println("Fail to open create survey page!");
			logger.error("Fail to open create survey page!");
		}

    
		 AssertJUnit.assertEquals("WeChat Web Application - Create Survey",driver.getTitle());
    
   // driver.findElement(By.tagName("body")).getText().contains("Create a Survey");
    
    //assertEquals("Create a Survey",)
    driver.findElement(By.id("Survey_survey_name")).clear();
    driver.findElement(By.id("Survey_survey_name")).sendKeys("个人信息调查问卷"+SurveyTitle);
    driver.findElement(By.id("Survey_select_prompt_message")).clear();
    driver.findElement(By.id("Survey_select_prompt_message")).sendKeys("Pls enter number...");
    driver.findElement(By.id("Survey_participated_message")).clear();
    driver.findElement(By.id("Survey_participated_message")).sendKeys("您已经参加过，Thankyou");
    driver.findElement(By.name("yt0")).click();
	while(true){
		if(methods.isElementPresent(driver,By.id("qtype")))
		break;
	 }
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Text");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("您的姓名？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("Name");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Text");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("您的手机号？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("Telephone");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Text");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("您的家乡是？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("Hometown");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Text");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("您的公司名称是什么？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("Company");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Select");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("您的性别？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("Gender");
    driver.findElement(By.name("o1")).clear();
    driver.findElement(By.name("o1")).sendKeys("男");
    driver.findElement(By.name("o2")).clear();
    driver.findElement(By.name("o2")).sendKeys("女");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Text");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("你平均一个月的花费大约是多少元？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("消费");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Text");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("你期望一个月的月消费是多少元?");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("Salary");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Select");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("你一般每个月花费主要在哪个方面？");
    driver.findElement(By.name("o1")).clear();
    driver.findElement(By.name("o1")).sendKeys("伙食");
    driver.findElement(By.name("o2")).clear();
    driver.findElement(By.name("o2")).sendKeys("购买衣物");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o3")).clear();
    driver.findElement(By.name("o3")).sendKeys("日用品");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o4")).clear();
    driver.findElement(By.name("o4")).sendKeys("学习");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o5")).clear();
    driver.findElement(By.name("o5")).sendKeys("其他");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Select");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("你日常一般在哪里用餐？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("用餐地点");
    driver.findElement(By.name("o1")).clear();
    driver.findElement(By.name("o1")).sendKeys("食堂");
    driver.findElement(By.name("o2")).clear();
    driver.findElement(By.name("o2")).sendKeys("饭馆");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o3")).clear();
    driver.findElement(By.name("o3")).sendKeys("路边小摊");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o4")).clear();
    driver.findElement(By.name("o4")).sendKeys("外卖");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o5")).clear();
    driver.findElement(By.name("o5")).sendKeys("其他");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
 
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Select");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("你是否有网购经历？");
    driver.findElement(By.name("o1")).clear();
    driver.findElement(By.name("o1")).sendKeys("是");
    driver.findElement(By.name("o2")).clear();
    driver.findElement(By.name("o2")).sendKeys("否");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    

    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Select");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("你网购的频率是？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("Shopping Rate");
    driver.findElement(By.name("o1")).clear();
    driver.findElement(By.name("o1")).sendKeys("每天网购");
    driver.findElement(By.name("o2")).clear();
    driver.findElement(By.name("o2")).sendKeys("每周4-5次");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o3")).clear();
    driver.findElement(By.name("o3")).sendKeys("每周2-3次");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o4")).clear();
    driver.findElement(By.name("o4")).sendKeys("每周1次");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o5")).clear();
    driver.findElement(By.name("o5")).sendKeys("每月2-3次");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o6")).clear();
    driver.findElement(By.name("o6")).sendKeys("每月1次");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o7")).clear();
    driver.findElement(By.name("o7")).sendKeys("更少次数");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);

    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Select");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("今天的天气怎么样？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText(SurveyTitle+" 的天气");
    driver.findElement(By.name("o1")).clear();
    driver.findElement(By.name("o1")).sendKeys("晴天");
    driver.findElement(By.name("o2")).clear();
    driver.findElement(By.name("o2")).sendKeys("阴天");
    driver.findElement(By.id("addOption")).click();
    driver.findElement(By.name("o3")).clear();
    driver.findElement(By.name("o3")).sendKeys("雨天");
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    new Select(driver.findElement(By.id("qtype"))).selectByVisibleText("Text");
    driver.findElement(By.id("qname")).clear();
    driver.findElement(By.id("qname")).sendKeys("平时您午餐时间大概在几点？");
    driver.findElement(By.id("toEavAttribute")).click();
    new Select(driver.findElement(By.id("eavAttributeSelector"))).selectByVisibleText("\"lunchtime\""+SurveyTitle);
    driver.findElement(By.id("create")).click();
    Thread.sleep(1000);
    
    driver.findElement(By.xpath(".//*[@id='yw0']/tbody/tr[4]/td/a")).click();
    Thread.sleep(2000);
    new Select(driver.findElement(By.id("Survey_status"))).selectByVisibleText("Active");
    driver.findElement(By.name("yt0")).click();
    Thread.sleep(2000);
    driver.get(methods.baseUrl+"Manager/survey/admin/category/surveys");
    Thread.sleep(2000);
    AssertJUnit.assertEquals(flag,driver.findElement(By.tagName("body")).getText().contains("个人信息调查问卷"+SurveyTitle));
    System.out.println("Create 个人信息调查问卷"+SurveyTitle+" successfully!");
    driver.findElement(By.xpath(".//*[@id='survey-grid']/div[1]/table/thead/tr[2]/td[1]/input")).sendKeys("个人信息调查问卷"+SurveyTitle);
    driver.findElement(By.xpath(".//*[@id='survey-grid']/div[1]/table/thead/tr[2]/td[1]/input")).sendKeys(Keys.ENTER);
     
	AssertJUnit.assertEquals("Active",driver.findElement(By.xpath(".//*[@id='survey-grid']/div[1]/table/tbody/tr[1]/td[2]")).getText().toString());
	System.out.println("个人信息调查问卷"+SurveyTitle+" status is active!");
	}
	

}
