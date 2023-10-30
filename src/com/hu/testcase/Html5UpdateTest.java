package com.hu.testcase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Html5UpdateTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(Html5UpdateTest.class);

	@BeforeClass
	public  void setUpBeforeClass() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterClass
	public  void tearDownAfterClass() throws Exception {
			driver.close();
	}

	@Test(dependsOnGroups="Html5")
	public void test() throws InterruptedException,Exception {
		//TagCreateTest,Html5CreateTest
		String titleD=methods.timeDate();
		String htmltitle="Html5_"+titleD;		
		methods.navigation(driver, "HTML5 Pages",By.linkText("Create a Page"));
		String updateURL=driver.findElement(By.linkText(htmltitle)).getAttribute("href");
		String[] html5Id=methods.Getnum(updateURL);
		updateURL=methods.baseUrl+"Manager/htmlFivePages/update/id/"+html5Id[1];
		try{
			driver.get(updateURL);
			System.out.println("Open Update "+htmltitle+" successfully!");
		}catch(Exception e){
			System.out.println("Fail to update "+htmltitle+" !");
			logger.error("Fail to update "+htmltitle+" !");
		}
			
        driver.findElement(By.xpath(".//*[@id='right_bottom_block']/div[2]/div[1]/span")).click();
       //pic_upload
		((JavascriptExecutor)driver).executeScript("$(\"#pic_upload\").attr(\"style\",'display:block')");

		String parentPath = getClass().getResource("../../").getFile().toString();
		String parentPath5 = parentPath + "/material/32.JPG";
		File f5 = new File(parentPath5);
		driver.findElement(By.name("files[]")).sendKeys(f5.toString());
		while(true){
			System.out.println(driver.findElement(By.xpath(".//*[@id='right_bottom_block']/div[2]/div[5]/img")).getAttribute("src"));
			if(methods.isElementPresent(driver,By.xpath(".//*[@id='right_bottom_block']/div[2]/div[5]/img"))&&driver.findElement(By.xpath(".//*[@id='right_bottom_block']/div[2]/div[5]/img")).getAttribute("src").contains("upload"))
				break;		
			System.out.println(this.getClass().getSimpleName()+" 图片上传中5");
		}
		
		//second way
		driver.findElement(By.id("HtmlFivePages_last_page_switch_1")).click();
		driver.findElement(By.id("HtmlFivePages_last_page_link")).sendKeys("http://chinamagento.jingdigital.net/");

		//save----
		driver.findElement(By.name("yt0")).click();
		 while(true){
				if(methods.isElementPresent(driver,By.id("html_five_page_url")))
					break;
			}
		 
		driver.switchTo().frame(0);
		 while(true){
				if(methods.isElementPresent(driver,By.id("iSlider-wrapper"))&&driver.findElement(By.xpath(".//*[@id='iSlider-wrapper']/ul/li[2]/div")).getAttribute("style").contains("/upload/images/"))
					break;
			}
				
		 System.out.println("Update Html5_"+titleD+" successfully!");
	}

}
