package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SurveyJoinedDataTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(SurveyJoinedDataTest.class);
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups={"UpdateSurvey","MenuClick"},groups="SurveyJoin")
	public void test() throws InterruptedException,Exception {
		String vartime = methods.timeDate();
		String SurveyTitle="个人信息调查问卷"+vartime;
	
		methods.navigation(driver, "Surveys", By.cssSelector("a.btn > button"));
		
		driver.findElement(By.name("Survey[survey_name]")).sendKeys(SurveyTitle);
		driver.findElement(By.name("Survey[survey_name]")).sendKeys(Keys.ENTER);
		while(true){
			if(methods.isElementPresent(driver, By.xpath(".//*[@id='survey-grid']/div[1]/table/tbody/tr/td[1]")))
				if(driver.findElement(By.xpath(".//*[@id='survey-grid']/div[1]/table/tbody/tr/td[1]")).getText().equals(SurveyTitle))
				break;
		}
		String parti=driver.findElement(By.xpath(".//*[@id='survey-grid']/div[1]/table/tbody/tr/td[3]")).getText();
		int partinum=Integer.parseInt(parti);
		AssertJUnit.assertTrue((partinum>=1));
		System.out.println("Survey List 页面  个人信息调查问卷"+SurveyTitle+"显示的参加总人数 = "+parti);
		try{
			driver.findElement(By.xpath(".//*[@id='survey-grid']/div[1]/table/tbody/tr[1]/td[3]/a")).click();
			while(true){
				if(methods.isElementPresent(driver, By.className("data-info")))
					break;
			}
			System.out.println("Open survey result successfully!");
			}
		catch(Exception e){
			System.out.println("fail to open survey result page!");
			logger.error("fail to open survey result page!");
			}
		String taken=driver.findElement(By.className("data-info")).getText();
		String[] takenarr=methods.Getnum(taken);
		taken=takenarr[0];
		System.out.println("Survey 结果 页面  个人信息调查问卷"+SurveyTitle+"显示的参加总人数 = "+taken);		
		AssertJUnit.assertEquals(parti,taken);
		driver.findElement(By.tagName("body")).getText().contains("H.'");
		
		String[] msgarr={"huhuan"+vartime,"1830210"+vartime,"江苏"+vartime,"Achang"+vartime,"女"+vartime,vartime,vartime+"00","2","3","1","7"+vartime,"3","晚上"+vartime};
		for(int i=0;i<msgarr.length;i++)
			AssertJUnit.assertTrue(driver.findElement(By.tagName("body")).getText().contains(msgarr[i]));
			System.out.println("Survey 参加的数据正常显示");
				
	}

}
