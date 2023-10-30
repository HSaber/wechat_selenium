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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.hu.testcase.LoginTest;
public class TagClassification {

	 WebDriver driver;
	public boolean flag=true;
	private static Logger logger = Logger.getLogger(TagClassification.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups={"DefaultMenuEdit"})
	public void test() throws InterruptedException,Exception {
		String CategoryTitle=methods.timeDate1();
		String titleStr=methods.timeDate();
		methods.navigation(driver, "Tags", By.linkText("Create a Tag"));
		 
			String TagArr[]={
					"QrcodeText_","QrcodeImage_",
					"Dynamic Menu","Image","Message","客服Menu","MultiPost Menu","SinglePost Menu","PostUrl Menu","WeChat_","Survey","FormEvent_",
					"HCDynaMenu","HC客服","HCHistory","HC官方网站","HCImag",
					"GY2 PerMenu",
					"PDynamic Menu","PImage","PMessage","P客服Menu","PMultiPost Menu","PSinglePost Menu","PPostUrl Menu","PWeChat_","PSurvey","PFormEvent_",};
			String[] CategoryArr={"Trigger"+CategoryTitle,"Menu"+CategoryTitle,"PerMenu"+CategoryTitle,"Qrcode"+CategoryTitle};
			for(int i=0;i<TagArr.length;i++){
				TagArr[i]=TagArr[i]+titleStr;
				driver.findElement(By.name("Tags[name]")).clear();
				driver.findElement(By.name("Tags[name]")).sendKeys(TagArr[i]);
				driver.findElement(By.name("Tags[name]")).sendKeys(Keys.ENTER);
				while(true){
					Thread.sleep(1000);
					if(methods.isElementPresent(driver, By.id("tags-grid")))
					{
					if(driver.findElement(By.id("tags-grid")).getAttribute("class").equals("grid-view"))
						break;
					System.out.println("搜索icon旋转中......");
					}					
				}
				while(true){
					Thread.sleep(500);
					if(driver.findElement(By.id("tags-grid")).getAttribute("class").equals("grid-view")){
						if(methods.isElementPresent(driver, By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[4]")))
						{
						 if(driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText().equals(TagArr[i])){
							driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[7]/a[1]")).click();
							while(true){
								Thread.sleep(500);
								if(methods.isElementPresent(driver, By.id("Tags_category_id")))
									break;								
							}
                           if(i<2)
								new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("Qrcode"+CategoryTitle);
							else if(i>=2&&i<12)
								new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("Menu"+CategoryTitle);
							else if(i>=12&&i<17)
								new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("HCMenu"+CategoryTitle);
							else if(i>=17&&i<18)
								new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("GYMenu"+CategoryTitle);
							else 
								new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("PerDefMenu"+CategoryTitle);
							
							driver.findElement(By.name("yt0")).click();
							 while(true){
								 Thread.sleep(500);
								 System.out.println("Tag category 修改ing ...");
								 if(methods.isElementPresent(driver, By.linkText("Create a Tag")))
									 break;
							 }
							 break;
						}else if(driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr[1]/td[4]")).getText().contains(TagArr[i])){
							String filterResult=driver.findElement(By.className("summary")).getText();
							String[] num=methods.Getnum(filterResult);
							int result=Integer.parseInt(num[2]);
							for(int j=1;j<=result;j++){
								if(driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr["+j+"]/td[4]")).getText().equals(TagArr[i]))
								{
									driver.findElement(By.xpath(".//*[@id='tags-grid']/div[1]/table/tbody/tr["+j+"]/td[7]/a[1]")).click();
									while(true){
										Thread.sleep(500);
										if(methods.isElementPresent(driver, By.id("Tags_category_id")))
											break;								
									}
									if(i<13)
										new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("Trigger"+CategoryTitle);
									else if(i>=13&&i<15)
										new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("Qrcode"+CategoryTitle);
									else if(i>=15&&i<25)
										new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("Menu"+CategoryTitle);
									else if(i>=25&&i<30)
										new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("HCMenu"+CategoryTitle);
									else if(i>=30&&i<31)
										new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("GYMenu"+CategoryTitle);
									else 
										new Select(driver.findElement(By.id("Tags_category_id"))).selectByVisibleText("PerDefMenu"+CategoryTitle);
									
									driver.findElement(By.name("yt0")).click();
									 while(true){
										 Thread.sleep(500);
										 System.out.println("Tag category 修改ing ...");
										 if(methods.isElementPresent(driver, By.linkText("Create a Tag")))
											 break;
									 }
								}
							}
							break;
						}
					}
						else if(driver.findElement(By.id("tags-grid")).getText().contains("No results found."))
						{
							System.out.println("没有搜索到相关的tag信息。");
							break;
						}
					
				}
				
					System.out.println("Tag筛选中。。。");
					Thread.sleep(500);
				}
				
			}		
		
	}
}
