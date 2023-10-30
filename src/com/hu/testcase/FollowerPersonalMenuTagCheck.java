package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class FollowerPersonalMenuTagCheck {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(FollowerPersonalMenuTagCheck.class);
	
	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="PersonalMenuClick")
	public void test() throws InterruptedException,Exception {
		 String timeStr=methods.timeDate();
		 String[] PersonalMenuTagArr={
                 "GY2 PerMenu","HCImag","HC官网网站","HCHistory","HC客服","HCDynaMenu","PDynamic Menu","PImage",
                 "PMessage","P客服Menu","PMultiPost Menu","PSinglePost Menu","PPostUrl Menu","PWeChat_",
                 "PSurvey","PFormEvent_","PURL LINK Menu"};//personal menu
		 
		 methods.navigation(driver, "Tags",By.linkText("Create a Tag"));


		for(int i=0;i<PersonalMenuTagArr.length;i++){
			if(i==(PersonalMenuTagArr.length-1))
			PersonalMenuTagArr[i]=PersonalMenuTagArr[i];
			else
			PersonalMenuTagArr[i]=PersonalMenuTagArr[i]+timeStr;
			driver.findElement(By.name("Tags[name]")).clear();
			driver.findElement(By.name("Tags[name]")).sendKeys(PersonalMenuTagArr[i]);
			driver.findElement(By.name("Tags[name]")).sendKeys(Keys.ENTER);
			Thread.sleep(500);		
			while(true){
				Thread.sleep(500);
				if(driver.findElement(By.id("tags-grid")).getAttribute("class").equals("grid-view"))
				{
					if(methods.isElementPresent(driver, By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[3]")))
					{
					if(driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[3]")).getText().equals(PersonalMenuTagArr[i])){
						driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[4]/a")).click();
						while(true){
							Thread.sleep(500);
							if(methods.isElementPresent(driver, By.xpath(".//*[@id='page']/div[2]/div[1]/div/h1")))
								if(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[1]/div/h1")).getText().contains(PersonalMenuTagArr[i]))
									break;
						}
						if(i==0){
							System.out.println(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]")).getText());
						   AssertJUnit.assertTrue(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]")).getText().contains("rainbow"));
						   System.out.println("rainbow 通过PersonalMenu打上Tag: "+PersonalMenuTagArr[i]);
						}else if(i<=5){
					      AssertJUnit.assertTrue(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]")).getText().contains("๑Candy๑3'"));
					      System.out.println("๑Candy๑3' 通过PersonalMenu打上Tag: "+PersonalMenuTagArr[i]);
						}
						else {
							AssertJUnit.assertTrue(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]")).getText().contains("Minglong"));
							 System.out.println("Minglong 通过PersonalMenu打上Tag: "+PersonalMenuTagArr[i]);
						}
						driver.navigate().back();

						while(true){
							Thread.sleep(500);		
							 if(methods.isElementPresent(driver, By.linkText("Create a Tag")))
								 break;
						 }
						break;						
					}
				}
					else if(driver.findElement(By.id("tags-grid")).getText().contains("No results found."))
					{
						System.out.println(PersonalMenuTagArr[i]+" tag 不存在!");
				        logger.error(PersonalMenuTagArr[i]+" tag 不存在!");
						break;
					}	
			}
				System.out.println(PersonalMenuTagArr[i]+" tag筛选中。。。。");
			}

			Thread.sleep(500);		
		}
		
		
	}

}
