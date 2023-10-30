package com.hu.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import static org.testng.AssertJUnit.*;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Html5CreateTest {
	 WebDriver driver;
	 private static Logger logger = Logger.getLogger(Html5CreateTest.class);

	@BeforeMethod
	public   void setUp() throws Exception {
		driver= new ChromeDriver();
		methods.account_login(driver);
	}

	@AfterMethod
	public  void tearDown() throws Exception {
			driver.close();
	}
	
	@Test(groups="Html5")
	public void test() throws InterruptedException,Exception {		
		methods.navigation(driver, "HTML5 Pages",By.linkText("Create a Page"));
        
				try{
				  driver.findElement(By.linkText("Create a Page")).click();
        	    while(true){
    				if(methods.isElementPresent(driver,By.id("HtmlFivePages_internal_name")))
    					break;
    			}
				System.out.println("Open Create html5 page successfully!");
				}
				catch(Exception e){
					System.out.println("Fail to open create html5 page!");
					logger.error("Fail to open create html5 page!");
				}
				
				String titleD=methods.timeDate();
				driver.findElement(By.id("HtmlFivePages_internal_name")).sendKeys("Html5_"+titleD);
				
				//tags
				StyleMethods.AddTag(driver, "Html5_"+titleD);
				
				driver.findElement(By.id("HtmlFivePages_title")).sendKeys("Pagetitle_"+titleD);

				//pic_upload
				((JavascriptExecutor)driver).executeScript("$(\"#pic_upload\").attr(\"style\",'display:block')");

				String parentPath = getClass().getResource("../../").getFile().toString();

				String parentPath1 = parentPath + "/material/27.JPG";
				String parentPath2 = parentPath + "/material/29.JPG";
				String parentPath3 = parentPath + "/material/11.JPG";
				String parentPath4 = parentPath + "/material/31.JPG";
				String parentPath5 = parentPath + "/material/33.JPG";
				

				File f1 = new File(parentPath1);
				File f2 = new File(parentPath2);
				File f3 = new File(parentPath3);
				File f4 = new File(parentPath4);
				File f5 = new File(parentPath5);
				
				driver.findElement(By.name("files[]")).sendKeys(f1.toString());
				Thread.sleep(1000);

				while(true){
					if(driver.findElement(By.xpath(".//*[@id='right_bottom_block']/div[2]/div[1]/img")).getAttribute("src").contains("upload"))
						break;			
					System.out.println(this.getClass().getSimpleName()+" 图片上传中1");
				}
				
				
				driver.findElement(By.name("files[]")).sendKeys(f2.toString());
				Thread.sleep(1000);
				while(true){
					if(methods.isElementPresent(driver,By.xpath(".//*[@id='right_bottom_block']/div[2]/div[2]/img"))&&driver.findElement(By.xpath(".//*[@id='right_bottom_block']/div[2]/div[2]/img")).getAttribute("src").contains("upload"))
						break;		
					System.out.println(this.getClass().getSimpleName()+" 图片上传中2");
				}
				
				
				driver.findElement(By.name("files[]")).sendKeys(f3.toString());
				Thread.sleep(1000);
				while(true){
					if(methods.isElementPresent(driver,By.xpath(".//*[@id='right_bottom_block']/div[2]/div[3]/img"))&&driver.findElement(By.xpath(".//*[@id='right_bottom_block']/div[2]/div[3]/img")).getAttribute("src").contains("upload"))
						break;		
					System.out.println(this.getClass().getSimpleName()+" 图片上传中3");
				}
				
				
				driver.findElement(By.name("files[]")).sendKeys(f4.toString());
				Thread.sleep(1000);
				while(true){
					if(methods.isElementPresent(driver,By.xpath(".//*[@id='right_bottom_block']/div[2]/div[4]/img"))&&driver.findElement(By.xpath(".//*[@id='right_bottom_block']/div[2]/div[4]/img")).getAttribute("src").contains("upload"))
						break;				
					System.out.println(this.getClass().getSimpleName()+" 图片上传中4");
				}
				
				
				driver.findElement(By.name("files[]")).sendKeys(f5.toString());
				Thread.sleep(1000);
				while(true){
					if(methods.isElementPresent(driver,By.xpath(".//*[@id='right_bottom_block']/div[2]/div[5]/img"))&&driver.findElement(By.xpath(".//*[@id='right_bottom_block']/div[2]/div[5]/img")).getAttribute("src").contains("upload"))
						break;		
					System.out.println(this.getClass().getSimpleName()+" 图片上传中5");
				}
			
				
				//first way-
				driver.findElement(By.id("HtmlFivePages_last_page_switch_0")).click();
			
				//save----
				driver.findElement(By.name("yt0")).click();
				 while(true){
	    				if(methods.isElementPresent(driver,By.id("html_five_page_url")))
	    					break;
	    			}
				driver.switchTo().frame(0);
				
				 while(true){
	    				if(methods.isElementPresent(driver,By.id("iSlider-wrapper"))&&driver.findElement(By.xpath(".//*[@id='iSlider-wrapper']/ul/li[1]/div")).getAttribute("style").contains("/upload/images/"))
	    					break;
	    			}
				driver.switchTo().defaultContent();
				assertNotNull(driver.findElement(By.id("html_five_page_url")).getText());
				methods.UrlLink[1]=driver.findElement(By.id("html_five_page_url")).getText();
				
				System.out.println("Create Html5_"+titleD+" successfully!");
	}

}
